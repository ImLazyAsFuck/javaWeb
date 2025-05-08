<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh Sách Sinh Viên</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>

<h2>Danh Sách Sinh Viên</h2>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Tên</th>
        <th>Tuổi</th>
        <th>Điểm Trung Bình</th>
    </tr>
    </thead>
    <tbody>
    <c:set var="count" value="0" />
    <c:forEach var="s" items="${students}">
        <tr>
            <td>${s.id}</td>
            <td>${s.name}</td>
            <td>${s.age}</td>
            <td>${s.gpa}</td>
        </tr>
        <c:if test="${s.gpa > 7.0}">
            <c:set var="count" value="${count + 1}" />
        </c:if>
    </c:forEach>
    </tbody>
</table>

<h3>Tổng số sinh viên có điểm trung bình lớn hơn 7.0 có ${count} sinh viên</h3>
<a href="/">back</a>

</body>
</html>
