<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 15/05/2025
  Time: 5:21 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css"
          rel="stylesheet">
</head>
<body>
    <h2>Movie</h2>
    <a href="/">back to main menu</a>
    <c:forEach var="movie" items="${movies}">
        <div>
            <p>${movie.title}</p>
            <p>${movie.director}</p>
            <p>${movie.description}</p>
            <p>${movie.genre}</p>
            <p>${movie.language}</p>
            <p>${movie.duration}</p>
            <a href="/movie/detail/${movie.id}">
                <button>Detail</button>
            </a>
        </div>
    </c:forEach>
</body>
</html>
