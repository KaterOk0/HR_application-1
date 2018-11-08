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
        <header><a href="/" class="logo">HR Application</a>
            <select class="selectLanguage" onchange="location = this.value;">
                <option><spring:message code="select.language"/></option>
                <option value="${pageContext.request.contextPath}?lang=ru">Ru</option>
                <option value="${pageContext.request.contextPath}?lang=en">En</option>
            </select>
        </header>

        <div class="main-block">
            <h1 align="center"><spring:message code="user.create"/></h1>
            <form:form action="SaveUser" method="post" modelAttribute="user">
                <table>
                    <tr>
                        <td><spring:message code="user.name"/>:</td>
                        <td><form:input path="name" /></td>
                    </tr>
                    <tr>
                        <td><spring:message code="user.surname"/>:</td>
                        <td><form:input path="surname" /></td>
                    </tr>
                    <tr>
                        <td>Email:</td>
                        <td><form:input path="email" /></td>
                    </tr>
                    <tr>
                        <td><spring:message code="user.password"/>:</td>
                        <td><form:input path="password" /></td>
                    </tr>
                    <tr>
                        <td><spring:message code="user.role"/>:</td>
                        <td><form:select path="role" items="${map}"></form:select>
                    <tr>
                        <td colspan="2" align="center"><input type="submit" value=<spring:message code="save"/>></td>
                    </tr>
                </table>
            </form:form>
        </div>
    <footer> by Team-3</footer>
</body>
</html>