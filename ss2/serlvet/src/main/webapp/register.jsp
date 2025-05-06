<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Đăng ký người dùng</title>
</head>
<body>
<h2>Form Đăng Ký</h2>
<form action="register" method="post">
  <label for="name">Họ và tên:</label><br>
  <input type="text" id="name" name="name" required><br><br>

  <label for="email">Email:</label><br>
  <input type="email" id="email" name="email" required><br><br>

  <button type="submit">Đăng ký</button>
</form>
</body>
</html>
