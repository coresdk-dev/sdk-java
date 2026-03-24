package io.coresdk.testing;

import io.coresdk.*;

import java.util.ArrayList;
import java.util.Arrays;
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

    @Override
    public CompletableFuture<Boolean> isRevoked(String token) {
        return CompletableFuture.completedFuture(false);
    }

    @Override
    public CompletableFuture<ExplainResult> explainAuthorize(String token, String path, String action) {
        return CompletableFuture.completedFuture(new ExplainResult(
            "allowed",
            Map.of("allowed", defaultAllow, "subject", defaultClaims.getSub())));
    }

    @Override
    public CompletableFuture<AgentToken> mintAgentToken(String parentToken, String targetService,
                                                         List<String> scopes, int ttlSeconds) {
        return CompletableFuture.completedFuture(
            new AgentToken("mock.agent.token", 300,
                Arrays.asList(parentToken, targetService)));
    }

    @Override
    public CompletableFuture<EgressDecision> checkEgress(String url) {
        return CompletableFuture.completedFuture(new EgressDecision(true, "mock-allowed"));
    }

    public List<String> getAuthorizeCalls() { return List.copyOf(authorizeCalls); }
}
