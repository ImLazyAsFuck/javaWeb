<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Kết Quả Gửi Góp Ý</title>
</head>
<body>
    <h2>Góp Ý Đã Được Ghi Nhận</h2>
    
    <div class="success-message">
        Cảm ơn bạn đã gửi góp ý! Chúng tôi đã nhận được thông tin của bạn.
    </div>
    
    <h3>Thông tin góp ý của bạn:</h3>
    
    <table>
        <tr>
            <th>Họ và tên</th>
            <td>${feedback.name}</td>
        </tr>
        <tr>
            <th>Số điện thoại</th>
            <td>${not empty feedback.phone ? feedback.phone : 'Không cung cấp'}</td>
        </tr>
        <tr>
            <th>Địa chỉ</th>
            <td>${not empty feedback.address ? feedback.address : 'Không cung cấp'}</td>
        </tr>
        <tr>
            <th>Nội dung góp ý</th>
            <td>${feedback.content}</td>
        </tr>
    </table>
    
    <div class="navigation">
        <a href="/form">Gửi góp ý khác</a>
        <a href="/form/list">Xem tất cả góp ý</a>
        <a href="/">back</a>
    </div>
</body>
</html>
