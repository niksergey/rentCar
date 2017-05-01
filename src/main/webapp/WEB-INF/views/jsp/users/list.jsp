<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="title">
      Список авто
    </jsp:attribute>
    <jsp:attribute name="stylecss">
        <link href="<c:url value="/resources/css/dashboard.css"/>" rel="stylesheet">
    </jsp:attribute>

    <jsp:attribute name="logaction">
            <form class="navbar-form navbar-right" action="/logout" method="post">
                <button type="submit" class="btn btn-success">Выйти</button>
            </form>
    </jsp:attribute>

    <jsp:body>
        <t:lksidebar/>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">Пользователи</h1>
            <c:if test="${not empty msg}">
                <div class="alert alert-${css} alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <strong>${msg}</strong>
                </div>
            </c:if>
        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>id</th>
                    <th>Фамилия</th>
                    <th>Имя</th>
                    <th>Отчество</th>
                    <th>Телефон</th>
                    <th>Админ</th>
                    <th>Активен</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.users}" var="car">
                        <tr>
                            <td><c:out value="${car.id}"/></td>
                            <td><c:out value="${car.getFirstName()}"/></td>
                            <td><c:out value="${car.getSecondName()}"/></td>
                            <td><c:out value="${car.getLastName()}"/></td>
                            <td><c:out value="${car.getPhoneNumber()}"/></td>
                            <td><c:out value="${car.isAdminFlag()}"/></td>
                            <td><c:out value="${car.isActiveFlag()}"/></td>
                            <td>
                                <form action="${path}/users/${car.getId()}/update">
                                    <button type="submit" class="btn btn-warning">Редактировать</button>
                                </form>
                            </td>
                            <td>
                                <form action="${path}/users/${car.getId()}/delete" method="post">
                                    <button type="submit" class="btn btn-danger">Удалить</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
            <form action="${path}/users/add">
                <button type="submit" class="btn btn-primary">Добавить</button>
            </form>
        </div>
    </jsp:body>
</t:genericpage>