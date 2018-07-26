package com.project.dao;

import com.project.model.Customer;
import com.project.model.User;

public interface CustomerDao {
	void registerCustomer(Customer customer);
	boolean isEmailValid(String email);
	boolean isUsernameValid(String username);
	User getUser(String username);
}
