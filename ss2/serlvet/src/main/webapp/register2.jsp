<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Form Đăng Ký</h2>
<form action="/user-registration-servlet" method="post">
    Tên: <input type="text" name="name" required><br><br>
    Email: <input type="email" name="email" required><br><br>
    Mật khẩu: <input type="password" name="password" required><br><br>
    <input type="submit" value="Đăng ký">
</form>
</body>
</html>
