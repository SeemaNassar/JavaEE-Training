<%@ page import="java.util.*"%>
<%@ page import="model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	UserModel auth = (UserModel) request.getSession().getAttribute("auth");
	if (auth != null) {
	    request.setAttribute("person", auth);
	}
	String CategorySelect = (String) request.getAttribute("CategorySelect");
	@SuppressWarnings("unchecked")
	List<ProductModel> product = (List<ProductModel>) request.getAttribute("productList");

%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/includes/head.jsp" %>
<meta charset="UTF-8">
<title>Products</title>

<style>
	.imgs1{
	        margin-right: 10%;
	        width: 100%;
	        display:block;
	}
	.imgs2{
	   		width: 100%;
	        display: none;
	}
	
	.imgContainer:hover .imgs2{
	        display:block;
	}
	.imgContainer:hover .imgs1{
	        display:none;
	}
	.overlay {
	  opacity: 0;
	  transition: .5s ease;
	}
	
	.imgContainer:hover .overlay {
	  opacity: 1;
	}
	.cn {
	  color: red;
	}
	
</style>
</head>
<body>
<%@ include file="/includes/navbar.jsp" %>
	<div class="container">
		<div class="card-header my-3"><%= CategorySelect.toUpperCase() %> </div>
		<div class="row">
			<%
			if (!product.isEmpty()) {
				for (ProductModel p : product) { 
					
			%>
			<div class="col-md-3 my-3">
				<div class="card w-100 ">
					<div class="imgContainer">
				   		<img class="card-img-top imgs1 " src="<%= p.getUrlImgs().get(0) %>"alt="Card image cap">
				 		<div class="overlay">
				 			<img class="card-img-top imgs2" src="<%= p.getUrlImgs().get(1) %>"alt="Card image cap">	
				 		</div> 
				 	</div>
					<div class="card-body">
						<h5 class="card-title"><%= p.getName() %></h5>
						<h6 class="price">Price: $<%= p.getPrice() %></h6>
						<h6 class="category cn" >Code number: <%= p.getPartNumber() %></h6>
						<div class="mt-3 d-flex justify-content-between">
							<a class="btn btn-dark" href="add-to-cart?id=<%= p.getId() %>">Add to Cart</a> 
							<a class="btn btn-primary" href="order-now?quantity=1&id=<%= p.getId() %>">Buy Now</a>
						</div>
					</div>
				</div>
			</div>
			<%
				}
			} else {
			out.println("There is no proucts");
			}
			%>
		</div>
	</div>
</body>
</html>