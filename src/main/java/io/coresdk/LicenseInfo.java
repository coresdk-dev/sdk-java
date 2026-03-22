package io.coresdk;

public class LicenseInfo {
    private final boolean entitled;
    private final int numericValue;
    private final long expiresAt;
    private final String plan;

    public LicenseInfo(boolean entitled, int numericValue, long expiresAt, String plan) {
        this.entitled = entitled;
        this.numericValue = numericValue;
        this.expiresAt = expiresAt;
        this.plan = plan;
    }

    public boolean isEntitled() { return entitled; }
    public int getNumericValue() { return numericValue; }
    public long getExpiresAt() { return expiresAt; }
    public String getPlan() { return plan; }

    @Override
    public String toString() {
        return "LicenseInfo{entitled=" + entitled + ", numericValue=" + numericValue + ", expiresAt=" + expiresAt + ", plan='" + plan + "'}";
    }
}
