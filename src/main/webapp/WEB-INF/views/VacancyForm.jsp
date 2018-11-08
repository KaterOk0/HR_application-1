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
            <h1 align="center"><spring:message code="vacancy.create"/></h1>
                <form:form action="SaveVacancy" method="post" modelAttribute="vacancy">
                <form:hidden path="id"/>
                    <table>
                        <tr>
                            <td><spring:message code="vacancy.position"/>:</td>
                            <td><form:input path="position" /></td>
                            <td><form:errors path="position"/></td>
                        </tr>
                        <tr>
                            <td><spring:message code="vacancy.salaryFrom"/>:</td>
                            <td><form:input path="salaryFrom"/></td>
                            <td><form:errors path="salaryFrom"/></td>
                        </tr>
                        <tr>
                            <td><spring:message code="vacancy.salaryTo"/>:</td>
                            <td><form:input path="salaryTo"/></td>
                            <td><form:errors path="salaryTo"/></td>

                        </tr>
                        <tr>
                            <td><spring:message code="vacancy.experience"/>:</td>
                            <td><form:input path="experienceYearRequire" /></td>
                            <td><form:errors path="experienceYearRequire"/></td>
                        </tr>
                        <tr>
                            <td><spring:message code="vacancy.developer"/>:</td>
                            <td><form:select path="idDeveloper" items="${developers}"></form:select>
                        </tr>
                        <tr>
                            <td><spring:message code="vacancy.skills"/>:</td>
                            <td><form:select path="skills" items="${skills}"></form:select></td>
                            <td><form:errors path="skills"/></td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center"><input type="submit" value=<spring:message code="save"/>></td>
                        </tr>
                    </table>
                </form:form>
            </div>
        <footer> by Team-3</footer>
    </body>
</html>