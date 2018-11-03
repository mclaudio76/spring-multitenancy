package mclaudio76.application.services.standard;

import org.springframework.beans.factory.annotation.Autowired;

import mclaudio76.application.core.annotations.AppService;
import mclaudio76.application.core.services.BaseService;
import mclaudio76.application.core.services.IStorageService;

@AppService
public class ProductServiceStd extends BaseService {

	@Autowired
	public ProductServiceStd(IStorageService storage) {
		logInfo("Created instance : "+this);
		logInfo(" Using storage "+storage);
	}
	
}
