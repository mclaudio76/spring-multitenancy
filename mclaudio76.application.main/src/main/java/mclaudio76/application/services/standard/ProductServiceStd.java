package mclaudio76.application.services.standard;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import mclaudio76.application.core.annotations.AppService;
import mclaudio76.application.core.entities.Product;
import mclaudio76.application.core.services.BaseService;
import mclaudio76.application.core.services.IProductService;
import mclaudio76.application.core.services.IStorageService;

@AppService
public class ProductServiceStd extends BaseService implements IProductService {

	private IStorageService storageService = null;
	
	@PersistenceContext
	EntityManager em;
	
	@Autowired
	public ProductServiceStd(IStorageService storage) {
		this.storageService = storage;
	}
	
	@Override
	@Transactional
	public ArrayList<Product> findByFilter(Product filter) {
		
		ArrayList<Product> pList = new ArrayList<>();
		for(int x = 0;  x < 5; x++) {
			storageService.isAvailable(filter);
		}
		if(storageService.isAvailable(filter)) {
			Product p1 = new Product();
			p1.productID = "00001";
			p1.description = "Description 0001";
			Product p2 = new Product();
			p2.productID = "00001";
			p2.description = "Description 0001";
			pList.add(p1);
			pList.add(p2);
		}
		return pList;
	}

	@Transactional
	public void saveProduct(Product item) {
		em.persist(em.contains(item) ? item : em.merge(item));
	}



	@Override
	public void dumpInfo() {
		logInfo("Created instance : "+this);
		logInfo(" Using storage "+this.storageService);
	}
	
}
