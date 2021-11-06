<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="fragments/header.jsp" flush="true"/>

<header>
    <jsp:include page="fragments/header-menu.jsp" flush="true"/>
</header>

<section class="login-page">
    <h2>Lista użytkowników</h2>

    <div class="table-responsive">
    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>NAZWA</th>
            <th>STATUS</th>
            <th>ROLA</th>
            <th>AKCJE</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.name}"/></td>
                <c:choose>
                    <c:when test="${user.enabled}">
                        <td>Odblokowany</td>
                    </c:when>
                    <c:otherwise>
                        <td>Zablokowany</td>
                    </c:otherwise>
                </c:choose>
                <td>
                    <c:forEach var="role" items="${user.roleSet}">
                        <c:out value="${role.name}, "/>
                    </c:forEach>
                </td>
                <td>
                    <button onclick="document.location='/admin/users/edit?id=${user.id}'">Edytuj</button>
                    <button onclick="document.location='/admin/users/delete?id=${user.id}'">Usuń</button>
                    <c:choose>
                        <c:when test="${user.enabled}">
                            <button onclick="document.location='/admin/users/status?id=${user.id}'">Zablokuj</button>
                        </c:when>
                        <c:otherwise>
                            <button onclick="document.location='/admin/users/status?id=${user.id}'">Odblokuj</button>
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