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
        <form:hidden path="id"/>

        <div class="form-group form-group--50">
            <label>
                Email
                <form:input path="email" disabled="true"/>
            </label>
        </div>
        <div class="form-group form-group--50">
            <label>
                Imie
                <form:input path="name"/>
            </label>
        </div>
        <div class="form-group form-group--50">
            <label>
                Nazwisko
                <form:input path="surname"/>
            </label>
        </div>
        <div class="form-group form-group--50">
            <label>
                Adres
                <form:input path="address"/>
            </label>
        </div>
        <div class="form-group form-group--50">
            <label>
                Telefon
                <form:input path="phone"/>
            </label>
        </div>

        <div class="form-group form-group--buttons">
            <a href="/charity/change-pass" class="btn btn--without-border">Zmień hasło</a>
            <button class="btn" type="submit">Zapisać</button>
        </div>
    </form:form>
</section>

<jsp:include page="fragments/footer.jsp" flush="true"/>