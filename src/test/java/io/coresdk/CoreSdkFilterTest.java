package io.coresdk;

import io.coresdk.testing.MockCoreSDK;
import io.coresdk.web.CoreSDKFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for CoreSDKFilter using Spring Mock Servlet objects.
 * No Spring context is loaded — instantiation is direct.
 */
class CoreSdkFilterTest {

    private MockCoreSDK allowSdk;
    private MockCoreSDK denySdk;

    @BeforeEach
    void setUp() {
        allowSdk = new MockCoreSDK();
        denySdk  = new MockCoreSDK(false,
            new Claims("test", "tenant", List.of(), 0L));
    }

    // -----------------------------------------------------------------------
    // Happy path: valid Bearer token → filter passes, chain invoked
    // -----------------------------------------------------------------------

    @Test
    void validTokenPassesChain() throws Exception {
        CoreSDKFilter filter = new CoreSDKFilter(allowSdk);

        MockHttpServletRequest  req   = new MockHttpServletRequest("GET", "/api/users");
        req.addHeader("Authorization", "Bearer valid-token-123");
        MockHttpServletResponse res   = new MockHttpServletResponse();
        MockFilterChain         chain = new MockFilterChain();

        filter.doFilter(req, res, chain);

        // Chain must have been invoked (request forwarded)
        assertNotNull(chain.getRequest(),
            "Filter chain should have been called for an authorized request");
        assertEquals(200, res.getStatus());
    }

    @Test
    void validTokenRecordsAuthorizeCall() throws Exception {
        CoreSDKFilter filter = new CoreSDKFilter(allowSdk);

        MockHttpServletRequest req = new MockHttpServletRequest("POST", "/api/orders");
        req.addHeader("Authorization", "Bearer my-token");
        MockHttpServletResponse res   = new MockHttpServletResponse();
        MockFilterChain         chain = new MockFilterChain();

        filter.doFilter(req, res, chain);

        assertEquals(1, allowSdk.getAuthorizeCalls().size());
        assertTrue(allowSdk.getAuthorizeCalls().get(0).startsWith("my-token"));
    }

    // -----------------------------------------------------------------------
    // Missing token → 401 with application/problem+json
    // -----------------------------------------------------------------------

    @Test
    void missingTokenReturns401() throws Exception {
        CoreSDKFilter filter = new CoreSDKFilter(allowSdk);

        MockHttpServletRequest  req   = new MockHttpServletRequest("GET", "/api/users");
        MockHttpServletResponse res   = new MockHttpServletResponse();
        MockFilterChain         chain = new MockFilterChain();

        filter.doFilter(req, res, chain);

        assertEquals(401, res.getStatus());
        assertEquals("application/problem+json", res.getContentType());

        String body = res.getContentAsString();
        assertTrue(body.contains("\"status\":401"),
            "Response body must contain status 401, got: " + body);
        assertTrue(body.contains("Unauthorized"),
            "Response body must contain 'Unauthorized', got: " + body);
    }

    @Test
    void missingTokenBodyIsValidProblemJson() throws Exception {
        CoreSDKFilter filter = new CoreSDKFilter(allowSdk);

        MockHttpServletRequest  req   = new MockHttpServletRequest("GET", "/api/data");
        MockHttpServletResponse res   = new MockHttpServletResponse();
        MockFilterChain         chain = new MockFilterChain();

        filter.doFilter(req, res, chain);

        String body = res.getContentAsString();
        assertTrue(body.contains("\"type\""), "Problem detail must include 'type' field");
        assertTrue(body.contains("\"title\""), "Problem detail must include 'title' field");
        assertTrue(body.contains("https://coresdk.io/errors/unauthorized"),
            "Type URI must be the canonical unauthorized URI");
    }

    // -----------------------------------------------------------------------
    // Non-Bearer Authorization header → treated as missing token
    // -----------------------------------------------------------------------

    @Test
    void nonBearerAuthorizationReturns401() throws Exception {
        CoreSDKFilter filter = new CoreSDKFilter(allowSdk);

        MockHttpServletRequest req = new MockHttpServletRequest("GET", "/api/users");
        req.addHeader("Authorization", "Basic dXNlcjpwYXNz");
        MockHttpServletResponse res   = new MockHttpServletResponse();
        MockFilterChain         chain = new MockFilterChain();

        filter.doFilter(req, res, chain);

        assertEquals(401, res.getStatus());
        assertNull(chain.getRequest(), "Chain must NOT be called for non-Bearer auth");
    }

    // -----------------------------------------------------------------------
    // SDK denies request → 403 Forbidden
    // -----------------------------------------------------------------------

    @Test
    void deniedRequestReturns403() throws Exception {
        CoreSDKFilter filter = new CoreSDKFilter(denySdk);

        MockHttpServletRequest req = new MockHttpServletRequest("DELETE", "/api/admin");
        req.addHeader("Authorization", "Bearer admin-token");
        MockHttpServletResponse res   = new MockHttpServletResponse();
        MockFilterChain         chain = new MockFilterChain();

        filter.doFilter(req, res, chain);

        assertEquals(403, res.getStatus());
        assertEquals("application/problem+json", res.getContentType());

        String body = res.getContentAsString();
        assertTrue(body.contains("Forbidden"),
            "Response body must contain 'Forbidden', got: " + body);
        assertNull(chain.getRequest(), "Chain must NOT be called when request is denied");
    }

    // -----------------------------------------------------------------------
    // ClaimsContext is populated (and cleaned up) during chain execution
    // -----------------------------------------------------------------------

    @Test
    void claimsContextSetDuringChain() throws Exception {
        CoreSDKFilter filter = new CoreSDKFilter(allowSdk);

        MockHttpServletRequest req = new MockHttpServletRequest("GET", "/api/me");
        req.addHeader("Authorization", "Bearer ctx-token");
        MockHttpServletResponse res = new MockHttpServletResponse();

        // Use a custom chain that captures ClaimsContext inside the chain call
        final Claims[] captured = {null};
        MockFilterChain chain = new MockFilterChain() {
            @Override
            public void doFilter(jakarta.servlet.ServletRequest request,
                                 jakarta.servlet.ServletResponse response) {
                captured[0] = ClaimsContext.get();
            }
        };

        filter.doFilter(req, res, chain);

        assertNotNull(captured[0], "ClaimsContext must be populated during chain execution");
        assertEquals("test-user", captured[0].getSub());
    }

    @Test
    void claimsContextClearedAfterChain() throws Exception {
        CoreSDKFilter filter = new CoreSDKFilter(allowSdk);

        MockHttpServletRequest  req   = new MockHttpServletRequest("GET", "/api/me");
        req.addHeader("Authorization", "Bearer clear-token");
        MockHttpServletResponse res   = new MockHttpServletResponse();
        MockFilterChain         chain = new MockFilterChain();

        filter.doFilter(req, res, chain);

        assertNull(ClaimsContext.get(),
            "ClaimsContext must be cleared after filter chain completes");
    }
}
