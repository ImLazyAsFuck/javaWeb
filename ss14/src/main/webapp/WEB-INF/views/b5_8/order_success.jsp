<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 29/05/2025
  Time: 10:02 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${not empty message}">
  <p style="color: green;">${message}</p>
</c:if>
<a href="${pageContext.request.contextPath}/order/view">Xem đơn hàng đã đặt</a>

</body>
</html>
