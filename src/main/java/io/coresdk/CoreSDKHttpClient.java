package io.coresdk;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

/**
 * An HTTP client wrapper that checks outbound URLs against the CoreSDK SSRF firewall
 * before allowing each request to proceed.
 *
 * <p>Usage:
 * <pre>{@code
 * CoreSDKHttpClient client = new CoreSDKHttpClient(sdk);
 * HttpResponse<String> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).join();
 * }</pre>
 */
public class CoreSDKHttpClient {
    private final CoreSDK sdk;
    private final HttpClient inner;

    public CoreSDKHttpClient(CoreSDK sdk) {
        this.sdk = sdk;
        this.inner = HttpClient.newHttpClient();
    }

    public CoreSDKHttpClient(CoreSDK sdk, HttpClient inner) {
        this.sdk = sdk;
        this.inner = inner;
    }

    /**
     * Sends the request asynchronously after verifying the target URL with the CoreSDK
     * egress firewall. Throws {@link SecurityException} if the URL is blocked.
     */
    public <T> CompletableFuture<HttpResponse<T>> sendAsync(
            HttpRequest request, HttpResponse.BodyHandler<T> bodyHandler) {
        String url = request.uri().toString();
        return sdk.checkEgress(url)
            .thenCompose(decision -> {
                if (!decision.isAllowed()) {
                    CompletableFuture<HttpResponse<T>> failed = new CompletableFuture<>();
                    failed.completeExceptionally(
                        new SecurityException("CoreSDK SSRF firewall blocked " + url + ": " + decision.getReason())
                    );
                    return failed;
                }
                return inner.sendAsync(request, bodyHandler);
            });
    }
}
