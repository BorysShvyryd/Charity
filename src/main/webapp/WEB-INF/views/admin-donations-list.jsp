<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="messages"/>

<jsp:include page="fragments/header.jsp" flush="true"/>

<header>
    <jsp:include page="fragments/header-menu.jsp" flush="true"/>
</header>

<section class="login-page">
    <h2>${title_page}</h2>

    <div class="table-responsive">
        <table class="table">
            <thead>
            <tr>
                <th><spring:message code="admin-donation-list.th-id.inner-text"/></th>
                <th><spring:message code="admin-donation-list.th-category.inner-text"/> </th>
                <th><spring:message code="admin-donation-list.th-count.inner-text"/> </th>
                <th><spring:message code="admin-donation-list.th-institution.inner-text"/>
                    <a href="/admin/donations/list/sort=institution_up"> ▲</a>
                    <a href="/admin/donations/list/sort=institution_down">▼</a><br>
                    <form method="post" action="/admin/donations/list/filter=institution" style="margin-top: 0">
                        <input type="text" name="querySearch" id="query-fund" placeholder="Search"/>
                    </form>
                </th>
                <th><spring:message code="admin-donation-list.th-address.inner-text"/>
                    <a href="/admin/donations/list/sort=address_up"> ▲</a>
                    <a href="/admin/donations/list/sort=address_down">▼</a><br>
                    <form method="post" action="/admin/donations/list/filter=address" style="margin-top: 0">
                        <input type="text" name="querySearch" id="query-address" placeholder="Search"/>
                    </form>
                </th>
                <th><spring:message code="admin-donation-list.th-user.inner-text"/>
                    <a href="/admin/donations/list/sort=username_up"> ▲</a>
                    <a href="/admin/donations/list/sort=username_down">▼</a><br>
                    <form method="post" action="/admin/donations/list/filter=username" style="margin-top: 0">
                        <input type="text" name="querySearch" id="query-user" placeholder="Search"/>
                    </form>
                </th>
                <th><spring:message code="admin-donation-list.th-received.inner-text"/> </th>
                <th><spring:message code="admin-donation-list.th-transfer.inner-text"/> </th>
                <th><spring:message code="admin-donation-list.th-action.inner-text"/>
                    <a href="/admin/donations/list"> (❌ filtr)</a>
                </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="donation" items="${donations}">
                <c:choose>
                    <c:when test="${donation.status == 2}">
                        <tr>
                    </c:when>
                    <c:when test="${donation.status == 1}">
                        <tr style="font-weight: bold; color: black">
                    </c:when>
                    <c:otherwise>
                        <tr style="font-weight: bold; background-color: lightblue; color: black">
                    </c:otherwise>
                </c:choose>
                <td><c:out value="${donation.id}"/></td>
                <td>
                    <c:forEach var="category" items="${donation.categories}">
                        <c:out value="${category.name}; "/>
                    </c:forEach>
                </td>
                <td><c:out value="${donation.quantity}"/></td>
                <td><c:out value="${donation.institution.name}"/></td>
                <td>
                    <c:out value="✉${donation.street}, "/>
                    <c:out value="${donation.city}, "/>
                    <c:out value="${donation.zipCode}, "/>
                    <c:out value="☎${donation.phone}"/>
                </td>
                <td><c:out value="${donation.user.name}"/>
                    <c:out value=" ${donation.user.surname}"/>
                </td>
                <td>
                    <fmt:parseDate value="${ donation.dateTimeReceived }" pattern="yyyy-MM-dd'T'HH:mm:ss"
                                   var="parsedDateTime" type="both"/>
                    <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${parsedDateTime}"/>
                </td>
                <td>
                    <fmt:parseDate value="${ donation.dateTimeTransmitted }" pattern="yyyy-MM-dd'T'HH:mm:ss"
                                   var="parsedDateTime" type="both"/>
                    <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${parsedDateTime}"/>
                </td>
                <td>
                    <c:if test="${donation.status == 2}">
                        <spring:message code="admin-donation-list.td-archived.inner-text"/>
                    </c:if>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <c:if test="${donation.status == 1}">
                            <button onclick="document.location='/admin/donations/transfer?id=${donation.id}'">
                                <spring:message code="admin-donation-list.td-devoted.inner-text"/>
                            </button>
                        </c:if>
                        <c:if test="${donation.status < 1}">
                            <button onclick="document.location='/admin/donations/devoted?id=${donation.id}'">
                                <spring:message code="admin-donation-list.td-received.inner-text"/>
                            </button>
                        </c:if>
                    </sec:authorize>
                </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</section>

<jsp:include page="fragments/footer.jsp" flush="true"/>