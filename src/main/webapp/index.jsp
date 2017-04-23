<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="title">
      Краткосрочная аренда автомобилей в Иннополисе
    </jsp:attribute>
    <jsp:attribute name="stylecss">
        <%--<c:set var="context" value="${pageContext.request.contextPath}" />--%>
        <%--<link href="${context}/css/sticky-footer.css" rel="stylesheet">--%>
    </jsp:attribute>

    <jsp:attribute name="logaction">
        <c:set var="context" value="${pageContext.request.contextPath}"/>
        <form class="navbar-form navbar-right" action="${context}/login">
                <%--<div class="form-group">--%>
                <%--<input type="text" placeholder="Email" class="form-control">--%>
                <%--</div>--%>
                <%--<div class="form-group">--%>
                <%--<input type="password" placeholder="Password" class="form-control">--%>
                <%--</div>--%>
            <button type="submit" class="btn btn-success">Войти</button>
        </form>
        <form class="navbar-form navbar-right" action="${context}/signup">
            <button type="submit" class="btn btn-success">Зарегистрироваться</button>
        </form>
    </jsp:attribute>

    <jsp:body>
        <h1>Добро пожаловать!</h1>
        <h2>Авторизируйтесь для работы с системой</h2>
    </jsp:body>
</t:genericpage>