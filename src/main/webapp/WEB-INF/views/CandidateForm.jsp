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
<header><a href="back">HR Application</a></header>
<div class="main-block">

<h1 align="center"><spring:message code="candidate.add"/></h1>
        <form:form action="SaveCandidate" method="post" modelAttribute="candidate">
        <form:hidden path="id"/>
        <table>
            <tr>
                <td><spring:message code="candidate.name"/>:</td>
                <td><form:input path="name" /></td>
                <td><form:errors path="name"/></td>
            </tr>
            <tr>
                <td><spring:message code="candidate.surname"/>:</td>
                <td><form:input path="surname"/></td>
                <td><form:errors path="surname"/></td>
            </tr>
            <tr>
                <td><spring:message code="candidate.birthday"/>:</td>
                <td><form:input type="date" path="birthday"/></td>

                <td><form:errors path="birthday"/></td>
            </tr>
            <tr>
                <td><spring:message code="candidate.salary"/>:</td>
                <td><form:input path="salary" /></td>
                <td><form:errors path="salary"/></td>
            </tr>
            <tr>
            	<td><spring:message code="candidate.state"/>:</td>
            	<td><form:select path="candidateState" items="${candidatestate}"></form:select>
            </tr>
            <tr>
            	<td><spring:message code="candidate.skills"/>:</td>
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