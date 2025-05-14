<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/">Back</a>
<a href="products/add">Add New Product</a>
  <table border="1">
    <thead>
      <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Stock</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${products}" var="product">
        <tr>
          <td>${product.id}</td>
          <td>${product.name}</td>
          <td>${product.price}</td>
          <td>${product.stock}</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</body>
</html>
