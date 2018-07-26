package com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.ProductDao;
import com.project.model.Category;
import com.project.model.Product;

@Service
public class ProductServiceImpl implements ProductService { 
	@Autowired
	private ProductDao productDao;
	public ProductServiceImpl(){
		System.out.println("ProductServiceImpl bean is Created");
	}
	public List<Product> getAllProducts() {
		return productDao.getAllProducts();
	}
	public Product getProduct(int id) {	
		//call dao 
		return productDao.getProduct(id);
	}
	public void deleteProduct(int id) {
		productDao.deleteProduct(id);
	}
	public void saveProduct(Product product) {
		productDao.saveOrUpdateProduct(product);
		
	}
	public void updateProduct(Product product) {
		productDao.saveOrUpdateProduct(product);		
	}
	public List<Category> getAllCategories() {
		return productDao.getAllCategories();
	}
}
