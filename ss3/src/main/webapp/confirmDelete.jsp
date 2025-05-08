<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Xác nhận xoá</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: sans-serif;
        }
        body {
            width: 100%;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            background-color: #f4f4f4;
        }
        form {
            background: #fff;
            padding: 20px 30px;
            border-radius: 12px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            display: flex;
            flex-direction: column;
            gap: 15px;
            width: 300px;
        }
        form h2 {
            text-align: center;
            margin-bottom: 10px;
            color: #333;
        }
        button {
            padding: 10px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-weight: bold;
            transition: background-color 0.2s;
        }
        button:hover {
            background-color: #0056b3;
        }
        .cancel {
            padding: 10px;
            background-color: #ff0000;
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-weight: bold;
            transition: background-color 0.2s;
            text-align: center;
            text-decoration: none;
        }
        .cancel:hover {
            background-color: #7a0000;
        }
        form h2 {
            text-align: center;
            margin-bottom: 10px;
            color: #333;
        }
    </style>
</head>
<body>

<%
    String id = request.getParameter("id");
%>

<form method="POST" action="delete-user">
    <h2>Xác nhận xoá người dùng?</h2>
    <input type="hidden" name="id" value="<%= id %>">

    <button type="submit">Xoá</button>
    <a href="list-user" class="cancel">Huỷ</a>
</form>

</body>
</html>
