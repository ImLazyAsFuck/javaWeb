<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Movie List</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        table { width: 100%; border-collapse: collapse; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        .error { color: red; }
        .poster-img { max-width: 100px; height: auto; }
    </style>
</head>
<body>
<a href="/">back</a>
<h2>Movie List</h2>
<a href="${pageContext.request.contextPath}/movies/add">Add New Movie</a>
<c:if test="${not empty errorMessage}">
    <p class="error">${errorMessage}</p>
</c:if>
<table>
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Director</th>
        <th>Release Date</th>
        <th>Genre</th>
        <th>Poster</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="movie" items="${movies}">
        <tr>
            <td>${movie.id}</td>
            <td>${movie.title}</td>
            <td>${movie.director}</td>
            <td>${movie.releaseDate}</td>
            <td>${movie.genre}</td>
            <td>
                <c:if test="${not empty movie.poster}">
                    <img src="${movie.poster}" alt="Poster" class="poster-img"/>
                </c:if>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/movies/edit/${movie.id}">Edit</a>
                <a href="${pageContext.request.contextPath}/movies/delete/${movie.id}"
                   onclick="return confirm('Are you sure you want to delete this movie?')">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>