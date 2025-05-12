<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css"
        rel="stylesheet">
</head>
<body class="bg-gray-200">
<div class="w-full h-screen flex p-10 justify_center flex-col gap-5 align-center">
    <table class="min-w-full border border-gray-300 shadow-md rounded-lg overflow-hidden">
        <thead class="bg-gray-100">
        <tr>
            <th class="px-4 py-2 text-left text-sm font-semibold text-gray-700">ID</th>
            <th class="px-4 py-2 text-left text-sm font-semibold text-gray-700">Name</th>
            <th class="px-4 py-2 text-left text-sm font-semibold text-gray-700">Price</th>
            <th class="px-4 py-2 text-left text-sm font-semibold text-gray-700">Description</th>
        </tr>
        </thead>
        <tbody class="divide-y divide-gray-200">
        <c:forEach items="${products}" var="product">
            <tr class="hover:bg-gray-50 transition-colors">
                <td class="px-4 py-2 text-sm text-gray-800">${product.id}</td>
                <td class="px-4 py-2 text-sm text-gray-800">${product.name}</td>
                <td class="px-4 py-2 text-sm text-gray-800">${product.price}</td>
                <td class="px-4 py-2 text-sm text-gray-800">${product.description}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a class="bg-blue-500 hover:bg-blue-700 text-center text-white font-bold py-2 px-4 rounded" href="index.jsp">Back</a>
</div>
</body>
</html>
