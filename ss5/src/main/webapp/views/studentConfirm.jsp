<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Confirm Student</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 min-h-screen flex items-center justify-center">
    <div class="bg-white shadow-lg rounded-2xl p-8 w-full max-w-md">
        <form action="${pageContext.request.contextPath}/student/" method="post" class="space-y-4">
            <input type="hidden" name="action" value="save">
            <input type="hidden" name="id" value="${student.id}">
            <input type="hidden" name="name" value="${student.name}">
            <input type="hidden" name="age" value="${student.age}">
            <input type="hidden" name="address" value="${student.address}">

            <div class="w-full flex justify-between items-center mb-2">
                <h1 class="text-2xl font-bold text-gray-800">Confirm Student</h1>
                <a href="${pageContext.request.contextPath}/student/list" class="text-2xl text-gray-400 hover:text-gray-600">×</a>
            </div>

            <div>
                <label class="block text-gray-700 font-medium">Name:</label>
                <p class="text-lg text-gray-900">${student.name}</p>
            </div>
            
            <div>
                <label class="block text-gray-700 font-medium">Age:</label>
                <p class="text-lg text-gray-900">${student.age}</p>
            </div>
            
            <div>
                <label class="block text-gray-700 font-medium">Address:</label>
                <p class="text-lg text-gray-900">${student.address}</p>
            </div>
            
            <div class="flex justify-between pt-6">
                <a href="${pageContext.request.contextPath}/student/add" 
                   class="px-6 py-2 bg-gray-200 text-gray-700 rounded-lg hover:bg-gray-300 transition duration-200">
                    Back
                </a>
                <button type="submit" 
                   class="px-6 py-2 bg-green-500 text-white rounded-lg hover:bg-green-600 transition duration-200">
                    Confirm & Save
                </button>
            </div>
        </form>
    </div>
</body>
</html>