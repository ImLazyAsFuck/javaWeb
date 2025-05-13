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
    <title>Add Book</title>
</head>
<body>
<form action="/books?action=create" method="post">
  <label>
    <input placeholder="title" type="text" name="title">
  </label>
  <label>
    <input placeholder="author" type="text" name="author">
  </label>
  <label>
    <input placeholder="category" type="text" name="category">
  </label>
  <label>
    <input placeholder="stock" type="number" name="stock">
  </label>
  <button type="submit">Save</button>
</form>
</body>
</html>
