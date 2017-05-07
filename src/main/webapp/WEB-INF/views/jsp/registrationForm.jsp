<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<t:genericpage>
    <jsp:attribute name="title">
      Регистрация
    </jsp:attribute>
    <jsp:attribute name="stylecss">
            <c:set var="context" value="${pageContext.request.contextPath}" />
        <link href="<c:url value="/resources/css/signup.css"/>" rel="stylesheet">
    </jsp:attribute>

    <jsp:body>
        <c:set var="context" value="${pageContext.request.contextPath}" />

        <form:form class="form-signup" method="post" modelAttribute="user" action="${context}/signup">
            <h2 class="form-signup-heading">Регистрация</h2>

            <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input path="firstName" type="text" class="form-control "
                                id="firstName" placeholder="Имя" required="true"/>
                    <form:errors path="firstName" class="control-label" />
            </div>

            <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input path="secondName" type="text" class="form-control "
                                id="secondName" placeholder="Фамилия"  required="true"/>
                    <form:errors path="secondName" class="control-label" />
            </div>

            <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input path="lastName" type="text" class="form-control "
                                id="lastName" placeholder="Отчество" />
                    <form:errors path="lastName" class="control-label" />
            </div>

            <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input path="phoneNumber" type="tel" class="form-control "
                                id="phoneNumber" placeholder="Номер телефона"  required="true"/>
                    <form:errors path="phoneNumber" class="control-label" />
            </div>

            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input path="email" type="email" class="form-control "
                            id="email" placeholder="email"  required="true"/>
                <form:errors path="email" class="control-label" />
            </div>

            <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input path="password" type="password" class="form-control "
                                id="phoneNumber" placeholder="Пароль"  required="true"/>
                    <form:errors path="password" class="control-label" />
            </div>

            <div class="checkbox">
                <label>
                    <input type="checkbox" value="apply-rules" required> Принимаю условия соглашения
                </label>
            </div>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Зарегистрироваться</button>
        </form:form>
    </jsp:body>
</t:genericpage>