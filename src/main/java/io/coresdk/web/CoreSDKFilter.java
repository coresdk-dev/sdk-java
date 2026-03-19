package io.coresdk.web;

import io.coresdk.AuthDecision;
import io.coresdk.CoreSDK;
import io.coresdk.ClaimsContext;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class CoreSDKFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(CoreSDKFilter.class);
    private final CoreSDK sdk;

    public CoreSDKFilter(CoreSDK sdk) {
        this.sdk = sdk;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String authHeader = req.getHeader("Authorization");
        String token = (authHeader != null && authHeader.startsWith("Bearer "))
            ? authHeader.substring(7) : "";

        if (token.isEmpty()) {
            sendProblem(res, 401, "https://coresdk.io/errors/unauthorized", "Unauthorized", "Missing Bearer token");
            return;
        }

        try {
            AuthDecision decision = sdk.authorize(token, req.getRequestURI(), req.getMethod()).get();
            if (!decision.isAllowed()) {
                sendProblem(res, 403, "https://coresdk.io/errors/forbidden", "Forbidden", null);
                return;
            }
            ClaimsContext.set(decision.getClaims());
            try {
                chain.doFilter(req, res);
            } finally {
                ClaimsContext.clear();
            }
        } catch (Exception e) {
            log.error("[coresdk] filter error: {}", e.getMessage());
            sendProblem(res, 500, "https://coresdk.io/errors/internal", "Internal Server Error", null);
        }
    }

    private void sendProblem(HttpServletResponse res, int status, String type, String title, String detail)
            throws IOException {
        res.setStatus(status);
        res.setContentType("application/problem+json");
        String body = String.format(
            "{\"type\":\"%s\",\"title\":\"%s\",\"status\":%d%s}",
            type, title, status,
            detail != null ? ",\"detail\":\"" + detail + "\"" : ""
        );
        res.getWriter().write(body);
    }
}
