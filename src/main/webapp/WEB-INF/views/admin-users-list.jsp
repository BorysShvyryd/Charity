<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="messages"/>

<jsp:include page="fragments/header.jsp" flush="true"/>

<header>
    <jsp:include page="fragments/header-menu.jsp" flush="true"/>
</header>

<section class="login-page">
    <h2><spring:message code="admin-users-list.h2.inner-text"/> </h2>

    <div class="table-responsive">
    <table class="table">
        <thead>
        <tr>
            <th><spring:message code="admin-users-list.th.id-inner-text"/>
                <a href="/admin/users/list/sort=id_up"> ▲</a>
                <a href="/admin/users/list/sort=id_down">▼</a>
            </th>
            <th><spring:message code="admin-users-list.th.email-inner-text"/>
                <a href="/admin/users/list/sort=email_up"> ▲</a>
                <a href="/admin/users/list/sort=email_down">▼</a>
                <form method="post" action="/admin/users/list/filter=email" style="margin-top: 0">
                    <input type="text" name="querySearch" id="query-email" placeholder="Search"/>
                </form>
            </th>
            <th><spring:message code="admin-users-list.th.name-inner-text"/>
                <a href="/admin/users/list/sort=name_up"> ▲</a>
                <a href="/admin/users/list/sort=name_down">▼</a>
                <form method="post" action="/admin/users/list/filter=name" style="margin-top: 0">
                    <input type="text" name="querySearch" id="query-name" placeholder="Search"/>
                </form>
            </th>
            <th><spring:message code="admin-users-list.th.surname-inner-text"/>
                <a href="/admin/users/list/sort=surname_up"> ▲</a>
                <a href="/admin/users/list/sort=surname_down">▼</a>
                <form method="post" action="/admin/users/list/filter=surname" style="margin-top: 0">
                    <input type="text" name="querySearch" id="query-surname" placeholder="Search"/>
                </form>
            </th>
            <th><spring:message code="admin-users-list.th.status-inner-text"/>
                <a href="/admin/users/list/filter=status_1"> ➕</a>
                <a href="/admin/users/list/filter=status_0"> ➖</a>
            </th>
            <th><spring:message code="admin-users-list.th.role-inner-text"/>
                <a href="/admin/users/list/filter=admin"> (❎</a> admin)</th>
            <th><spring:message code="admin-users-list.th.action-inner-text"/>
                <a href="/admin/users/list"> (❌ zresetuj wszystkie filtry)</a></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.surname}"/></td>
                <c:choose>
                    <c:when test="${user.enabled == 1}">
                        <td><spring:message code="admin-users-list.th.unlocked-inner-text"/> </td>
                    </c:when>
                    <c:otherwise>
                        <td><spring:message code="admin-users-list.th.locked-inner-text"/> </td>
                    </c:otherwise>
                </c:choose>
                <td>
                    <c:forEach var="role" items="${user.roleSet}">
                        <c:out value="${role.name}, "/>
                    </c:forEach>
                </td>
                <td>
                    <button onclick="document.location='/admin/users/delete?id=${user.id}'">
                        <spring:message code="admin-users-list.btn.remove-inner-text"/>
                    </button>
                    <button onclick="document.location='/admin/users/forgot?email=${user.name}'">
                        <spring:message code="admin-users-list.btn.forgot-inner-text"/>
                    </button>
                    <button onclick="document.location='/admin/users/role?id=${user.id}'">
                        <spring:message code="admin-users-list.btn.change-role.inner-text"/> </button>
                    <c:choose>
                        <c:when test="${user.enabled == 1}">
                            <button onclick="document.location='/admin/users/status?id=${user.id}'">
                            <spring:message code="admin-users-list.btn.blocked.inner-text"/> </button>
                        </c:when>
                        <c:otherwise>
                            <button onclick="document.location='/admin/users/status?id=${user.id}'">
                                <spring:message code="admin-users-list.btn.unblocked.inner-text"/>
                            </button>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </div>
</section>

<jsp:include page="fragments/footer.jsp" flush="true"/>