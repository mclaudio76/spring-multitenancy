package mclaudio76.application.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Target({ElementType.METHOD,ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Bean
@Scope(value="tenant", proxyMode=ScopedProxyMode.TARGET_CLASS)
public @interface EntityManagerFactoryProvider {
	public String tenantID() default "";
}
