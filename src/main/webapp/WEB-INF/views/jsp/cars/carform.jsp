<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<t:genericpage>
    <jsp:attribute name="title">
        <c:set var="carNew"  value="${carForm['new']}"/>
        <c:choose>
            <c:when test="${carNew}">
                Добавить автомобиль
            </c:when>
            <c:otherwise>
                Обновить автомобиль
            </c:otherwise>
        </c:choose>
    </jsp:attribute>
    <jsp:attribute name="stylecss">
            <c:set var="context" value="${pageContext.request.contextPath}" />
        <link href="<c:url value="/resources/css/dashboard.css"/>" rel="stylesheet">
    </jsp:attribute>

    <jsp:body>

        <c:choose>
            <c:when test="${carNew}">
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

            <spring:bind path="carModel.id">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label">Модель(id)</label>
                    <div class="col-sm-10">
                        <form:input path="carModel.id" type="text" class="form-control " id="carModel" placeholder="carModel" />
                        <form:errors path="carModel.id" class="control-label" />
                    </div>
                </div>
            </spring:bind>
            <spring:bind path="vin">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label">VIN</label>
                    <div class="col-sm-10">
                        <form:input path="vin" type="text" class="form-control " id="vin" placeholder="VIN" />
                        <form:errors path="vin" class="control-label" />
                    </div>
                </div>
            </spring:bind>

            <spring:bind path="year">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label">Год выпуска</label>
                    <div class="col-sm-10">
                        <form:input path="year" type="number" class="form-control" id="year" placeholder="Год выпуска" />
                        <form:errors path="year" class="control-label" />
                    </div>
                </div>
            </spring:bind>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <c:choose>
                        <c:when test="${carNew}">
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