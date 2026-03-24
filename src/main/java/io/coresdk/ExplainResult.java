package io.coresdk;

import java.util.Map;
import java.util.HashMap;

/**
 * Structured explanation of why a request was allowed or denied.
 */
public class ExplainResult {
    private String requestId = "";
    private String outcome = "";  // "allowed" | "denied"
    private Map<String, Object> auth = new HashMap<>();
    private Map<String, Object> policy = new HashMap<>();
    private Map<String, Object> rateLimit = new HashMap<>();
    private Map<String, Object> masking = new HashMap<>();
    private double latencyMs = 0.0;

    public ExplainResult() {}

    public ExplainResult(String outcome, Map<String, Object> auth) {
        this.outcome = outcome;
        this.auth = auth;
    }

    public String getRequestId() { return requestId; }
    public void setRequestId(String requestId) { this.requestId = requestId; }
    public String getOutcome() { return outcome; }
    public void setOutcome(String outcome) { this.outcome = outcome; }
    public Map<String, Object> getAuth() { return auth; }
    public void setAuth(Map<String, Object> auth) { this.auth = auth; }
    public Map<String, Object> getPolicy() { return policy; }
    public void setPolicy(Map<String, Object> policy) { this.policy = policy; }
    public Map<String, Object> getRateLimit() { return rateLimit; }
    public void setRateLimit(Map<String, Object> rateLimit) { this.rateLimit = rateLimit; }
    public Map<String, Object> getMasking() { return masking; }
    public void setMasking(Map<String, Object> masking) { this.masking = masking; }
    public double getLatencyMs() { return latencyMs; }
    public void setLatencyMs(double latencyMs) { this.latencyMs = latencyMs; }

    @Override
    public String toString() {
        return "ExplainResult{outcome='" + outcome + "', auth=" + auth + "}";
    }
}
