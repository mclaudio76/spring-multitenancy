package mclaudio76.application.core.multitenancy;

public class TenantContext {
	  
	  private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();
	  private static final String DEF_CONTEXT 		   = "";
	  
	  public static void setTenantID(String tenantId) {
	    CONTEXT.set(tenantId);
	  }
	
	  public static String getTenantID() {
	    return CONTEXT.get() == null ? DEF_CONTEXT : CONTEXT.get();
	  }
	
	  public static void clear() {
	    CONTEXT.remove();
	  }
}