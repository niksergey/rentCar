<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<t:genericpage>
    <jsp:attribute name="title">
      Краткосрочная аренда автомобилей в Иннополисе
        <c:set var="context" value="${pageContext.request.contextPath}"/>
    </jsp:attribute>
    <jsp:attribute name="stylecss">
        <%--<c:if test="${sessionKey != null}">--%>
        <%--</c:if>--%>
        <spring:url value="/resources/css/dashboard.css" var="coreCss" />
        <link href="${coreCss}" rel="stylesheet" />
    </jsp:attribute>

    <jsp:attribute name="logaction">
        <c:choose>
        <c:when test="${sessionKey eq null}">
            <form class="navbar-form navbar-right" action="${context}/signup">
                <button type="submit" class="btn btn-success">Зарегистрироваться</button>
            </form>

            <form class="navbar-form navbar-right" action="${context}/login">
                <button type="submit" class="btn btn-success">Войти</button>
            </form>
        </c:when>
        <c:otherwise>
            <form class="navbar-form navbar-right" action="${pageContext.request.contextPath}/logout" method="post">
                <button type="submit" class="btn btn-success">Выйти</button>
            </form>
        </c:otherwise>
        </c:choose>


    </jsp:attribute>

    <jsp:body>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <c:choose>
                <c:when test="${sessionKey eq null}">
                    <h2>Поминутная аренда автомобилей в Иннополисе</h2>
                    <h4>Управляй арендой через личный кабинет</h4>
                    <br/>
                    <br/>
                    <h3>
                        <ul >
                            <li><a href="${context}/signup">Зарегистрируйся</a></li>
                            <li>Выбери удобный для тебя автомобиль</li>
                            <li>Начни аренду</li>
                            <li>Наслаждайся поездкой</li>
                            <li>Заверши аренду в любом разрешенном месте</li>
                        </ul>
                    </h3>
                </c:when>
                <c:otherwise>
                    <t:lksidebar/>
                </c:otherwise>
            </c:choose>
        </div>
    </jsp:body>
</t:genericpage>