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

	<h1 align="center"><spring:message code="show.interview"/></h1>
	<p align="center"><a href="InterviewCreate" class="create-button"><spring:message code="interview.create"/></a></p>


	<div class="filter-sort">
		<spring:message code="filter"/>: <a href="InterviewView" class="filter-sort-item"><spring:message code="find"/></a>
		<spring:message code="sort"/>: <a href="SortPlanDate" class="filter-sort-item">Plan date</a>
				<a href="SortFactDate" class="filter-sort-item">Fact date</a>
	</div>


	<table border="1">
		<tr>
			<th><spring:message code="interview.name"/></th>
			<th><spring:message code="interview.factDate"/></th>
			<th><spring:message code="interview.planDate"/></th>
			<th><spring:message code="interview.vacancy"/></th>
			<th><spring:message code="interview.candidate"/></th>
			<th><spring:message code="interview.edit"/></th>
			<th><spring:message code="interview.delete"/></th>
		</tr>
		<c:forEach var="interview" items="${list}">
			<tr>
				<td><c:out value="${interview.name}"/></td>
				<td><c:out value="${interview.factDate}"/></td>
				<td><c:out value="${interview.planDate}"/></td>
				<td><c:out value="${interview.vacancyname}"/></td>
				<td><c:out value="${interview.candidatename}"/></td>
			    <td><a href="InterviewEdit?id=${interview.id}"><spring:message code="edit"/></a></td>
			    <td><a href="InterviewDelete?id=${interview.id}"><spring:message code="delete"/></a></td>
		</c:forEach>
	</table>
</div>
<footer> by Team-3</footer>
</body>
</html>