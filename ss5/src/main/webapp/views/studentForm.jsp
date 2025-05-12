<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${editMode ? 'Edit' : 'Add'} Student</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 min-h-screen flex items-center justify-center">
    <div class="bg-white p-8 rounded-2xl shadow-lg w-full max-w-md">
        <form action="/student/" method="post" class="space-y-4">
            <input type="hidden" name="action" value="${editMode ? 'update' : 'confirm'}">
            
            <div class="w-full flex justify-between">
                <h2 class="text-2xl font-semibold text-gray-700">${editMode ? 'Edit' : 'Add'} Student</h2>
                <a class="text-2xl text-gray-400 hover:text-gray-600" href="${pageContext.request.contextPath}/student/list">×</a>
            </div>
            
            <c:if test="${not empty formError}">
                <div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative" role="alert">
                    <span class="block sm:inline">${formError}</span>
                </div>
            </c:if>
            
            <input type="hidden" name="id" value="${student.id != null ? student.id : 0}">
            
            <div>
                <label class="block text-sm font-medium text-gray-700 mb-1">Name</label>
                <input type="text" name="name" placeholder="Enter name" value="${student.name}"
                       class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-400">
                
                <c:if test="${not empty nameError}">
                    <p class="text-red-500 text-sm mt-1">${nameError}</p>
                </c:if>
            </div>
            
            <div>
                <label class="block text-sm font-medium text-gray-700 mb-1">Age</label>
                <input type="number" name="age" placeholder="Enter age" value="${student.age > 0 ? student.age : ''}"
                       class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-400">
                
                <c:if test="${not empty ageError}">
                    <p class="text-red-500 text-sm mt-1">${ageError}</p>
                </c:if>
            </div>
            
            <div>
                <label class="block text-sm font-medium text-gray-700 mb-1">Address</label>
                <textarea name="address" placeholder="Enter address" rows="4"
                          class="w-full px-4 py-2 border border-gray-300 resize-none rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-400">${student.address}</textarea>
                
                <c:if test="${not empty addressError}">
                    <p class="text-red-500 text-sm mt-1">${addressError}</p>
                </c:if>
            </div>
            
            <div>
                <input type="submit" value="${editMode ? 'Update' : 'Submit'}"
                       class="w-full bg-blue-500 text-white py-2 px-4 rounded-lg hover:bg-blue-600 transition duration-200">
            </div>
        </form>
    </div>
</body>
</html>
