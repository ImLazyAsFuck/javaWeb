<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 16/05/2025
  Time: 12:43 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${movie.title} - Chi tiết phim</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css"
          rel="stylesheet">
</head>
<body>
    <a href="/movie/home">back to home</a>
    <c:choose>
        <c:when test="${empty movie}">
            <%
                response.sendRedirect("/not_found/404");
            %>
        </c:when>
        <c:otherwise>
            <div class="movie-details">
                <h2>${movie.title}</h2>
                <p><strong>Đạo diễn:</strong> ${movie.director}</p>
                <p><strong>Mô tả:</strong> ${movie.description}</p>
                <p><strong>Thể loại:</strong> ${movie.genre}</p>
                <p><strong>Ngôn ngữ:</strong> ${movie.language}</p>
                <p><strong>Thời lượng:</strong> ${movie.duration} phút</p>
            </div>
            
            <div class="schedules">
                <h3>Lịch chiếu phim</h3>
                <c:choose>
                    <c:when test="${empty schedules}">
                        <p>Không có lịch chiếu nào sắp tới cho phim này.</p>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="schedule" items="${schedules}">
                            <div class="schedule-item">
                                <div class="schedule-details">
                                    <p><strong>Ngày chiếu:</strong> ${schedule.showTime}</p>
                                    <p><strong>Phòng chiếu:</strong> ${schedule.screenRoomId}</p>
                                    <p><strong>Số ghế còn trống:</strong> ${schedule.availableSeats}</p>
                                </div>
                                <div>
                                    <span class="schedule-format">${schedule.format}</span>
                                </div>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </div>
        </c:otherwise>
    </c:choose>
</body>
</html>
