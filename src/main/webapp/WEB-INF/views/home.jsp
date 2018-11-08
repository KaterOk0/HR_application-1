<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <style>
        <%@include file="../styles/home_style.css"%>
    </style>

    <head>
        <title>Home</title>
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
            <div class="menu">
                <a class="menu-point" href="ViewUserForm"><spring:message code="show.user"/></a>
                <a class="menu-point" href="ViewVacancyForm"><spring:message code="show.vacancy"/></a>
                <a class="menu-point" href="ViewCandidateForm"><spring:message code="show.candidate"/></a>
                <a class="menu-point" href="ViewSkillForm"><spring:message code="show.skill"/></a>
                <a class="menu-point" href="InterviewView"><spring:message code="show.interview"/></a>
                <a class="menu-point" href="ViewFeedbackForm"><spring:message code="show.feedback"/></a>
            </div>
        </div>

    <footer> by Team-3</footer>
</body>
</html>
