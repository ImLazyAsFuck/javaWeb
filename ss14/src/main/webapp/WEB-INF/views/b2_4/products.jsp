<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 29/05/2025
  Time: 2:14 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/">index</a>
<a href="/product/add">add product</a>
<a href="/cart/list">carts</a>
<table border="1">
    <thead>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>price</th>
        <th>action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${products}" var="product">
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.price} VND</td>
            <td>
                <a href="/product/${product.id}">detail</a>
                <a href="/product/delete/${product.id}">delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
