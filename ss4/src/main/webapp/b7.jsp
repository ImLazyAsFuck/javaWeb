<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="css/styles.css" rel="stylesheet">
</head>
<body>
<form method="POST" action="b7">
    <label>
        <input type="number" min="0" name="minPrice" placeholder="Min price"/>
    </label>
    <label>
        <input type="number" min="0" name="maxPrice" placeholder="Max price"/>
    </label>
    <button type="submit" >Search</button>
</form>
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
<a href="/">Back</a>
</body>
</html>
