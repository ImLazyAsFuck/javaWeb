<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <form action="b2" method="post">
        <h2>Login</h2>
        <label>
            <input type="text" placeholder="username" name="username">
        </label>
        <label>
            <input type="password" placeholder="password" name="password">
        </label>
        <button type="submit">Login</button>
    </form>

    <c:if test="${not empty title}">
        <div class="alert success">
            <h2>${title}</h2>
        </div>
    </c:if>

    <c:if test="${not empty error}">
        <div class="alert error">
            <p>${error}</p>
        </div>
    </c:if>

    <a href="/">back</a>
</body>
</html>
