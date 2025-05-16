<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 15/05/2025
  Time: 4:01 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css"
          rel="stylesheet">
</head>
<body>
  <form action="/login" method="post">
       <input placeholder="username" type="text" name="username">
      <c:if test="${not empty error}">
          <p style="color: red">${error}</p>
      </c:if>
      <c:if test="${not empty errorUsername}">
          <p style="color: red">${errorUsername}</p>
      </c:if>
       <input placeholder="password" type="password" name="password">
      <c:if test="${not empty error}">
          <p style="color: red">${error}</p>
      </c:if>
      <c:if test="${not empty errorPassword}">
          <p style="color: red">${errorPassword}</p>
      </c:if>
      <button type="submit">Login</button>
   </form>
    <a href="/">back</a>
</body>
</html>
