package com.project.controllers;

import java.security.Principal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.model.Cart;
import com.project.model.CartItem;
import com.project.model.Customer;
import com.project.model.Product;
import com.project.model.ShippingAddress;
import com.project.model.User;
import com.project.services.CartItemService;
import com.project.services.ProductService;

@Controller
public class CartItemController {
	@Autowired
	private CartItemService cartItemService;
		@Autowired
	private ProductService productService;
		
		@RequestMapping(value="/cart/addtocart/{id}")
		public String addToCart(@PathVariable int id,@AuthenticationPrincipal Principal principal, @RequestParam int requestedQuantity)
		{
		    String email=principal.getName(); //return the email id of the logged in user
			User user=cartItemService.getUser(email);
			Product product=productService.getProduct(id);
			List<CartItem> cartItems = user.getCartItems();
			//if the selected product already exists in the cartitem table, only update the quantity
			//New cartITem object need not be created.
			for(CartItem cartItem:cartItems)
			{
				if(cartItem.getProduct().getId()==id){
					cartItem.setQuantity(requestedQuantity);
					cartItem.setTotalPrice(requestedQuantity * product.getPrice());
					cartItemService.saveOrUpdateCartItem(cartItem);//update
					return "redirect:/cart/purchasedetails";
				}
			}
			
		    CartItem cartItem=new CartItem();
		    cartItem.setQuantity(requestedQuantity);
		    cartItem.setTotalPrice(requestedQuantity * product.getPrice());
		    cartItem.setUser(user);//FK user_email
		    cartItem.setProduct(product);//FK column product_id
		    cartItemService.saveOrUpdateCartItem(cartItem);
			return "redirect:/cart/purchasedetails";
		}
		
		
		
		@RequestMapping(value="/cart/purchasedetails")
		public String getPurchaseDetails(@AuthenticationPrincipal Principal principal,Model model){
			String email=principal.getName();
			User user=cartItemService.getUser(email);
			List<CartItem> cartItems=user.getCartItems();//list of cartitems/products
			model.addAttribute("cartitems",cartItems);
			return "cart";
		}
		
		
		
		@RequestMapping(value="/cart/deletecartitem/{cartItemId}")
		public String removeCartItem(@PathVariable int cartItemId){
			cartItemService.removeCartItem(cartItemId);
			return "redirect:/cart/purchasedetails";
		}
		@RequestMapping(value="/cart/clearcart")
	    public String clearCart(@AuthenticationPrincipal Principal principal)
		{
			cartItemService.clearAllCartItems(principal.getName());		//email	
			return "redirect:/cart/purchasedetails";
	    }
		
		@RequestMapping(value="/cart/checkout")
		public ModelAndView checkout(@AuthenticationPrincipal Principal principal,Model model)
		//checkout(@PathVariable int cartId,Model model){
		{
			ModelAndView modelAndView = new ModelAndView("checkout");
			User user=cartItemService.getUser(principal.getName());
			modelAndView.addObject("user", user);				
			return modelAndView;
		}
				
		
		 @RequestMapping(value="/cart/getcart")	
		 public String getCart(@AuthenticationPrincipal Principal principal,Model model) {
			 String username=principal.getName();
			 User user=cartItemService.getUser(username);
			// Customer customer=user.getCustomer();
		//	 Cart cart=customer.getCart();
			 List<CartItem>  obj1=user.getCartItems();
			 model.addAttribute("cartitems", obj1); 
			 return "cart";
		
		}
}

