<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<%
    String name = request.getParameter("name");
    String email = request.getParameter("email");

    if (name != null && email != null && !name.isEmpty() && !email.isEmpty()) {
        session.setAttribute("userName", name);
        session.setAttribute("userEmail", email);
%>
<h2>Thông tin đã được lưu!</h2>
<p><strong>Tên:</strong> <%= session.getAttribute("userName") %></p>
<p><strong>Email:</strong> <%= session.getAttribute("userEmail") %></p>
<button onclick="window.location.href='b2.jsp'">Nhập lại?</button>
<br/>
<%
} else {
%>
<h2>Nhập thông tin người dùng</h2>
<form method="post" action="b2.jsp">
    Tên: <input type="text" name="name"><br>
    Email: <input type="text" name="email"><br>
    <input type="submit" value="Lưu">
</form>

<%
    }
%>
<a href="index.jsp">back</a>
</body>
</html>
