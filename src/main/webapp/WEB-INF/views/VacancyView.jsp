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
<header><a href="back">HR Application</a></header>
<div class="main-block">
<h1 align="center"><spring:message code="show.vacancy"/></h1>

<p align="center"><a href="VacancyCreate" class="create-button"><spring:message code="vacancy.create"/></a></p>

<div class="filter-sort">
	<spring:message code="filter"/>:
	<a href="ViewVacancyForm" class="filter-sort-item"><spring:message code="find"/></a>
	<spring:message code="sort"/>:
    <a href="SortSalaryFrom" class="filter-sort-item">SalaryFrom</a>
	<a href="SortSalaryTo" class="filter-sort-item">SalaryTo</a>
	<a href="SortExperience" class="filter-sort-item">Experience</a>
</div>

    <!--  <p>Filter by | who create
    	<form action="FilterByCreate" method="GET">
    		<td><select path="dev" items="${developers}"></select>
    		<td><input type="submit" value="OK"></td>
    	</form>-->
</span>
<table border="1">
	<tr>
		<th><spring:message code="vacancy.position"/></th>
		<th><spring:message code="vacancy.salaryFrom"/></th>
		<th><spring:message code="vacancy.salaryTo"/></th>
		<th><spring:message code="vacancy.skills"/></th>
		<th><spring:message code="vacancy.developer"/></th>
		<th><spring:message code="vacancy.skills"/></th>
		<th><spring:message code="vacancy.edit"/></th>
		<th><spring:message code="vacancy.delete"/></th>
	</tr>
	<c:forEach var="vacancy" items="${list}">
		<tr>
			<td><c:out value="${vacancy.position}"/></td>
			<td><c:out value="${vacancy.salaryFrom}"/></td>
			<td><c:out value="${vacancy.salaryTo}"/></td>
			<td><c:out value="${vacancy.experienceYearRequire}"/></td>
			<td><c:out value="${vacancy.developerName}"/></td>
			<td>
				<c:forEach var="skill" items="${vacancy.skills}">
					<c:out value="${skill}"/>|
				</c:forEach>
			</td>
			<td><a href="VacancyEdit?id=${vacancy.id}"><spring:message code="vacancy.edit"/></a></td>
			<td><a href="VacancyDelete?id=${vacancy.id}"><spring:message code="vacancy.delete"/></a></td>
		</tr>
	</c:forEach>
</table>
</div>
<footer> by Team-3</footer>
</body>

</html>