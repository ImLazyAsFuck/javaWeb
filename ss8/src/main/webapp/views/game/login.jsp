<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Farm Login</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body style="background-image: url('https://i.pinimg.com/736x/3f/c1/6d/3fc16d570196e9b334733642dd545967.jpg'); background-size: cover;" class=" bg-green-100 min-h-screen flex items-center justify-center">
<div class="bg-white shadow-2xl rounded-2xl p-10 max-w-md w-full border-4 border-yellow-300">
    <h1 class="text-3xl font-bold text-green-700 text-center mb-6">🌾 FarmVille Đăng Nhập</h1>
    <form action="login" method="post" class="space-y-4">
        <div>
            <label class="block text-green-800 font-semibold mb-1">👩‍🌾 Tên nông dân</label>
            <input type="text" name="username" required
                   class="w-full px-4 py-2 border-2 border-green-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-green-500">
        </div>
        <div>
            <label class="block text-green-800 font-semibold mb-1">🔒 Mật khẩu</label>
            <input type="password" name="password" required
                   class="w-full px-4 py-2 border-2 border-green-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-green-500">
        </div>
        <div class="flex justify-between items-center">
            <button type="submit"
                    class="bg-yellow-400 hover:bg-yellow-500 text-green-900 font-bold py-2 px-4 rounded-xl shadow-md transition">
                🚪 Vào nông trại
            </button>
            <a href="/game/register" class="text-sm text-green-700 hover:underline">Chưa có tài khoản? Đăng ký</a>
        </div>
    </form>
</div>
</body>
</html>