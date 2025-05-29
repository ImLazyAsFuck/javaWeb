<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 29/05/2025
  Time: 8:41 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/product/list">back to product</a>
    <table border="1">
        <thead>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>price</th>
            <th>quantity</th>
            <th>total</th>
            <th>action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${carts}" var="cart">
            <tr>
                <td>${cart.cartId}</td>
                <td>${cart.product.name}</td>
                <td>${cart.product.price} VND</td>
                <td>${cart.quantity}</td>
                <td>${cart.product.price * cart.quantity}VND</td>
                <td>
                    <a href="/cart/delete/${cart.cartId}">delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
