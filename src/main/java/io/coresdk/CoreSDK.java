package io.coresdk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * CoreSDK client. Obtain via {@link CoreSDK#fromEnv()} or Spring auto-configuration.
 *
 * <h3>Transport (Phase 1b)</h3>
 * <p>This client uses Java's {@link HttpClient} to POST JSON to the control plane's
 * HTTP API ({@code /api/v1/auth/validate}).  The control plane URL is read from the
 * {@code CORESDK_CONTROL_PLANE_URL} environment variable (e.g.
 * {@code http://localhost:8080}).  If the variable is not set the client fails-open
 * (or fails-closed when {@code CORESDK_FAIL_MODE=closed}).</p>
 *
 * <h3>TODO — Phase 2 GA</h3>
 * <p>Replace HTTP transport with gRPC using {@code io.coresdk.proto.AuthServiceGrpc}
 * over a {@code ManagedChannel} targeting the sidecar on port 50051.  The hand-written
 * stub in {@code AuthServiceGrpc} is already wired; buf Java codegen will replace it
 * once {@code protoc} is available in CI.</p>
 */
public class CoreSDK {

    private static final Logger log = LoggerFactory.getLogger(CoreSDK.class);

    private static final HttpClient HTTP_CLIENT = HttpClient.newBuilder()
        .connectTimeout(Duration.ofSeconds(5))
        .build();

    private final CoreSDKConfig config;

    public CoreSDK(CoreSDKConfig config) {
        this.config = config;
    }

    public static CoreSDK fromEnv() {
        return new CoreSDK(CoreSDKConfig.fromEnv());
    }

    /**
     * Authorize a request against the control plane.
     *
     * <p>If {@code CORESDK_CONTROL_PLANE_URL} is not configured:
     * <ul>
     *   <li>fail-mode {@code open} — returns {@code AuthDecision(allowed=true, reason="no-control-plane")}</li>
     *   <li>fail-mode {@code closed} — throws {@link CoreSDKException} (503)</li>
     * </ul>
     *
     * <p>On any network / HTTP error the same fail-open / fail-closed logic applies.
     *
     * @param token    raw Bearer token (without the "Bearer " prefix)
     * @param resource resource path or name being accessed
     * @param action   action being attempted (e.g. "GET", "DELETE")
     */
    public CompletableFuture<AuthDecision> authorize(String token, String resource, String action) {
        return CompletableFuture.supplyAsync(() -> {
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

                // Strip trailing slash for safe concatenation
                String base = controlPlaneUrl.endsWith("/")
                    ? controlPlaneUrl.substring(0, controlPlaneUrl.length() - 1)
                    : controlPlaneUrl;

                String body = String.format(
                    "{\"token\":\"%s\",\"resource\":\"%s\",\"action\":\"%s\",\"tenant_id\":\"%s\"}",
                    escapeJson(token),
                    escapeJson(resource),
                    escapeJson(action),
                    escapeJson(config.getTenantId())
                );

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

                String responseBody = response.body();
                boolean allowed = responseBody.contains("\"allowed\":true");
                String subject  = extractJsonString(responseBody, "sub");
                String tenantId = extractJsonString(responseBody, "tenant_id");

                Claims claims = new Claims(
                    subject.isEmpty()   ? "unknown"             : subject,
                    tenantId.isEmpty()  ? config.getTenantId()  : tenantId,
                    List.of(),
                    System.currentTimeMillis() / 1000 + 3600
                );
                return new AuthDecision(allowed, claims, null);

            } catch (CoreSDKException e) {
                throw e;
            } catch (Exception e) {
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

    // -------------------------------------------------------------------------
    // Helpers
    // -------------------------------------------------------------------------

    /** Minimal JSON string escaping — no external dependency required. */
    private static String escapeJson(String value) {
        if (value == null) return "";
        return value.replace("\\", "\\\\")
                    .replace("\"", "\\\"")
                    .replace("\n", "\\n")
                    .replace("\r", "\\r")
                    .replace("\t", "\\t");
    }

    private static String extractJsonString(String json, String key) {
        String search = "\"" + key + "\":\"";
        int start = json.indexOf(search);
        if (start < 0) return "";
        start += search.length();
        int end = json.indexOf("\"", start);
        return end < 0 ? "" : json.substring(start, end);
    }

    public CoreSDKConfig getConfig() { return config; }
}
