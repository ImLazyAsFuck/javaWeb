<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css"
          rel="stylesheet">
</head>
<body class="bg-gray-100">
<div class="w-full h-screen gap-5 flex-col flex justify-center items-center h-screen">
    <div class="w-1/2 flex justify-between items-center">
        <h1 class="text-2xl font-bold">Category</h1>
        <a href="views/newCategory.jsp" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
            Add Category
        </a>
    </div>

    <table class="w-1/2 divide-y divide-gray-200 border rounded-lg shadow-md">
        <thead class="bg-gray-100">
        <tr>
            <th class="px-4 py-2 text-left text-sm font-semibold text-gray-700">ID</th>
            <th class="px-4 py-2 text-left text-sm font-semibold text-gray-700">Name</th>
            <th class="px-4 py-2 text-left text-sm font-semibold text-gray-700">Description</th>
            <th class="px-4 py-2 text-left text-sm font-semibold text-gray-700">Status</th>
            <th class="px-4 py-2 text-left text-sm font-semibold text-gray-700">Action</th>
        </tr>
        </thead>
        <tbody class="divide-y divide-gray-200 bg-white">
        <c:if test="${empty categories}">
            <tr>
                <td colspan="5" class="px-4 py-3 text-center text-sm text-gray-500">No Category found</td>
            </tr>
        </c:if>
        <c:forEach var="category" items="${categories}">
            <c:if test="${category.status != 'DELETED'}">
                <tr>
                    <td class="px-4 py-2 text-sm text-gray-700">${category.id}</td>
                    <td class="px-4 py-2 text-sm text-gray-700">${category.name}</td>
                    <td class="px-4 py-2 text-sm text-gray-700">${category.description}</td>
                    <td class="px-4 py-2 text-sm">
                <span class="inline-block px-2 py-1 rounded-full text-xs font-medium
                    ${category.status == 'ACTIVE' ? 'bg-green-100 text-green-700' :
                    category.status == 'INACTIVE' ? 'bg-yellow-100 text-yellow-700' :
                    'bg-red-100 text-red-700'}">
                        ${category.status}
                </span>
                    </td>
                    <td class="px-4 py-2 text-sm">
                        <button onclick="window.location.href='/category-controller?action=edit&id=${category.id}'" class="p-2 rounded bg-blue-500 text-white hover:underline">Edit</button>
                        <button onclick="window.location.href='/category-controller?action=confirmDelete&id=${category.id}'" class="p-2 rounded bg-red-500 text-white hover:underline ml-2">Delete</button>
                    </td>
                </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
