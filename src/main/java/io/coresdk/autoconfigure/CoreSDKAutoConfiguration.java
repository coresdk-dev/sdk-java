package io.coresdk.autoconfigure;

import io.coresdk.CoreSDK;
import io.coresdk.CoreSDKConfig;
import io.coresdk.web.CoreSDKFilter;
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
