<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="fragments/header.jsp" flush="true"/>

<header>
    <jsp:include page="fragments/header-menu.jsp" flush="true"/>
</header>

<section class="form-section">
    <h2>Mój profil</h2>
    <form:form modelAttribute="user" method="post">
        <form:hidden path="id"/>

        <div class="form-section form-section--columns">
            <div class="form-section--column">
                <div class="form-group form-group--inline">
                    <label>
                        Email
                        <form:input path="email" disabled="true"/>
                    </label>
                </div>
                <div class="form-group form-group--inline">
                    <label>
                        Imie
                        <form:input path="name"/>
                    </label>
                </div>
                <div class="form-group form-group--inline">
                    <label>
                        Nazwisko
                        <form:input path="surname"/>
                    </label>
                </div>
            </div>
            <div class="form-section--column">
                <div class="form-group form-group--inline">
                    <label>
                        Ulica
                        <form:input path="address"/>
                    </label>
                </div>
                <div class="form-group form-group--inline">
                    <label>
                        Miasto
                        <form:input path="city"/>
                    </label>
                </div>
                <div class="form-group form-group--inline">
                    <label>
                        Kod pocztowy
                        <form:input path="zipcode"/>
                    </label>
                </div>
                <div class="form-group form-group--inline">
                    <label>
                        Telefon
                        <form:input path="phone"/>
                    </label>
                </div>
            </div>
        </div>
        <div class="form-group"></div>
        <div class="form-section--columns form-group">
            <a href="/charity/change-pass" class="btn btn--without-border">Zmień hasło</a>
            <button class="btn" type="submit">Zapisać</button>
        </div>
        <div></div>
    </form:form>
</section>

<jsp:include page="fragments/footer.jsp" flush="true"/>