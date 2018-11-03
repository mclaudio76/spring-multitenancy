package mclaudio76.application.core.services;

import java.util.ArrayList;

import mclaudio76.application.core.entities.Product;

public interface IProductService {
	public ArrayList<Product> findByFilter(Product filter);
	public void saveProduct(Product item);
}
