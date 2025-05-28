<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 28/05/2025
  Time: 9:59 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
</head>
<body>
<a href="/">index</a>

<h2><spring:message code="title" /></h2>

<form action="/category/add" method="post">
    <label><spring:message code="category.name" /> (VI):</label>
    <input type="text" name="categoryNameVi" />

    <label><spring:message code="category.description" /> (VI):</label>
    <input type="text" name="descriptionVi" />

    <label><spring:message code="category.name" /> (EN):</label>
    <input type="text" name="categoryNameEn" />

    <label><spring:message code="category.description" /> (EN):</label>
    <input type="text" name="descriptionEn" />

    <button type="submit"><spring:message code="submit" /></button>
</form>

</body>
</html>
