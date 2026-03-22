package io.coresdk;

public class FlagDecision {
    private final boolean enabled;
    private final String variant;
    private final String reason;

    public FlagDecision(boolean enabled, String variant, String reason) {
        this.enabled = enabled;
        this.variant = variant;
        this.reason = reason;
    }

    public boolean isEnabled() { return enabled; }
    public String getVariant() { return variant; }
    public String getReason() { return reason; }

    @Override
    public String toString() {
        return "FlagDecision{enabled=" + enabled + ", variant='" + variant + "', reason='" + reason + "'}";
    }
}
