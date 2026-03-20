package io.coresdk;

import io.coresdk.testing.MockCoreSDK;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class CoreSDKTest {

    @Test
    void mockAllowsByDefault() throws ExecutionException, InterruptedException {
        MockCoreSDK sdk = new MockCoreSDK();
        AuthDecision decision = sdk.authorize("token", "/api/users", "GET").get();
        assertTrue(decision.isAllowed());
    }

    @Test
    void mockDenyWorks() throws ExecutionException, InterruptedException {
        Claims claims = new Claims("test", "tenant", List.of(), 0L);
        MockCoreSDK sdk = new MockCoreSDK(false, claims);
        AuthDecision decision = sdk.authorize("token", "/api/admin", "DELETE").get();
        assertFalse(decision.isAllowed());
    }

    @Test
    void mockTracksCalls() throws ExecutionException, InterruptedException {
        MockCoreSDK sdk = new MockCoreSDK();
        sdk.authorize("tok123", "/api/users", "GET").get();
        assertEquals(1, sdk.getAuthorizeCalls().size());
        assertTrue(sdk.getAuthorizeCalls().get(0).startsWith("tok123"));
    }

    @Test
    void requireThrowsWhenDenied() throws ExecutionException, InterruptedException {
        Claims claims = new Claims("test", "tenant", List.of(), 0L);
        MockCoreSDK sdk = new MockCoreSDK(false, claims);
        AuthDecision decision = sdk.authorize("token", "/api", "GET").get();
        CoreSDKException ex = assertThrows(CoreSDKException.class, decision::require);
        assertEquals(403, ex.getStatus());
    }

    @Test
    void problemDetailUnauthorized() {
        ProblemDetail pd = ProblemDetail.unauthorized("missing token");
        assertEquals(401, pd.getStatus());
        assertEquals("Unauthorized", pd.getTitle());
        assertEquals("missing token", pd.getDetail());
    }

    @Test
    void configFromEnvDefaults() {
        CoreSDKConfig cfg = CoreSDKConfig.fromEnv();
        assertNotNull(cfg);
        assertEquals("localhost:50051", cfg.getEndpoint());
        assertEquals("open", cfg.getFailMode());
        assertNotNull(cfg.getTls());
    }

    @Test
    void mockAuthorizeReturnsClaims() throws ExecutionException, InterruptedException {
        Claims claims = new Claims("alice", "acme", List.of("admin", "viewer"), 9999L);
        MockCoreSDK sdk = new MockCoreSDK(true, claims);
        AuthDecision decision = sdk.authorize("token", "/api/orders", "GET").get();
        assertTrue(decision.isAllowed());
        assertEquals("alice", decision.getClaims().getSub());
        assertEquals("acme", decision.getClaims().getTenantId());
        assertEquals(List.of("admin", "viewer"), decision.getClaims().getRoles());
    }

    @Test
    void failClosedThrowsOnError() {
        CoreSDKConfig cfg = new CoreSDKConfig();
        cfg.setFailMode("closed");
        cfg.setEndpoint("localhost:1"); // unreachable
        CoreSDK sdk = new CoreSDK(cfg);
        assertThrows(Exception.class, () -> sdk.authorize("tok", "/x", "GET").join());
    }

    @Test
    void claimsContextThreadLocal() {
        Claims claims = new Claims("user-1", "tenant-a", List.of("admin"), 9999L);
        ClaimsContext.set(claims);
        assertEquals("user-1", ClaimsContext.get().getSub());
        ClaimsContext.clear();
        assertNull(ClaimsContext.get());
    }
}
