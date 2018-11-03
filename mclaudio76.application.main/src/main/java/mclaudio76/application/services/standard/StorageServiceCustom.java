package mclaudio76.application.services.standard;

import mclaudio76.application.core.annotations.AppService;
import mclaudio76.application.core.entities.Product;
import mclaudio76.application.core.services.BaseService;
import mclaudio76.application.core.services.IStorageService;

@AppService(customFor="ACME")
public class StorageServiceCustom extends BaseService implements IStorageService{

	@Override
	public boolean isAvailable(Product p) {
		return false;
	}

}