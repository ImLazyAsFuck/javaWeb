<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 13/05/2025
  Time: 9:11 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Biểu Mẫu Góp Ý</title>
</head>
<body>
  <h2>Biểu Mẫu Góp Ý</h2>
  
  <c:if test="${not empty errorMessage}">
    <div class="error">${errorMessage}</div>
  </c:if>
  
  <form action="/form/submit" method="post">
    <div class="form-group">
      <label class="required">Họ và tên</label>
      <input type="text" name="name" value="${feedback.name}" required>
    </div>
    
    <div class="form-group">
      <label>Số điện thoại</label>
      <input type="text" name="phone" value="${feedback.phone}" placeholder="VD: 0987654321">
    </div>
    
    <div class="form-group">
      <label>Địa chỉ</label>
      <input type="text" name="address" value="${feedback.address}">
    </div>
    
    <div class="form-group">
      <label class="required">Nội dung góp ý</label>
      <textarea name="content" required>${feedback.content}</textarea>
    </div>
    
    <button type="submit">Gửi Góp Ý</button>
  </form>
  
  <p><a href="/form/list">Xem danh sách góp ý</a></p>
  <a href="/">back</a>
</body>
</html>
