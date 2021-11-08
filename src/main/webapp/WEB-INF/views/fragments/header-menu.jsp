<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<nav class="container container--70">

    <sec:authorize access="!isAuthenticated()">
        <ul class="nav--actions">
            <li><a href="/login"><spring:message code="header-menu.li.login.title"/></a></li>
            <li class="highlighted"><a href="/register"><spring:message code="header-menu.li.register.title"/></a></li>
        </ul>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <ul class="nav--actions">
            <li><a href="/charity/profile"><spring:message code="header-menu.li.name.title"/>
                    ${currentUserName} ⚙</a></li>
            <li class="highlighted"><a href="/logout"><spring:message code="header-menu.li.logout.title"/></a></li>
        </ul>
    </sec:authorize>

    <ul>
        <li><a href="/" class="btn btn--without-border"><spring:message code="header-menu.li.menu.home.title"/></a></li>
        <sec:authorize access="isAuthenticated()">
            <li><a href="/charity" class="btn btn--without-border active">
                <spring:message code="header-menu.li.menu.start.title"/></a></li>
            <li><a href="/charity/list-bag" class="btn btn--without-border">
                <spring:message code="header-menu.li.menu.list-inst.title"/></a></li>
            <sec:authorize access="!hasRole('ROLE_ADMIN')">
                <li><a href="/charity/profile" class="btn btn--without-border">
                    <spring:message code="header-menu.li.menu.profile.title"/></a></li>
            </sec:authorize>
        </sec:authorize>
        <sec:authorize access="!isAuthenticated()">
            <li><a href="/#steps" class="btn btn--without-border">
                <spring:message code="header-menu.li.menu.steps.title"/></a></li>
            <li><a href="/#about-us" class="btn btn--without-border">
                <spring:message code="header-menu.li.menu.about-us.title"/></a></li>
            <li><a href="/#help" class="btn btn--without-border">
                <spring:message code="header-menu.li.menu.help.title"/></a></li>
            <li><a href="/#contact" class="btn btn--without-border">
                <spring:message code="header-menu.li.menu.contact.title"/></a></li>
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <li><a href="/admin/category/list" class="btn btn--without-border">
                <spring:message code="header-menu.li.menu.list-cat.title"/></a></li>
            <li><a href="/admin/institution/list" class="btn btn--without-border">
                <spring:message code="header-menu.li.menu.list-fund.title"/></a></li>
            <li><a href="/admin/users/list" class="btn btn--without-border">
                <spring:message code="header-menu.li.menu.list-users.title"/></a></li>
            <li><a href="/admin/donations/list" class="btn btn--without-border">
                <spring:message code="header-menu.li.menu.list-donat.title"/></a></li>
            <li><a href="/admin/messages/list" class="btn btn--without-border">
                <spring:message code="header-menu.li.menu.list-mess.title"/></a></li>
        </sec:authorize>
    </ul>
</nav>