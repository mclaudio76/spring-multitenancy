package mclaudio76.application.services.standard;

import mclaudio76.application.core.annotations.AppService;
import mclaudio76.application.core.entities.Product;
import mclaudio76.application.core.services.BaseService;
import mclaudio76.application.core.services.IStorageService;

@AppService
public class StorageServiceStd extends BaseService implements IStorageService {

	@Override
	public boolean isAvailable(Product p) {
		return true;
	}
	
}
