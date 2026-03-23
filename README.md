# CoreSDK — Java Spring Boot Starter

Auto-configures CoreSDK for Spring Boot 3.x applications.

**New here?** The [Getting Started guide](GETTING-STARTED.md) takes you from zero to a working sidecar + SDK call in 15 minutes, with a **why** explanation at every step.

## Maven

```xml
<dependency>
  <groupId>io.coresdk</groupId>
  <artifactId>coresdk-spring-boot-starter</artifactId>
  <version>0.1.0</version>
</dependency>
```

## Gradle

```kotlin
implementation("io.coresdk:coresdk-spring-boot-starter:0.1.0")
```

## Configuration (application.yml)

```yaml
coresdk:
  endpoint: http://127.0.0.1:50051
  tenant-id: ${CORESDK_TENANT_ID}
  fail-mode: open
```

## Transport Note — Important

> **The Java SDK currently uses HTTP REST transport (calls `:8080` control plane), not the gRPC sidecar.**
>
> This means the following sidecar features are **not active** for Java services:
> - JWT signature verification via JWK cache
> - Token revocation list (`.is_revoked()` not available)
> - Sidecar-side rate limiting
> - Hash-chained audit pipeline
> - Fail-mode semantics of the sidecar
>
> `AuthServiceGrpc.java` exists in the codebase and the gRPC path is planned. Track progress at [core-sdk#grpc-java](https://github.com/coresdk-dev/core-sdk/issues).
>
> For production Java services requiring sidecar guarantees, proxy auth calls through a Python or Go sidecar service until the gRPC path is wired.

## Authorize requests

```java
@Autowired CoreSDK sdk;

CompletableFuture<AuthDecision> decision = sdk.authorize(token, "/orders", "GET");
decision.thenAccept(d -> {
    if (d.isAllowed()) {
        System.out.println("Allowed for " + d.getClaims().getSub());
    }
});
```

## Usage

```java
@RestController
public class InvoiceController {

    @GetMapping("/api/invoices")
    public List<Invoice> list() {
        Claims claims = ClaimsContext.get();
        // claims.getTenantId() — already scoped to correct tenant
        return invoiceService.findByTenant(claims.getTenantId());
    }
}
```

## mTLS

To enable mutual TLS between your application and the sidecar, set the following environment variables (or Spring properties):

| Variable | Spring property | Description |
|----------|----------------|-------------|
| `CORESDK_TLS_CERT` | `coresdk.tls.cert` | Path to the client certificate (PEM) |
| `CORESDK_TLS_KEY` | `coresdk.tls.key` | Path to the client private key (PEM) |
| `CORESDK_TLS_CA` | `coresdk.tls.ca` | Path to the CA certificate (PEM) |

```bash
export CORESDK_TLS_CERT=/path/to/client.crt
export CORESDK_TLS_KEY=/path/to/client.key
export CORESDK_TLS_CA=/path/to/ca.crt
```

> **Note:** The Java SDK currently uses HTTP REST transport. mTLS support via gRPC (`AuthServiceGrpc.java`) is present but not yet wired. See the [core-sdk README](https://github.com/coresdk-dev/core-sdk#mtls-configuration) for certificate generation instructions.

## Auto-wired beans

| Bean | Type | Description |
|------|------|-------------|
| `coreSDK` | `CoreSDK` | Pre-configured SDK client |
| `coreSDKFilter` | `FilterRegistrationBean` | JWT filter on `/api/*` |
