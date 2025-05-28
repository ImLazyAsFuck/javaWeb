<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 28/05/2025
  Time: 9:58 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
</head>
<body>
<a href="/">index</a>
<h2><spring:message code="title" /></h2>
<a href="<c:url value="/category/add"/>"><spring:message code="submit" /></a>
<div>
    <a href="?lang=en">English</a> |
    <a href="?lang=vi">Tiếng Việt</a> |
</div>
<table border="1">
    <thead>
    <tr>
        <th><spring:message code="category.id" /></th>
        <th><spring:message code="category.name" /></th>
        <th><spring:message code="category.description" /></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="cat" items="${categories}">
        <tr>
            <td>${cat.id}</td>
            <td>${cat.categoryName}</td>
            <td>${cat.description}</td>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
