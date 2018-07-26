package com.project.dao;
import java.util.List;


import com.project.model.Category;
import com.project.model.Product;
public interface ProductDao {
	List<Product> getAllProducts();
	Product getProduct(int id);
	void deleteProduct(int id);
	void saveOrUpdateProduct(Product product);
	List<Category> getAllCategories();
	}
