<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Farm Shop</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=VT323&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'VT323', monospace;
        }

        .farm-background {
            background-image: url('https://i.pinimg.com/originals/89/0e/eb/890eeb90c3fd93f9e67d7aa050ef4eb4.gif');
            background-size: cover;
            background-repeat: no-repeat;
            background-position: center;
        }

        .wooden-card {
            background: rgba(255, 250, 240, 0.9);
            box-shadow: 0 8px 12px rgba(0, 0, 0, 0.15);
            border: 3px solid #b56b2d;
            border-radius: 16px;
            transition: transform 0.2s ease-in-out;
        }

        .wooden-card:hover {
            transform: rotate(-1deg) scale(1.03);
        }

        .wooden-border {
            border: 6px double #a0522d;
            border-radius: 20px;
            background: rgba(255, 255, 255, 0.8);
            backdrop-filter: blur(5px);
        }

        .cute-button {
            background: #88c057;
            color: #fff;
            font-weight: bold;
            transition: all 0.2s ease;
            border-radius: 8px;
            box-shadow: 2px 2px 0 #4a772f;
        }

        .cute-button:hover {
            background: #76a84d;
            transform: translateY(-2px);
            box-shadow: 2px 4px 0 #3e6126;
        }
    </style>
</head>
<body class="farm-background text-gray-800">
<div class="max-w-7xl mx-auto py-10 px-6 wooden-border">
    <h1 class="text-5xl text-center text-green-800 mb-6">🌱 Farm Shop 🌻</h1>
    <p class="text-center text-gray-700 text-xl italic mb-6">Chào mừng đến với cửa hàng nông trại! Mua hạt giống để gieo trồng nào~</p>

    <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-8">
        <c:choose>
            <c:when test="${not empty seeds}">
                <c:forEach var="seed" items="${seeds}">
                    <div class="wooden-card p-5">
                        <img class="rounded w-full h-40 object-cover mb-3" src="${seed.imageUrl}" alt="${seed.seedsName}">
                        <h2 class="text-2xl text-yellow-800 mb-2">${seed.seedsName}</h2>
                        <p class="text-lg text-gray-700 mb-2">
                            💰 Giá: <span class="text-red-600 font-bold">$${seed.price}</span>
                        </p>
                            <form hidden="hidden" action="game/shop">
                                <input type="hidden" name="id" value="${seed.id}">
                                <input type="hidden" name="price" value="${seed.price}">
                                <input type="hidden" name="seedsName" value="${seed.seedsName}">
                                <input type="hidden" name="imageUrl" value="${seed.imageUrl}">
                                <button type="submit" class="cute-button py-2 px-5 flex items-center justify-center space-x-2 mt-2">
                                    <span>🛒 Mua hạt giống</span>
                                </button>
                            </form>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <p class="col-span-3 text-center text-gray-600 font-bold italic text-2xl">Oops~ Hết hàng mất rồi! 🌾</p>
            </c:otherwise>
        </c:choose>
    </div>
        <div class="mt-10 text-center">
            <a href="/game/home" class="text-sm text-blue-700 hover:underline">
                ⬅ Quay lại trang chủ nông trại
            </a>
        </div>
</div>
</body>
</html>
