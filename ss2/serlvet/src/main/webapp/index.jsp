<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 20px;
        }
        h1 {
            color: #2c3e50;
        }
        .menu-link {
            display: block;
            margin: 10px 0;
            padding: 10px 15px;
            background-color: #3498db;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
            width: fit-content;
        }
        .menu-link:hover {
            background-color: #2980b9;
        }
        .game-link {
            background-color: #e74c3c;
        }
        .game-link:hover {
            background-color: #c0392b;
        }
    </style>
</head>
<body>
<h1>SS2</h1>
<a href="life-cycle-servlet" class="menu-link"><h2>B1</h2></a>
<a href="input.jsp" class="menu-link"><h2>B2</h2></a>
<a href="form.jsp" class="menu-link"><h2>B3</h2></a>
<a href="register.jsp" class="menu-link"><h2>B4</h2></a>
<a href="register2.jsp" class="menu-link"><h2>B5</h2></a>
<a href="products" class="menu-link"><h2>B6-7-8</h2></a>
<a href="guess-word" class="menu-link game-link"><h2>B9</h2></a>
</body>
</html>