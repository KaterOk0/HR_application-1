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
	<h1 align="center"><spring:message code="candidate.candidate"/></h1>

<p align="center"><a href="CandidateCreate" class="create-button"><spring:message code="candidate.create"/></a></p>
<div class="filter-sort">
	<spring:message code="filter"/>:
<a href="ViewCandidateForm" class="filter-sort-item"><spring:message code="find"/></a>
<a href="CandidateFilter?type=active" class="filter-sort-item">active</a>
<a href="CandidateFilter?type=passive" class="filter-sort-item">passive</a>
Sort by: <a href="CandidateSortSalary" class="filter-sort-item">salary</a>
<a href="CandidateSortName" class="filter-sort-item"><spring:message code="candidate.name"/></a>
</div>

	<table border="1">
		<tr>
			<th><spring:message code="candidate.name"/></th>
			<th><spring:message code="candidate.surname"/></th>
			<th><spring:message code="candidate.birthday"/></th>
			<th><spring:message code="candidate.salary"/></th>
			<th><spring:message code="candidate.state"/></th>
			<th><spring:message code="candidate.skills"/></th>
			<th><spring:message code="candidate.edit"/></th>
			<th><spring:message code="candidate.delete"/></th>
		</tr>
		<c:forEach var="candidate" items="${list}">
			<tr>
				<td><c:out value="${candidate.name}"></c:out></td>
				<td><c:out value="${candidate.surname}"/></td>
				<td><c:out value="${candidate.birthday}"/></td>
				<td><c:out value="${candidate.salary}"/></td>
				<td><c:out value="${candidate.candidateState}"/></td>
				<td>
					<c:forEach var="skill" items="${candidate.skills}">
						<c:out value="${skill}"/>|
					</c:forEach>
				</td>
				<td><a href="CandidateEdit?id=${candidate.id}"><spring:message code="edit"/></a></td>
			    <td><a href="CandidateDelete?id=${candidate.id}"><spring:message code="delete"/></a></td>
			</tr>
		</c:forEach>
	</table>

</div>
<footer> by Team-3</footer>
</body>

</html>