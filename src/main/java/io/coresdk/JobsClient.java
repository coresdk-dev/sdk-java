package io.coresdk;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/**
 * Client for the CoreSDK {@code JobService}.
 *
 * <p><strong>Transport note:</strong> the Java SDK currently talks to the
 * control plane's REST surface for jobs (mirroring its existing pattern for
 * the other 9 services). {@link #watchJob} polls {@code GET /api/v1/jobs/{id}}
 * at the configured interval rather than holding a streaming RPC open — a
 * pragmatic compromise that avoids the Netty server-streaming machinery and
 * still composes cleanly with Spring's {@code CompletableFuture} idioms.
 * For low-latency event delivery prefer the gRPC SDKs (Python / Go / TS).
 */
public class JobsClient {

    private static final Logger log = LoggerFactory.getLogger(JobsClient.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final HttpClient HTTP = HttpClient.newBuilder()
        .connectTimeout(Duration.ofSeconds(5))
        .build();

    private final CoreSDKConfig config;
    private final Duration pollInterval;

    public JobsClient(CoreSDKConfig config) {
        this(config, Duration.ofSeconds(2));
    }

    public JobsClient(CoreSDKConfig config, Duration pollInterval) {
        this.config = config;
        this.pollInterval = pollInterval;
    }

    private String controlPlaneBase() {
        String url = config.getControlPlaneUrl();
        if (url == null || url.isBlank()) {
            throw new IllegalStateException(
                "CoreSDKConfig.controlPlaneUrl must be set for JobService — set CORESDK_CONTROL_PLANE_URL");
        }
        return url.replaceAll("/$", "");
    }

    private HttpRequest.Builder request(String path) {
        HttpRequest.Builder b = HttpRequest.newBuilder()
            .uri(URI.create(controlPlaneBase() + path))
            .timeout(Duration.ofSeconds(15))
            .header("content-type", "application/json")
            .header("accept", "application/json");
        // The auth middleware on the control plane is API-key based.
        String key = System.getenv("CORESDK_CONTROL_PLANE_TOKEN");
        if (key != null && !key.isBlank()) {
            b.header("x-api-key", key);
        }
        return b;
    }

    private String tenantId(String override) {
        if (override != null && !override.isBlank()) return override;
        String configured = config.getTenantId();
        return configured == null ? "default" : configured;
    }

    /**
     * Submit a job. Returns the freshly-persisted snapshot in pending state.
     *
     * <p>Inputs in {@link SubmitJobRequest#getInlineFiles()} are uploaded to
     * the sidecar's blob backend as part of submission. For large inputs,
     * pre-stage to S3 and pass {@link SubmitJobRequest#getInputS3Uri()}.
     */
    public CompletableFuture<Job> submitJob(SubmitJobRequest req) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                String id = UUID.randomUUID().toString();
                ObjectNode body = MAPPER.createObjectNode();
                body.put("id", id);
                body.put("tenant_id", tenantId(req.getTenantId()));
                body.put("user_id", req.getUserId());
                body.put("kind", req.getKind());
                body.put("image", req.getImage());
                body.put("state", "pending");
                if (!req.getInputS3Uri().isBlank()) {
                    body.put("input_uri", req.getInputS3Uri());
                }
                body.put("created_at", Instant.now().toString());

                ObjectNode spec = MAPPER.createObjectNode();
                spec.put("kind", req.getKind());
                spec.put("image", req.getImage());
                ArrayNode command = spec.putArray("command");
                req.getCommand().forEach(command::add);
                ArrayNode args = spec.putArray("args");
                req.getArgs().forEach(args::add);
                ObjectNode env = spec.putObject("env");
                req.getEnv().forEach(env::put);
                ObjectNode inline = spec.putObject("inline_files");
                req.getInlineFiles().forEach((k, v) -> inline.put(k, Base64.getEncoder().encodeToString(v)));
                ArrayNode bundles = spec.putArray("secret_bundles");
                req.getSecretBundles().forEach(bundles::add);
                spec.put("timeout_seconds", req.getTimeoutSeconds());
                spec.put("capture_logs", req.isCaptureLogs());
                spec.put("capture_output", req.isCaptureOutput());
                spec.put("output_prefix", req.getOutputPrefix());
                spec.put("user_id", req.getUserId());
                body.put("spec_json", MAPPER.writeValueAsString(spec));

                HttpRequest httpReq = request("/api/v1/jobs")
                    .POST(HttpRequest.BodyPublishers.ofString(MAPPER.writeValueAsString(body)))
                    .build();
                HttpResponse<String> resp = HTTP.send(httpReq, HttpResponse.BodyHandlers.ofString());
                if (resp.statusCode() >= 400) {
                    throw new RuntimeException("submitJob HTTP " + resp.statusCode() + ": " + resp.body());
                }
                return MAPPER.readValue(resp.body(), Job.class);
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e) {
                throw new RuntimeException("submitJob", e);
            }
        });
    }

    public CompletableFuture<Job> getJob(String jobId) {
        return getJob(jobId, "");
    }

    public CompletableFuture<Job> getJob(String jobId, String tenantIdOverride) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                String q = "tenant_id=" + URLEncoder.encode(tenantId(tenantIdOverride), StandardCharsets.UTF_8);
                HttpRequest httpReq = request("/api/v1/jobs/" + URLEncoder.encode(jobId, StandardCharsets.UTF_8) + "?" + q)
                    .GET()
                    .build();
                HttpResponse<String> resp = HTTP.send(httpReq, HttpResponse.BodyHandlers.ofString());
                if (resp.statusCode() == 404 || "null".equals(resp.body().trim())) {
                    throw new RuntimeException("job " + jobId + " not found");
                }
                if (resp.statusCode() >= 400) {
                    throw new RuntimeException("getJob HTTP " + resp.statusCode() + ": " + resp.body());
                }
                return MAPPER.readValue(resp.body(), Job.class);
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e) {
                throw new RuntimeException("getJob", e);
            }
        });
    }

    /** List jobs. {@code state} empty = all states. */
    public CompletableFuture<List<Job>> listJobs(String state, int limit) {
        return listJobs(state, limit, "");
    }

    public CompletableFuture<List<Job>> listJobs(String state, int limit, String tenantIdOverride) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                StringBuilder q = new StringBuilder("tenant_id=");
                q.append(URLEncoder.encode(tenantId(tenantIdOverride), StandardCharsets.UTF_8));
                if (state != null && !state.isBlank()) {
                    q.append("&state=").append(URLEncoder.encode(state, StandardCharsets.UTF_8));
                }
                if (limit > 0) q.append("&limit=").append(limit);
                HttpRequest httpReq = request("/api/v1/jobs?" + q).GET().build();
                HttpResponse<String> resp = HTTP.send(httpReq, HttpResponse.BodyHandlers.ofString());
                if (resp.statusCode() >= 400) {
                    throw new RuntimeException("listJobs HTTP " + resp.statusCode() + ": " + resp.body());
                }
                JsonNode arr = MAPPER.readTree(resp.body());
                List<Job> out = new ArrayList<>();
                if (arr.isArray()) {
                    for (JsonNode el : arr) out.add(MAPPER.treeToValue(el, Job.class));
                }
                return out;
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e) {
                throw new RuntimeException("listJobs", e);
            }
        });
    }

    /**
     * Mark a job as cancelled via PATCH. The sidecar's recovery loop will
     * propagate the state transition to the K8s Job on its next sync tick.
     */
    public CompletableFuture<Job> cancelJob(String jobId, String reason) {
        return cancelJob(jobId, reason, "");
    }

    public CompletableFuture<Job> cancelJob(String jobId, String reason, String tenantIdOverride) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                ObjectNode body = MAPPER.createObjectNode();
                body.put("tenant_id", tenantId(tenantIdOverride));
                body.put("state", "cancelled");
                body.put("error", reason == null ? "" : reason);
                body.put("finished_at", Instant.now().toString());
                HttpRequest httpReq = request("/api/v1/jobs/" + URLEncoder.encode(jobId, StandardCharsets.UTF_8))
                    .method("PATCH", HttpRequest.BodyPublishers.ofString(MAPPER.writeValueAsString(body)))
                    .build();
                HttpResponse<String> resp = HTTP.send(httpReq, HttpResponse.BodyHandlers.ofString());
                if (resp.statusCode() >= 400) {
                    throw new RuntimeException("cancelJob HTTP " + resp.statusCode() + ": " + resp.body());
                }
                return getJob(jobId, tenantIdOverride).get();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e) {
                throw new RuntimeException("cancelJob", e);
            }
        });
    }

    /**
     * Poll {@code GET /api/v1/jobs/{id}} until the job reaches a terminal
     * state, invoking {@code onUpdate} on each state transition observed.
     * Returns the final terminal snapshot.
     */
    public CompletableFuture<Job> watchJob(String jobId, Consumer<Job> onUpdate) {
        return watchJob(jobId, onUpdate, "");
    }

    public CompletableFuture<Job> watchJob(String jobId, Consumer<Job> onUpdate, String tenantIdOverride) {
        return CompletableFuture.supplyAsync(() -> {
            String lastState = "";
            while (true) {
                Job cur;
                try {
                    cur = getJob(jobId, tenantIdOverride).get();
                } catch (Exception e) {
                    throw new RuntimeException("watchJob poll", e);
                }
                if (!cur.getState().equals(lastState)) {
                    if (onUpdate != null) {
                        try {
                            onUpdate.accept(cur);
                        } catch (RuntimeException ignored) {
                            // user callback errors must not break the poll loop
                        }
                    }
                    lastState = cur.getState();
                }
                if (cur.isTerminal()) {
                    return cur;
                }
                try {
                    Thread.sleep(pollInterval.toMillis());
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("watchJob interrupted", ie);
                }
            }
        });
    }
}
