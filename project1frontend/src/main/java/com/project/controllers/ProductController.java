package com.project.controllers;	
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.project.model.Category;
import com.project.model.Product;
import com.project.services.ProductService;

@Controller
public class ProductController {
	@Autowired
private ProductService productService;
	public ProductController(){
		System.out.println("ProductController bean is created");
	}
	
	//http://localhost:8080/project1frontend/all/getproducts 
	@RequestMapping(value="/all/getproducts")
	public ModelAndView getAllProducts(){
		List<Product> products=productService.getAllProducts();
		//1st parameter - logical view name - productlist
		//2nd parameter - model attribute name - refer it in jsp page
		//3rd parameter - model [data] list of products
		// /WEB-INF/views/productlist.jsp
		return new ModelAndView("productlist","productsAttr",products);
	}
	@RequestMapping(value="/all/getproduct/{id}")  //id is pathvariable  id=1, id=2, id=3
	//  all/getproduct/1  , all/getproduct/2 , all/getproduct/3...
	public ModelAndView getProduct(@PathVariable int id)
	{
		System.out.println("Product Id is " + id);
		Product product=productService.getProduct(id);
		System.out.println("Product is " + product );
		return new ModelAndView("productdetails","product",product);
			}
	
	@RequestMapping(value="/admin/deleteproduct/{id}")
	public String deleteProduct(@PathVariable int id,HttpServletRequest request) {
		productService.deleteProduct(id);
		String rootdirectory=request.getServletContext().getRealPath("/");
		System.out.println("Root Directory " + rootdirectory);
		//create a path
		Path paths=Paths.get(rootdirectory+"/WEB-INF/resources/images/"+id+".png");
		if(Files.exists(paths)){
			try {
				Files.delete(paths);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:/all/getproducts";
	}
	@RequestMapping(value="/admin/getproductform")
	public String getProductForm(Model model){//model to send data to the view
		//Two model attributes
		//product =new Product();
		//categories = List<Category>
		List<Category> categories=productService.getAllCategories();
		model.addAttribute("product",new Product());
		model.addAttribute("categories",categories);
		System.out.println("Size of category list " + categories.size());
		return "productform";
	}
	@RequestMapping(value="/admin/updateproductform/{id}")
	public ModelAndView getUpdateProductForm(@PathVariable int id,Model model){
		List<Category> categories=productService.getAllCategories();
		model.addAttribute("categories",categories);
		Product product=productService.getProduct(id);//select* from product_s180396 where id=?
		return new ModelAndView("updateproductform","product",product);
	}
	
	
	@RequestMapping(value="/admin/saveproduct")
	//product is the value entered by the user in the product form
	//validate productname is notempty, productdescription is notempty, min price is 1,min quantity is 1
	public String saveProduct(@Valid @ModelAttribute(name="product") Product product,BindingResult result,Model model,HttpServletRequest request){
		if(result.hasErrors()){//hasErrors return true if product details in not valid
			model.addAttribute("categories",productService.getAllCategories());
			return "productform";
		}
		System.out.println("New Product Details " + product);
		productService.saveProduct(product);
		MultipartFile prodImage=product.getImage();//image uploaded in the productform.jsp
		if(prodImage!=null && !prodImage.isEmpty()){
			//how to get rootdirectory
			String rootdirectory=request.getServletContext().getRealPath("/");
			System.out.println("Root Directory " + rootdirectory);
			//create a path
			Path paths=Paths.get(rootdirectory+"/WEB-INF/resources/images/"+product.getId()+".png");
			
				//it throws checked exception
				try {
					prodImage.transferTo(new File(paths.toString()));
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		return "redirect:/all/getproducts";
	}
	
	@RequestMapping(value="/admin/updateproduct")
	public String updateProduct(@Valid @ModelAttribute(name="product") Product product,BindingResult result,Model model,HttpServletRequest request){
		if(result.hasErrors()){
			model.addAttribute("categories",productService.getAllCategories());
			return "updateproductform";
		}
		System.out.println("New Product Details " + product);
		productService.updateProduct(product);
		
		MultipartFile prodImage=product.getImage();//image uploaded in the productform.jsp
		if(prodImage!=null && !prodImage.isEmpty()){
			//how to get rootdirectory
			String rootdirectory=request.getServletContext().getRealPath("/");
			System.out.println("Root Directory " + rootdirectory);
			//create a path
			Path paths=Paths.get(rootdirectory+"/WEB-INF/resources/images/"+product.getId()+".png");
			
				//it throws checked exception
				try {
					prodImage.transferTo(new File(paths.toString()));
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		return "redirect:/all/getproducts";
	}
	
	@RequestMapping(value="/all/searchbycategory")
	public String searchByCategory(@RequestParam String searchCondition,Model model){
		if(searchCondition.equals("All")){
			model.addAttribute("searchCondition","");
		}
		else
		model.addAttribute("searchCondition",searchCondition);
		List<Product> products=productService.getAllProducts();
		model.addAttribute("productsAttr",products);
		return "productlist";
	}
}
