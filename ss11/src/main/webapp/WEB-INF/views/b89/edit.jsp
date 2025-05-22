<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Chỉnh sửa danh mục</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
        }
        h1 {
            color: #333;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        input[type="text"], select {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }
        .error {
            color: red;
            font-size: 14px;
            margin-top: 5px;
        }
        button {
            background-color: #2196F3;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background-color: #0b7dda;
        }
        .back-link {
            display: inline-block;
            margin-top: 20px;
            color: #2196F3;
            text-decoration: none;
        }
        .back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Chỉnh sửa danh mục</h1>
        
        <c:if test="${not empty errorMessage}">
            <div class="error-message" style="background-color: #f8d7da; color: #721c24; padding: 10px; margin-bottom: 20px; border-radius: 4px;">
                ${errorMessage}
            </div>
        </c:if>
        
        <form:form action="${pageContext.request.contextPath}/categories/edit/${category.id}" method="post" modelAttribute="category">
            <div class="form-group">
                <label for="categoryName">Tên danh mục:</label>
                <form:input path="categoryName" id="categoryName" />
                <form:errors path="categoryName" cssClass="error" />
            </div>
            
            <div class="form-group">
                <label for="status">Trạng thái:</label>
                <form:select path="status" id="status">
                    <form:option value="true" label="Hoạt động" />
                    <form:option value="false" label="Không hoạt động" />
                </form:select>
                <form:errors path="status" cssClass="error" />
            </div>
            
            <button type="submit">Cập nhật</button>
        </form:form>
        
        <a href="${pageContext.request.contextPath}/categories" class="back-link">Quay lại danh sách</a>
    </div>
</body>
</html>
