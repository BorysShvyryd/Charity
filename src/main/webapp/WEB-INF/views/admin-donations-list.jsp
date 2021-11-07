<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
                <th>ID</th>
                <th>KATEGORII</th>
                <th>LICZBĘ WORKÓW</th>
                <th>FUNDACJI</th>
                <th>ADRES</th>
                <th>UŻYTKOWNIK</th>
                <th>DATA OTRZYMANIA</th>
                <th>DATA PRZENIESIENIA</th>
                <th>AKCJE</th>
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
                <td><c:out value="${donation.user.name}"/></td>
                <td><c:out value="${donation.dateTimeReceived}"/></td>
                <td><c:out value="${donation.dateTimeTransmitted}"/></td>
                <td>
                    <c:if test="${donation.status == 2}">
                        <button onclick="document.location='#'">ZARCHIWIZOWANO</button>
                    </c:if>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <c:if test="${donation.status == 1}">
                            <button onclick="document.location='/admin/donations/transfer?id=${donation.id}'">ODDANY
                            </button>
                        </c:if>
                        <c:if test="${donation.status < 1}">
                            <button onclick="document.location='/admin/donations/devoted?id=${donation.id}'">ODEBRANE
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