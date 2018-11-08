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

	<h1 align="center">Show feedback table</h1>
	<p align="center"><a href="FeedbackCreate" class="create-button"><spring:message code="feedback.create"/></a></p>
<div class="filter-sort">
	<spring:message code="filter"/>:
<a href="ViewFeedbackForm" class="filter-sort-item"><spring:message code="find"/></a>
<a href="FeedBackFilter?type=failure" class="filter-sort-item">failure</a>
<a href="FeedBackFilter?type=success" class="filter-sort-item">success</a>
<a href="FeedBackFilter?type=awaiting" class="filter-sort-item">awaiting</a>
</div>
	<table border="1">
		<tr>
			<th><spring:message code="feedback.reason"/></th>
			<th><spring:message code="feedback.feedback"/></th>
			<th><spring:message code="feedback.interviewer"/></th>
			<th><spring:message code="feedback.interview"/></th>
			<th><spring:message code="feedback.edit"/></th>
			<th><spring:message code="feedback.delete"/></th>
		</tr>
		<c:forEach var="feedback" items="${list}">
			<tr>
				<td><c:out value="${feedback.reason}"></c:out></td>
				<td><c:out value="${feedback.feedbackState}"/></td>
				<td><c:out value="${feedback.interviewerName}"/></td>
				<td><c:out value="${feedback.interviewName}"/></td>
				<td><a href="FeedBackEdit?id=${feedback.id}"><spring:message code="edit"/></a></td>
			    <td><a href="FeedBackDelete?id=${feedback.id}"><spring:message code="delete"/></a></td>
			</tr>
		</c:forEach>
	</table>
</div>
<footer> by Team-3</footer>
</body>
</html>