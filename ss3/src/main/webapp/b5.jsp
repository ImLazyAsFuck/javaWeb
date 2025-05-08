
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String username = request.getParameter("username");
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    boolean submitted = request.getMethod().equalsIgnoreCase("POST");
     if(!submitted) { %>
    <form method="post" action="b5.jsp">
        <label>Username:
            <input name="username" type="text" />
        </label>
        <label>
            email:
            <input name="email" type="email" />
        </label>
        <label>
            password:
            <input name="password" type="password" />
        </label>
        <button type="submit">Submit</button>
    </form>
    <% } else { %>
        <h2>Đăng ký thành công!</h2>
        <p>Xin chào, <strong><%= username %></strong>!</p>
        <p>Email của bạn là: <strong><%= email %></strong></p>
    <% } %>
    <a href="index.jsp">Back</a>
</body>
</html>
