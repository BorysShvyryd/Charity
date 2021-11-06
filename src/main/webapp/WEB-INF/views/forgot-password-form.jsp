<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="fragments/header.jsp" flush="true"/>

<header>
    <jsp:include page="fragments/header-menu.jsp" flush="true"/>
</header>

<section class="login-page">
    <h2>Odzyskiwanie hasła</h2>
    <form method="post">
        <div class="form-group">
            <input type="email" name="email" placeholder="Email" />
        </div>

        <div class="form-group form-group--buttons">
            <a href="/login" class="btn btn--without-border">Powrót</a>
            <button class="btn" type="submit">Wyślij e-mail</button>
        </div>

    </form>
</section>

<jsp:include page="fragments/footer.jsp" flush="true"/>