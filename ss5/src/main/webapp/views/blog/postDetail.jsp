<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>${post.title} - Blog</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
    <style>
        .markdown h1 { font-size: 2em; font-weight: bold; margin-top: 0.5em; margin-bottom: 0.5em; }
        .markdown h2 { font-size: 1.5em; font-weight: bold; margin-top: 0.5em; margin-bottom: 0.5em; }
        .markdown h3 { font-size: 1.17em; font-weight: bold; margin-top: 0.5em; margin-bottom: 0.5em; }
        .markdown p { margin-top: 1em; margin-bottom: 1em; }
        .markdown ul { list-style-type: disc; margin-left: 2em; margin-top: 1em; margin-bottom: 1em; }
        .markdown ol { list-style-type: decimal; margin-left: 2em; margin-top: 1em; margin-bottom: 1em; }
        .markdown li { margin-top: 0.5em; margin-bottom: 0.5em; }
        .markdown code { font-family: monospace; background-color: #f0f0f0; padding: 0.2em 0.4em; border-radius: 3px; }
        .markdown pre { background-color: #f0f0f0; padding: 1em; border-radius: 5px; overflow-x: auto; margin: 1em 0; }
        .markdown pre code { background: none; padding: 0; }
        .markdown blockquote { border-left: 4px solid #ddd; padding-left: 1em; color: #666; }
    </style>
</head>
<body class="bg-gray-100 min-h-screen">
    <div class="container mx-auto px-4 py-8">
        <header class="mb-8">
            <a href="${pageContext.request.contextPath}/blog" class="inline-block bg-gray-200 hover:bg-gray-300 text-gray-700 font-medium py-2 px-4 rounded transition mb-4">
                ← Back to Blog Posts
            </a>
        </header>
        
        <main>
            <article class="bg-white shadow-md rounded-lg overflow-hidden">
                <div class="p-6">
                    <h1 class="text-3xl font-bold text-gray-800 mb-4">${post.title}</h1>
                    <div class="flex items-center text-gray-600 mb-6 pb-6 border-b border-gray-200">
                        <span class="mr-4">By: ${post.author}</span>
                        <span>Published: ${post.publishDate}</span>
                    </div>
                    
                    <div class="markdown prose max-w-none">
                        ${post.content}
                    </div>
                </div>
            </article>
        </main>
    </div>
</body>
</html>
