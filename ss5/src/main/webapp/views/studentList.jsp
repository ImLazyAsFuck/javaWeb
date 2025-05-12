<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Student List</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 min-h-screen">
    <div class="container mx-auto px-4 py-8">
        <div class="bg-white rounded-lg shadow-md p-6">
            <div class="flex justify-between items-center mb-6">
                <h1 class="text-2xl font-bold text-gray-800">Student List</h1>
                <a href="${pageContext.request.contextPath}/student/add" class="bg-blue-500 hover:bg-blue-600 text-white font-medium py-2 px-4 rounded-lg transition duration-150">
                    Add New Student
                </a>
            </div>
            
            <div class="mb-4 text-gray-600">
                <c:choose>
                    <c:when test="${totalCount > 0}">
                        Showing page ${currentPage} of ${totalPages} (Total: ${totalCount} students)
                    </c:when>
                    <c:otherwise>
                        No students found. Add a new student to get started.
                    </c:otherwise>
                </c:choose>
            </div>
            
            <div class="overflow-x-auto">
                <table class="min-w-full bg-white">
                    <thead>
                        <tr class="bg-gray-200 text-gray-700 uppercase text-sm leading-normal">
                            <th class="py-3 px-6 text-left">ID</th>
                            <th class="py-3 px-6 text-left">Name</th>
                            <th class="py-3 px-6 text-left">Age</th>
                            <th class="py-3 px-6 text-left">Address</th>
                            <th class="py-3 px-6 text-center">Actions</th>
                        </tr>
                    </thead>
                    <tbody class="text-gray-600 text-sm">
                        <c:forEach var="student" items="${students}">
                            <tr class="border-b border-gray-200 hover:bg-gray-50">
                                <td class="py-3 px-6 text-left">${student.id}</td>
                                <td class="py-3 px-6 text-left">${student.name}</td>
                                <td class="py-3 px-6 text-left">${student.age}</td>
                                <td class="py-3 px-6 text-left">${student.address}</td>
                                <td class="py-3 px-6 text-center">
                                    <div class="flex item-center justify-center">
                                        <a href="${pageContext.request.contextPath}/student/edit?id=${student.id}" class="mr-2 transform hover:text-blue-500 hover:scale-110 transition duration-150">
                                            <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15.232 5.232l3.536 3.536m-2.036-5.036a2.5 2.5 0 113.536 3.536L6.5 21.036H3v-3.572L16.732 3.732z" />
                                            </svg>
                                        </a>
                                        <a href="${pageContext.request.contextPath}/student/delete?id=${student.id}" 
                                           onclick="return confirm('Are you sure you want to delete this student?')"
                                           class="transform hover:text-red-500 hover:scale-110 transition duration-150">
                                            <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16" />
                                            </svg>
                                        </a>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            
            <!-- Pagination -->
            <c:if test="${totalPages > 0}">
                <div class="mt-6 flex justify-center">
                    <nav class="flex items-center space-x-2">
                        <c:if test="${currentPage > 1}">
                            <a href="${pageContext.request.contextPath}/student/list?page=${currentPage - 1}" 
                               class="px-3 py-1 rounded-md bg-gray-200 text-gray-700 hover:bg-gray-300 transition duration-150">
                                Previous
                            </a>
                        </c:if>
                        
                        <!-- Page numbers -->
                        <div class="flex space-x-1">
                            <c:forEach begin="1" end="${totalPages}" var="i">
                                <c:choose>
                                    <c:when test="${currentPage == i}">
                                        <span class="px-3 py-1 rounded-md bg-blue-500 text-white">${i}</span>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="${pageContext.request.contextPath}/student/list?page=${i}" 
                                           class="px-3 py-1 rounded-md bg-gray-200 text-gray-700 hover:bg-gray-300 transition duration-150">
                                            ${i}
                                        </a>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </div>
                        
                        <c:if test="${currentPage < totalPages}">
                            <a href="${pageContext.request.contextPath}/student/list?page=${currentPage + 1}" 
                               class="px-3 py-1 rounded-md bg-gray-200 text-gray-700 hover:bg-gray-300 transition duration-150">
                                Next
                            </a>
                        </c:if>
                    </nav>
                </div>
            </c:if>
        <a class="text-blue-500" href="/index.jsp">Back</a>
        </div>
    </div>
</body>
</html>
