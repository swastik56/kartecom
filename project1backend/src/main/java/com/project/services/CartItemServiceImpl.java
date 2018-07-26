package com.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.dao.CartItemDao;
import com.project.model.CartItem;
import com.project.model.User;

@Service
public class CartItemServiceImpl implements CartItemService{
	@Autowired
	private CartItemDao cartItemDao;
	
	public void saveOrUpdateCartItem(CartItem cartItem) {
		cartItemDao.saveOrUpdateCartItem(cartItem);
		
	}

	public void removeCartItem(int cartItemId) {
		cartItemDao.removeCartItem(cartItemId);
	}
   
	public User getUser(String email) {
		return cartItemDao.getUser(email);
	}

	@Override
	public void clearAllCartItems(String email)
	{
		cartItemDao.clearAllCartItems(email);
	}

}


