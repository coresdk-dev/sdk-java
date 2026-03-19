package io.coresdk.testing;

import io.coresdk.*;

import java.util.ArrayList;
import java.util.List;
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

    public List<String> getAuthorizeCalls() { return List.copyOf(authorizeCalls); }
}
