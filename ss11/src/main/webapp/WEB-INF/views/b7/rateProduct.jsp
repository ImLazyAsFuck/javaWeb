<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 22/05/2025
  Time: 8:16 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Đánh giá sản phẩm</title>
    <style>
        .error {
            color: red;
        }
    </style>
</head>
<body>
    <a href="/">back</a>
    <h1>Đánh giá sản phẩm</h1>
    
    <form:form action="/b7/rate" method="post" modelAttribute="rating">
        <div class="form-group">
            <label for="name">Tên:</label>
            <form:input path="name" id="name" />
            <form:errors path="name" cssClass="error" element="span" />
        </div>
        
        <div class="form-group">
            <label for="email">Email:</label>
            <form:input path="email" id="email" type="email" />
            <form:errors path="email" cssClass="error" element="span" />
        </div>
        
        <div class="form-group">
            <label>Đánh giá:</label>
            <div class="rating">
                <form:radiobutton path="rating" id="star5" value="5" />
                <label for="star5">&#9733;</label>
                <form:radiobutton path="rating" id="star4" value="4" />
                <label for="star4">&#9733;</label>
                <form:radiobutton path="rating" id="star3" value="3" />
                <label for="star3">&#9733;</label>
                <form:radiobutton path="rating" id="star2" value="2" />
                <label for="star2">&#9733;</label>
                <form:radiobutton path="rating" id="star1" value="1" />
                <label for="star1">&#9733;</label>
            </div>
            <form:errors path="rating" cssClass="error" element="span" />
        </div>
        
        <div class="form-group">
            <label for="comment">Bình luận:</label>
            <form:textarea path="comment" id="comment" rows="4" />
            <form:errors path="comment" cssClass="error" element="span" />
        </div>
        
        <button type="submit">Gửi đánh giá</button>
    </form:form>
</body>
</html>
