
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css"
          rel="stylesheet">
</head>
<body class="bg-gray-100">
    <div class="w-full h-screen flex justify-center items-center">
        <form class="w-full max-w-md bg-white shadow-md rounded-lg p-8 space-y-4" action="/category-controller?action=delete&id=${id}" method="post">
            <h1 class="text-3xl font-bold text-center">Delete Category</h1>
            <h2 class="text-xl font-bold text-center">Are you sure to delete this category?</h2>
            <input type="hidden" name="id" value="${id}">
            <div class="w-full flex justify-center gap-4">
                <button class=" bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded">Delete</button>
                <button type="button" onclick="window.location.href='/category-controller?action=findAll'"
                        class="bg-blue-500 text-white py-2 px-4 rounded-md hover:bg-blue-600 transition duration-200">Back</button>
            </div>
        </form>
    </div>

</body>
</html>
