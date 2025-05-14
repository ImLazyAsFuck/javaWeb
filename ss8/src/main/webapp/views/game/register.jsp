<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Farm Register</title>
  <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body style="background-image: url('https://i.pinimg.com/736x/3f/c1/6d/3fc16d570196e9b334733642dd545967.jpg'); background-size: cover;" class="bg-green-50 min-h-screen flex items-center justify-center">
<div class="bg-white border-4 border-pink-300 shadow-2xl rounded-2xl p-10 max-w-md w-full">
  <h1 class="text-3xl font-bold text-pink-700 text-center mb-6">🌸 Đăng Ký Nông Dân Mới</h1>
  <form action="register" method="post" class="space-y-4">
    <div>
      <label class="block text-pink-800 font-semibold mb-1">👩‍🌾 Tên nông dân</label>
      <input type="text" name="username" required
             class="w-full px-4 py-2 border-2 border-pink-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-pink-500">
    </div>
    <div>
      <label class="block text-pink-800 font-semibold mb-1">📧 Email</label>
      <input type="email" name="email" required
             class="w-full px-4 py-2 border-2 border-pink-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-pink-500">
    </div>
    <div>
      <label class="block text-pink-800 font-semibold mb-1">🔑 Mật khẩu</label>
      <input type="password" name="password" required
             class="w-full px-4 py-2 border-2 border-pink-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-pink-500">
    </div>
    <div class="flex justify-between items-center">
      <button type="submit"
              class="bg-pink-300 hover:bg-pink-400 text-white font-bold py-2 px-4 rounded-xl shadow-md transition">
        🌱 Bắt đầu trồng trọt!
      </button>
      <a href="/game/login" class="text-sm text-pink-700 hover:underline">Đã có tài khoản?</a>
    </div>
  </form>
</div>
</body>
</html>
