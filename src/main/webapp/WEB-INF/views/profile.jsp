<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

<section class="form-section">
    <h2><spring:message code="profile.h2.inner-text"/> </h2>
    <form:form modelAttribute="user" method="post">
        <form:hidden path="id"/>

        <div class="form-section form-section--columns" style="margin-left: 25%">
            <div class="form-section--column">
                <div class="form-group form-group--inline">
                    <label>
                        <spring:message code="profile.label.email.inner-text"/>
                        <form:input path="email" readonly="true"/>
                    </label>
                </div>
                <div class="form-group form-group--inline">
                    <label>
                        <spring:message code="profile.label.name.inner-text"/>
                        <form:input path="name"/>
                    </label>
                </div>
                <div class="form-group form-group--inline">
                    <label>
                        <spring:message code="profile.label.surname.inner-text"/>
                        <form:input path="surname"/>
                    </label>
                </div>
            </div>
            <div class="form-section--column">
                <div class="form-group form-group--inline">
                    <label>
                        <spring:message code="profile.label.address.inner-text"/>
                        <form:input path="address"/>
                    </label>
                </div>
                <div class="form-group form-group--inline">
                    <label>
                        <spring:message code="profile.label.city.inner-text"/>
                        <form:input path="city"/>
                    </label>
                </div>
                <div class="form-group form-group--inline">
                    <label>
                        <spring:message code="profile.label.zipcode.inner-text"/>
                        <form:input path="zipcode"/>
                    </label>
                </div>
                <div class="form-group form-group--inline">
                    <label>
                        <spring:message code="profile.label.phone.inner-text"/>
                        <form:input path="phone"/>
                    </label>
                </div>
            </div>
        </div>
        <div class="form-group"></div>
        <div class="form-group"></div>
        <div class="form-section--columns form-group" style="margin-left: 40%">
            <a href="/charity/change-pass" class="btn btn--without-border">
                <spring:message code="profile.btn.change-pass.inner-text"/> </a>
            <button class="btn" type="submit">
                <spring:message code="profile.btn.save.inner-text"/>
            </button>
        </div>
        <div></div>
    </form:form>
</section>

<jsp:include page="fragments/footer.jsp" flush="true"/>