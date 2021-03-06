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
    <h2>${title_form}</h2>
    <form:form modelAttribute="institution" method="post">

        <form:hidden path="id"/>
        <form:hidden path="status"/>

        <div class="form-group">
            <form:input path="name" title="Nazwa fundacji"/>
        </div>
        <div class="form-group">
            <form:input path="description" title="Opis fundacji"/>
        </div>

        <div class="form-group form-group--buttons">
            <a href="/admin/institution/list" class="btn btn--without-border">
                <spring:message code="admin-institution.btn.return.inner-text"/> </a>
            <button class="btn" type="submit">
                <spring:message code="admin-institution.btn.save.inner-text"/> </button>
        </div>
    </form:form>
</section>

<jsp:include page="fragments/footer.jsp" flush="true"/>