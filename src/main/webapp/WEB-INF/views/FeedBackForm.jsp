<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<style>
    <%@include file="../styles/home_style.css"%>
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<header><a href="/" class="logo">HR Application</a>
    <select class="selectLanguage" onchange="location = this.value;">
        <option><spring:message code="select.language"/></option>
        <option value="${pageContext.request.contextPath}?lang=ru">Ru</option>
        <option value="${pageContext.request.contextPath}?lang=en">En</option>
    </select>
</header>
<div class="main-block">
<h1 align="center"><spring:message code="feedback.add"/></h1>
<div class="user_form">
        <form:form action="SaveFeedback" method="post" modelAttribute="feedback">
        <form:hidden path="id"/>
        <table>
            <tr>
                <td><spring:message code="feedback.reason"/>:</td>
                <td><form:input path="reason"/></td>
                <td><form:errors path="reason"/></td>
            </tr>
            <tr>
                <td><spring:message code="feedback.feedback"/>:</td>
                <td><form:select path="feedbackState" items="${feedbackstates}">
                </form:select></td>
            </tr>
            <tr>
                <td><spring:message code="feedback.interviewer"/>:</td>
                <td><form:select path="idInterviewer" items="${developers}"></form:select></td>
            </tr>
            <tr>
                <td><spring:message code="feedback.interview"/>:</td>
                <td><form:select path="idInterview" items="${interviews}"></form:select></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value=<spring:message code="save"/>></td>
            </tr>
        </table>
        </form:form>
</div>
</div>
<footer> by Team-3</footer>
</body>

</html>