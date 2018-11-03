package mclaudio76.application.core.multitenancy;

class TenantSpecificServiceImplementationKey {
	private String beanName   		= "";
	private String tenantIdentifier = "";
	
	TenantSpecificServiceImplementationKey(String beanName, String tenantID) {
		this.beanName 		   = beanName;
		this.tenantIdentifier  = tenantID;
	}

	@Override
	public String toString() {
		return beanName+"@"+tenantIdentifier;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((beanName == null) ? 0 : beanName.hashCode());
		result = prime * result + ((tenantIdentifier == null) ? 0 : tenantIdentifier.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TenantSpecificServiceImplementationKey other = (TenantSpecificServiceImplementationKey) obj;
		if (beanName == null) {
			if (other.beanName != null)
				return false;
		} else if (!beanName.equals(other.beanName))
			return false;
		if (tenantIdentifier == null) {
			if (other.tenantIdentifier != null)
				return false;
		} else if (!tenantIdentifier.equals(other.tenantIdentifier))
			return false;
		return true;
	}
	
	
}
