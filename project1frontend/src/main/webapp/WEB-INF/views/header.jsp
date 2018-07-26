<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page isELIgnored="false" %>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
     <%@ taglib uri= "http://www.springframework.org/tags/form" prefix="form" %>
      <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!--  
<link rel="stylesheet" href="http://localhost:8080/project1/frontend/resources/css/table.css">
<link rel="stylesheet" href="http://localhost:8080/project1/frontend/resources/css/navbar.css">
-->
<link rel="stylesheet" href='<c:url value="/resources/css/table.css"></c:url>' >
<link rel="stylesheet" href='<c:url value="/resources/css/navbar.css"></c:url>' >

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	 <script src="https://code.jquery.com/jquery-2.2.1.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>
	<link href="https://cdn.datatables.net/1.10.10/css/jquery.dataTables.min.css" rel="stylesheet"> 
  </head>
<body>
<nav class="navbar navbar-inverse" id="nav_bar">
		<div class="container-fluid">
		<button type="button" class="navbar-toggle collapsed" 
   data-toggle="collapse" data-target="#collapse-example" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
			<div class="navbar-header">
			<!-- mapping /resources/**  -> /resources/images/niit.jpg
			/WEB-INF/resources/images/niit.jpg
			url - http://localhost:8080/project1frontend/resources/images/niit.jpg
			 -->
				<a class="navbar-brand" href=""><img src='<c:url value="/resources/images/logoedited.jpg"></c:url>' alt="Choose&Pick" height="30px" width="80px"></a>
			</div>
			<div class="collapse navbar-collapse" id="collapse-example">
			<ul class="nav navbar-nav" id="links">	
				<li ><a href='<c:url value="/home"></c:url>'>Home</a></li>
				<!-- value is for DispatcherServlet
				DispatcherServlet using the value it has to find the handler method which can handle the request
				http://localhost:8080/project1frontend/all/getproducts -> DispatcherServlet -> /all/getproducts
				-> ProductController.getAllProducts() -> Service -> Dao -> Select * from product 
				
				controller returns ModelAndView [model is List<Product>,view is productlist]
				/WEB-INF/views/productlist.jsp
				 -->
				<li><a href='<c:url value="/all/getproducts"></c:url>'>Browse All Products</a></li>
			<security:authorize access="hasRole('ROLE_ADMIN')">
				<li><a href='<c:url value="/admin/getproductform"></c:url>'>Add Product</a></li>
		       </security:authorize>
		       
		       <security:authorize access="hasRole('ROLE_USER')">
			<li><a href="<c:url value='/cart/getcart'></c:url>">Cart</a></li>
			</security:authorize>
			
              <c:if test="${pageContext.request.userPrincipal.name==null }">
			<li><a href='<c:url value="/all/registrationform"></c:url>'>Sign Up</a></li>
			<li><a href='<c:url value="/login"></c:url>'>Sign In</a></li>
			</c:if>
			
			<c:if test="${pageContext.request.userPrincipal.name!=null }">
			<li><a href='<c:url value="/j_spring_security_logout"></c:url>'>logout</a></li>
			</c:if>
			</ul> 				
			<li class="dropdown">
			<a href="" class="dropdown-toggle" data-toggle="dropdown">Select by Category<b class="caret"></b></a>
			<ul class="dropdown-menu">
					<li>
					<!-- Request parameter
					parameter name is 'searchCondition' -->
					   <a href='<c:url value="/all/searchbycategory?searchCondition=Jewelries"></c:url>'>Jewelries</a>
					   <a href='<c:url value="/all/searchbycategory?searchCondition=Clothings"></c:url>'>Clothings</a>
					   <a href='<c:url value="/all/searchbycategory?searchCondition=Footwear"></c:url>'>Footwear</a>
					   <a href='<c:url value="/all/searchbycategory?searchCondition=Bags"></c:url>'>Bags</a>
					   <a href='<c:url value="/all/searchbycategory?searchCondition=Hair Accessories"></c:url>'>Hair Accessories</a>
					   <a href='<c:url value="/all/searchbycategory?searchCondition=Makeup Products"></c:url>'>Makeup Products</a>
					   <a href='<c:url value="/all/searchbycategory?searchCondition=Sunglasses"></c:url>'>Sunglasses</a>
					   <a href='<c:url value="/all/searchbycategory?searchCondition=All"></c:url>'>All</a>	  
                    </li>
			</ul>
			</li>
			</ul>
			</div>
		</div>
	</nav>
</body>
</html>
