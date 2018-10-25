<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Home</title>
    <style type="text/css">
        .bootstrap-select .dropdown-toggle .filter-option-inner-inner {
            overflow: hidden;
            margin-top: -7px;
        }
    </style>
</head>
<body>
<div class="container">
    <form action="/compare" type="GET">
        <div class="card">
            <div class="card-body col-12">
                <span class="col-6 text-right">Please select a version</span>
                <span class="col-3">
                 <select class="selectpicker" data-live-search="true" id="firstVersion" name="firstVersion">
                    <c:forEach var="version" items="${versionInfo}">
                        <option ${firstVersion eq version? 'selected': '' } >${version}</option>
                    </c:forEach>
                </select>
            </span>
                <span class="col-3">
                <select class="selectpicker" data-live-search="true" id="secondVersion" name="secondVersion">
                    <c:forEach var="version" items="${versionInfo}">
                        <option ${secondVersion eq version? 'selected': '' } >${version}</option>
                    </c:forEach>
                </select>
            </span>
                <span class="col-6 text-left"> <button onclick="showDependencies()" class="btn-lg btn-primary">CompareDependencies
                </button></span>
            </div>
        </div>
    </form>

    <c:if test="${error != null}">
        <div class="alert alert-danger" role="alert">
                ${error}
        </div>
    </c:if>

    <c:if test="${compareResult != null}">
        <table class="table col-6">
            <tr>
                <th>
                    Group Id
                </th>
                <th>
                    Artifact Id
                </th>
                <th>
                        ${compareResult.firstBootVersion}
                </th>
                <th>
                        ${compareResult.secondBootVersion}
                </th>
                <th>
                        Comparison
                </th>
            </tr>
            <c:forEach var="item" items="${compareResult.artifacts}">
                <tr>
                    <td>${item.groupId}</td>
                    <td>${item.artifactId}</td>
                    <td>${item.firstArtifactVersion}</td>
                    <td>${item.secondArtifactVersion}</td>
                    <td>${item.change}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
<script type="application/javascript">
    function showDependencies() {

    }

</script>
</body>
</html>