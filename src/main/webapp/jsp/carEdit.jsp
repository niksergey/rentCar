<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="title">
      Редактирование :: Краткосрочная аренда автомобилей в Иннополисе
    </jsp:attribute>
    <jsp:attribute name="stylecss">
            <c:set var="context" value="${pageContext.request.contextPath}" />
        <link href="${context}/css/signup.css" rel="stylesheet">
    </jsp:attribute>

    <jsp:body>
        <c:set var="context" value="${pageContext.request.contextPath}" />
        <div class="container">
            <form class="form-signup" action="${context}/car/list" method="post">
                <h2 class="form-signup-heading">Редактирование</h2>
                <input type="text" id="vin" class="form-control" name="vin"
                       value="${car.getVin()}"  required autofocus>
                <input type="number" id="year" class="form-control" name="year"
                       value="${car.getYear()}" placeholder="Год выпуска" required>
                <input type="number" id="model" class="form-control" name="model"
                       value="${car.getCarModel().getId()}"  required>
                <input type="hidden" name="actionType" value="saveEdit"/>
                <input type="hidden" name="carId" value="${car.getId()}"/>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Сохранить</button>
            </form>
        </div> <!-- /container -->
    </jsp:body>
</t:genericpage>