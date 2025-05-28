<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 28/05/2025
  Time: 11:31 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/">index</a>
<div>
    <a href="?lang=en">English</a>
    <a href="?lang=vi">Tiếng Việt</a>
</div>
<form:form modelAttribute="user" action="${pageContext.request.contextPath}/register" method="post">
    <div>
        <form:label path="username"><spring:message code="user.username"/>: </form:label>
        <form:input path="username" />
        <form:errors path="username" cssClass="error" />
    </div>

    <div>
        <form:label path="password"><spring:message code="user.password"/>:</form:label>
        <form:password path="password" />
        <form:errors path="password" cssClass="error" />
    </div>

    <div>
        <form:label path="confirmPassword"><spring:message code="user.confirmPassword"/>:</form:label>
        <form:password path="confirmPassword" />
        <form:errors path="confirmPassword" cssClass="error" />
    </div>

    <div>
        <form:label path="email"><spring:message code="user.email"/>:</form:label>
        <form:input path="email" />
        <form:errors path="email" cssClass="error" />
    </div>

    <form:button type="submit" >
        <spring:message code="user.register"/>
    </form:button>
</form:form>

<c:if test="${not empty success}">
    <p style="color:green;">${success}</p>
</c:if>


<c:if test="${not empty errors}">
    <ul style="color:red;">
        <c:forEach var="err" items="${errors}">
            <li>${err}</li>
        </c:forEach>
    </ul>
</c:if>
</body>
</html>
