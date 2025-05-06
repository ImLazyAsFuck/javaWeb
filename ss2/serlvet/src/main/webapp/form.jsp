<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Nhập thông tin</title>
</head>
<body>
<h2>Nhập thông tin cá nhân</h2>
<form action="/forward-servlet" method="post">
  Họ tên: <input type="text" name="fullname"><br>
  Tuổi: <input type="number" name="age"><br>
  <input type="submit" value="Gửi đi">
</form>
</body>
</html>