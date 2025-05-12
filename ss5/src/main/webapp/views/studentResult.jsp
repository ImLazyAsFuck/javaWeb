<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Saved</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 min-h-screen flex items-center justify-center">
    <div class="bg-white shadow-lg rounded-2xl p-8 w-full max-w-md">
        <div class="space-y-4">
            <div class="text-center">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-16 w-16 text-green-500 mx-auto mb-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
                </svg>
                <h1 class="text-2xl font-bold text-gray-800 mb-4">${message != null ? message : 'Student Added Successfully!'}</h1>
            </div>

            <div class="bg-gray-50 p-4 rounded-lg">
                <div>
                    <label class="block text-gray-700 font-medium">Name:</label>
                    <p class="text-lg text-gray-900">${student.name}</p>
                </div>

                <div class="mt-2">
                    <label class="block text-gray-700 font-medium">Age:</label>
                    <p class="text-lg text-gray-900">${student.age}</p>
                </div>

                <div class="mt-2">
                    <label class="block text-gray-700 font-medium">Address:</label>
                    <p class="text-lg text-gray-900">${student.address}</p>
                </div>
            </div>

            <div class="pt-4">
                <a href="${pageContext.request.contextPath}/student/list"
                   class="block w-full bg-blue-500 hover:bg-blue-600 text-white font-semibold py-2 px-4 rounded-lg text-center transition duration-300">
                    Back to Student List
                </a>
            </div>
        </div>
    </div>
</body>
</html>
