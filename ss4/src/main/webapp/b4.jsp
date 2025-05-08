<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<table>
    <thead>
    <tr>
        <th>Mã</th>
        <th>Tên</th>
        <th>Giá</th>
        <th>Mô tả</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="p" items="${products}">
        <tr>
            <td>${p.id}</td>
            <td>
                <c:choose>
                    <c:when test="${not empty p.name}">
                        ${p.name}
                    </c:when>
                    <c:otherwise>
                        Sản phẩm này chưa có tên
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${p.price <= 0}">
                        ${p.price}
                    </c:when>
                    <c:otherwise>
                        Sản phẩm này chưa có giá
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${not empty p.description}">
                        ${p.description}
                    </c:when>
                    <c:otherwise>
                        Sản phẩm này chưa có mô tả
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>

    </tbody>
</table>
<a href="/">back</a>
</body>
</html>
