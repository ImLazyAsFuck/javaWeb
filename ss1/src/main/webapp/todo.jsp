<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>To-Do List</title>
    <style>
        body { font-family: Arial, sans-serif; }
        .nav { background: purple; padding: 10px; }
        .nav a { color: white; margin: 0 10px; text-decoration: none; }
        .add-form { margin: 20px 0; }
        table { width: 100%; border-collapse: collapse; }
        th, td { border: 1px solid black; padding: 8px; text-align: left; }
        th { background: purple; color: white; }
        tr:nth-child(even) { background: purple; }
        tr:nth-child(odd) { background: yellow; }
    </style>
</head>
<body>
<div class="nav">
    <a href="#">Trang chủ</a>
    <a href="#">Liên hệ</a>
    <a href="#">Giỏ hàng</a>
    <a href="#">Tài khoản</a>
</div>
<div class="add-form">
    <form action="todo" method="post">
        <input type="text" name="newJob" placeholder="Enter new job">
        <input type="submit" value="Add">
    </form>
</div>
<table>
    <tr>
        <th>ID</th>
        <th>Job Name</th>
        <th>Status</th>
        <th>Action</th>
    </tr>
    <%
        ArrayList<String> tasks = (ArrayList<String>) request.getAttribute("tasks");
        ArrayList<String> statuses = (ArrayList<String>) request.getAttribute("statuses");
        if (tasks != null && !tasks.isEmpty()) {
            for (int i = 0; i < tasks.size(); i++) {
    %>
    <tr>
        <td><%= i + 1 %></td>
        <td><%= tasks.get(i) %></td>
        <td><%= statuses.get(i) %></td>
        <td><a href="todo?taskId=<%= i %>">Change status</a></td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>