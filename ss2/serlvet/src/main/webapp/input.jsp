<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nhập Thông Tin</title>
</head>
<body>
<h2>Nhập Tên và Tuổi</h2>
<form action="/user-info-servlet" method="post">
    <label for="name">Tên:</label>
    <input type="text" name="name" required><br><br>

    <label for="age">Tuổi:</label>
    <input type="number" name="age" required><br><br>

    <button type="submit">Gửi</button>
</form>
</body>
</html>