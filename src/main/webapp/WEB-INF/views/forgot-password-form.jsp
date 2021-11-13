<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${cookie['lang'].value == param.lang}">
        <fmt:setLocale value="${cookie['lang'].value}"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="${param.lang}"/>
    </c:otherwise>
</c:choose>
<fmt:setBundle basename="messages"/>

<jsp:include page="fragments/header.jsp" flush="true"/>

<header>
    <jsp:include page="fragments/header-menu.jsp" flush="true"/>
</header>

<section class="login-page">
    <h2><spring:message code="forgot-pass.h2.inner-text"/> </h2>
    <form method="post">
        <div class="form-group">
            <input type="email" name="email" placeholder="Email" />
        </div>

        <div class="form-group form-group--buttons">
            <a href="/login" class="btn btn--without-border">
                <spring:message code="forgot-pass.btn.return.inner-text"/> </a>
            <button class="btn" type="submit">
                <spring:message code="forgot-pass.btn.send-mail.inner-text"/>
            </button>
        </div>

    </form>
</section>

<jsp:include page="fragments/footer.jsp" flush="true"/>