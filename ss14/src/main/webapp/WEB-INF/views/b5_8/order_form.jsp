<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 29/05/2025
  Time: 9:37 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>${not empty order.orderId ? 'Chỉnh sửa' : 'Tạo'} đơn hàng</h2>

<form action="${pageContext.request.contextPath}/orders/save" method="post">
  <c:choose>
    <c:when test="${not empty order.orderId}">
      Mã đơn hàng: <input type="text" name="orderId" value="${order.orderId}" readonly><br>
    </c:when>
    <c:otherwise>
      Mã đơn hàng: <input type="text" name="orderId" required><br>
    </c:otherwise>
  </c:choose>

  Tên người dùng: <input type="text" name="username" value="${order.username}" required><br>
  Tên sản phẩm: <input type="text" name="productName" value="${order.productName}" required><br>
  Số lượng: <input type="number" name="quantity" value="${order.quantity}" required><br>

  <button type="submit">Lưu</button>
</form>

<a href="${pageContext.request.contextPath}/orders">Quay lại danh sách</a>

</body>
</html>
