<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error/Exception</title>
</head>

<body>

<% String errorMessageType=((String)request.getAttribute("errorMessageType"));%>
<% String pageType=((String)request.getAttribute("pageType"));%>

<fmt:setLocale value="en" scope="session"/>
<fmt:setBundle basename="bundles.messages" scope="session" /> 
<fmt:message key="${errorMessageType}" var="errorMessage" ></fmt:message> 

 <script>
     alert("${errorMessage}");
     window.location.href = "/Orianteed_Project/${pageType}";
</script> 


</body>
</html>