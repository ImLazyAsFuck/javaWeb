<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Movie Schedules</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-900 text-white min-h-screen font-sans">
<div class="max-w-4xl mx-auto px-4 py-8">
    <h1 class="text-3xl font-bold mb-6 text-center text-yellow-400">🎬 Movie Schedule Details</h1>

    <div class="bg-gray-800 rounded-lg shadow-lg p-6">
        <c:choose>
            <c:when test="${empty schedule}">
                <p class="text-center text-red-400 font-semibold">No schedules found. Please check the database or try a different search.</p>
            </c:when>
            <c:otherwise>
                    <div class="bg-gray-700 rounded-md p-4 mb-4 border-l-4 border-yellow-400">
                        <ul class="text-sm space-y-1">
                            <li><span class="font-medium text-gray-300">🎫 Schedule ID:</span> ${schedule.id}</li>
                            <li><span class="font-medium text-gray-300">🕒 Show Time:</span> ${schedule.showTime}</li>
                            <li><span class="font-medium text-gray-300">🏟️ Screen Room:</span> ${schedule.screenRoomId}</li>
                            <li><span class="font-medium text-gray-300">💺 Available Seats:</span> ${schedule.availableSeats}</li>
                            <li><span class="font-medium text-gray-300">📽️ Format:</span> ${schedule.format}</li>
                        </ul>
                    </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>
