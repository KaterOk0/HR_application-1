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
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
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

			<h1 align="center"><spring:message code="user.user"/></h1>

				<p align="center">
					<a href="UserCreate" class="create-button"><spring:message code="user.add"/></a>
				</p>

				<div class="filter-sort">
					<spring:message code="filter"/>:
					<a href="ViewUserForm" class="filter-sort-item">|  show all</a>
					<a href="UserFilter?type=manager" class="filter-sort-item">|  manager</a>
					<a href="UserFilter?type=developer" class="filter-sort-item">|  developer</a>
				</div>

			<table border="1">
				<tr>
					<th>
						<span><spring:message code="user.name"/></span>
						<a href="UserSortName" class="filter-sort-item">↓</a>
						<%--<a href="UserSortNameReverse" class="filter-sort-item">↑</a>--%>
					</th>
					<th>
						<spring:message code="user.surname"/>
						<a href="UserSortSurName" class="filter-sort-item">↓</a>
					</th>
					<th>
						Email
						<a href="UserSortName" class="filter-sort-item">↓</a>
					</th>
					<th>
						<spring:message code="user.password"/>
					</th>
					<th>
						<spring:message code="user.role"/>
					</th>
					<th>
						<spring:message code="user.edit"/>
					</th>
					<th>
						<spring:message code="user.delete"/>
					</th>
				</tr>

			<c:forEach var="user" items="${list}">
				<tr>
					<td>
						<c:out value="${user.name}"></c:out>
					</td>
					<td>
						<c:out value="${user.surname}"/>
					</td>
					<td>
						<c:out value="${user.email}"/>
					</td>
					<td>
						<c:out value="${user.password}"/>
					</td>
					<td>
						<c:out value="${user.role}"></c:out>
					</td>
					<td>
						<a href="UserEdit?id=${user.id}"><spring:message code="edit"/></a>
					</td>
					<td>
						<a href="UserDelete?id=${user.id}"><spring:message code="delete"/></a>
					</td>
				</tr>
			</c:forEach>
		</table>
		</div>
	<footer> by Team-3</footer>
</body>
</html>