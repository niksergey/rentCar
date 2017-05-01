<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="title">
      Список моделей :: Краткосрочная аренда автомобилей в Иннополисе
    </jsp:attribute>
    <jsp:attribute name="stylecss">
        <link href="<c:url value="/resources/css/dashboard.css"/>" rel="stylesheet">
    </jsp:attribute>

    <jsp:attribute name="logaction">
            <form class="navbar-form navbar-right" action="${pageContext.request.contextPath}/logout" method="post">
                <button type="submit" class="btn btn-success">Выйти</button>
            </form>
    </jsp:attribute>

    <jsp:body>
        <t:lksidebar/>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">Модельный ряд ${sessionScope.userEmail}</h1>

            <div class="table-responsive">
                <table class="table table-striped">
                    <%--int id, String manufacturer, String model, String gear, int power--%>
                    <thead>
                    <tr>
                        <th>id</th>
                        <th>Марка</th>
                        <th>Модель</th>
                        <th>Мощность</th>
                        <th>Коробка передач</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.carModels}" var="user">
                        <tr>
                            <td><c:out value="${user.getId()}"/></td>
                            <td><c:out value="${user.getManufacturer()}"/></td>
                            <td><c:out value="${user.getModel()}"/></td>
                            <td><c:out value="${user.getPower()}"/></td>
                            <td><c:out value="${user.getGear()}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </jsp:body>
</t:genericpage>