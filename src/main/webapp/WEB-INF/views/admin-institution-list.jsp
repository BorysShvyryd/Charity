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
    <h2><spring:message code="admin-institution-list.h2.inner-text"/></h2>

    <div class="table-responsive">
        <table class="table">
            <thead>
            <tr>
                <th><spring:message code="admin-institution-list.th-id.inner-text"/></th>
                <th><spring:message code="admin-institution-list.th-name.inner-text"/></th>
                <th><spring:message code="admin-institution-list.th-description.inner-text"/></th>
                <th><spring:message code="admin-institution-list.th-status.inner-text"/></th>
                <th><spring:message code="admin-institution-list.th-action.inner-text"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="institution" items="${institutions}">
                <tr>
                    <td><c:out value="${institution.id}"/></td>
                    <td><c:out value="${institution.name}"/></td>
                    <td><c:out value="${institution.description}"/></td>
                    <c:choose>
                        <c:when test="${institution.status}">
                            <td><spring:message code="admin-institution-list.td-active.inner-text"/></td>
                        </c:when>
                        <c:otherwise>
                            <td><spring:message code="admin-institution-list.td-not-active.inner-text"/></td>
                        </c:otherwise>
                    </c:choose>
                    <td>
                        <button onclick="document.location='/admin/institution/edit?id=${institution.id}'">
                            <spring:message code="admin-institution-list.btn-edit.inner-text"/></button>
                        <button onclick="document.location='/admin/institution/delete?id=${institution.id}'">
                            <spring:message code="admin-institution-list.btn-remove.inner-text"/></button>
                        <c:choose>
                            <c:when test="${institution.status}">
                                <button onclick="document.location='/admin/institution/status?id=${institution.id}'">
                                    <spring:message code="admin-institution-list.btn-not-activate.inner-text"/></button>
                            </c:when>
                            <c:otherwise>
                                <button onclick="document.location='/admin/institution/status?id=${institution.id}'">
                                    <spring:message code="admin-institution-list.btn-activate.inner-text"/></button>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td>
                    <button onclick="document.location='/admin/institution/add'">
                        <spring:message code="admin-institution-list.btn-add.inner-text"/></button>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</section>

<jsp:include page="fragments/footer.jsp" flush="true"/>