<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Welcome</title>
</head>
<body>
<div class="welcome-container">
  <h2>Welcome, <%= session.getAttribute("username") %>!</h2>
  <a href="/logout">Logout</a>
</div>
</body>
</html>