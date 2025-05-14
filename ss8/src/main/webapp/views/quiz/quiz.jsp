<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div>
        <c:if test="${not empty message}">
            <p style="color: ${message eq 'Correct answer!' ? 'green' : 'red'}">${message}</p>
        </c:if>
        <img src="${question.imageUrl}" width="20%">
        <form action="/guess" method="post">
            <input type="hidden" name="questionId" value="${question.id}">
            <input type="text" name="answer" value=""><br>
            <input type="submit" value="Submit">
        </form>
        <a href="/">back</a>
    </div>
</body>
</html>
