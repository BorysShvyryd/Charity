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
            <th>ID<a href="/admin/users/list/sort=id_up"> ▲</a>
                <a href="/admin/users/list/sort=id_down">▼</a>
            </th>
            <th>NAZWA
<%--                <input name="find"/>--%>
                <a href="/admin/users/list/sort=name_up"> ▲</a>
                <a href="/admin/users/list/sort=name_down">▼</a>
            </th>
            <th>STATUS<a href="/admin/users/list/filter=status_1"> ➕</a>
                <a href="/admin/users/list/filter=status_0"> ➖</a>
            </th>
            <th>ROLA<a href="/admin/users/list/filter=admin"> (❎</a> admin)</th>
            <th>AKCJE<a href="/admin/users/list"> (❌ zresetuj wszystkie filtry)</a></th>
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
                    <button onclick="document.location='/admin/users/delete?id=${user.id}'">Usuń</button>
                    <button onclick="document.location='/admin/users/forgot?email=${user.name}'">Odzyskiwanie hasła</button>
                    <button onclick="document.location='/admin/users/role?id=${user.id}'">Zmień rolę admin</button>
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