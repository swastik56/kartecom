package com.project.dao;

import com.project.model.CartItem;
import com.project.model.User;
public interface CartItemDao {
	void saveOrUpdateCartItem(CartItem cartItem);
	void removeCartItem(int cartItemId);
	void clearAllCartItems(String email);
    User getUser(String email);
	//CustomerOrder createOrder(User user);
    
}
