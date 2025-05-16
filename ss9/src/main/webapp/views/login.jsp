<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đăng nhập | MovieZone</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-900 text-white min-h-screen flex items-center justify-center bg-cover bg-center" style="background-image: url('https://wallpaperaccess.com/full/2076081.jpg');">

<div class="bg-black bg-opacity-70 p-8 rounded-xl shadow-2xl w-full max-w-md">
    <h2 class="text-3xl font-bold mb-6 text-center text-yellow-400">🎬 Đăng Nhập</h2>

    <form action="/login" method="post" class="space-y-4">
        <div>
            <input placeholder="Tên đăng nhập" type="text" name="username"
                   class="w-full px-4 py-2 rounded-lg bg-gray-800 text-white placeholder-gray-400 focus:outline-none focus:ring-2 focus:ring-yellow-400">
            <c:if test="${not empty errorUsername}">
                <p class="text-red-500 text-sm mt-1">${errorUsername}</p>
            </c:if>
        </div>

        <div>
            <input placeholder="Mật khẩu" type="password" name="password"
                   class="w-full px-4 py-2 rounded-lg bg-gray-800 text-white placeholder-gray-400 focus:outline-none focus:ring-2 focus:ring-yellow-400">
            <c:if test="${not empty errorPassword}">
                <p class="text-red-500 text-sm mt-1">${errorPassword}</p>
            </c:if>
        </div>

        <c:if test="${not empty error}">
            <p class="text-red-500 text-center text-sm">${error}</p>
        </c:if>

        <button type="submit"
                class="w-full bg-yellow-400 hover:bg-yellow-500 text-black font-semibold py-2 rounded-lg transition duration-300">
            Đăng Nhập
        </button>
    </form>

</div>

</body>
</html>
