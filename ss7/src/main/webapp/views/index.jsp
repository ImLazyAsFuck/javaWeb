<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Trang Chủ</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            margin: 0;
            padding: 0;
            max-width: 1200px;
            margin: 0 auto;
        }
        header {
            background-color: #4CAF50;
            color: white;
            text-align: center;
            padding: 1em 0;
        }
        nav {
            background-color: #333;
            overflow: hidden;
        }
        nav a {
            float: left;
            display: block;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }
        nav a:hover {
            background-color: #ddd;
            color: black;
        }
        .active {
            background-color: #4CAF50;
        }
        main {
            padding: 20px;
        }
        footer {
            background-color: #333;
            color: white;
            text-align: center;
            padding: 10px;
            position: fixed;
            bottom: 0;
            width: 100%;
        }
    </style>
</head>
<body>
    <header>
        <h1>Chào mừng đến với trang web của chúng tôi</h1>
    </header>
    
    <nav>
        <a href="/" class="active">Trang Chủ</a>
        <a href="/about">Giới Thiệu</a>
        <a href="/contact">Liên Hệ</a>
    </nav>
    
    <main>
        <ul>
            <a href="/home">B1</a>
            <a href="/greet?name=Vy">B2</a>
            <a href="/something">B3</a>
            <a href="/form">B4</a>
        </ul>
    </main>
    
    <footer>
        <p>© 2025 - Trang Web Demo</p>
    </footer>
</body>
</html>
