<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="fragments/header.jsp" flush="true"/>

<header class="header--form-page">
    <jsp:include page="fragments/header-menu.jsp" flush="true"/>

    <div class="slogan container container--90">
        <h2>
            ${textMessage}
        </h2>
    </div>
</header>


<jsp:include page="fragments/footer.jsp" flush="true"/>