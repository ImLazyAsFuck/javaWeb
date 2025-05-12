<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Todo</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
    <script>
        function completeTask(checkbox, formId) {
            if (checkbox.checked) {
                // Disable checkbox immediately before any other actions
                checkbox.disabled = true;
                checkbox.setAttribute('disabled', 'disabled');
                
                // Set a small timeout to make the UI update visible before form submission
                setTimeout(function() {
                    document.getElementById(formId).submit();
                }, 10);
                
                return false; // Prevent any default behavior
            }
        }
        
        function confirmDelete(formId, taskName) {
            if (confirm("Are you sure you want to delete task: '" + taskName + "'?")) {
                document.getElementById(formId).submit();
            }
            return false;
        }
    </script>
</head>
<body class="bg-gray-100 p-8">
<div class="max-w-4xl mx-auto bg-white rounded shadow-md overflow-hidden">
    <div class="p-4 flex justify-between items-center bg-blue-50">
        <h1 class="text-xl font-semibold text-blue-600">Task List</h1>
        <a href="/tasks?action=add" class="bg-blue-500 hover:bg-blue-600 text-white px-4 py-2 rounded-md text-sm">
            Add New Task
        </a>
    </div>
    <table class="min-w-full table-auto border-collapse">
        <thead class="bg-blue-500 text-white">
        <tr>
            <th class="px-4 py-2 text-left w-10">Status</th>
            <th class="px-4 py-2 text-left">Name</th>
            <th class="px-4 py-2 text-left w-10">Action</th>
        </tr>
        </thead>
        <tbody class="divide-y divide-gray-200">
        <c:choose>
            <c:when test="${empty tasks}">
                <tr class="hover:bg-gray-100 transition">
                    <td colspan="3" class="px-4 py-2 text-center">
                        <p>No tasks</p>
                    </td>
                </tr>
            </c:when>
            <c:otherwise>
                <c:forEach var="task" items="${tasks}">
                    <tr class="hover:bg-gray-100 transition">
                        <td class="px-4 py-2">
                            <form id="form-${task.id}" action="/tasks" method="post" class="inline">
                                <input type="hidden" name="action" value="toggleStatus">
                                <input type="hidden" name="taskId" value="${task.id}">
                                <input type="hidden" name="completed" value="true">
                                <div class="focus:outline-none">
                                    <input type="checkbox" class="form-checkbox h-5 w-5 text-blue-600" 
                                           ${task.completed ? 'checked' : ''} ${task.completed ? 'disabled' : ''} 
                                           onclick="this.disabled=true; completeTask(this, 'form-${task.id}'); return false;" >
                                </div>
                            </form>
                        </td>
                        <td class="px-4 py-2">
                            <p class="${task.completed ? 'line-through text-gray-500' : ''}">${task.name}</p>
                        </td>
                                                    <td class="px-4 py-2 flex space-x-2">
                            <a href="/tasks?action=edit&id=${task.id}" class="text-blue-500 hover:text-blue-700">
                                <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M11 5H6a2 2 0 00-2 2v11a2 2 0 002 2h11a2 2 0 002-2v-5m-1.414-9.414a2 2 0 112.828 2.828L11.828 15H9v-2.828l8.586-8.586z" />
                                </svg>
                            </a>
                            <form id="delete-form-${task.id}" action="/tasks" method="post" class="inline">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="taskId" value="${task.id}">
                                <button type="button" onclick="confirmDelete('delete-form-${task.id}', '${task.name}')" 
                                        class="text-red-500 hover:text-red-700 focus:outline-none">
                                    <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none"
                                         viewBox="0 0 24 24" stroke="currentColor">
                                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                              d="M6 18L18 6M6 6l12 12"></path>
                                    </svg>
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>
</div>
</body>
</html>
