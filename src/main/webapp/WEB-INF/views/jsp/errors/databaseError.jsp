<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="path" value="${pageContext.request.contextPath}" />


<html>
<head>
    <title>Извините, возникла системная ошибка.</title>
    <link rel="stylesheet" href="<spring:url value="/resources/css/error.css"/>">
    <link rel="stylesheet" href="<spring:url value="/resources/css/bootstrap.min.css"/>">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="error-template">
                <h1>
                    Упс!</h1>
                <h2>
                    Внутренняя ошибка системы</h2>
                <div class="error-details">
                    Попробуйте повторить действие, затем обратитесь в техподдержку
                </div>
                <div class="error-actions">
                    <a href="${path}/" class="btn btn-primary btn-lg"><span class="glyphicon glyphicon-home"></span>
                        На главную </a><a href="${path}/help" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-envelope"></span> Связаться с техподдержкой </a>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
