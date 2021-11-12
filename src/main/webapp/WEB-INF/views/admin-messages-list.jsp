<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:include page="fragments/header.jsp" flush="true"/>

<header>
    <jsp:include page="fragments/header-menu.jsp" flush="true"/>
</header>

<section class="login-page">
    <h2><spring:message code="admin-messages-list.h2.inner-text"/> </h2>

    <div class="table-responsive">
        <table class="table">
            <thead>
            <tr>
                <th><spring:message code="admin-messages-list.th-id.inner-text"/> </th>
                <th><spring:message code="admin-messages-list.th-name.inner-text"/> </th>
                <th><spring:message code="admin-messages-list.th-surname.inner-text"/> </th>
                <th><spring:message code="admin-messages-list.th-message.inner-text"/> </th>
                <th><spring:message code="admin-messages-list.th-action.inner-text"/> </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="message" items="${messages}">
                <c:choose>
                    <c:when test="${message.read}">
                        <tr>
                    </c:when>
                    <c:otherwise>
                        <tr style="font-weight: bold; font-style: italic; color: black">
                    </c:otherwise>
                </c:choose>
                    <td><c:out value="${message.id}"/></td>
                    <td><c:out value="${message.name}"/></td>
                    <td><c:out value="${message.surname}"/></td>
                    <td><c:out value="${message.message}"/></td>
                    <td>
                        <button onclick="document.location='/admin/messages/view?id=${message.id}'">
                            <spring:message code="admin-messages-list.btn-read.inner-text"/> </button>
                        <button onclick="document.location='/admin/messages/delete?id=${message.id}'">
                            <spring:message code="admin-messages-list.btn-delete.inner-text"/>
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</section>

<jsp:include page="fragments/footer.jsp" flush="true"/>