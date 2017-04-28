<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="title">
      Список аренд :: Краткосрочная аренда автомобилей в Иннополисе
    </jsp:attribute>
    <jsp:attribute name="stylecss">
        <c:set var="path" value="${pageContext.request.contextPath}" />
        <link href="<c:url value="/resources/css/dashboard.css"/>" rel="stylesheet">
    </jsp:attribute>

    <jsp:attribute name="logaction">
            <form class="navbar-form navbar-right" action="${pageContext.request.contextPath}/signin" method="post">
                <input type="hidden" name="currentSession" value="delete"/>
                <button type="submit" class="btn btn-success">Выйти</button>
            </form>
    </jsp:attribute>

    <jsp:body>
        <t:lksidebar/>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">Список аренд</h1>

            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>id</th>
                        <th>Пользователь</th>
                        <th>Марка</th>
                        <th>Модель</th>
                        <th>Старт аренды</th>
                        <th>Завершение аренды</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.rents}" var="car">
                        <tr>
                            <td><c:out value="${car.getId()}"/></td>
                            <td><c:out value="${car.getLeaser().getEmail()}"/></td>
                            <td><c:out value="${car.getCar().getCarModel().getManufacturer()}"/></td>
                            <td><c:out value="${car.getCar().getCarModel().getModel()}"/></td>
                            <td><c:out value="${car.getStartTime()}"/></td>
                            <td><c:out value="${car.getFinishDate()}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </jsp:body>
</t:genericpage>