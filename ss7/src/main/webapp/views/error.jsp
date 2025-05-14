
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            margin: 0;
            padding: 20px;
            background-color: #f8f8f8;
            color: #333;
        }
        .container {
            max-width: 800px;
            margin: 40px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            text-align: center;
        }
        h1 {
            color: #e74c3c;
            margin-bottom: 20px;
        }
        .error-message {
            font-size: 18px;
            margin-bottom: 30px;
            color: #555;
        }
        .error-details {
            margin-top: 20px;
            padding: 15px;
            background-color: #f8f8f8;
            border-radius: 5px;
            text-align: left;
            font-family: monospace;
            overflow-x: auto;
        }
        .home-btn {
            display: inline-block;
            padding: 10px 20px;
            background-color: #3498db;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            transition: background-color 0.3s;
        }
        .home-btn:hover {
            background-color: #2980b9;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Oops! Something went wrong</h1>
        <div class="error-message">${message}</div>
        
        <c:if test="${not empty error}">
            <div class="error-details">
                ${error}
            </div>
        </c:if>
        
        <a href="${pageContext.request.contextPath}/" class="home-btn">Return to Home</a>
    </div>
</body>
</html>
