<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="title">
      Список авто
    </jsp:attribute>
    <jsp:attribute name="stylecss">
                <c:set var="path" value="${pageContext.request.contextPath}" />
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
                    <th>ФИО</th>
                    <th>Email</th>
                    <th>Телефон</th>
                    <th>Активен</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.users}" var="user">
                        <tr>
                            <td><c:out value="${user.id}"/></td>
                            <td><c:out value="${user.getSecondName()} ${user.getFirstName()} ${user.getLastName()}"/></td>
                            <td><c:out value="${user.getEmail()}"/></td>
                            <td><c:out value="${user.getPhoneNumber()}"/></td>
                            <td><c:out value="${user.isEnabled()}"/></td>
                            <td>
                                <form action="${path}/users/${user.getId()}/update">
                                    <button type="submit" class="btn btn-warning">Редактировать</button>
                                </form>
                            </td>
                            <td>
                                <form action="${path}/users/${user.getId()}/delete" method="post">
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