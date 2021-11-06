<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<nav class="container container--70">

    <sec:authorize access="!isAuthenticated()">
        <ul class="nav--actions">
            <li><a href="/login">Zaloguj</a></li>
            <li class="highlighted"><a href="/register">Załóż konto</a></li>
        </ul>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <ul class="nav--actions">
            <li><a href="/profile">${pageContext.request.userPrincipal.name}</a></li>
            <li class="highlighted"><a href="/logout">Wyloguj</a></li>
        </ul>
    </sec:authorize>

    <ul>
        <li><a href="/" class="btn btn--without-border">Główna</a></li>
        <sec:authorize access="isAuthenticated()">
            <li><a href="/charity" class="btn btn--without-border active">Start</a></li>
            <li><a href="/charity/list-bag" class="btn btn--without-border">Lista moich darów</a></li>
            <sec:authorize access="!hasRole('ROLE_ADMIN')">
                <li><a href="/profile" class="btn btn--without-border">Mój profil</a></li>
            </sec:authorize>
        </sec:authorize>
        <sec:authorize access="!isAuthenticated()">
            <li><a href="/#steps" class="btn btn--without-border">O co chodzi?</a></li>
            <li><a href="/#about-us" class="btn btn--without-border">O nas</a></li>
            <li><a href="/#help" class="btn btn--without-border">Fundacje i organizacje</a></li>
            <li><a href="/#contact" class="btn btn--without-border">Kontakt</a></li>
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <li><a href="/admin/category/list" class="btn btn--without-border">Lista kategorii</a></li>
            <li><a href="/admin/institution/list" class="btn btn--without-border">Lista fundacji</a></li>
            <li><a href="/admin/users/list" class="btn btn--without-border">Lista użytkowników</a></li>
            <li><a href="#" class="btn btn--without-border">Lista darów</a></li>
            <li><a href="/admin/messages/list" class="btn btn--without-border">Lista wiadomości</a></li>
        </sec:authorize>
    </ul>
</nav>