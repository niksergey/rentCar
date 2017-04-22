<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Cars</title>
</head>
<body>
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
</body>
</html>
