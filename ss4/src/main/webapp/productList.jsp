<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <td>${p.name}</td>
                <td>${p.price}</td>
                <td>${p.description}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="/">back</a>
</body>
</html>
