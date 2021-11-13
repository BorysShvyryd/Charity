<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="messages"/>

<nav class="container container--85">

    <sec:authorize access="!isAuthenticated()">
        <ul class="nav--actions">
            <li><a href="/login"><spring:message code="header-menu.li.login.title"/></a></li>
            <li></li>
            <li class="highlighted"><a><spring:message code="header-menu.li.register.title"/></a></li>
            <li></li>
            <li><a href="?lang=en"  onclick="document.cookie = 'lang = en; path=/'">
                <img src="../../../resources/images/flags/us.svg" title="<fmt:message key="string.lang.en"/>">
                <fmt:message key="label.lang.en"/>
            </a></li>
            <li><a href="?lang=uk" onclick="document.cookie = 'lang = uk; path=/'">
                <img src="../../../resources/images/flags/ua.svg" title="<fmt:message key="string.lang.uk"/>">
                <fmt:message key="label.lang.uk"/>
            </a></li>
            <li><a href="?lang=pl" onclick="document.cookie = 'lang = pl; path=/'">
                <img src="../../../resources/images/flags/pl.svg" title="<fmt:message key="string.lang.pl"/>">
                <fmt:message key="label.lang.pl"/>
            </a></li>
            <li><a href="#" onclick="document.cookie = 'lang = ru; path=/'">
                <img src="../../../resources/images/flags/ru.svg" title="<fmt:message key="string.lang.ru"/>">
                <fmt:message key="label.lang.ru"/>
            </a></li>
        </ul>
    </sec:authorize>

    <script>
        function getCookie(cname) {
            let name = cname + "=";
            let decodedCookie = decodeURIComponent(document.cookie);
            let ca = decodedCookie.split(';');
            for(let i = 0; i <ca.length; i++) {
                let c = ca[i];
                while (c.charAt(0) == ' ') {
                    console.log(c.substring(1));
                    c = c.substring(1);
                }
                if (c.indexOf(name) == 0) {
                    console.log(c.substring(name.length, c.length));
                    return c.substring(name.length, c.length);
                }
            }
            return "";
        }
    </script>

    <sec:authorize access="isAuthenticated()">
        <ul class="nav--actions">
            <li><a href="/charity/profile"><spring:message code="header-menu.li.name.title"/>
                    ${currentUserName} ⚙</a></li>
            <li></li>
            <li class="highlighted"><a href="/logout"><spring:message code="header-menu.li.logout.title"/></a></li>
            <li></li>
            <li><a href="?lang=en" onclick="document.cookie = 'lang = en; path=/'">
                <img src="../../../resources/images/flags/us.svg" title="<fmt:message key="string.lang.en"/>">
                <fmt:message key="label.lang.en"/>
            </a></li>
            <li><a href="?lang=uk" onclick="document.cookie = 'lang = uk; path=/'">
                <img src="../../../resources/images/flags/ua.svg" title="<fmt:message key="string.lang.uk"/>">
                <fmt:message key="label.lang.uk"/>
            </a></li>
            <li><a href="?lang=pl" onclick="document.cookie = 'lang = pl; path=/'">
                <img src="../../../resources/images/flags/pl.svg" title="<fmt:message key="string.lang.pl"/>">
                <fmt:message key="label.lang.pl"/>
            </a></li>
            <li><a href="#" onclick="document.cookie = 'lang = ru; path=/'">
                <img src="../../../resources/images/flags/ru.svg" title="<fmt:message key="string.lang.ru"/>">
                <fmt:message key="label.lang.ru"/>
            </a></li>
        </ul>
    </sec:authorize>

    <ul>
        <li><a href="/" class="btn btn--without-border"><spring:message code="header-menu.li.menu.home.title"/></a></li>
        <sec:authorize access="isAuthenticated()">
            <li><a href="/charity" class="btn btn--without-border active">
                <spring:message code="header-menu.li.menu.start.title"/></a></li>
            <sec:authorize access="!hasRole('ROLE_ADMIN')">
                <li><a href="/charity/list-bag" class="btn btn--without-border">
                    <spring:message code="header-menu.li.menu.list-inst.title"/></a></li>
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