package io.coresdk.testing;

import io.coresdk.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class MockCoreSDK extends CoreSDK {
    private final boolean defaultAllow;
    private final Claims defaultClaims;
    private final List<String> authorizeCalls = new ArrayList<>();

    public MockCoreSDK() {
        this(true, new Claims("test-user", "test-tenant", List.of("member"),
            System.currentTimeMillis() / 1000 + 3600));
    }

    public MockCoreSDK(boolean defaultAllow, Claims claims) {
        super(CoreSDKConfig.fromEnv());
        this.defaultAllow = defaultAllow;
        this.defaultClaims = claims;
    }

    @Override
    public CompletableFuture<AuthDecision> authorize(String token, String resource, String action) {
        authorizeCalls.add(token + ":" + resource + ":" + action);
        return CompletableFuture.completedFuture(
            new AuthDecision(defaultAllow, defaultClaims, null));
    }

    @Override
    public CompletableFuture<PolicyResult> evaluatePolicy(String rule, Map<String, Object> input) {
        return CompletableFuture.completedFuture(new PolicyResult(defaultAllow, null));
    }

    @Override
    public CompletableFuture<RateLimitDecision> checkRateLimit(String key) {
        return CompletableFuture.completedFuture(new RateLimitDecision(true, 999, 0));
    }

    @Override
    public CompletableFuture<AuditRecord> emitAuditEvent(String action, String userId, String outcome, Map<String, String> metadata) {
        return CompletableFuture.completedFuture(new AuditRecord("mock-id", 0, "mock"));
    }

    @Override
    public CompletableFuture<FlagDecision> evaluateFlag(String flagKey, String userId) {
        return CompletableFuture.completedFuture(new FlagDecision(true, "", "mock"));
    }

    @Override
    public CompletableFuture<LicenseInfo> checkEntitlement(String key) {
        return CompletableFuture.completedFuture(new LicenseInfo(true, 0, 0, "enterprise"));
    }

    @Override
    public CompletableFuture<Void> revokeToken(String token) {
        return CompletableFuture.completedFuture(null);
    }

    public List<String> getAuthorizeCalls() { return List.copyOf(authorizeCalls); }
}
