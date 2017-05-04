<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <c:set var="path" value="${pageContext.request.contextPath}" />
    <spring:url value="/resources/css/error.css" var="coreCss" />
    <link href="${coreCss}" rel="stylesheet" />
    <link rel="stylesheet" href="<spring:url value="/resources/css/bootstrap.min.css"/>">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="error-template">
                <h1>
                    Извините!</h1>
                <h2>
                    Внутренняя ошибка системы</h2>
                <div class="error-details">
                    Sorry, an error has occured, Requested page not found!
                </div>
                <div class="error-actions">
                    <a href="${path}/main/webapp" class="btn btn-primary btn-lg"><span class="glyphicon glyphicon-home"></span>
                        На главную </a><a href="${path}/main/webapp" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-envelope"></span> Связаться с техподдержкой </a>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
