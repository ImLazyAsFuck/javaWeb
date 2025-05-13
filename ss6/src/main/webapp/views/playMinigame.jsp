<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 13/05/2025
  Time: 11:13 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Trò chơi Oẳn Tù Tì</h1>
<h3>Lựa chọn của bạn</h3>
<form action="${pageContext.request.contextPath}/playMiniGameServlet" method="post">
    <div class="radio-group">
        <label><input type="radio" name="playerChoice" value="1" required> Búa</label>
        <label><input type="radio" name="playerChoice" value="2"> Kéo</label>
        <label><input type="radio" name="playerChoice" value="3"> Lá</label>
    </div>
    <input type="submit" value="Chơi">
</form>
<h3>Kết quả</h3>
<div class="result">
    <p>Lựa chọn của bạn: <%= request.getAttribute("playerChoice") != null ? request.getAttribute("playerChoice") : "Chưa chọn" %></p>
    <p>Lựa chọn của máy tính: <%= request.getAttribute("computerChoice") != null ? request.getAttribute("computerChoice") : "Chưa có" %></p>
    <p>Kết quả: <%= request.getAttribute("result") != null ? request.getAttribute("result") : "Chưa chơi" %></p>
</div>
</body>
</html>
