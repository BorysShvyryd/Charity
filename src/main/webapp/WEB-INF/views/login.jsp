<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="messages"/>

<jsp:include page="fragments/header.jsp" flush="true"/>

<header>
    <jsp:include page="fragments/header-menu.jsp" flush="true"/>
</header>

<section class="login-page">
    <h2><spring:message code="login.h2.inner-text"/> </h2>
    <form method="post">
        <div class="form-group">
            <input type="email" name="username"
                   placeholder="<spring:message code="login.input.login.placeholder.inner-text"/>" />
        </div>
        <div class="form-group">
            <input type="password" name="password"
                   placeholder="<spring:message code="login.input.pass.placeholder.inner-text"/>" />
            <a href="/login/forgot" class="btn btn--small btn--without-border reset-password">
                <spring:message code="login.btn.forgot-pass.inner-text"/>
            </a>
        </div>

        <div class="form-group form-group--buttons">
            <a href="/register" class="btn btn--without-border">
                <spring:message code="login.btn.register.inner-text"/>
            </a>
            <button class="btn" type="submit">
                <spring:message code="login.btn.login.inner-text"/> </button>
        </div>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</section>

<jsp:include page="fragments/footer.jsp" flush="true"/>