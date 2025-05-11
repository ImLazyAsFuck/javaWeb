<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Category</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
<div class="w-full h-screen flex justify-center items-center">
    <form action="/category-controller?action=add" method="post"
          class="w-full max-w-md bg-white shadow-md rounded-lg p-8 space-y-4">
        <h2 class="text-2xl font-semibold text-gray-700 text-center">Add Category</h2>
        <input type="text" name="name" placeholder="Name"
               class="w-full outline-none px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400" required>
        <input type="text" name="description" placeholder="Description"
               class="w-full outline-none px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400" required>
        <div class="w-full flex gap-4">
            <button type="submit"
                    class="w-full bg-blue-500 text-white py-2 px-4 rounded-md hover:bg-blue-600 transition duration-200">Add</button>
            <button type="button" onclick="window.location.href='/category-controller?action=findAll'"
                    class="w-full bg-red-500 text-white py-2 px-4 rounded-md hover:bg-red-600 transition duration-200">Back</button>
        </div>
    </form>
</div>
</body>
</html>
