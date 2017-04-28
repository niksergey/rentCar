<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<t:genericpage>
    <jsp:attribute name="title">
      Редактирование :: Краткосрочная аренда автомобилей в Иннополисе
    </jsp:attribute>
    <jsp:attribute name="stylecss">
            <c:set var="context" value="${pageContext.request.contextPath}" />
        <link href="<c:url value="/resources/css/signup.css"/>" rel="stylesheet">
    </jsp:attribute>

    <jsp:body>

        <c:choose>
            <c:when test="${carForm['new']}">
                <h1>Добавить автомобиль</h1>
            </c:when>
            <c:otherwise>
                <h1>Обновить автомобиль</h1>
            </c:otherwise>
        </c:choose>
        <br />

        <spring:url value="/cars" var="carActionUrl"/>

        <form:form class="form-horizontal" method="post" modelAttribute="carForm" action="${carActionUrl}">

            <form:hidden path="id" />

            <spring:bind path="carModel">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label">Name</label>
                    <div class="col-sm-10">
                        <form:input path="carModel" type="text" class="form-control " id="carModel" placeholder="carModel" />
                        <form:errors path="carModel" class="control-label" />
                    </div>
                </div>
            </spring:bind>
            <spring:bind path="vin">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label">Name</label>
                    <div class="col-sm-10">
                        <form:input path="vin" type="text" class="form-control " id="vin" placeholder="VIN" />
                        <form:errors path="vin" class="control-label" />
                    </div>
                </div>
            </spring:bind>

            <spring:bind path="year">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label">Email</label>
                    <div class="col-sm-10">
                        <form:input path="year" type="number" class="form-control" id="year" placeholder="Год выпуска" />
                        <form:errors path="year" class="control-label" />
                    </div>
                </div>
            </spring:bind>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <c:choose>
                        <c:when test="${userForm['new']}">
                            <button type="submit" class="btn-lg btn-primary pull-right">Добавить</button>
                        </c:when>
                        <c:otherwise>
                            <button type="submit" class="btn-lg btn-primary pull-right">Обновить</button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </form:form>

        <%--<c:set var="context" value="${pageContext.request.contextPath}" />--%>
        <%--<div class="container">--%>
            <%--<form class="form-signup" action="${context}/car/list" method="post">--%>
                <%--<h2 class="form-signup-heading">Редактирование</h2>--%>
                <%--<input type="text" id="vin" class="form-control" name="vin"--%>
                       <%--value="${car.getVin()}"  required autofocus>--%>
                <%--<input type="number" id="year" class="form-control" name="year"--%>
                       <%--value="${car.getYear()}" placeholder="Год выпуска" required>--%>
                <%--<input type="number" id="model" class="form-control" name="model"--%>
                       <%--value="${car.getCarModel().getId()}"  required>--%>
                <%--<input type="hidden" name="actionType" value="saveEdit"/>--%>
                <%--<input type="hidden" name="carId" value="${car.getId()}"/>--%>
                <%--<button class="btn btn-lg btn-primary btn-block" type="submit">Сохранить</button>--%>
            <%--</form>--%>
        <%--</div> <!-- /container -->--%>
    </jsp:body>
</t:genericpage>