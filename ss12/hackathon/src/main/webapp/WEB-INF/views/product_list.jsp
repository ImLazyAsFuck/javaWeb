<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
<!DOCTYPE html>
<html>
<head>
    <title>Product List</title>
</head>
<body class="bg-gray-100 text-gray-800">

<div class="container mx-auto mt-10 px-4">
    <h1 class="text-3xl font-bold mb-6 text-center">Danh sách sản phẩm</h1>
    <form action="/product" method="get" class="mb-6 flex justify-center">
        <input type="text" name="name" placeholder="Tìm kiếm sản phẩm..."
               class="border border-gray-300 rounded-l px-4 py-2 w-1/2" />
        <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded-r hover:bg-blue-600">Tìm</button>
    </form>

    <div class="mb-4 flex justify-between">
        <a href="/" class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">Quay lại</a>
        <a href="/product/add" class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">Thêm sản phẩm</a>
    </div>

    <table class="min-w-full bg-white shadow-md rounded-lg overflow-hidden">
        <thead class="bg-gray-200">
        <tr>
            <th class="py-3 px-5 text-left">ID</th>
            <th class="py-3 px-5 text-left">Tên</th>
            <th class="py-3 px-5 text-left">Giá</th>
            <th class="py-3 px-5 text-left">Ảnh</th>
            <th class="py-3 px-5 text-left">Mô tả</th>
            <th class="py-3 px-5 text-left">Trạng thái</th>
            <th class="py-3 px-5 text-left">Ngày tạo</th>
            <th class="py-3 px-5 text-center">Hành động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="product" items="${products}">
            <tr class="border-b hover:bg-gray-100">
                <td class="py-2 px-5">${product.id}</td>
                <td class="py-2 px-5">${product.name}</td>
                <td class="py-2 px-5">${product.price}</td>
                <td class="py-2 px-5">
                    <img src="${product.image}" alt="${product.name}" width="100px" height="100px">
                </td>
                <td class="py-2 px-5">${product.description}</td>
                <td class="py-2 px-5">${product.status}</td>
                <td class="py-2 px-5">${product.createdAt}</td>
                <td class="py-2 px-5 text-center">
                    <a href="/product/edit/${product.id}" class="text-yellow-500 hover:text-yellow-700 mr-2">Sửa</a>
                    <p class="text-red-500 hover:text-red-700 cursor-pointer" onclick="confirmDelete(${product.id})">Xóa</p>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script>
    function confirmDelete(id) {
        if(confirm("Bạn có chắc chắn muốn xóa sản phẩm này?"))
            window.location.href = "/product/delete/" + id;
    }
</script>
</body>
</html>
