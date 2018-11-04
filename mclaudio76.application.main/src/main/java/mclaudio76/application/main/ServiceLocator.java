package mclaudio76.application.main;

import java.util.List;

import org.springframework.aop.support.AopUtils;
import org.springframework.context.annotation.Configuration;

import mclaudio76.application.core.annotations.AppService;
import mclaudio76.application.core.annotations.ServiceProvider;
import mclaudio76.application.core.multitenancy.TenantContext;
import mclaudio76.application.core.services.IProductService;
import mclaudio76.application.core.services.IStorageService;

@Configuration
public class ServiceLocator {
	
	
	@ServiceProvider
	public IProductService initProductService(List<IProductService> services) {
		return (IProductService) selectImplementation(services);
	}
	
	@ServiceProvider
	public IStorageService initStorageService(List<IStorageService> services) {
		return (IStorageService) selectImplementation(services);
	}
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Object selectImplementation(List<? extends Object> availImpls) {
		Object standardImpl = null;
		for(Object service : availImpls) {
			try {
				Class target = getActualUnderlyingServiceImplClass(service);
				if(target.isAnnotationPresent(AppService.class)) {
					AppService lsAnnot = (AppService) target.getAnnotation(AppService.class);
					if(lsAnnot.customFor().trim().equals(TenantContext.getTenantID())) {
						return service;
					}
					if(lsAnnot.customFor().trim().isEmpty()) {
						standardImpl = service;	
					}
				}
			}
			catch(Exception e) {
				System.err.println("Errore --> "+e.getMessage());
			}
		}
		return standardImpl;
	}
	
	
	@SuppressWarnings("rawtypes")
	private Class getActualUnderlyingServiceImplClass(Object cls) {
		if(AopUtils.isJdkDynamicProxy(cls)) {
			return AopUtils.getTargetClass(cls);
		}
		if(AopUtils.isCglibProxy(cls)) {
			return AopUtils.getTargetClass(cls);
		}
		return cls.getClass();
	}
	
	
}
