<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Task</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
    <script>
        function disableOnCheck(checkbox) {
            if (checkbox.checked) {
                checkbox.disabled = true;
                checkbox.setAttribute('disabled', 'disabled');

                var hiddenInput = document.createElement('input');
                hiddenInput.type = 'hidden';
                hiddenInput.name = 'completed';
                hiddenInput.value = 'on';
                checkbox.parentNode.appendChild(hiddenInput);
            }
        }
    </script>
</head>
<body class="bg-gray-100 p-8">
    <div class="max-w-md mx-auto bg-white rounded-xl shadow-md overflow-hidden">
        <div class="p-4 bg-blue-50">
            <h1 class="text-xl font-semibold text-blue-600">Edit Task</h1>
        </div>
        <div class="p-4">
            <form action="/tasks" method="post">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="taskId" value="${task.id}">
                <div class="mb-4">
                    <label for="name" class="block text-gray-700 font-medium mb-2">Task Name</label>
                    <input type="text" name="name" id="name" value="${task.name}" required
                           class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
                </div>
                <div class="mb-4">
                    <label class="flex items-center space-x-3 ${task.completed ? 'opacity-75' : ''}">
                        <input type="checkbox" id="completedCheckbox" name="completed" 
                               ${task.completed ? 'checked' : ''} ${task.completed ? 'disabled' : ''} 
                               class="form-checkbox h-5 w-5 text-blue-600"
                               onclick="this.disabled=true; disableOnCheck(this); return false;">
                        <input type="hidden" name="isAlreadyCompleted" value="${task.completed}">
                        <span class="text-gray-700 font-medium">Completed</span>
                        ${task.completed ? '<span class="text-green-600 ml-2 text-sm">(Cannot be unchecked once completed)</span>' : ''}
                    </label>
                </div>
                <div class="flex justify-between">
                    <a href="/tasks" class="bg-gray-300 hover:bg-gray-400 text-gray-800 font-medium py-2 px-4 rounded-md">
                        Cancel
                    </a>
                    <button type="submit" class="bg-blue-500 hover:bg-blue-600 text-white font-medium py-2 px-4 rounded-md">
                        Save Changes
                    </button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
