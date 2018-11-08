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
        <title>HR_app</title>
    </head>
    <body>

    <header>
        <a href="/" class="logo">HR Application</a>
        <select class="selectLanguage" onchange="location = this.value;">
            <option><spring:message code="select.language"/></option>
            <option value="${pageContext.request.contextPath}?lang=ru">Ru</option>
            <option value="${pageContext.request.contextPath}?lang=en">En</option>
        </select>
    </header>

    <div class="main-block">
        <h1 align="center"><spring:message code="interview.create"/></h1>
            <div class="user_form">
                <form:form action="SaveInterview" method="post" modelAttribute="interview">
                <form:hidden path="id"/>
                <table>
                    <tr>
                        <td><spring:message code="interview.name"/>:</td>
                        <td><form:input path="name" /></td>
                        <td><form:errors path="name"/></td>
                    </tr>
                    <tr>
                        <td><spring:message code="interview.planDate"/>:</td>
                        <td><form:input type="date" path="planDate" /></td>
                        <td><form:errors path="planDate"/></td>
                    </tr>
                    <tr>
                        <td><spring:message code="interview.factDate"/>:</td>
                        <td><form:input type="date" path="factDate"/></td>
                        <td><form:errors path="factDate"/></td>
                    </tr>
                    <tr>
                        <td><spring:message code="interview.vacancy"/>:</td>
                        <td><form:select path="idVacancy" items="${vacancys}"></form:select>
                    </tr>
                    <tr>
                        <td><spring:message code="interview.candidate"/>:</td>
                        <td><form:select path="idCandidate" items="${candidates}"></form:select>
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