<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="fragments/header.jsp" flush="true"/>

<header>
    <jsp:include page="fragments/header-menu.jsp" flush="true"/>
</header>

<section class="login-page">
    <h2>Lista wiadomości</h2>

    <div class="table-responsive">
        <table class="table">
            <thead>
            <tr>
                <th>ID</th>
                <th>IMIĘ</th>
                <th>NAZWISKO</th>
                <th>AKCJE</th>
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
<%--                <tr>--%>
                    <td><c:out value="${message.id}"/></td>
                    <td><c:out value="${message.name}"/></td>
                    <td><c:out value="${message.surname}"/></td>
                    <td>
                        <button onclick="document.location='/admin/messages/view?id=${message.id}'">Czytać</button>
                        <button onclick="document.location='/admin/messages/delete?id=${message.id}'">Usuń</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</section>

<jsp:include page="fragments/footer.jsp" flush="true"/>