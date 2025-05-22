<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title><c:choose>
        <c:when test="${movie.id == 0}">Add Movie</c:when>
        <c:otherwise>Edit Movie</c:otherwise>
    </c:choose></title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .error { color: red; }
        .form-group { margin-bottom: 15px; }
        label { display: inline-block; width: 100px; }
        input, select { padding: 5px; }
    </style>
</head>
<body>
<h2><c:choose>
    <c:when test="${movie.id == 0}">Add Movie</c:when>
    <c:otherwise>Edit Movie</c:otherwise>
</c:choose></h2>

<c:if test="${not empty errorMessage}">
    <p class="error">${errorMessage}</p>
</c:if>

<form:form modelAttribute="movie"
           method="post"
           enctype="multipart/form-data"
           action="${pageContext.request.contextPath}/movies/${movie.id == 0 ? 'save' : 'update'}">

    <form:hidden path="id"/>

    <div class="form-group">
        <form:label path="title">Title:</form:label>
        <form:input path="title" maxlength="100" />
        <form:errors path="title" cssClass="error"/>
    </div>

    <div class="form-group">
        <form:label path="director">Director:</form:label>
        <form:input path="director" maxlength="50" />
        <form:errors path="director" cssClass="error"/>
    </div>

    <div class="form-group">
        <form:label path="releaseDate">Release Date:</form:label>
        <form:input path="releaseDate" type="date"/>
        <form:errors path="releaseDate" cssClass="error"/>
    </div>

    <div class="form-group">
        <form:label path="genre">Genre:</form:label>
        <form:input path="genre" maxlength="30" />
        <form:errors path="genre" cssClass="error"/>
    </div>

    <div class="form-group">
        <label for="posterFile">Poster:</label>
        <input type="file" id="posterFile" name="posterFile" accept="image/*" />
        <c:if test="${not empty movie.poster}">
            <p>Current Poster: <img src="${movie.poster}" alt="Poster" style="max-width: 100px; height: auto;"/></p>
        </c:if>
    </div>

    <button type="submit">Save</button>
    <a href="${pageContext.request.contextPath}/movies">Cancel</a>

</form:form>

</body>
</html>
