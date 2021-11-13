<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="messages"/>

<jsp:include page="fragments/header.jsp" flush="true"/>

<header>
    <jsp:include page="fragments/header-menu.jsp" flush="true"/>
</header>

<section class="login-page">
    <h2><spring:message code="admin-category-list.h2.message"/></h2>

    <div class="table-responsive">
    <table class="table">
        <thead>
        <tr>
            <th><spring:message code="admin-category-list.th.id.title"/></th>
            <th><spring:message code="admin-category-list.th.name.title"/></th>
            <th><spring:message code="admin-category-list.th.status.title"/></th>
            <th><spring:message code="admin-category-list.th.action.title"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="category" items="${categories}">
            <tr>
                <td><c:out value="${category.id}"/></td>
                <td><c:out value="${category.name}"/></td>
                <c:choose>
                    <c:when test="${category.status}">
                        <td><spring:message code="admin-category-list.td.status.message"/></td>
                    </c:when>
                    <c:otherwise>
                        <td><spring:message code="admin-category-list.td.status.otherwise.message"/></td>
                    </c:otherwise>
                </c:choose>
                <td>
                    <button onclick="document.location='/admin/category/edit?id=${category.id}'">
                        <spring:message code="admin-category-list.btn.edit.title"/></button>
                    <button onclick="document.location='/admin/category/delete?id=${category.id}'">
                        <spring:message code="admin-category-list.btn.delete.title"/></button>
                    <c:choose>
                        <c:when test="${category.status}">
                            <button onclick="document.location='/admin/category/status?id=${category.id}'">
                                <spring:message code="admin-category-list.btn.no-activate.title"/></button>
                        </c:when>
                        <c:otherwise>
                            <button onclick="document.location='/admin/category/status?id=${category.id}'">
                                <spring:message code="admin-category-list.btn.activate.title"/></button>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td><button onclick="document.location='/admin/category/add'">
                <spring:message code="admin-category-list.btn.add.title"/></button></td>
        </tr>
        </tbody>
    </table>
    </div>
</section>

<jsp:include page="fragments/footer.jsp" flush="true"/>