<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 12/05/2025
  Time: 5:18 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        function confirmDelete(bookId) {
            if (confirm("Are you sure you want to delete this book?")) {
                window.location.href = "/books?action=delete&id=" + bookId;
            }
        }
    </script>
</head>
<body>
<div>
    <a href="/books?action=add">Add Book</a>
</div>
<div style="display: flex; margin-bottom: 15px; gap: 20px;">
    <form action="/books" method="get">
        <input type="hidden" name="action" value="find">
        <input type="hidden" name="searchType" value="title">
        <input type="text" name="titleKeyword" placeholder="Search by title...">
        <input type="submit" value="Search by Title">
    </form>
    
    <form action="/books" method="get">
        <input type="hidden" name="action" value="find">
        <input type="hidden" name="searchType" value="id">
        <input type="number" name="idKeyword" placeholder="Search by ID..." min="1">
        <input type="submit" value="Search by ID">
    </form>
</div>

<table border="1">
    <thead>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Author</th>
            <th>Category</th>
            <th>Stock</th>
            <th colspan="2">Action</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach items="${books}" var="book">
        <tr>
            <td>${book.id}</td>
            <td>${book.title}</td>
            <td>${book.author}</td>
            <td>${book.category}</td>
            <td>${book.stock}</td>
            <td><a href="/books?action=edit&id=${book.id}">Edit</a></td>
            <td><a href="javascript:void(0);" onclick="confirmDelete(${book.id});">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="/index.jsp">back</a>
</body>
</html>
