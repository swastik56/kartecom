package com.project.services;
import java.util.List;

import com.project.model.Category;
import com.project.model.Product;

public interface ProductService {
	List<Product> getAllProducts();
	Product getProduct(int id);
	void deleteProduct(int id);
	void saveProduct(Product product);
	void updateProduct(Product product);
	List<Category> getAllCategories();	
}
