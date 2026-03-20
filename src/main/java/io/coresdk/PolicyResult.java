package io.coresdk;

/** Result of a policy evaluation. */
public class PolicyResult {
    private final boolean allowed;
    private final String reason;

    public PolicyResult(boolean allowed, String reason) {
        this.allowed = allowed;
        this.reason  = reason;
    }

    public boolean isAllowed() { return allowed; }
    public String  getReason()  { return reason; }

    @Override
    public String toString() {
        return "PolicyResult{allowed=" + allowed + ", reason='" + reason + "'}";
    }
}
