<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="messages"/>

<jsp:include page="fragments/header.jsp" flush="true"/>

<header class="header--main-page">

    <jsp:include page="fragments/header-menu.jsp" flush="true"/>

    <div class="slogan container container--90">
        <div class="slogan--item">
            <h1>
                <spring:message code="index.h1-1.inner-text"/><br/>
                <spring:message code="index.h1-2.inner-text"/>
            </h1>
        </div>
    </div>
</header>

<section class="stats">
    <div class="container container--85">
        <div class="stats--item">
            <em>${allBagsReturned}</em>

            <h3><spring:message code="index.h3.count-bags.inner-text"/> </h3>
            <p><spring:message code="index.p.count-bags.inner-text"/> </p>
        </div>

        <div class="stats--item">
            <em>${countDonations}</em>
            <h3><spring:message code="index.h3.count-donation.inner-text"/> </h3>
            <p><spring:message code="index.p.count-donation.inner-text"/> </p>
        </div>

    </div>
</section>

<section class="steps" id="steps">
    <h2><spring:message code="index.h2-steps.inner-text"/> </h2>

    <div class="steps--container">
        <div class="steps--item">
            <span class="icon icon--hands"></span>
            <h3><spring:message code="index.h3-steps-item-1.inner-text"/> </h3>
            <p><spring:message code="index.p-steps-item-1.inner-text"/> </p>
        </div>
        <div class="steps--item">
            <span class="icon icon--arrow"></span>
            <h3><spring:message code="index.h3-steps-item-2.inner-text"/> </h3>
            <p><spring:message code="index.p-steps-item-2.inner-text"/> </p>
        </div>
        <div class="steps--item">
            <span class="icon icon--glasses"></span>
            <h3><spring:message code="index.h3-steps-item-3.inner-text"/> </h3>
            <p><spring:message code="index.p-steps-item-3.inner-text"/> </p>
        </div>
        <div class="steps--item">
            <span class="icon icon--courier"></span>
            <h3><spring:message code="index.h3-steps-item-4.inner-text"/> </h3>
            <p><spring:message code="index.p-steps-item-4.inner-text"/> </p>
        </div>
    </div>

    <a href="/register" class="btn btn--large"><spring:message code="index.a.register.inner-text"/> </a>
</section>

<section class="about-us" id="about-us">
    <div class="about-us--text">
        <h2><spring:message code="index.h2.about.inner-text"/> </h2>
        <p><spring:message code="index.p.about.inner-text"/> </p>
        <img src="<c:url value="../../resources/images/signature.svg"/>" class="about-us--text-signature"
             alt="Signature"/>
    </div>
    <div class="about-us--image"><img src="<c:url value="../../resources/images/about-us.jpg"/>"
                                      alt="People in circle"/>
    </div>
</section>

<c:if test="${institutions.size()>0}">
    <section class="help" id="help">
        <h2><spring:message code="index.h2.help.inner-text"/> </h2>

        <!-- SLIDE 1 -->
        <div class="help--slides active" data-id="1">
            <p><spring:message code="index.p.help.inner-text"/> </p>

            <ul class="help--slides-items">
                <c:forEach begin="0" end="${institutions.size()}" step="2" var="sample">
                    <li>
                        <c:forEach var="institution" items="${institutions}" begin="${sample}" end="${sample + 1}">
                            <div class="col">
                                <div class="title">${institution.name}</div>
                                <div class="subtitle">${institution.description}</div>
                            </div>
                        </c:forEach>
                    </li>
                </c:forEach>
            </ul>

        </div>

    </section>
</c:if>

<jsp:include page="fragments/footer.jsp" flush="true"/>
