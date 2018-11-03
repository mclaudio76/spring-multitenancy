package mclaudio76.application.customization.acme;

import mclaudio76.application.core.annotations.AppService;
import mclaudio76.application.core.entities.Product;
import mclaudio76.application.core.services.BaseService;
import mclaudio76.application.core.services.IStorageService;

@AppService(customFor="ACME")
public class StorageServiceCustom extends BaseService implements IStorageService{

	@Override
	public boolean isAvailable(Product p) {
		logInfo("No product is available in profile ACME ");
		return false;
	}

}
