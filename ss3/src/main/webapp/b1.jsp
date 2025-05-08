<%@ page contentType="text/html;charset=UTF-8"  language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        String name = "Vy";
        int age = 20;
    %>
    <%!
        String greating(String name, int age){
            return "Xin chào " + name + " với tuổi là: " + age;
        }
    %>
    <h1>Information</h1>
    <p><%=greating(name, age)%></p>
    <a href="index.jsp">back</a>
</body>
</html>
