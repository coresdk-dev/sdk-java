package io.coresdk;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;

/**
 * Snapshot of a CoreSDK job's state. Returned by {@link CoreSDK#submitJob},
 * {@link CoreSDK#getJob}, {@link CoreSDK#cancelJob}, {@link CoreSDK#listJobs},
 * and every progress tick of {@link CoreSDK#watchJob}.
 *
 * <p>Secret values are never present on this type — only the resolved logical
 * names appear in {@link #getResolvedSecretNames()}.
 */
public class Job {

    @JsonProperty("id")
    private String jobId = "";

    @JsonProperty("kind")
    private String kind = "";

    @JsonProperty("image")
    private String image = "";

    /**
     * One of {@code "pending"}, {@code "scheduling"}, {@code "running"},
     * {@code "succeeded"}, {@code "failed"}, {@code "cancelled"}.
     */
    @JsonProperty("state")
    private String state = "pending";

    @JsonProperty("exit_code")
    private long exitCode = 0;

    @JsonProperty("error")
    private String error = "";

    @JsonProperty("input_uri")
    private String inputS3Uri = "";

    @JsonProperty("output_uri")
    private String outputS3Uri = "";

    @JsonProperty("logs_uri")
    private String logsS3Uri = "";

    @JsonProperty("tenant_id")
    private String tenantId = "";

    @JsonProperty("user_id")
    private String userId = "";

    @JsonProperty("k8s_namespace")
    private String k8sNamespace = "";

    @JsonProperty("k8s_job_name")
    private String k8sJobName = "";

    @JsonProperty("created_at")
    private String createdAt = "";

    @JsonProperty("started_at")
    private String startedAt;

    @JsonProperty("finished_at")
    private String finishedAt;

    /** Logical secret names injected into the job. Values are never recorded. */
    private List<String> resolvedSecretNames = Collections.emptyList();

    public Job() {}

    public boolean isTerminal() {
        return "succeeded".equals(state) || "failed".equals(state) || "cancelled".equals(state);
    }

    public String getJobId() { return jobId; }
    public void setJobId(String v) { this.jobId = v; }
    public String getKind() { return kind; }
    public void setKind(String v) { this.kind = v; }
    public String getImage() { return image; }
    public void setImage(String v) { this.image = v; }
    public String getState() { return state; }
    public void setState(String v) { this.state = v; }
    public long getExitCode() { return exitCode; }
    public void setExitCode(long v) { this.exitCode = v; }
    public String getError() { return error; }
    public void setError(String v) { this.error = v; }
    public String getInputS3Uri() { return inputS3Uri; }
    public void setInputS3Uri(String v) { this.inputS3Uri = v; }
    public String getOutputS3Uri() { return outputS3Uri; }
    public void setOutputS3Uri(String v) { this.outputS3Uri = v; }
    public String getLogsS3Uri() { return logsS3Uri; }
    public void setLogsS3Uri(String v) { this.logsS3Uri = v; }
    public String getTenantId() { return tenantId; }
    public void setTenantId(String v) { this.tenantId = v; }
    public String getUserId() { return userId; }
    public void setUserId(String v) { this.userId = v; }
    public String getK8sNamespace() { return k8sNamespace; }
    public void setK8sNamespace(String v) { this.k8sNamespace = v; }
    public String getK8sJobName() { return k8sJobName; }
    public void setK8sJobName(String v) { this.k8sJobName = v; }
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String v) { this.createdAt = v; }
    public String getStartedAt() { return startedAt; }
    public void setStartedAt(String v) { this.startedAt = v; }
    public String getFinishedAt() { return finishedAt; }
    public void setFinishedAt(String v) { this.finishedAt = v; }
    public List<String> getResolvedSecretNames() { return resolvedSecretNames; }
    public void setResolvedSecretNames(List<String> v) {
        this.resolvedSecretNames = v == null ? Collections.emptyList() : v;
    }
}
