<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Danh Sách Phim | MovieZone</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-900 text-white min-h-screen px-4 py-8">

<div class="max-w-7xl mx-auto">
    <h2 class="text-4xl font-bold text-yellow-400 mb-8 text-center">🎬 Danh Sách Phim</h2>

    <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
        <c:forEach var="movie" items="${movies}">
            <div class="bg-gray-800 rounded-xl overflow-hidden shadow-lg hover:shadow-2xl transition duration-300">
                <div class="p-4">
                    <h3 class="text-xl font-semibold text-yellow-300 mb-2">${movie.title}</h3>
                    <p class="text-sm text-gray-400"><strong>Đạo diễn:</strong> ${movie.director}</p>
                    <p class="text-sm text-gray-400 mt-1"><strong>Thể loại:</strong> ${movie.genre}</p>
                    <p class="text-sm text-gray-400 mt-1"><strong>Ngôn ngữ:</strong> ${movie.language}</p>
                    <p class="text-sm text-gray-400 mt-1"><strong>Thời lượng:</strong> ${movie.duration}</p>
                    <p class="text-sm text-gray-300 mt-2">${movie.description}</p>

                    <div class="mt-4 text-center">
                        <a href="detail/${movie.id}">
                            <button class="bg-yellow-400 hover:bg-yellow-500 text-black font-semibold px-4 py-2 rounded-lg transition duration-300">
                                Xem chi tiết
                            </button>
                        </a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

</body>
</html>
