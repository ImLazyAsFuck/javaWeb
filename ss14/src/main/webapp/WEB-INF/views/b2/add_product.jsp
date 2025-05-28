<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 29/05/2025
  Time: 2:15 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form method="post" action="/product/add" modelAttribute="product">
    <form:input path="name"/>
    <form:errors cssStyle="color: red" path="name"/>
    <form:input path="price"/>
    <form:errors cssStyle="color: red" path="price"/>
    <form:button type="submit">Add Product</form:button>
</form:form>
</body>
</html>
