<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Task</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 p-8">
    <div class="max-w-md mx-auto bg-white rounded-xl shadow-md overflow-hidden">
        <div class="p-4 bg-blue-50">
            <h1 class="text-xl font-semibold text-blue-600">Add New Task</h1>
        </div>
        <div class="p-4">
            <form action="/tasks" method="post">
                <input type="hidden" name="action" value="create">
                <div class="mb-4">
                    <label for="name" class="block text-gray-700 font-medium mb-2">Task Name</label>
                    <input type="text" name="name" id="name" required
                           class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
                </div>
                <div class="flex justify-between">
                    <a href="/tasks" class="bg-gray-300 hover:bg-gray-400 text-gray-800 font-medium py-2 px-4 rounded-md">
                        Cancel
                    </a>
                    <button type="submit" class="bg-blue-500 hover:bg-blue-600 text-white font-medium py-2 px-4 rounded-md">
                        Save Task
                    </button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
