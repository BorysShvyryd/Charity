<%@ page import="java.util.Locale" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<li><a href="" onclick="document.cookie = 'lang = en; path=/'">
    <img src="../../../resources/images/flags/us.svg" title="<fmt:message key="string.lang.en"/>">
    <fmt:message key="label.lang.en"/>
</a></li>
<li><a href="" onclick="document.cookie = 'lang = uk; path=/'">
    <img src="../../../resources/images/flags/ua.svg" title="<fmt:message key="string.lang.uk"/>">
    <fmt:message key="label.lang.uk"/>
</a></li>
<li><a href="" onclick="document.cookie = 'lang = pl; path=/'">
    <img src="../../../resources/images/flags/pl.svg" title="<fmt:message key="string.lang.pl"/>">
    <fmt:message key="label.lang.pl"/>
</a></li>
<li><a href="" onclick="document.cookie = 'lang = ru; path=/'">
    <img src="../../../resources/images/flags/ru.svg" title="<fmt:message key="string.lang.ru"/>">
    <fmt:message key="label.lang.ru"/>
</a></li>
<%
    Locale locale = request.getLocale();
    String language = locale.getLanguage();
    out.println("Language : " + language  + "<br />");
%>