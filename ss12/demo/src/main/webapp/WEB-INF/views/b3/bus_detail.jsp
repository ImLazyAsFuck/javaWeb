<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
<html>
<head>
  <title>Bus Seat Layout</title>
</head>
<body class="bg-gray-100">
<div class="container mx-auto p-6">
  <h1 class="text-3xl font-bold text-center mb-6">Seat Layout for Bus ID: ${busId}</h1>
  <div class="bg-white p-6 rounded-lg shadow-md">
    <div class="grid gap-2" style="grid-template-columns: repeat(${colSeat}, minmax(0, 1fr));">
      <c:forEach var="seat" items="${seats}">
        <div class="p-2 text-center ${seat.status == 'AVAILABLE' ? 'bg-green-200' : 'bg-red-200'} rounded">
            ${seat.nameSeat} ($${seat.price})
        </div>
      </c:forEach>
    </div>
    <div class="mt-4">
      <a href="/bus" class="bg-gray-500 text-white px-4 py-2 rounded hover:bg-gray-600">Back to List</a>
    </div>
  </div>
</div>
</body>
</html>