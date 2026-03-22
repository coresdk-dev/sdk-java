package io.coresdk;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.coresdk.metrics.CoreSDKMetrics;
import io.coresdk.proto.AuthServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.netty.shaded.io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslContext;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
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
                    String target = config.getSidecarAddr();
                    String certPath = config.getTls() != null ? config.getTls().getCertPath() : null;
                    if (certPath != null && !certPath.isEmpty()) {
                        try {
                            SslContext sslCtx = GrpcSslContexts.forClient()
                                .keyManager(new File(certPath), new File(config.getTls().getKeyPath()))
                                .trustManager(new File(config.getTls().getCaPath()))
                                .build();
                            grpcChannel = NettyChannelBuilder.forTarget(target)
                                .useTransportSecurity()
                                .sslContext(sslCtx)
                                .build();
                        } catch (Exception e) {
                            throw new RuntimeException("Failed to configure mTLS", e);
                        }
                    } else {
                        grpcChannel = ManagedChannelBuilder.forTarget(target)
                            .usePlaintext()
                            .build();
                    }
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
        String tenantId = config.getTenantId() != null ? config.getTenantId() : "";

        // Use Authorize RPC when resource+action are provided, else ValidateToken
        boolean useAuthorize = resource != null && !resource.isEmpty()
                            && action != null && !action.isEmpty();

        java.io.ByteArrayOutputStream buf = new java.io.ByteArrayOutputStream();
        writeString(buf, 1, token);
        if (useAuthorize) {
            writeString(buf, 2, resource);
            writeString(buf, 3, action);
            writeString(buf, 4, tenantId);
            byte[] responseBytes = stub.authorize(buf.toByteArray());
            return decodeAuthorizeResponse(responseBytes, reg, sample);
        } else {
            writeString(buf, 2, tenantId);
            byte[] responseBytes = stub.validateToken(buf.toByteArray());
            return decodeValidateTokenResponse(responseBytes, reg, sample);
        }
    }

    private AuthDecision decodeAuthorizeResponse(byte[] data, MeterRegistry reg, Timer.Sample sample) {
        // Protobuf: field 1=allowed(varint), field 2=subject(string), field 3=roles(repeated string), field 4=reason(string)
        boolean allowed = false;
        String subject = "";
        List<String> roles = new ArrayList<>();
        String reason = null;
        int pos = 0;
        while (pos < data.length) {
            long[] tagResult = readVarint(data, pos);
            long tag = tagResult[0];
            pos = (int) tagResult[1];
            int fieldNum = (int) (tag >>> 3);
            int wireType = (int) (tag & 0x7);
            if (wireType == 0) { // varint
                long[] valResult = readVarint(data, pos);
                pos = (int) valResult[1];
                if (fieldNum == 1) allowed = valResult[0] != 0;
            } else if (wireType == 2) { // length-delimited
                long[] lenResult = readVarint(data, pos);
                int len = (int) lenResult[0];
                pos = (int) lenResult[1];
                String s = new String(data, pos, len, StandardCharsets.UTF_8);
                pos += len;
                if (fieldNum == 2) subject = s;
                else if (fieldNum == 3) roles.add(s);
                else if (fieldNum == 4) reason = s;
            }
        }
        Claims claims = new Claims(
            subject.isEmpty() ? "unknown" : subject,
            config.getTenantId(),
            roles,
            0L
        );
        if (sample != null) CoreSDKMetrics.recordAuthorize(reg, sample, allowed ? "allow" : "deny");
        return new AuthDecision(allowed, claims, reason);
    }

    private AuthDecision decodeValidateTokenResponse(byte[] data, MeterRegistry reg, Timer.Sample sample) {
        // Protobuf: field 1=valid(varint), field 2=subject(string), field 3=roles(repeated string), field 4=expiresAt(varint)
        boolean valid = false;
        String subject = "";
        List<String> roles = new ArrayList<>();
        long expiresAt = 0;
        int pos = 0;
        while (pos < data.length) {
            long[] tagResult = readVarint(data, pos);
            long tag = tagResult[0];
            pos = (int) tagResult[1];
            int fieldNum = (int) (tag >>> 3);
            int wireType = (int) (tag & 0x7);
            if (wireType == 0) {
                long[] valResult = readVarint(data, pos);
                pos = (int) valResult[1];
                if (fieldNum == 1) valid = valResult[0] != 0;
                else if (fieldNum == 4) expiresAt = valResult[0];
            } else if (wireType == 2) {
                long[] lenResult = readVarint(data, pos);
                int len = (int) lenResult[0];
                pos = (int) lenResult[1];
                String s = new String(data, pos, len, StandardCharsets.UTF_8);
                pos += len;
                if (fieldNum == 2) subject = s;
                else if (fieldNum == 3) roles.add(s);
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
        String tenantId = config.getTenantId() != null ? config.getTenantId() : "";

        java.io.ByteArrayOutputStream buf = new java.io.ByteArrayOutputStream();
        writeString(buf, 1, rule);
        writeString(buf, 2, inputJson);
        writeString(buf, 3, tenantId);

        byte[] responseBytes = stub.evaluatePolicy(buf.toByteArray());
        // Decode protobuf: field 1=result(varint bool)
        boolean result = false;
        int pos = 0;
        while (pos < responseBytes.length) {
            long[] tagResult = readVarint(responseBytes, pos);
            long tag = tagResult[0];
            pos = (int) tagResult[1];
            int fieldNum = (int) (tag >>> 3);
            int wireType = (int) (tag & 0x7);
            if (wireType == 0) {
                long[] valResult = readVarint(responseBytes, pos);
                pos = (int) valResult[1];
                if (fieldNum == 1) result = valResult[0] != 0;
            } else if (wireType == 2) {
                long[] lenResult = readVarint(responseBytes, pos);
                int len = (int) lenResult[0];
                pos = (int) lenResult[1];
                pos += len; // skip unknown string fields
            }
        }
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

    // ---- Rate Limit ----

    public CompletableFuture<RateLimitDecision> checkRateLimit(String key) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                AuthServiceGrpc.BlockingStub stub = getGrpcStub();
                java.io.ByteArrayOutputStream buf = new java.io.ByteArrayOutputStream();
                writeString(buf, 1, key);
                String tenantId = config.getTenantId() != null ? config.getTenantId() : "";
                writeString(buf, 2, tenantId);

                byte[] responseBytes = stub.checkRateLimit(buf.toByteArray());
                // Protobuf: field 1=allowed(varint), field 2=remaining(varint), field 3=retry_after_ms(varint)
                boolean allowed = false;
                int remaining = 0;
                int retryAfterMs = 0;
                int pos = 0;
                while (pos < responseBytes.length) {
                    long[] tagResult = readVarint(responseBytes, pos);
                    pos = (int) tagResult[1];
                    int fieldNum = (int) (tagResult[0] >>> 3);
                    int wireType = (int) (tagResult[0] & 0x7);
                    if (wireType == 0) {
                        long[] valResult = readVarint(responseBytes, pos);
                        pos = (int) valResult[1];
                        if (fieldNum == 1) allowed = valResult[0] != 0;
                        else if (fieldNum == 2) remaining = (int) valResult[0];
                        else if (fieldNum == 3) retryAfterMs = (int) valResult[0];
                    } else if (wireType == 2) {
                        long[] lenResult = readVarint(responseBytes, pos);
                        pos = (int) lenResult[1] + (int) lenResult[0];
                    }
                }
                return new RateLimitDecision(allowed, remaining, retryAfterMs);
            } catch (Exception e) {
                if ("closed".equals(config.getFailMode())) {
                    throw new CoreSDKException(new ProblemDetail(
                        "https://coresdk.io/errors/internal", "Internal Error", 500), e);
                }
                log.warn("[coresdk] checkRateLimit fail-open: {}", e.getMessage());
                return new RateLimitDecision(true, 999, 0);
            }
        });
    }

    // ---- Audit ----

    public CompletableFuture<AuditRecord> emitAuditEvent(String action, String userId, String outcome, Map<String, String> metadata) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                AuthServiceGrpc.BlockingStub stub = getGrpcStub();
                String tenantId = config.getTenantId() != null ? config.getTenantId() : "";
                String metaJson = "";
                try {
                    metaJson = metadata != null ? MAPPER.writeValueAsString(metadata) : "{}";
                } catch (Exception ignored) {
                    metaJson = "{}";
                }

                java.io.ByteArrayOutputStream buf = new java.io.ByteArrayOutputStream();
                writeString(buf, 1, action);
                writeString(buf, 2, userId);
                writeString(buf, 3, outcome);
                writeString(buf, 4, tenantId);
                writeString(buf, 5, metaJson);

                byte[] responseBytes = stub.emitAudit(buf.toByteArray());
                // Protobuf: field 1=event_id(string), field 2=sequence_id(varint), field 3=record_hash(string)
                String eventId = "";
                int sequenceId = 0;
                String recordHash = "";
                int pos = 0;
                while (pos < responseBytes.length) {
                    long[] tagResult = readVarint(responseBytes, pos);
                    pos = (int) tagResult[1];
                    int fieldNum = (int) (tagResult[0] >>> 3);
                    int wireType = (int) (tagResult[0] & 0x7);
                    if (wireType == 0) {
                        long[] valResult = readVarint(responseBytes, pos);
                        pos = (int) valResult[1];
                        if (fieldNum == 2) sequenceId = (int) valResult[0];
                    } else if (wireType == 2) {
                        long[] lenResult = readVarint(responseBytes, pos);
                        int len = (int) lenResult[0];
                        pos = (int) lenResult[1];
                        String s = new String(responseBytes, pos, len, StandardCharsets.UTF_8);
                        pos += len;
                        if (fieldNum == 1) eventId = s;
                        else if (fieldNum == 3) recordHash = s;
                    }
                }
                return new AuditRecord(eventId, sequenceId, recordHash);
            } catch (Exception e) {
                if ("closed".equals(config.getFailMode())) {
                    throw new CoreSDKException(new ProblemDetail(
                        "https://coresdk.io/errors/internal", "Internal Error", 500), e);
                }
                log.warn("[coresdk] emitAuditEvent fail-open: {}", e.getMessage());
                return new AuditRecord("", 0, "");
            }
        });
    }

    // ---- Feature Flags ----

    public CompletableFuture<FlagDecision> evaluateFlag(String flagKey, String userId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                AuthServiceGrpc.BlockingStub stub = getGrpcStub();
                String tenantId = config.getTenantId() != null ? config.getTenantId() : "";

                java.io.ByteArrayOutputStream buf = new java.io.ByteArrayOutputStream();
                writeString(buf, 1, flagKey);
                writeString(buf, 2, userId);
                writeString(buf, 3, tenantId);

                byte[] responseBytes = stub.evaluateFlag(buf.toByteArray());
                // Protobuf: field 1=enabled(varint), field 2=variant(string), field 3=reason(string)
                boolean enabled = false;
                String variant = "";
                String reason = "";
                int pos = 0;
                while (pos < responseBytes.length) {
                    long[] tagResult = readVarint(responseBytes, pos);
                    pos = (int) tagResult[1];
                    int fieldNum = (int) (tagResult[0] >>> 3);
                    int wireType = (int) (tagResult[0] & 0x7);
                    if (wireType == 0) {
                        long[] valResult = readVarint(responseBytes, pos);
                        pos = (int) valResult[1];
                        if (fieldNum == 1) enabled = valResult[0] != 0;
                    } else if (wireType == 2) {
                        long[] lenResult = readVarint(responseBytes, pos);
                        int len = (int) lenResult[0];
                        pos = (int) lenResult[1];
                        String s = new String(responseBytes, pos, len, StandardCharsets.UTF_8);
                        pos += len;
                        if (fieldNum == 2) variant = s;
                        else if (fieldNum == 3) reason = s;
                    }
                }
                return new FlagDecision(enabled, variant, reason);
            } catch (Exception e) {
                if ("closed".equals(config.getFailMode())) {
                    throw new CoreSDKException(new ProblemDetail(
                        "https://coresdk.io/errors/internal", "Internal Error", 500), e);
                }
                log.warn("[coresdk] evaluateFlag fail-open: {}", e.getMessage());
                return new FlagDecision(false, "", "fail-open");
            }
        });
    }

    // ---- License / Entitlements ----

    public CompletableFuture<LicenseInfo> checkEntitlement(String key) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                AuthServiceGrpc.BlockingStub stub = getGrpcStub();
                String tenantId = config.getTenantId() != null ? config.getTenantId() : "";

                java.io.ByteArrayOutputStream buf = new java.io.ByteArrayOutputStream();
                writeString(buf, 1, key);
                writeString(buf, 2, tenantId);

                byte[] responseBytes = stub.checkEntitlement(buf.toByteArray());
                // Protobuf: field 1=entitled(varint), field 2=numeric_value(varint), field 3=expires_at(varint), field 4=plan(string)
                boolean entitled = false;
                int numericValue = 0;
                long expiresAt = 0;
                String plan = "";
                int pos = 0;
                while (pos < responseBytes.length) {
                    long[] tagResult = readVarint(responseBytes, pos);
                    pos = (int) tagResult[1];
                    int fieldNum = (int) (tagResult[0] >>> 3);
                    int wireType = (int) (tagResult[0] & 0x7);
                    if (wireType == 0) {
                        long[] valResult = readVarint(responseBytes, pos);
                        pos = (int) valResult[1];
                        if (fieldNum == 1) entitled = valResult[0] != 0;
                        else if (fieldNum == 2) numericValue = (int) valResult[0];
                        else if (fieldNum == 3) expiresAt = valResult[0];
                    } else if (wireType == 2) {
                        long[] lenResult = readVarint(responseBytes, pos);
                        int len = (int) lenResult[0];
                        pos = (int) lenResult[1];
                        String s = new String(responseBytes, pos, len, StandardCharsets.UTF_8);
                        pos += len;
                        if (fieldNum == 4) plan = s;
                    }
                }
                return new LicenseInfo(entitled, numericValue, expiresAt, plan);
            } catch (Exception e) {
                if ("closed".equals(config.getFailMode())) {
                    throw new CoreSDKException(new ProblemDetail(
                        "https://coresdk.io/errors/internal", "Internal Error", 500), e);
                }
                log.warn("[coresdk] checkEntitlement fail-open: {}", e.getMessage());
                return new LicenseInfo(false, 0, 0, "");
            }
        });
    }

    // ---- Token Revocation ----

    public CompletableFuture<Void> revokeToken(String token) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                AuthServiceGrpc.BlockingStub stub = getGrpcStub();
                String tenantId = config.getTenantId() != null ? config.getTenantId() : "";

                java.io.ByteArrayOutputStream buf = new java.io.ByteArrayOutputStream();
                writeString(buf, 1, token);
                writeString(buf, 2, tenantId);

                stub.revokeToken(buf.toByteArray());
                return null;
            } catch (Exception e) {
                if ("closed".equals(config.getFailMode())) {
                    throw new CoreSDKException(new ProblemDetail(
                        "https://coresdk.io/errors/internal", "Internal Error", 500), e);
                }
                log.warn("[coresdk] revokeToken fail-open: {}", e.getMessage());
                return null;
            }
        });
    }

    // ---- Protobuf wire-format helpers ----

    private static void writeVarint(java.io.ByteArrayOutputStream out, long value) throws java.io.IOException {
        while ((value & ~0x7FL) != 0) {
            out.write((int) ((value & 0x7F) | 0x80));
            value >>>= 7;
        }
        out.write((int) (value & 0x7F));
    }

    private static void writeString(java.io.ByteArrayOutputStream out, int fieldNum, String value) throws java.io.IOException {
        if (value == null || value.isEmpty()) return;
        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
        writeVarint(out, ((long) fieldNum << 3) | 2L);
        writeVarint(out, bytes.length);
        out.write(bytes);
    }

    private static long[] readVarint(byte[] data, int pos) {
        long result = 0;
        int shift = 0;
        while (pos < data.length) {
            byte b = data[pos++];
            result |= (long) (b & 0x7F) << shift;
            if ((b & 0x80) == 0) break;
            shift += 7;
        }
        return new long[]{result, pos};
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
