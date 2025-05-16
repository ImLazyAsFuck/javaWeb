<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${movie.title} - Chi tiết phim</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-900 text-white min-h-screen px-6 py-8">

<c:choose>
    <c:when test="${empty movie}">
        <%
            response.sendRedirect("/not_found/404");
        %>
    </c:when>
    <c:otherwise>
        <div class="max-w-5xl mx-auto">
            <div class="mb-6">
                <a href="/home" class="text-gray-300 hover:text-yellow-400 transition">⬅ Quay lại danh sách phim</a>
            </div>

            <div class="bg-gray-800 rounded-lg shadow-lg p-6 mb-10">
                <h2 class="text-3xl font-bold text-yellow-400 mb-4">${movie.title}</h2>
                <p class="mb-2"><strong>🎬 Đạo diễn:</strong> ${movie.director}</p>
                <p class="mb-2"><strong>📝 Mô tả:</strong> ${movie.description}</p>
                <p class="mb-2"><strong>📚 Thể loại:</strong> ${movie.genre}</p>
                <p class="mb-2"><strong>🌐 Ngôn ngữ:</strong> ${movie.language}</p>
                <p class="mb-2"><strong>⏱ Thời lượng:</strong> ${movie.duration} phút</p>
            </div>

            <div class="bg-gray-800 rounded-lg shadow-lg p-6">
                <h3 class="text-2xl font-semibold text-yellow-300 mb-4">🗓️ Lịch chiếu phim</h3>
                <c:choose>
                    <c:when test="${empty schedules}">
                        <p class="text-gray-400">Không có lịch chiếu nào sắp tới cho phim này.</p>
                    </c:when>
                    <c:otherwise>
                        <div  class="space-y-4">
                            <c:forEach var="schedule" items="${schedules}">
                                <div onclick="handleClick(${schedule.id})" class="border border-gray-600 rounded-lg p-4 flex justify-between items-center hover:bg-gray-700 transition">
                                    <div>
                                        <p><strong>🕒 Ngày chiếu:</strong> ${schedule.showTime}</p>
                                        <p><strong>🏢 Phòng chiếu:</strong> ${schedule.screenRoomId}</p>
                                        <p><strong>💺 Ghế trống:</strong> ${schedule.availableSeats}</p>
                                    </div>
                                    <span class="bg-yellow-400 text-black font-semibold px-3 py-1 rounded-lg">${schedule.format}</span>
                                </div>
                            </c:forEach>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </c:otherwise>
</c:choose>

</body>
<script>
    function handleClick(id) {
        // window.location.href = "/room/" + id;
    }
</script>
</html>
