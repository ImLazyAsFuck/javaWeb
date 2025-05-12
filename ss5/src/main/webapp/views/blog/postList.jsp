<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Blog Posts</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 min-h-screen">
    <div class="container mx-auto px-4 py-8">
        <header class="mb-8">
            <div class="bg-white shadow-md rounded-lg p-6 mb-4">
                <h1 class="text-3xl font-bold text-gray-800">Simple Blog</h1>
                <p class="text-gray-600 mt-2">A collection of interesting articles</p>
            </div>
            <a href="/" class="inline-block bg-gray-200 hover:bg-gray-300 text-gray-700 font-medium py-2 px-4 rounded transition">
                ← Back to Home
            </a>
        </header>
        
        <main>
            <div class="grid gap-6 md:grid-cols-2 lg:grid-cols-3">
                <c:choose>
                    <c:when test="${empty posts}">
                        <div class="col-span-full bg-white shadow-md rounded-lg p-6">
                            <p class="text-gray-700 text-center">No posts available.</p>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="post" items="${posts}">
                            <div class="bg-white shadow-md rounded-lg overflow-hidden hover:shadow-lg transition">
                                <div class="p-6">
                                    <h2 class="text-xl font-bold text-gray-800 mb-2 hover:text-blue-600">
                                        <a href="${pageContext.request.contextPath}/blog/post?id=${post.id}">
                                            ${post.title}
                                        </a>
                                    </h2>
                                    <div class="flex items-center text-gray-600 text-sm mb-4">
                                        <span class="mr-4">By: ${post.author}</span>
                                        <span>Published: ${post.publishDate}</span>
                                    </div>
                                    <div class="mt-4">
                                        <a href="${pageContext.request.contextPath}/blog/post?id=${post.id}" 
                                           class="inline-block bg-blue-500 hover:bg-blue-600 text-white font-medium py-2 px-4 rounded-md transition">
                                            Read More
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </div>
        </main>
    </div>
</body>
</html>
