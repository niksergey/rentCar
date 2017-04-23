<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="title">
      Список авто :: Краткосрочная аренда автомобилей в Иннополисе
    </jsp:attribute>
    <jsp:attribute name="stylecss">
        <%--<c:set var="context" value="${pageContext.request.contextPath}" />--%>
        <%--<link href="${context}/css/sticky-footer.css" rel="stylesheet">--%>
    </jsp:attribute>

    <jsp:attribute name="logaction">
            <form class="navbar-form navbar-right" action="${pageContext.request.contextPath}/login" method="post">
                    <%--<div class="form-group">--%>
                    <%--<input type="text" placeholder="Email" class="form-control">--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                    <%--<input type="password" placeholder="Password" class="form-control">--%>
                    <%--</div>--%>
                        <input type="hidden" name="currentSession" value="delete"/>
                <button type="submit" class="btn btn-success">Выйти</button>
            </form>
    </jsp:attribute>

    <jsp:body>
        <h1>Cars</h1>
        <c:forEach items="${requestScope.cars}" var="car">
            <ol>
                <li>
                    <td><c:out value="${car.getId()}"></c:out></td>
                    <td><c:out value="${car.getVin()}"></c:out></td>
                    <td><c:out value="${car.getYear()}"></c:out></td>
                    <td><c:out value="${car.getCarModel().getModel()}"></c:out></td>
                        <%--<form action="/students/manage" method="post">--%>
                        <%--<input type="submit" value="deleteStudent"/>--%>
                        <%--<input type="hidden" name="studentId" value="${car.getId()}"/>--%>
                        <%--<input type="hidden" name="requestType" value="delete"/>--%>
                        <%--</form>--%>
                </li>
            </ol>
        </c:forEach>
    </jsp:body>
</t:genericpage>