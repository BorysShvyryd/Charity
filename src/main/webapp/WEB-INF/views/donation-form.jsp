<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="fragments/header.jsp" flush="true"/>

<form:form modelAttribute="donation">
    <form:hidden path="id"/>
    <form:checkboxes path="categories"
                     items="${categories}"/>
    <form:select path="institution" items="${institutions}"/>
    <form:input path="zipCode"/>
    <form:input path="street"/>
    <form:input path="city"/>
    <form:input path="quantity"/>
    <form:textarea path="pickUpComment"/>
    <form:input type="date" path="pickUpDate"/>
    <form:input type="time" path="pickUpTime"/>
    <input type="submit">
</form:form>

<jsp:include page="fragments/footer.jsp" flush="true"/>