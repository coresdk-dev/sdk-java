package io.coresdk;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "coresdk")
public class CoreSDKConfig {
    private String endpoint = "localhost:50051";
    private String tenantId = "";
    private String failMode = "open";
    private String controlPlaneUrl = "";
    private Tls tls = new Tls();

    public static CoreSDKConfig fromEnv() {
        CoreSDKConfig cfg = new CoreSDKConfig();
        // CORESDK_SIDECAR_ADDR is the canonical env var (matches Python, Go, Rust sidecar).
        // CORESDK_ENDPOINT is accepted as a deprecated alias for backwards compatibility.
        String ep = System.getenv("CORESDK_SIDECAR_ADDR");
        if (ep == null) ep = System.getenv("CORESDK_ENDPOINT");
        if (ep != null) cfg.setEndpoint(ep);
        String tenant = System.getenv("CORESDK_TENANT_ID");
        if (tenant != null) cfg.setTenantId(tenant);
        String failMode = System.getenv("CORESDK_FAIL_MODE");
        if (failMode != null) cfg.setFailMode(failMode);
        String cpUrl = System.getenv("CORESDK_CONTROL_PLANE_URL");
        if (cpUrl != null) cfg.setControlPlaneUrl(cpUrl);
        return cfg;
    }

    public String getEndpoint() { return endpoint; }
    public void setEndpoint(String endpoint) { this.endpoint = endpoint; }

    public String getTenantId() { return tenantId; }
    public void setTenantId(String tenantId) { this.tenantId = tenantId; }

    public String getFailMode() { return failMode; }
    public void setFailMode(String failMode) { this.failMode = failMode; }

    /**
     * HTTP base URL of the control plane (e.g. {@code http://localhost:8080}).
     * Read from {@code CORESDK_CONTROL_PLANE_URL}.
     * When blank, {@link io.coresdk.CoreSDK#authorize} fails-open (or fails-closed
     * if {@code failMode=closed}).
     *
     * <p>TODO Phase 2 GA: remove this in favour of gRPC transport to the sidecar
     * ({@code endpoint}, port 50051) via {@code io.coresdk.proto.AuthServiceGrpc}.
     */
    public String getControlPlaneUrl() { return controlPlaneUrl; }
    public void setControlPlaneUrl(String controlPlaneUrl) { this.controlPlaneUrl = controlPlaneUrl; }

    public Tls getTls() { return tls; }
    public void setTls(Tls tls) { this.tls = tls; }

    public static class Tls {
        private String certPath;
        private String keyPath;
        private String caPath;

        public String getCertPath() { return certPath; }
        public void setCertPath(String certPath) { this.certPath = certPath; }
        public String getKeyPath() { return keyPath; }
        public void setKeyPath(String keyPath) { this.keyPath = keyPath; }
        public String getCaPath() { return caPath; }
        public void setCaPath(String caPath) { this.caPath = caPath; }
    }
}
