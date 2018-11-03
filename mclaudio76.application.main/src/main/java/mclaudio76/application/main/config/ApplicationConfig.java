package mclaudio76.application.main.config;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import mclaudio76.application.core.multitenancy.TenantBasedScopeBeanPostProcessor;

@Configuration
public class ApplicationConfig {

	@Bean
    public static BeanFactoryPostProcessor beanFactoryPostProcessor() {
        return new TenantBasedScopeBeanPostProcessor();
    }
}
