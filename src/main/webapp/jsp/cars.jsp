<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="title">
      Список авто :: Краткосрочная аренда автомобилей в Иннополисе
    </jsp:attribute>
    <jsp:attribute name="stylecss">
        <c:set var="path" value="${pageContext.request.contextPath}" />
        <link href="${path}/css/dashboard.css" rel="stylesheet">
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
            <h1 class="page-header">Доступные авто</h1>

        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>id</th>
                    <th>Марка</th>
                    <th>Модель</th>
                    <th>Год выпуска</th>
                    <th>Vin</th>
                    <th>Редактировать</th>
                    <th>Удалить</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.cars}" var="car">
                        <tr>
                            <td><c:out value="${car.getId()}"/></td>
                            <td><c:out value="${car.getCarModel().getManufacturer()}"/></td>
                            <td><c:out value="${car.getCarModel().getModel()}"/></td>
                            <td><c:out value="${car.getYear()}"/></td>
                            <td><c:out value="${car.getVin()}"/></td>
                            <td>
                                <form action="${path}/car/list">
                                    <input type="hidden" name="actionType" value="edit"/>
                                    <input type="hidden" name="carId" value="${car.getId()}"/>
                                    <button type="submit" class="btn btn-warning">Редактировать</button>
                                </form>
                            </td>
                            <td>
                                <form action="${path}/car/list" method="post">
                                    <input type="hidden" name="actionType" value="delete"/>
                                    <input type="hidden" name="carId" value="${car.getId()}"/>
                                    <button type="submit" class="btn btn-danger">Удалить</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        </div>
    </jsp:body>
</t:genericpage>