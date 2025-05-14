<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 13/05/2025
  Time: 9:31 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Danh Sách Góp Ý</title>
</head>
<body>
    <h2>Danh Sách Góp Ý</h2>
    
    <c:if test="${not empty successMessage}">
        <div class="success">${successMessage}</div>
    </c:if>
    
    <c:choose>
        <c:when test="${empty feedbackList}">
            <p class="empty-list">Chưa có góp ý nào được gửi.</p>
        </c:when>
        <c:otherwise>
            <table>
                <thead>
                    <tr>
                        <th>STT</th>
                        <th>Họ và tên</th>
                        <th>Số điện thoại</th>
                        <th>Địa chỉ</th>
                        <th>Nội dung góp ý</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${feedbackList}" var="feedback" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${feedback.name}</td>
                            <td>${not empty feedback.phone ? feedback.phone : 'N/A'}</td>
                            <td>${not empty feedback.address ? feedback.address : 'N/A'}</td>
                            <td>${feedback.content}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
    
    <a class="back-link" href="/form">Quay lại biểu mẫu góp ý</a>
    <a href="/">back</a>
</body>
</html>
