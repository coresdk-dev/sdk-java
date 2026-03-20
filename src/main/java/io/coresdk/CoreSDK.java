package io.coresdk;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.coresdk.metrics.CoreSDKMetrics;
import io.coresdk.proto.AuthServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpTimeoutException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * CoreSDK client. Obtain via {@link CoreSDK#fromEnv()} or Spring auto-configuration.
 */
public class CoreSDK {

    private static final Logger log = LoggerFactory.getLogger(CoreSDK.class);

    private static final HttpClient HTTP_CLIENT = HttpClient.newBuilder()
        .connectTimeout(Duration.ofSeconds(5))
        .build();

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final CoreSDKConfig config;
    private volatile MeterRegistry meterRegistry;
    private volatile ManagedChannel grpcChannel;
    private volatile AuthServiceGrpc.BlockingStub grpcStub;

    public CoreSDK(CoreSDKConfig config) {
        this.config = config;
    }

    /** Inject a Micrometer {@link MeterRegistry}. Safe to call after construction. */
    public void setMeterRegistry(MeterRegistry registry) {
        this.meterRegistry = registry;
    }

    public static CoreSDK fromEnv() {
        return new CoreSDK(CoreSDKConfig.fromEnv());
    }

    private AuthServiceGrpc.BlockingStub getGrpcStub() {
        if (grpcStub == null) {
            synchronized (this) {
                if (grpcStub == null) {
                    String target = config.getEndpoint();
                    grpcChannel = ManagedChannelBuilder.forTarget(target)
                        .usePlaintext()
                        .build();
                    grpcStub = AuthServiceGrpc.newBlockingStub(grpcChannel);
                }
            }
        }
        return grpcStub;
    }

    /**
     * Authorize a request via the sidecar gRPC service.
     * Falls back to control plane HTTP REST if controlPlaneUrl is set and gRPC fails.
     *
     * @param token    raw Bearer token (without the "Bearer " prefix)
     * @param resource resource path or name being accessed
     * @param action   action being attempted (e.g. "GET", "DELETE")
     */
    public CompletableFuture<AuthDecision> authorize(String token, String resource, String action) {
        return CompletableFuture.supplyAsync(() -> {
            MeterRegistry reg = this.meterRegistry;
            Timer.Sample sample = reg != null ? CoreSDKMetrics.startSample(reg) : null;
            try {
                return authorizeViaGrpc(token, resource, action, reg, sample);
            } catch (Exception grpcErr) {
                // If control plane URL is configured, fall back to HTTP REST
                String controlPlaneUrl = config.getControlPlaneUrl();
                if (controlPlaneUrl != null && !controlPlaneUrl.isBlank()) {
                    log.debug("[coresdk] gRPC failed, falling back to control plane REST: {}", grpcErr.getMessage());
                    try {
                        return authorizeViaHttp(token, resource, action, controlPlaneUrl, reg, sample);
                    } catch (Exception httpErr) {
                        return handleAuthError(httpErr, reg, sample);
                    }
                }
                return handleAuthError(grpcErr, reg, sample);
            }
        });
    }

    private AuthDecision authorizeViaGrpc(String token, String resource, String action,
                                           MeterRegistry reg, Timer.Sample sample) throws Exception {
        AuthServiceGrpc.BlockingStub stub = getGrpcStub();

        String requestJson = MAPPER.writeValueAsString(Map.of(
            "token", token != null ? token : "",
            "resource", resource != null ? resource : "",
            "action", action != null ? action : "",
            "tenant_id", config.getTenantId() != null ? config.getTenantId() : ""
        ));

        byte[] responseBytes = stub.validateToken(requestJson.getBytes(StandardCharsets.UTF_8));
        JsonNode json = MAPPER.readTree(responseBytes);

        boolean valid = json.path("valid").asBoolean(false);
        String subject = json.path("subject").asText("");
        long expiresAt = json.path("expiresAt").asLong(0);

        List<String> roles = new ArrayList<>();
        JsonNode rolesNode = json.get("roles");
        if (rolesNode != null && rolesNode.isArray()) {
            for (JsonNode r : rolesNode) {
                roles.add(r.asText());
            }
        }

        Claims claims = new Claims(
            subject.isEmpty() ? "unknown" : subject,
            config.getTenantId(),
            roles,
            expiresAt
        );
        if (sample != null) CoreSDKMetrics.recordAuthorize(reg, sample, valid ? "allow" : "deny");
        return new AuthDecision(valid, claims, null);
    }

    private AuthDecision authorizeViaHttp(String token, String resource, String action,
                                           String controlPlaneUrl, MeterRegistry reg,
                                           Timer.Sample sample) throws Exception {
        String base = stripTrailingSlash(controlPlaneUrl);

        String body = MAPPER.writeValueAsString(Map.of(
            "token", token != null ? token : "",
            "resource", resource != null ? resource : "",
            "action", action != null ? action : "",
            "tenant_id", config.getTenantId() != null ? config.getTenantId() : ""
        ));

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(base + "/api/v1/auth/validate"))
            .timeout(Duration.ofSeconds(5))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(body))
            .build();

        HttpResponse<String> response =
            HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() >= 400) {
            throw new CoreSDKException(new ProblemDetail(
                "https://coresdk.io/errors/control-plane-error",
                "Control Plane Error",
                response.statusCode()));
        }

        JsonNode json = MAPPER.readTree(response.body());
        boolean allowed = json.path("allowed").asBoolean(false);
        String subject = json.path("sub").asText("");
        String tenantId = json.path("tenant_id").asText("");

        Claims claims = new Claims(
            subject.isEmpty()  ? "unknown"            : subject,
            tenantId.isEmpty() ? config.getTenantId() : tenantId,
            List.of(),
            System.currentTimeMillis() / 1000 + 3600
        );
        if (sample != null) CoreSDKMetrics.recordAuthorize(reg, sample, allowed ? "allow" : "deny");
        return new AuthDecision(allowed, claims, null);
    }

    private AuthDecision handleAuthError(Exception e, MeterRegistry reg, Timer.Sample sample) {
        if (sample != null) {
            String errType = e instanceof HttpTimeoutException || e instanceof StatusRuntimeException ? "timeout" : "network";
            CoreSDKMetrics.incrementAuthorizeError(reg, errType);
            CoreSDKMetrics.recordAuthorize(reg, sample, "fail_open");
        }
        if (e instanceof CoreSDKException) throw (CoreSDKException) e;
        if ("closed".equals(config.getFailMode())) {
            throw new CoreSDKException(
                new ProblemDetail(
                    "https://coresdk.io/errors/internal",
                    "Internal Error",
                    500),
                e);
        }
        log.warn("[coresdk] authorize fail-open: {}", e.getMessage());
        Claims failOpenClaims = new Claims("unknown", config.getTenantId(), List.of(), 0L);
        return new AuthDecision(true, failOpenClaims, "fail-open");
    }

    /**
     * Evaluate a named policy rule via gRPC to the sidecar.
     * Falls back to control plane HTTP REST if controlPlaneUrl is configured.
     *
     * @param rule  fully-qualified OPA rule path, e.g. {@code "data.authz.allow"}
     * @param input arbitrary JSON object sent as policy input
     */
    public CompletableFuture<PolicyResult> evaluatePolicy(String rule, Map<String, Object> input) {
        return CompletableFuture.supplyAsync(() -> {
            MeterRegistry reg = this.meterRegistry;
            Timer.Sample sample = reg != null ? CoreSDKMetrics.startSample(reg) : null;
            try {
                return evaluatePolicyViaGrpc(rule, input, reg, sample);
            } catch (Exception grpcErr) {
                String controlPlaneUrl = config.getControlPlaneUrl();
                if (controlPlaneUrl != null && !controlPlaneUrl.isBlank()) {
                    log.debug("[coresdk] gRPC policy failed, falling back to REST: {}", grpcErr.getMessage());
                    try {
                        return evaluatePolicyViaHttp(rule, input, controlPlaneUrl, reg, sample);
                    } catch (Exception httpErr) {
                        return handlePolicyError(httpErr, rule, reg, sample);
                    }
                }
                return handlePolicyError(grpcErr, rule, reg, sample);
            }
        });
    }

    private PolicyResult evaluatePolicyViaGrpc(String rule, Map<String, Object> input,
                                                MeterRegistry reg, Timer.Sample sample) throws Exception {
        AuthServiceGrpc.BlockingStub stub = getGrpcStub();

        String inputJson = MAPPER.writeValueAsString(input != null ? input : Map.of());
        String requestJson = MAPPER.writeValueAsString(Map.of(
            "rule", rule != null ? rule : "",
            "input_json", inputJson,
            "tenant_id", config.getTenantId() != null ? config.getTenantId() : ""
        ));

        byte[] responseBytes = stub.evaluatePolicy(requestJson.getBytes(StandardCharsets.UTF_8));
        JsonNode json = MAPPER.readTree(responseBytes);
        boolean result = json.path("result").asBoolean(false);
        if (sample != null) CoreSDKMetrics.recordPolicy(reg, sample, rule, result ? "allow" : "deny");
        return new PolicyResult(result, null);
    }

    private PolicyResult evaluatePolicyViaHttp(String rule, Map<String, Object> input,
                                                String controlPlaneUrl, MeterRegistry reg,
                                                Timer.Sample sample) throws Exception {
        String base = stripTrailingSlash(controlPlaneUrl);

        String body = MAPPER.writeValueAsString(Map.of(
            "rule", rule != null ? rule : "",
            "tenant_id", config.getTenantId() != null ? config.getTenantId() : "",
            "input", input != null ? input : Map.of()
        ));

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(base + "/api/v1/policy/evaluate"))
            .timeout(Duration.ofSeconds(5))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(body))
            .build();

        HttpResponse<String> response =
            HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() >= 400) {
            throw new CoreSDKException(new ProblemDetail(
                "https://coresdk.io/errors/policy-error",
                "Policy Evaluation Error", response.statusCode()));
        }
        JsonNode json = MAPPER.readTree(response.body());
        boolean allowed = json.path("allowed").asBoolean(false);
        if (sample != null) CoreSDKMetrics.recordPolicy(reg, sample, rule, allowed ? "allow" : "deny");
        return new PolicyResult(allowed, null);
    }

    private PolicyResult handlePolicyError(Exception e, String rule, MeterRegistry reg, Timer.Sample sample) {
        if (sample != null) {
            CoreSDKMetrics.incrementPolicyError(reg);
            CoreSDKMetrics.recordPolicy(reg, sample, rule, "fail_open");
        }
        if (e instanceof CoreSDKException) throw (CoreSDKException) e;
        if ("closed".equals(config.getFailMode())) {
            throw new CoreSDKException(new ProblemDetail(
                "https://coresdk.io/errors/internal", "Internal Error", 500), e);
        }
        log.warn("[coresdk] evaluatePolicy fail-open: {}", e.getMessage());
        return new PolicyResult(true, "fail-open");
    }

    public CoreSDKConfig getConfig() { return config; }

    /** Shut down the gRPC channel gracefully. */
    public void shutdown() {
        ManagedChannel ch = this.grpcChannel;
        if (ch != null) {
            ch.shutdown();
            try {
                ch.awaitTermination(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                ch.shutdownNow();
            }
        }
    }

    private static String stripTrailingSlash(String url) {
        return url.endsWith("/") ? url.substring(0, url.length() - 1) : url;
    }
}
