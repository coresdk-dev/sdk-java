package io.coresdk;

public class AuthDecision {
    private final boolean allowed;
    private final Claims claims;
    private final String reason;

    public AuthDecision(boolean allowed, Claims claims, String reason) {
        this.allowed = allowed;
        this.claims = claims;
        this.reason = reason;
    }

    public boolean isAllowed() { return allowed; }
    public Claims getClaims() { return claims; }
    public String getReason() { return reason; }

    public void require() {
        if (!allowed) {
            throw new CoreSDKException(ProblemDetail.forbidden(reason));
        }
    }
}
