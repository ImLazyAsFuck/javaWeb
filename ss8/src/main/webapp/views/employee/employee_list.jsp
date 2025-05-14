<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 14/05/2025
  Time: 7:41 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <a href="/">Back</a>
  <a href="/employees/add">Add Employee</a>
  <table>
    <thead>
      <tr>
        <th>Name</th>
        <th>Email</th>
        <th>Position</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="employee" items="${employees}">
        <tr>
          <td>${employee.name}</td>
          <td>${employee.email}</td>
          <td>${employee.position}</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</body>
</html>
