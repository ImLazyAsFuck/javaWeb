<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ss8.model.game.User" %>

<html>
<head>
    <title>FarmVille - Home</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
    <style>
        body {
            background-image: url('https://i.pinimg.com/736x/47/79/fe/4779feb84569ce17f3d9e8ce346f017f.jpg');
            background-size: cover;
            background-attachment: fixed;
        }
        .option-card img {
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }
        .option-card img:hover {
            transform: scale(1.1);
            box-shadow: 0px 10px 15px rgba(0, 0, 0, 0.3);
        }
    </style>
</head>
<body class="bg-green-50 min-h-screen flex flex-col items-center justify-center">
    <div class="text-center">
        <h1 class="text-4xl font-bold text-yellow-800 mb-6">🌾 Chào Mừng Đến Nông Trại 🌾</h1>
        <p class="text-xl text-gray-700">Xin chào, <strong>${user.username}</strong>!</p>
        <p class="text-lg text-gray-700">Số dư tài khoản của bạn: <strong>${user.balance}</strong></p>
    </div>


    <div class="grid grid-cols-3 gap-8 mt-10">

        <div class="option-card text-center">
            <a href="/game/shop">
                <img src="https://static.vecteezy.com/system/resources/previews/025/449/723/non_2x/cartoon-game-shop-window-with-assets-free-vector.jpg"
                     alt="Shop" 
                     class="w-48 h-48 mx-auto rounded shadow-lg">
                <p class="text-lg font-bold text-yellow-700 mt-4">🛒 Cửa Hàng</p>
            </a>
        </div>

        <div class="option-card text-center">
            <a href="/game/warehouse">
                <img src="https://i.pinimg.com/736x/a6/1e/f3/a61ef38d00f0385c5b9974fdadb6122f.jpg"
                     alt="Warehouse" 
                     class="w-48 h-48 mx-auto rounded shadow-lg">
                <p class="text-lg font-bold text-green-700 mt-4">🏠 Kho Lương Thực</p>
            </a>
        </div>
        <div class="option-card text-center">
            <a href="/game/farm">
                <img src="https://i.pinimg.com/736x/b3/fa/2d/b3fa2d271e10d3214b0b788bca963351.jpg"
                     alt="Farm" 
                     class="w-48 h-48 mx-auto rounded shadow-lg">
                <p class="text-lg font-bold text-blue-700 mt-4">🌽 Nông Trại</p>
            </a>
        </div>
    </div>

    <div class="mt-12 text-center">
        <a href="/game/logout" class="text-sm text-pink-700 hover:underline">
            ❌ Đăng Xuất
        </a>
    </div>
</body>
</html>