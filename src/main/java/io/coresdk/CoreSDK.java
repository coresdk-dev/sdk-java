package io.coresdk;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.coresdk.metrics.CoreSDKMetrics;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpTimeoutException;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

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

    /**
     * Authorize a request against the control plane.
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
                String controlPlaneUrl = config.getControlPlaneUrl();

                if (controlPlaneUrl == null || controlPlaneUrl.isBlank()) {
                    if ("closed".equals(config.getFailMode())) {
                        throw new CoreSDKException(new ProblemDetail(
                            "https://coresdk.io/errors/configuration",
                            "No Control Plane Configured",
                            503));
                    }
                    log.warn("[coresdk] CORESDK_CONTROL_PLANE_URL not set — failing open");
                    Claims failOpenClaims = new Claims("unknown", config.getTenantId(), List.of(), 0L);
                    return new AuthDecision(true, failOpenClaims, "no-control-plane");
                }

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

            } catch (CoreSDKException e) {
                if (sample != null) {
                    String errType = e.getMessage() != null && e.getMessage().contains("timeout") ? "timeout" : "network";
                    CoreSDKMetrics.incrementAuthorizeError(reg, errType);
                    CoreSDKMetrics.recordAuthorize(reg, sample, "deny");
                }
                throw e;
            } catch (Exception e) {
                if (sample != null) {
                    String errType = e instanceof HttpTimeoutException ? "timeout" : "network";
                    CoreSDKMetrics.incrementAuthorizeError(reg, errType);
                    CoreSDKMetrics.recordAuthorize(reg, sample, "fail_open");
                }
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
        });
    }

    /**
     * Evaluate a named policy rule via the control plane.
     *
     * @param rule  fully-qualified OPA rule path, e.g. {@code "data.authz.allow"}
     * @param input arbitrary JSON object sent as policy input
     */
    public CompletableFuture<PolicyResult> evaluatePolicy(String rule, Map<String, Object> input) {
        return CompletableFuture.supplyAsync(() -> {
            MeterRegistry reg = this.meterRegistry;
            Timer.Sample sample = reg != null ? CoreSDKMetrics.startSample(reg) : null;
            try {
                String controlPlaneUrl = config.getControlPlaneUrl();
                if (controlPlaneUrl == null || controlPlaneUrl.isBlank()) {
                    if ("closed".equals(config.getFailMode())) {
                        throw new CoreSDKException(new ProblemDetail(
                            "https://coresdk.io/errors/configuration",
                            "No Control Plane Configured", 503));
                    }
                    if (sample != null) CoreSDKMetrics.recordPolicy(reg, sample, rule, "fail_open");
                    return new PolicyResult(true, "no-control-plane");
                }
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
            } catch (CoreSDKException e) {
                if (sample != null) {
                    CoreSDKMetrics.incrementPolicyError(reg);
                    CoreSDKMetrics.recordPolicy(reg, sample, rule, "deny");
                }
                throw e;
            } catch (Exception e) {
                if (sample != null) {
                    CoreSDKMetrics.incrementPolicyError(reg);
                    CoreSDKMetrics.recordPolicy(reg, sample, rule, "fail_open");
                }
                if ("closed".equals(config.getFailMode())) {
                    throw new CoreSDKException(new ProblemDetail(
                        "https://coresdk.io/errors/internal", "Internal Error", 500), e);
                }
                log.warn("[coresdk] evaluatePolicy fail-open: {}", e.getMessage());
                return new PolicyResult(true, "fail-open");
            }
        });
    }

    public CoreSDKConfig getConfig() { return config; }

    private static String stripTrailingSlash(String url) {
        return url.endsWith("/") ? url.substring(0, url.length() - 1) : url;
    }
}
