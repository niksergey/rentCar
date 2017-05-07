<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="title">
      Войти :: Краткосрочная аренда автомобилей в Иннополисе
    </jsp:attribute>
    <jsp:attribute name="stylecss">
        <%--<c:set var="context" value="${pageContext.request.contextPath}" />--%>
        <link href="<c:url value="/resources/css/signin.css"/>" rel="stylesheet">
    </jsp:attribute>

    <jsp:attribute name="logaction">
    <c:set var="path" value="${pageContext.request.contextPath}"/>
    <form class="navbar-form navbar-right" action="${path}/signup">
        <button type="submit" class="btn btn-success">Зарегистрироваться</button>
    </form>
    </jsp:attribute>

    <jsp:body>
        <c:if test="${not empty msg}">
            <div class="alert alert-${css} alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert"
                        aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
                <strong>${msg}</strong>
            </div>
        </c:if>
        <div class="container">
            <form class="form-signin" action="${pageContext.request.contextPath}/login" method="post">
                <h2 class="form-signin-heading">Вход</h2>
                <label for="inputEmail" class="sr-only">Email address</label>
                <input type="text" id="inputEmail" class="form-control" placeholder="E-mail" name="email" required autofocus>
                <label for="inputPassword" class="sr-only">Password</label>
                <input type="password" id="inputPassword" class="form-control" placeholder="Пароль" name="password" required>
                <div class="checkbox">
                    <label>
                        <input type="checkbox" value="remember-me"> Запомнить меня
                    </label>
                </div>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>
            </form>
        </div> <!-- /container -->
    </jsp:body>
</t:genericpage>