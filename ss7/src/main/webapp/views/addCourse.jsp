<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
  <title>Thêm khóa học</title>
</head>
<body>
<h2>Thêm khóa học mới</h2>
<form action="${pageContext.request.contextPath}/course/add" method="post">
  <label for="name">Tên khóa học:</label>
  <input type="text" id="name" name="name" required><br><br>

  <label for="description">Mô tả:</label>
  <textarea id="description" name="description" required></textarea><br><br>

  <input type="submit" value="Thêm">
</form>
<a href="${pageContext.request.contextPath}/course">Quay lại</a>
</body>
</html>