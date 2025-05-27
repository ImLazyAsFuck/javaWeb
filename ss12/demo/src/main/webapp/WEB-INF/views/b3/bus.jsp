<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
<html>
<head>
  <title>Bus List</title>
  <script>
    function confirmDelete(busId) {
      if (confirm("Are you sure you want to delete this bus?")) {
        window.location.href = "/bus/delete/" + busId;
      }
    }
  </script>
</head>
<body class="bg-gray-100">
<div class="container mx-auto p-6">
  <h1 class="text-3xl font-bold text-center mb-6">Bus Management System</h1>
  <div class="mb-4">
    <a href="/" class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">Home</a>
    <a href="/bus/add" class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">Add New Bus</a>
  </div>
  <div class="bg-white p-6 rounded-lg shadow-md">
    <h2 class="text-xl font-semibold mb-4">Bus List</h2>
    <table class="w-full table-auto">
      <thead>
      <tr class="bg-gray-200">
        <th class="px-4 py-2">ID</th>
        <th class="px-4 py-2">License Plate</th>
        <th class="px-4 py-2">Type</th>
        <th class="px-4 py-2">Rows</th>
        <th class="px-4 py-2">Columns</th>
        <th class="px-4 py-2">Total Seats</th>
        <th class="px-4 py-2">Image</th>
        <th class="px-4 py-2">Actions</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="bus" items="${buses}">
        <tr>
          <td class="border px-4 py-2">${bus.id}</td>
          <td class="border px-4 py-2">${bus.licensePlate}</td>
          <td class="border px-4 py-2">${bus.busType}</td>
          <td class="border px-4 py-2">${bus.rowSeat}</td>
          <td class="border px-4 py-2">${bus.colSeat}</td>
          <td class="border px-4 py-2">${bus.totalSeat}</td>
          <td class="border px-4 py-2">
            <img src="${bus.image}" alt="img" style="width: 200px; height: 150px; object-fit: cover;" />
          </td>
          <td class="border px-4 py-2">
            <a href="bus/seats/${bus.id}" class="bg-blue-500 text-white px-2 py-1 rounded hover:bg-blue-600">View Seats</a>
            <a href="/bus/edit/${bus.id}" class="bg-yellow-500 text-white px-2 py-1 rounded hover:bg-yellow-600">Edit</a>
            <button onclick="confirmDelete('${bus.id}')" class="bg-red-500 text-white px-2 py-1 rounded hover:bg-red-600">Delete</button>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
</div>
</body>
</html>