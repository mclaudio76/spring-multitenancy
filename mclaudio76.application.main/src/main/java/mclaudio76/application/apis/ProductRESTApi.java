package mclaudio76.application.apis;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mclaudio76.application.core.entities.Product;
import mclaudio76.application.core.services.IProductService;

@RestController
@RequestMapping("/product")
public class ProductRESTApi {
	
	@Autowired
	IProductService prodService;

	@RequestMapping(path="/list", method= {RequestMethod.GET})
    public List<Product> listProduct() {
		prodService.dumpInfo();
		ArrayList<Product> result = prodService.findByFilter(new Product());
	   return result;
    }
	
}
