<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="header.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
body {
background-color:#32A1A0;
}
* {
    box-sizing: border-box;
}

/* Create two unequal columns that floats next to each other */
.column {
    float: left;
    padding: 10px;
    height: 700px; /* Should be removed. Only for demonstration */
}

.left {
  width: 65%;
}

.right {
  width: 35%;
}

/* Clear floats after the columns */
.row:after {
    content: "";
    display: table;
    clear: both;
}
</style>
<title>Insert title here</title>
<link rel="stylesheet" href='<c:url value="/resources/css/login.css"></c:url>' >
</head>
<body>
   	<h3 align="center">To Pick your favorites products Login below... </h3>
	<div class="row">
  <div class="column left" style="background-color:#aaa;">
  <img src='<c:url value="/resources/images/login.jpg"></c:url>' style="width:100%">
	</div>
	<div class="column right" style="background-color:#32A1A0;">
	<div id="login-box">
	
	<c:if test="${error!=null }">
<div  class="loginerror" > <!--  invalid credentials -->
<span >${error }</span>
</div>
</c:if>
<c:if test="${msg!=null }">
<div class="msg" ><!--  loggedout successfully -->
<span>${msg }</span>
</div>
</c:if>
	<form action="<c:url value='/j_spring_security_check'></c:url>" method="post">
		<table>
			<tr>
				<td>Enter Username</td>
				<td><input type="text" name="j_username"></td>
			</tr>
			<tr>
				<td>Enter Password</td>
				<td><input type="password" name="j_password"></td>
			</tr>
			<tr>
			<td><input type="submit" value="submit"></td>
			</tr>
		</table>
	</form>
	</div>
	 </div>
</div>
</body>
</html>