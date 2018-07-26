package com.project.model;
import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import javassist.expr.Cast;
@Entity
@Table(name="customer_s180396")
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private int id;
private String firstname;
private String lastname;
private String phonenumber;
@OneToOne(cascade=CascadeType.ALL)
private User user;
@OneToOne(cascade=CascadeType.ALL)
private BillingAddress billingaddress;
@OneToOne(cascade=CascadeType.ALL)
private ShippingAddress shippingaddress;
@OneToOne(cascade=CascadeType.ALL)
private Cart cart;//FK cart_id
public Customer(){
	System.out.println("Customer Object is created");
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getFirstname() {
	return firstname;
}
public void setFirstname(String firstname) {
	this.firstname = firstname;
}
public String getLastname() {
	return lastname;
}
public void setLastname(String lastname) {
	this.lastname = lastname;
}
public String getPhonenumber() {
	return phonenumber;
}
public void setPhonenumber(String phonenumber) {
	this.phonenumber = phonenumber;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public BillingAddress getBillingaddress() {
	return billingaddress;
}
public void setBillingaddress(BillingAddress billingaddress) {
	this.billingaddress = billingaddress;
}
public ShippingAddress getShippingaddress() {
	return shippingaddress;
}
public void setShippingaddress(ShippingAddress shippingaddress) {
	this.shippingaddress = shippingaddress;
}
public Cart getCart() {
	return cart;
}
public void setCart(Cart cart) {
	this.cart = cart;
}

}
