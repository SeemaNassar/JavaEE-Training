<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.*"%>
<%@page import="java.util.*"%>
<%
UserModel auth = (UserModel) request.getSession().getAttribute("auth");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ProfileWithName</title>
</head>
<body>
<% 
	if (auth != null) {
%>

	<h1><% out.print((String)request.getAttribute("UserType1"));%> Page:</h1>
	<h1>Welcome <% out.print((String)request.getAttribute("UserName1"));%></h1>

<% 
	} 
%>
<a href="LogoutServlet">Logout</a>  
</body>
</html>