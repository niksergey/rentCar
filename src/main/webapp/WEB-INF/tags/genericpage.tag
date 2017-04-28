<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@attribute name="title" fragment="true" %>
<%@attribute name="stylecss" fragment="true" %>
<%@attribute name="logaction" fragment="true" %>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title><jsp:invoke fragment="title"/></title>

    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="<c:url value="/resources/css/ie10-viewport-bug-workaround.css"/>" rel="stylesheet">
    <!-- Custom styles -->
    <link rel="stylesheet" href="<c:url value="/resources/css/dashboard-common.css"/>">
    <jsp:invoke fragment="stylecss"/>
</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">InnoRentCar</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <%--<form class="navbar-form navbar-right">--%>
            <%--<input type="text" class="form-control" placeholder="Search...">--%>
            <jsp:invoke fragment="logaction"/>
            <%--<ul class="nav navbar-nav navbar-right">--%>
                <%--<li><a href="#">Dashboard</a></li>--%>
                <%--<li><a href="#">Settings</a></li>--%>
                <%--<li><a href="#">Profile</a></li>--%>
                <%--<li><a href="#">Help</a></li>--%>
            <%--</ul>--%>

        </div>
    </div>
</nav>

<div class="container-fluid">
<jsp:doBody/>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="<c:url value="/resources/js/jquery.min.js"/>"><\/script>')</script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="<c:url value="/resources/js/ie10-viewport-bug-workaround.js"/>"></script>
</body>
</html>