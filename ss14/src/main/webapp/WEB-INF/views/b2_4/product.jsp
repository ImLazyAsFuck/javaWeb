<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 29/05/2025
  Time: 7:59 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Product</h1>
<ul>
    <a href="/product/">back</a>
    <li>Id: ${product.id}</li>
    <li>Name: ${product.name}</li>
    <li>Price: ${product.price}</li>
    <form:form modelAttribute="product"  method="post" action="/product/${product.id}">
        <form:input path="id" cssStyle="display: none"/>
        <form:button type="submit">Add To Cart</form:button>
    </form:form>
</ul>
</body>
</html>
