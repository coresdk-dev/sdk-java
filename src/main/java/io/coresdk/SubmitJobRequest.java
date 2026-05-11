package io.coresdk;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Builder-style submission payload for {@link CoreSDK#submitJob}.
 *
 * <p>Reserved env keys ({@code CORESDK_*}) are rejected server-side.
 * Inline files are capped at 1 MiB total (configurable via
 * {@code CORESDK_JOBS_INLINE_MAX_BYTES} on the sidecar).
 */
public class SubmitJobRequest {

    private String kind = "";
    private String image = "";
    private List<String> command = Collections.emptyList();
    private List<String> args = Collections.emptyList();
    private Map<String, String> env = Collections.emptyMap();
    private Map<String, byte[]> inlineFiles = Collections.emptyMap();
    private String inputS3Uri = "";
    private List<String> secretBundles = Collections.emptyList();
    private int timeoutSeconds = 0;
    private boolean captureLogs = true;
    private boolean captureOutput = true;
    private String outputPrefix = "";
    private String userId = "";
    private String tenantId = "";

    public String getKind() { return kind; }
    public SubmitJobRequest setKind(String v) { this.kind = v; return this; }
    public String getImage() { return image; }
    public SubmitJobRequest setImage(String v) { this.image = v; return this; }
    public List<String> getCommand() { return command; }
    public SubmitJobRequest setCommand(List<String> v) { this.command = v == null ? Collections.emptyList() : v; return this; }
    public List<String> getArgs() { return args; }
    public SubmitJobRequest setArgs(List<String> v) { this.args = v == null ? Collections.emptyList() : v; return this; }
    public Map<String, String> getEnv() { return env; }
    public SubmitJobRequest setEnv(Map<String, String> v) { this.env = v == null ? Collections.emptyMap() : v; return this; }
    public Map<String, byte[]> getInlineFiles() { return inlineFiles; }
    public SubmitJobRequest setInlineFiles(Map<String, byte[]> v) { this.inlineFiles = v == null ? Collections.emptyMap() : v; return this; }
    public String getInputS3Uri() { return inputS3Uri; }
    public SubmitJobRequest setInputS3Uri(String v) { this.inputS3Uri = v == null ? "" : v; return this; }
    public List<String> getSecretBundles() { return secretBundles; }
    public SubmitJobRequest setSecretBundles(List<String> v) { this.secretBundles = v == null ? Collections.emptyList() : v; return this; }
    public int getTimeoutSeconds() { return timeoutSeconds; }
    public SubmitJobRequest setTimeoutSeconds(int v) { this.timeoutSeconds = v; return this; }
    public boolean isCaptureLogs() { return captureLogs; }
    public SubmitJobRequest setCaptureLogs(boolean v) { this.captureLogs = v; return this; }
    public boolean isCaptureOutput() { return captureOutput; }
    public SubmitJobRequest setCaptureOutput(boolean v) { this.captureOutput = v; return this; }
    public String getOutputPrefix() { return outputPrefix; }
    public SubmitJobRequest setOutputPrefix(String v) { this.outputPrefix = v == null ? "" : v; return this; }
    public String getUserId() { return userId; }
    public SubmitJobRequest setUserId(String v) { this.userId = v == null ? "" : v; return this; }
    public String getTenantId() { return tenantId; }
    public SubmitJobRequest setTenantId(String v) { this.tenantId = v == null ? "" : v; return this; }
}
