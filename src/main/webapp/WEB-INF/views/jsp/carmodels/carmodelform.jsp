<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<t:genericpage>
    <jsp:attribute name="title">
        <c:set var="carModelNew" value="${carModelForm['new']}"/>
        <c:choose>
            <c:when test="${carModelNew}">
                Добавить модель автомобиля
            </c:when>
            <c:otherwise>
                Обновить модель автомобиля
            </c:otherwise>
        </c:choose>
    </jsp:attribute>
    <jsp:attribute name="stylecss">
            <c:set var="context" value="${pageContext.request.contextPath}" />
        <link href="<c:url value="/resources/css/dashboard.css"/>" rel="stylesheet">
    </jsp:attribute>

    <jsp:body>

        <c:choose>
            <c:when test="${carModelNew}">
                <h1>Добавить модель автомобиля</h1>
            </c:when>
            <c:otherwise>
                <h1>Обновить модель автомобиля</h1>
            </c:otherwise>
        </c:choose>
        <br />

        <spring:url value="/carmodels" var="carModelActionUrl"/>

        <form:form class="form-horizontal" method="post" modelAttribute="carModelForm" action="${carModelActionUrl}">

            <form:hidden path="id" />

            <spring:bind path="manufacturer">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label">Производитель</label>
                    <div class="col-sm-10">
                        <form:input path="manufacturer" type="text" class="form-control "
                                    id="manufacturer" placeholder="Производитель" />
                        <form:errors path="manufacturer" class="control-label" />
                    </div>
                </div>
            </spring:bind>

            <spring:bind path="model">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label">VIN</label>
                    <div class="col-sm-10">
                        <form:input path="model" type="text" class="form-control "
                                    id="model" placeholder="Модель" />
                        <form:errors path="model" class="control-label" />
                    </div>
                </div>
            </spring:bind>

            <spring:bind path="power">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label">Мощность</label>
                    <div class="col-sm-10">
                        <form:input path="power" type="number" class="form-control" id="power" placeholder="Мощность" />
                        <form:errors path="power" class="control-label" />
                    </div>
                </div>
            </spring:bind>

            <spring:bind path="gear">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label">Коробка передач</label>
                    <div class="col-sm-10">
                        <form:input path="gear" type="text" class="form-control "
                                    id="gear" placeholder="Коробка передач" />
                        <form:errors path="gear" class="control-label" />
                    </div>
                </div>
            </spring:bind>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <c:choose>
                        <c:when test="${carModelNew}">
                            <button type="submit" class="btn-lg btn-primary pull-right">Добавить</button>
                        </c:when>
                        <c:otherwise>
                            <button type="submit" class="btn-lg btn-primary pull-right">Обновить</button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </form:form>
    </jsp:body>
</t:genericpage>