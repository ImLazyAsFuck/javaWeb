<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 19/05/2025
  Time: 8:44 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<header>
  <div>
    <h1>Project List</h1>
    <a href="/">back</a>
  </div>
  <button onclick="window.location.href='/project/add'">Add Project</button>
  <table border="1">
    <thead>
    <tr>
      <th>Name</th>
      <th>Description</th>
      <th>Document</th>
      <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${projects}" var="project">
      <tr>
        <td>${project.name}</td>
        <td>${project.description}</td>
        <td><img width="200px" src="${project.documents}" alt="${project.name}"/></td>
        <td>
          <a href="/project/edit?id=${project.id}">Edit</a>
          <p style="" onclick="handleDelete(${project.id})" href="/project/delete?id=${project.id}">Delete</p>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</header>
<script>
  function handleDelete(projectId) {
    if (confirm('Are you sure?')) {
      return window.location.href = '/project/delete?id=' + projectId;
    }
  }
</script>
</body>
</html>
