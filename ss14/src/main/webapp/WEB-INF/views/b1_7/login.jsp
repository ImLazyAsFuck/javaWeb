<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Login</title>
</head>
<body>
<a href="/">index</a>
<div class="login-container">
  <h2>Login</h2>
  <% if (request.getAttribute("error") != null) { %>
  <div style="color: red"><%= request.getAttribute("error") %></div>
  <% } %>
  <form action="/login" method="post">
    <div>
      <label>Username:</label><br/>
      <input type="text" name="username" value="${cookie.rememberMeUsername.value}" required/>
    </div>
    <div>
      <label>Password:</label><br/>
      <input type="password" name="password" required/>
    </div>
    <div>
      <input type="checkbox" name="rememberMe" id="rememberMe"/>
      <label for="rememberMe">Remember me</label>
    </div>
    <button type="submit">Login</button>
  </form>
</div>
</body>
</html>