<%@ page import="static java.lang.Integer.parseInt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <%
        String first = request.getParameter("first");
        String second = request.getParameter("second");
        int result = 0;
        boolean valid = true;

        if (first != null && second != null && !first.isEmpty() && !second.isEmpty()) {
            try {
                int firstnum = Integer.parseInt(first);
                int secondnum = Integer.parseInt(second);
                result = firstnum + secondnum;
            } catch (NumberFormatException e) {
                valid = false;
            }
        } else {
            valid = false;
        }
    %>
    <form method="post" action="b4.jsp">
        <h2>Bảng tính cộng: </h2>
        <input style="width: 5%; padding: 5px;" type="number" name="first">
        +
        <input style="width: 5%; padding: 5px;" type="number" name="second">
        <button style="padding: 5px" type="submit">=</button>
    </form>
    <% if (first != null && second != null) { %>
    <h3>Kết quả:</h3>
    <% if (valid) { %>
    <p><%= result %></p>
    <% } else { %>
    <p style="color: red;">Vui lòng nhập hai số hợp lệ!</p>
    <% } %>
    <% } %>
    <a href="/">back</a>
</body>
</html>
