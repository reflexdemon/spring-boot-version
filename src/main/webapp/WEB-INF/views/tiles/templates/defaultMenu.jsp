<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <a class="navbar-brand" href="#"><img src="<c:url value='/static/img/springboot.png' />" alt="Spring Boot Imgae" width="25" height="25"> BootTree</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarColor01">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item ${viewName == 'home' ? 'active' : ''}">
                <a class="nav-link" href="${pageContext.request.contextPath}/">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item ${viewName == 'dependency' ? 'active' : ''}">
                <a class="nav-link" href="${pageContext.request.contextPath}/dependency">Dependency</a>
            </li>
            <li class="nav-item ${viewName == 'about' ? 'active' : ''}">
                <a class="nav-link" href="${pageContext.request.contextPath}/about">About</a>
            </li>

        </ul>
        <%--<form class="form-inline my-2 my-lg-0">--%>
            <%--<input class="form-control mr-sm-2" placeholder="Search" type="text">--%>
            <%--<button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button>--%>
        <%--</form>--%>
    </div>
</nav>