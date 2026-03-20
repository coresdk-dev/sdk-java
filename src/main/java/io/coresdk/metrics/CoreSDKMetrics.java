package io.coresdk.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.binder.MeterBinder;

/**
 * Micrometer {@link MeterBinder} that registers CoreSDK operational metrics.
 *
 * <p>Meters registered:
 * <ul>
 *   <li>{@code coresdk.authorize.duration} — Timer; tags: {@code outcome} (allow/deny/fail_open)</li>
 *   <li>{@code coresdk.policy.duration} — Timer; tags: {@code rule}, {@code outcome} (allow/deny/fail_open)</li>
 *   <li>{@code coresdk.authorize.errors} — Counter; tags: {@code type} (network/timeout)</li>
 *   <li>{@code coresdk.policy.errors} — Counter</li>
 * </ul>
 *
 * <p>Register via Spring Boot auto-configuration or manually:
 * <pre>{@code
 *   new CoreSDKMetrics().bindTo(meterRegistry);
 * }</pre>
 */
public class CoreSDKMetrics implements MeterBinder {

    @Override
    public void bindTo(MeterRegistry registry) {
        // Pre-register outcome variants so dashboards show series from startup
        for (String outcome : new String[]{"allow", "deny", "fail_open"}) {
            Timer.builder("coresdk.authorize.duration")
                .description("Duration of CoreSDK authorize() calls")
                .tag("outcome", outcome)
                .register(registry);

            Timer.builder("coresdk.policy.duration")
                .description("Duration of CoreSDK evaluatePolicy() calls")
                .tag("rule", "")
                .tag("outcome", outcome)
                .register(registry);
        }

        for (String type : new String[]{"network", "timeout"}) {
            Counter.builder("coresdk.authorize.errors")
                .description("Number of CoreSDK authorize() errors")
                .tag("type", type)
                .register(registry);
        }

        Counter.builder("coresdk.policy.errors")
            .description("Number of CoreSDK evaluatePolicy() errors")
            .register(registry);
    }

    // -------------------------------------------------------------------------
    // Timer.Sample factories — used by CoreSDK instrumentation
    // -------------------------------------------------------------------------

    public static Timer.Sample startSample(MeterRegistry registry) {
        return Timer.start(registry);
    }

    public static void recordAuthorize(MeterRegistry registry, Timer.Sample sample, String outcome) {
        sample.stop(Timer.builder("coresdk.authorize.duration")
            .tag("outcome", outcome)
            .register(registry));
    }

    public static void recordPolicy(MeterRegistry registry, Timer.Sample sample,
                                    String rule, String outcome) {
        sample.stop(Timer.builder("coresdk.policy.duration")
            .tag("rule", rule != null ? rule : "")
            .tag("outcome", outcome)
            .register(registry));
    }

    public static void incrementAuthorizeError(MeterRegistry registry, String type) {
        Counter.builder("coresdk.authorize.errors")
            .tag("type", type)
            .register(registry)
            .increment();
    }

    public static void incrementPolicyError(MeterRegistry registry) {
        Counter.builder("coresdk.policy.errors")
            .register(registry)
            .increment();
    }
}
