<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 16/05/2025
  Time: 2:42 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Movie Schedules</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css"
          rel="stylesheet">
</head>
<body>
    <h1>Movie Schedules</h1>
    <a href="/">Back to Home</a>
    
    <form action="schedule" method="post">
        <input placeholder="Search movie title" type="text" name="title">
        <input type="submit" value="Search">
    </form>
    
    <div>
        <c:choose>
            <c:when test="${empty schedules}">
                <p class="no-data">No schedules found. Please check the database or try a different search.</p>
            </c:when>
            <c:otherwise>
                <p>Found ${schedules.size()} schedule(s)</p>
                <table border="1">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Movie Title</th>
                            <th>Show Time</th>
                            <th>Screen Room ID</th>
                            <th>Available Seats</th>
                            <th>Format</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${schedules}" var="schedule">
                            <tr>
                                <td>${schedule.id}</td>
                                <td>${schedule.movieTitle}</td>
                                <td>${schedule.showTime}</td>
                                <td>${schedule.screenRoomId}</td>
                                <td>${schedule.availableSeats}</td>
                                <td>${schedule.format}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
