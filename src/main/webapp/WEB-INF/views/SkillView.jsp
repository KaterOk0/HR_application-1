<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
    <h1 align="center"><spring:message code="show.skill"/></h1>
    <p align="center"><a href="SkillCreate" class="create-button"><spring:message code="skill.create"/></a></p>

    <div class="filter-sort">
		<spring:message code="filter"/> <a href="ViewSkillForm" class="filter-sort-item"><spring:message code="find"/></a>
		<spring:message code="sort"/>: <a href="SkillSort" class="filter-sort-item">name</a>
    </div>

	<table border="1">
		<tr>
			<th><spring:message code="skill.skill"/></th>
			<th><spring:message code="skill.delete"/></th>
		</tr>
		<c:forEach var="skill" items="${list}">
			<tr>
				<td><c:out value="${skill.name}"/></td>
				<td><a href="SkillDelete?name=${skill.name}"><spring:message code="delete"/></a></td>
			</tr>
		</c:forEach>
	</table>
</div>
        <footer> by Team-3</footer>
</body>
</html>