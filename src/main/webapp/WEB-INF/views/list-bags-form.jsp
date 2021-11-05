<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="fragments/header.jsp" flush="true"/>

<header>
    <jsp:include page="fragments/header-menu.jsp" flush="true"/>
</header>

<section class="login-page">
    <h2>Lista wszystkich moich darów</h2>

    <div class="table-responsive">
    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>KATEGORIE</th>
            <th>LICZBĘ WORKÓW</th>
            <th>ORGANIZACJE</th>
            <th>ULICA</th>
            <th>MIASTO</th>
            <th>KOD POCZTOWY</th>
            <th>NUMER TELEFONU</th>
            <th>DATA</th>
            <th>GODZINA</th>
            <th>UWAGI</th>
            <th>AKCJE</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="donation" items="${donationsList}">
            <tr>
                <td><c:out value="${donation.id}"/></td>
                <td><c:out value="${donation.categories}"/></td>
                <td><c:out value="${donation.quantity}"/></td>
                <td><c:out value="${donation.institution.name}"/></td>
                <td><c:out value="${donation.street}"/></td>
                <td><c:out value="${donation.city}"/></td>
                <td><c:out value="${donation.zipCode}"/></td>
                <td><c:out value="${donation.phone}"/></td>
                <td><c:out value="${donation.pickUpDate}"/></td>
                <td><c:out value="${donation.pickUpTime}"/></td>
                <td><c:out value="${donation.pickUpComment}"/></td>
                <td>
                    <button>Przejrzeć</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </div>
    <%--    <form method="post">--%>
    <%--        <div class="form-group">--%>
    <%--            <input type="email" name="username" placeholder="Email" />--%>
    <%--        </div>--%>
    <%--        <div class="form-group">--%>
    <%--            <input type="password" name="password" placeholder="Hasło" />--%>
    <%--            <a href="#" class="btn btn--small btn--without-border reset-password">Przypomnij hasło</a>--%>
    <%--        </div>--%>
    <%----%>
    <%--        <div class="form-group form-group--buttons">--%>
    <%--            <a href="/register" class="btn btn--without-border">Załóż konto</a>--%>
    <%--            <button class="btn" type="submit">Zaloguj się</button>--%>
    <%--        </div>--%>
    <%----%>
    <%--        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
    <%--    </form>--%>
</section>

<jsp:include page="fragments/footer.jsp" flush="true"/>