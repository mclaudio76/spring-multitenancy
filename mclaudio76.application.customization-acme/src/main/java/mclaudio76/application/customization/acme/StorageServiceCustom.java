package mclaudio76.application.customization.acme;

import org.springframework.beans.factory.annotation.Autowired;

import mclaudio76.application.core.annotations.AppService;
import mclaudio76.application.core.entities.Product;
import mclaudio76.application.core.services.BaseService;
import mclaudio76.application.core.services.IStorageService;
import mclaudio76.application.services.standard.StorageServiceStd;

@AppService(customFor="ACME")
public class StorageServiceCustom extends BaseService implements IStorageService {

	private StorageServiceStd std;
	
	@Autowired
	public void setStorageStd(StorageServiceStd std) {
		this.std = std;
	}
	
	@Override
	public boolean isAvailable(Product p) {
		logInfo("No product is available in profile ACME, Storage sevice STD  =  "+std);
		return false;
	}

}
