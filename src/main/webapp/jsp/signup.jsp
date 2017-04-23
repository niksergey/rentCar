<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="title">
      Регистрация :: Краткосрочная аренда автомобилей в Иннополисе
    </jsp:attribute>
    <jsp:attribute name="stylecss">
            <c:set var="context" value="${pageContext.request.contextPath}" />
        <link href="${context}/css/signup.css" rel="stylesheet">
    </jsp:attribute>

    <jsp:body>
        <c:set var="context" value="${pageContext.request.contextPath}" />
        <div class="container">
            <form class="form-signup" action="${context}/signup" method="post">
                <h2 class="form-signup-heading">Регистрация</h2>
                <input type="tel" id="phone" class="form-control" name="phone" placeholder="Телефон" required autofocus>
                <input type="tel" id="second_name" class="form-control" name="second_name" placeholder="Фамилия" required>
                <input type="tel" id="first_name" class="form-control" name="first_name" placeholder="Имя" required>
                <input type="tel" id="last_name" class="form-control" name="last_name" placeholder="Отчество">
                    <%--<label for="inputEmail" class="sr-only">Email address</label>--%>
                <input type="email" id="inputEmail" class="form-control" placeholder="E-mail" name="email" required >
                    <%--<label for="inputPassword" class="sr-only">Password</label>--%>
                <input type="password" id="inputPassword" class="form-control" placeholder="Пароль" name="password" required>
                <div class="checkbox">
                    <label>
                        <input type="checkbox" value="apply-rules" required> Принимаю условия соглашения
                    </label>
                </div>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Зарегистрироваться</button>
            </form>
        </div> <!-- /container -->
    </jsp:body>
</t:genericpage>