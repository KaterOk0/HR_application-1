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

		<h1 align="center"><spring:message code="show.interview"/></h1>
		<p align="center"><a href="InterviewCreate" class="create-button"><spring:message code="interview.create"/></a></p>


		<div class="filter-sort">
			<spring:message code="filter"/>: <a href="InterviewView" class="filter-sort-item"><spring:message code="find"/></a>
		</div>

		<table border="1">
			<tr>
				<th>
					<spring:message code="interview.name"/>
					<a href="SortInterviewName" class="filter-sort-item">↓</a>
				</th>
				<th>
					<spring:message code="interview.factDate"/>
					<a href="SortFactDate" class="filter-sort-item">↓</a>
				</th>
				<th>
					<spring:message code="interview.planDate"/>
					<spring:message code="sort"/>: <a href="SortPlanDate" class="filter-sort-item">↓</a>
				</th>
				<th>
					<spring:message code="interview.vacancy"/>
					<a href="SortVacancy" class="filter-sort-item">↓</a></th>
				<th>
					<spring:message code="interview.candidate"/>
					<a href="SortCandidate" class="filter-sort-item">↓</a>
				</th>
				<th><spring:message code="interview.edit"/></th>
				<th><spring:message code="interview.delete"/></th>
			</tr>
			<c:forEach var="interview" items="${list}">
				<tr>
					<td><c:out value="${interview.name}"/></td>
					<td><c:out value="${interview.factDate}"/></td>
					<td><c:out value="${interview.planDate}"/></td>
					<td><c:out value="${interview.vacancyName}"/></td>
					<td><c:out value="${interview.candidateName}"/></td>
					<td><a href="InterviewEdit?id=${interview.id}"><spring:message code="edit"/></a></td>
					<td><a href="InterviewDelete?id=${interview.id}"><spring:message code="delete"/></a></td>
			</c:forEach>
		</table>
	</div>
	<footer> by Team-3</footer>
	</body>
</html>