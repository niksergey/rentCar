<%@tag description="Sidebar Page template" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}" />

<div class="row">
    <div class="col-sm-3 col-md-2 sidebar">
        <ul class="nav nav-sidebar">
            <li><a href="${path}">Главная</a></li>
            <li><a href="${path}/car/list">Свободные авто</a></li>
            <li><a href="${path}/rent/list">История поездок</a></li>
            <li><a href="${path}/carmodel/list">Модели авто</a></li>
            <li><a href="${path}/user/list">Пользователи</a></li>
        </ul>
    </div>
</div>
