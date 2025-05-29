<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 29/05/2025
  Time: 9:36 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Danh sách đơn hàng</h2>
<a href="/">index</a>
<a href="${pageContext.request.contextPath}/orders/new">Đặt hàng mới</a>
<br><br>
<c:if test="${not empty message}">
    <p style="color: green;">${message}</p>
</c:if>
<table border="1">
    <tr>
        <th>Người dùng</th><th>Sản phẩm</th><th>Số lượng</th><th>Hành động</th>
    </tr>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>${order.username}</td>
            <td>${order.productName}</td>
            <td>${order.quantity}</td>
            <td>
                <a href="${pageContext.request.contextPath}/orders/view?username=${order.username}&id=${order.orderId}">Chi tiết</a>
                <a href="${pageContext.request.contextPath}/orders/edit?id=${order.orderId}">Sửa</a>
                <a href="${pageContext.request.contextPath}/orders/delete?id=${order.orderId}">Xóa</a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
