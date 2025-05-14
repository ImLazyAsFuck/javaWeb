<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 14/05/2025
  Time: 7:41 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/employees/add" method="post">
        <input placeholder="name" type="text" name="name">
        <input placeholder="email" type="email" name="email">
        <input placeholder="position" type="text" name="position">
        <button type="submit">Add Employee</button>
    </form>
</body>
</html>
