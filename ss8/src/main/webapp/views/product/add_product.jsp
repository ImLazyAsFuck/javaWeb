
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/products/add" method="post">
        <input placeholder="name" name="name" type="text">
        <input placeholder="price" name="price" type="text">
        <input placeholder="stock" name="stock" type="number" value="0">
        <button name="submit" type="submit">Add Product</button>
    </form>
</body>
</html>
