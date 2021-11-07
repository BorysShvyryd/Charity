<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:include page="fragments/header.jsp" flush="true"/>

<header>
    <jsp:include page="fragments/header-menu.jsp" flush="true"/>

    <div class="slogan container container--90">
        <h2>
            <spring:message code="403.h2.message"/>
<%--            Access denied. Error 403.--%>
        </h2>
    </div>

</header>

<jsp:include page="fragments/footer.jsp" flush="true"/>