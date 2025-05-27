<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">

<html>
<head>
    <title>Student List</title>
</head>
<body class="bg-gray-100 min-h-screen flex items-center justify-center">

<div class="bg-white p-8 rounded-xl shadow-md w-full max-w-4xl">
    <div class="flex justify-between items-center mb-6">
        <h1 class="text-2xl font-semibold text-gray-800">Student List</h1>
        <button onclick="window.location.href='/student/add'"
                class="px-4 py-2 rounded-lg text-white bg-blue-500 hover:bg-blue-600 transition">Add Student</button>
    </div>

    <table class="table-auto w-full rounded border-collapse shadow-sm">
        <thead class="bg-gray-200 text-gray-700">
        <tr>
            <th class="border px-4 py-2 text-left">ID</th>
            <th class="border px-4 py-2 text-left">Name</th>
            <th class="border px-4 py-2 text-left">Email</th>
            <th class="border px-4 py-2 text-left">Date Of Birth</th>
            <th class="border px-4 py-2 text-left">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty students}">
            <tr>
                <td colspan="5" class="border px-4 py-4 text-center text-red-500">No Students Found</td>
            </tr>
        </c:if>
        <c:forEach items="${students}" var="student">
            <tr class="hover:bg-gray-100">
                <td class="border px-4 py-2">${student.id}</td>
                <td class="border px-4 py-2">${student.name}</td>
                <td class="border px-4 py-2">${student.email}</td>
                <td class="border px-4 py-2">${student.dob}</td>
                <td class="border px-4 py-2 space-x-2">
                    <button onclick="window.location.href='/student/edit/${student.id}'"
                            class="px-3 py-1 rounded text-white bg-blue-500 hover:bg-blue-600 transition">Edit</button>
                    <button onclick="deleteStudent(${student.id})"
                            class="px-3 py-1 rounded text-white bg-red-500 hover:bg-red-600 transition">Delete</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="flex justify-center px-2 pt-2">
        <button class="px-3 py-1 rounded text-white bg-blue-500 hover:bg-blue-600 transition" onclick="window.location.href='/'">Back</button>
    </div>
</div>
<script>
    function deleteStudent(id) {
        if(confirm('Are you sure you want to delete this student?'))
            window.location.href = '/student/delete/' + id;
        else
            alert('Delete Cancelled')
    }
</script>
</body>
</html>
