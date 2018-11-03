package mclaudio76.application.core.multitenancy;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

public class TenantBasedScope implements Scope {
	
	private Map<TenantSpecificServiceImplementationKey, Object> tenantScope = Collections.synchronizedMap(new HashMap<>());
	private Map<String, Runnable> destructionCallbacks = Collections.synchronizedMap(new HashMap<String, Runnable>());
	
	@Override
	public Object get(String name, ObjectFactory<?> objectFactory) {
		TenantSpecificServiceImplementationKey impl = new TenantSpecificServiceImplementationKey(name, TenantContext.getTenantID());
		System.err.println("Entering TenantScope::get object for Implementation "+impl);
		Map<TenantSpecificServiceImplementationKey, Object> scope = tenantScope;
        Object object = scope.get(impl);
        if (object == null) {
            object = objectFactory.getObject();
            scope.put(impl, object);
        }
        System.err.println(">>> "+object);
        return object;
	}

	@Override
	public Object remove(String name) {
		TenantSpecificServiceImplementationKey impl = new TenantSpecificServiceImplementationKey(name, TenantContext.getTenantID());
		return tenantScope.remove(impl);	
	}

	@Override
	public void registerDestructionCallback(String name, Runnable callback) {
		destructionCallbacks.put(name, callback);
	}

	@Override
	public Object resolveContextualObject(String key) {
		return null;
	}

	@Override
	public String getConversationId() {
		  return "tenant";
	}

}
