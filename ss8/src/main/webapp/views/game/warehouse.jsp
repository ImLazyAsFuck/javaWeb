<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>FarmVille - Warehouse</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
    <style>
        body {
            background-image: url('https://i.pinimg.com/originals/4d/92/a5/4d92a54d64cf09f5d94488f9616c4802.jpg');
            background-size: cover;
            background-attachment: fixed;
        }
        .seed-card img {
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }
        .seed-card img:hover {
            transform: scale(1.05);
            box-shadow: 0px 10px 15px rgba(0, 0, 0, 0.3);
        }
    </style>
</head>
<body class="bg-green-50 min-h-screen flex flex-col items-center justify-center">
    <div class="text-center mb-12">
        <h1 class="text-5xl font-bold text-yellow-800 mb-4">🏠 Kho Lương Thực</h1>
        <p class="text-lg text-gray-700">Danh sách hạt giống đã mua.</p>
    </div>

    <div class="grid grid-cols-3 gap-8">
        <c:forEach var="entry" items="${warehouseSeeds}">
            <div class="seed-card text-center border rounded-lg bg-white shadow-lg p-6">
                <img src="${entry.seeds.imageUrl}" alt="${entry.seeds.seedsName}" class="w-32 h-32 mx-auto mb-4">
                <h3 class="text-lg font-bold text-green-800">${entry.seeds.seedsName}</h3>
                <p class="text-gray-600 text-sm">Giá: ${entry.seeds.price} VND</p>
                <p class="text-yellow-700 font-semibold">Số lượng: ${entry.quantity}</p>
            </div>
        </c:forEach>
    </div>

    <div class="mt-10 text-center">
        <a href="/game/home" class="text-sm text-blue-700 hover:underline">
            ⬅ Quay lại trang chủ nông trại
        </a>
    </div>
</body>
</html>