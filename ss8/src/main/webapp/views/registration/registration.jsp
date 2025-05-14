<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/">back</a>
<form action="registration" method="post">
  <label>Name:</label>
  <input type="text" name="name" value="${user.name}">
  <c:if test="${not empty nameError}">
    <p style="color:red;">${nameError}</p>
  </c:if>

  <label>Email:</label>
  <input type="text" name="email" value="${user.email}">
  <c:if test="${not empty emailError}">
    <p style="color:red;">${emailError}</p>
  </c:if>

  <label>Phone Number:</label>
  <input type="text" name="phoneNumber" value="${user.phone}">
  <c:if test="${not empty phoneError}">
    <p style="color:red;">${phoneError}</p>
  </c:if>

  <button type="submit">Submit</button>
</form>
</body>
</html>
