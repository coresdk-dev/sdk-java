# CoreSDK — Java Spring Boot Starter

Auto-configures CoreSDK for Spring Boot 3.x applications.

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

## Auto-wired beans

| Bean | Type | Description |
|------|------|-------------|
| `coreSDK` | `CoreSDK` | Pre-configured SDK client |
| `coreSDKFilter` | `FilterRegistrationBean` | JWT filter on `/api/*` |
