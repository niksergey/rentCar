<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="title">
      Редактирование :: Краткосрочная аренда автомобилей в Иннополисе
    </jsp:attribute>
    <jsp:attribute name="stylecss">
        <link href="<c:url value="/resources/css/signup.css"/>" rel="stylesheet">
    </jsp:attribute>

    <jsp:body>
        <c:set var="path" value="${pageContext.request.contextPath}/admin" />
        <div class="container">
            <form class="form-signup" action="${path}/user/list" method="post">
                <h2 class="form-signup-heading">Редактирование</h2>
                <input type="tel" id="phone" class="form-control" name="phone" placeholder="Телефон" required autofocus>
                <input type="tel" id="second_name" class="form-control" name="second_name" placeholder="Фамилия" required>
                <input type="tel" id="first_name" class="form-control" name="first_name" placeholder="Имя" required>
                <input type="tel" id="last_name" class="form-control" name="last_name" placeholder="Отчество">
                <input type="email" id="inputEmail" class="form-control" placeholder="E-mail" name="email" required >
                <input type="password" id="inputPassword" class="form-control" placeholder="Пароль" name="password" required>
                <input type="hidden" name="actionType" value="saveChanges"/>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Сохранить</button>
            </form>
        </div>
    </jsp:body>
</t:genericpage>