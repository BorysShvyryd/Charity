<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="fragments/header.jsp" flush="true"/>

<header>
    <jsp:include page="fragments/header-menu.jsp" flush="true"/>
</header>

<section class="login-page">
    <h2>Mój profil</h2>
    <form:form modelAttribute="user" method="post">

        <div class="form-group">
            <form:input path="name" disabled="true"/>
        </div>

        <div class="form-group form-group--buttons">
            <a href="/change-pass" class="btn btn--without-border">Zmień hasło</a>
        </div>
    </form:form>
</section>

<jsp:include page="fragments/footer.jsp" flush="true"/>