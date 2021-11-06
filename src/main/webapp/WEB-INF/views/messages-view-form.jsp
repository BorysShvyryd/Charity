<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="fragments/header.jsp" flush="true"/>

<header>
    <jsp:include page="fragments/header-menu.jsp" flush="true"/>
</header>

<section class="login-page">
    <h2>Wiadomość</h2>
    <form:form modelAttribute="message" method="post">

        <div class="form-group">
            <div><form:input path="name" title="IMIĘ" disabled="true"/></div>
            <div><form:input path="surname" title="NAZWISKO" disabled="true"/></div>
        </div>

        <div class="form-group">
            <form:textarea path="message" title="Wiadomość" disabled="true"/>
        </div>
<%--        onclick="document.location='/admin/messages/list'"--%>
        <div class="form-group form-group--buttons">
            <button class="btn" type="submit" >Powrót</button>
        </div>
    </form:form>
</section>

<jsp:include page="fragments/footer.jsp" flush="true"/>