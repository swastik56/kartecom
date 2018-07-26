<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="header.jsp" %>
<%@ page isELIgnored="false" %>
<jsp:useBean id="now" class="java.util.Date" />
<html>

<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
        <h1>Thank you for Shopping with us</h1>
                   </div>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 

                            <h1>Receipt</h1>
                     
 <strong>Shipping Address</strong><br/>
${user.customer.shippingaddress.apartmentnumber},<br>
${user.customer.shippingaddress.streetname},<br>
${user.customer.shippingaddress.city},<br>
${user.customer.shippingaddress.state},<br>
${user.customer.shippingaddress.zipcode}<br>
<br>
   
                 
                    
<c:forEach items="${user.cartItems}" var="cartItem">

<th>Product </th><td>${cartItem.product.productname }</td><br>
<th>Quantity </th><td>${cartItem.quantity }</td><br>
<th>Price </th><td>${cartItem.totalPrice }</td><br>
</tr>

<!--  grandTotal = cartItem.totalPrice + grandTotal -->

<c:set var="grandTotal" value="${grandTotal + cartItem.totalPrice }"></c:set>

</c:forEach>  
               
<h3>Total Price : ${grandTotal } </h3><br>                       
<h3><a href= "<c:url value="/home"></c:url>">back to home </h3>

</body>
</html>