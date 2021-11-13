<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
    <c:when test="${cookie['lang'].value == param.lang}">
        <fmt:setLocale value="${cookie['lang'].value}"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="${param.lang}"/>
    </c:otherwise>
</c:choose>
<fmt:setBundle basename="messages"/>

<footer>

    <div class="contact" id="contact">
        <sec:authorize access="!isAuthenticated()">
            <c:if test="${pageContext.request.httpServletMapping.matchValue == 'WEB-INF/views/index'}">
                <h2><spring:message code="footer.h2.message"/></h2>
                <h3><spring:message code="footer.h3.message"/></h3>
                <form class="form--contact" method="post">
                    <div class="form-group form-group--50">
                        <input type="text" name="name" placeholder="<spring:message code="footer.placeholder.name"/>"/></div>
                    <div class="form-group form-group--50">
                        <input type="text" name="surname" placeholder="<spring:message code="footer.placeholder.surname"/>"/>
                    </div>
                    <div class="form-group">
                        <textarea name="message" placeholder="<spring:message code="footer.placeholder.message"/>" rows="1"></textarea></div>
                    <button class="btn" type="submit"><spring:message code="footer.btn.submit.title"/></button>
                </form>
            </c:if>
        </sec:authorize>
    </div>
    <div class="bottom-line">
        <span class="bottom-line--copy">Copyright &copy; 2021</span>
        <div class="bottom-line--icons">
            <a href="#" class="btn btn--small"><img src="<c:url value="../../../resources/images/icon-facebook.svg"/>"/></a>
            <a href="#" class="btn btn--small"><img
                    src="<c:url value="../../../resources/images/icon-instagram.svg"/>"/></a>
        </div>
    </div>
</footer>

<script type="text/javascript" src="<c:url value="../../../resources/js/app.js"/>"></script>
</body>
</html>