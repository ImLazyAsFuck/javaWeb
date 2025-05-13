<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 12/05/2025
  Time: 5:19 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Book</title>
</head>
<body>
  <form action="/book?action=update" method="post">
    <input value="${book.title}" type="text" name="title">
    <input value="${book.author}" type="text" name="author">
    <input value="${book.category}" type="text" name="category">
    <input value="${book.stock}" type="number" name="stock">
    <input type="submit" value="Save">
  </form>
</body>
</html>
