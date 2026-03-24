package io.coresdk;

/**
 * Result of an outbound URL safety check.
 */
public class EgressDecision {
    private boolean allowed = true;
    private String reason = "";

    public EgressDecision() {}

    public EgressDecision(boolean allowed, String reason) {
        this.allowed = allowed;
        this.reason = reason;
    }

    public boolean isAllowed() { return allowed; }
    public void setAllowed(boolean allowed) { this.allowed = allowed; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    @Override
    public String toString() {
        return "EgressDecision{allowed=" + allowed + ", reason='" + reason + "'}";
    }
}
