<%@ page import="com.example.model.Book" %>
<%@ page import="java.util.List" %>
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
            flex-direction: column;
            padding: 40px 20px;
            gap: 30px;
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
<form method="POST" action="home">
    <h2>Thêm sách</h2>
    <label>
        Tiêu đề:
        <input type="text" name="title" />
    </label><label>
        Tác giả:
        <input type="text" name="author" />
    </label><label>
        Năm:
        <input type="number" name="year" />
    </label>
    <button type="submit">Xác nhận</button>
</form>

<form method="GET" action="home">
    <input type="text" name="search" placeholder="Tìm sách theo tiêu đề..." />
    <button type="submit">Tìm kiếm</button>
</form>

<table>
    <thead>
        <tr>
            <th>Tiêu đề</th>
            <th>Tác giả</th>
            <th>Năm</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <%
                List<Book> books = (List<Book>) request.getAttribute("books");
                if (books == null || books.isEmpty()) {
            %>
            <td colspan="3">Không có sách</td>
            <%
            }else{
                for(Book book: books){
            %>
                <td><%= book.getTitle() %></td>
                <td><%= book.getAuthor() %></td>
                <td><%= book.getYear() %></td>
            <%
                }
                }
            %>
        </tr>
    </tbody>
</table>
<a href="/">Back</a>
</body>
</html>
