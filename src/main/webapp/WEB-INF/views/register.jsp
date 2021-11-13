<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="messages"/>

<jsp:include page="fragments/header.jsp" flush="true"/>

<header>
    <jsp:include page="fragments/header-menu.jsp" flush="true"/>
</header>

<section class="login-page">
    <h2><spring:message code="register.h2.inner-text"/> </h2>
    <form:form modelAttribute="user" method="post">

        <div class="form-group form-group--inline">
            <label><spring:message code="register.label-email.inner-text"/>
            <form:input path="email" readonly="${not pre_registration}"/>
            </label>
        </div>

        <c:if test="${not pre_registration}">
            <div class="form-group">
                <input type="password" name="password" placeholder="Hasło"/>
            </div>
            <div class="form-group">
                <input type="password" name="password2" placeholder="Powtórz hasło"/>
            </div>
        </c:if>

        <div class="form-group form-group--buttons">
            <a href="/login" class="btn btn--without-border">
                <spring:message code="register.btn.login.inner-text"/> </a>
            <button class="btn" type="submit">
                <spring:message code="register.btn.register.inner-text"/> </button>
        </div>
    </form:form>
</section>

<jsp:include page="fragments/footer.jsp" flush="true"/>