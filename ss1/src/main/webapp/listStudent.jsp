<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách sinh viên đăng ký vé xe</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        h2 {
            color: #333;
        }
    </style>
</head>
<body>
<h2>Danh sách sinh viên đăng ký vé xe</h2>
<table>
    <tr>
        <th>Họ và Tên</th>
        <th>Lớp</th>
        <th>Loại xe</th>
        <th>Biển số xe</th>
    </tr>
    <c:forEach var="student" items="${studentList}">
        <tr>
            <td>${student.fullName}</td>
            <td>${student.className}</td>
            <td>${student.vehicleType}</td>
            <td>${student.licensePlate}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>