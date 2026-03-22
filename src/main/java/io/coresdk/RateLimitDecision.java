package io.coresdk;

public class RateLimitDecision {
    private final boolean allowed;
    private final int remaining;
    private final int retryAfterMs;

    public RateLimitDecision(boolean allowed, int remaining, int retryAfterMs) {
        this.allowed = allowed;
        this.remaining = remaining;
        this.retryAfterMs = retryAfterMs;
    }

    public boolean isAllowed() { return allowed; }
    public int getRemaining() { return remaining; }
    public int getRetryAfterMs() { return retryAfterMs; }

    @Override
    public String toString() {
        return "RateLimitDecision{allowed=" + allowed + ", remaining=" + remaining + ", retryAfterMs=" + retryAfterMs + "}";
    }
}
