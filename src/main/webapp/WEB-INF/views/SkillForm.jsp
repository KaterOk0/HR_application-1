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
<h1 align="center"><spring:message code="skill.create"/></h1>
    <div class="user_form">
        <form:form action="SaveSkill" method="post" modelAttribute="skill">
        <table>
            <tr>
                <td><spring:message code="skill.name"/>:</td>
                <td><form:input path="name" /></td>
                <td><form:errors path="name"/></td>
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