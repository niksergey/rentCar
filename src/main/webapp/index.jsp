<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="title">
      Краткосрочная аренда автомобилей в Иннополисе
    </jsp:attribute>
    <jsp:attribute name="stylecss">
        <c:set var="context" value="${pageContext.request.contextPath}" />
        <link href="${context}/css/sticky-footer.css" rel="stylesheet">
    </jsp:attribute>

    <jsp:body>
        <a href="${context}/login">Войти</a>
    </jsp:body>
</t:genericpage>