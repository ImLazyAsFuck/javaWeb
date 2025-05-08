<%@ page import="java.util.List" %>
<%@ page import="com.example.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Segoe UI', sans-serif;
        }

        body {
            width: 100%;
            min-height: 100vh;
            background-color: #f9f9f9;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 40px 20px;
            flex-direction: column;
            gap: 20px;
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

        label {
            display: flex;
            flex-direction: column;
            font-weight: bold;
            color: #444;
        }

        input {
            margin-top: 5px;
            padding: 8px;
            border-radius: 6px;
            border: 1px solid #ccc;
            outline: none;
            transition: border-color 0.2s;
        }

        input:focus {
            border-color: #007bff;
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

        table {
            width: 80%;
            max-width: 800px;
            border-collapse: collapse;
            background-color: white;
            border-radius: 12px;
            overflow: hidden;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }

        thead {
            background-color: #007bff;
            color: white;
        }

        th, td {
            padding: 12px 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        tbody tr:hover {
            background-color: #f1f1f1;
        }

        td[colspan="3"] {
            text-align: center;
            color: #999;
            font-style: italic;
        }
    </style>
</head>
<body>
    <form method="POST" action="list-user">
        <h2>Add User</h2>
        <label>
            <input type="text" name="name" placeholder="name" />
        </label>
        <label>
            <input type="email" name="email" placeholder="email" />
        </label>
        <button type="submit">Add User</button>
    </form>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Tên</th>
                <th>Email</th>
                <th>Hành động</th>
            </tr>
        </thead>
        <tbody>
            <tr>
            <%
                List<User> users = (List<User>) request.getAttribute("users");
                if(users == null || users.isEmpty()){
            %>
                <td colspan="4">Danh sách người dùng trống</td>
            <%
            }else{
                    for(User user: users){

            %>
                <td><%= user.getId() %></td>
                <td><%= user.getName() %></td>
                <td><%= user.getEmail() %></td>
                <td><a href="delete-user?id=<%= user.getId() %>">Delete</a></td>
                <%
                    }
                    }
                %>
            </tr>
        </tbody>
    </table>
    <a href="/">back</a>
</body>
</html>
