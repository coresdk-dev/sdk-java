package io.coresdk.autoconfigure;

import io.coresdk.CoreSDK;
import io.coresdk.CoreSDKConfig;
import io.coresdk.metrics.CoreSDKMetrics;
import io.coresdk.tracing.PIIMaskingSpanProcessor;
import io.coresdk.web.CoreSDKFilter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import io.opentelemetry.sdk.trace.SpanProcessor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@EnableConfigurationProperties(CoreSDKConfig.class)
public class CoreSDKAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public CoreSDK coreSDK(CoreSDKConfig config) {
        return new CoreSDK(config);
    }

    @Bean
    @ConditionalOnClass(MeterRegistry.class)
    @ConditionalOnMissingBean(CoreSDKMetrics.class)
    public MeterBinder coreSDKMetrics(CoreSDK sdk, MeterRegistry registry) {
        sdk.setMeterRegistry(registry);
        return new CoreSDKMetrics();
    }

    @Bean
    @ConditionalOnClass(SpanProcessor.class)
    @ConditionalOnMissingBean(PIIMaskingSpanProcessor.class)
    public PIIMaskingSpanProcessor piiMaskingSpanProcessor() {
        return PIIMaskingSpanProcessor.create();
    }

    @Bean
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
    @ConditionalOnClass(name = "jakarta.servlet.Filter")
    public FilterRegistrationBean<CoreSDKFilter> coreSDKFilter(CoreSDK sdk) {
        FilterRegistrationBean<CoreSDKFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new CoreSDKFilter(sdk));
        registration.addUrlPatterns("/api/*");
        registration.setOrder(10);
        return registration;
    }
}
