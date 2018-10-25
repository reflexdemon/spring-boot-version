<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Home</title>
</head>
<body>
<div class="card">
    <div class="card-header">
        About <span class="badge">${buildInfo.artifact} ${buildInfo.version}</span>
    </div>
    <div class="card-body">
        <h5 class="card-title">Purpose</h5>
        <p class="card-text">This is a simple tool that actually lists all the spring boot versions and its dependencies.</p>
        <a href="https://github.com/reflexdemon/spring-boot-version" target="_blank" class="btn btn-primary">Github Project</a>
    </div>
</div>
</body>
</html>