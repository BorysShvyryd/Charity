<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="fragments/header.jsp" flush="true"/>

<header>
    <jsp:include page="fragments/header-menu.jsp" flush="true"/>
</header>

<section class="login-page">
    <h2>Lista fundacji</h2>

    <div class="table-responsive">
    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>NAZWA</th>
            <th>OPIS</th>
            <th>STATUS</th>
            <th>AKCJE</th>
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
                        <td>Aktywny</td>
                    </c:when>
                    <c:otherwise>
                        <td>Nie aktywny</td>
                    </c:otherwise>
                </c:choose>
                <td>
                    <button onclick="document.location='/admin/institution/edit?id=${institution.id}'">Edytuj</button>
                    <button onclick="document.location='/admin/institution/delete?id=${institution.id}'">Usuń</button>
                    <c:choose>
                        <c:when test="${institution.status}">
                            <button onclick="document.location='/admin/institution/status?id=${institution.id}'">Deaktywuj</button>
                        </c:when>
                        <c:otherwise>
                            <button onclick="document.location='/admin/institution/status?id=${institution.id}'">Aktywuj</button>
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
            <td><button onclick="document.location='/admin/institution/add'">Dodaj</button></td>
        </tr>
        </tbody>
    </table>
    </div>
</section>

<jsp:include page="fragments/footer.jsp" flush="true"/>