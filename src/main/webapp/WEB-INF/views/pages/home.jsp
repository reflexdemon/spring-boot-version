<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Home</title>
</head>
<body>
<div class="jumbotron jumbotron-fluid">
    <div class="container">
        <img src="<c:url value='/static/img/springboot.png' />" alt="Spring Boot Imgae">
        <h1 class="display-4">Boot Tree</h1>
        <p class="lead">

            This is a simple tool to list all the dependencies for the Spring Boot project...</p>
    </div>
</div>

<ul class="column-splitter">
    <c:forEach var="version" items="${versionInfo}">
        <li><a href="${pageContext.request.contextPath}/dependency/${version}">${version}</a></li>
    </c:forEach>
</ul>
</body>
</html>