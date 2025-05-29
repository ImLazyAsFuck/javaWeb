<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 29/05/2025
  Time: 10:01 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Đơn Hàng Của Bạn</h2>
<c:if test="${not empty order}">
    <p><strong>Người đặt:</strong> ${order.username}</p>
    <p><strong>Sản phẩm:</strong> ${order.productName}</p>
    <p><strong>Số lượng:</strong> ${order.quantity}</p>
</c:if>
<c:if test="${empty order}">
    <p>Chưa có đơn hàng nào được đặt!</p>
</c:if>
<a href="${pageContext.request.contextPath}/orders">Đặt đơn hàng khác</a>

</body>
</html>
