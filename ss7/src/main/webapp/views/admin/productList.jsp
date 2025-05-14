<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Management</title>
</head>
<body>
    <div class="container">
        <h1>Product Management</h1>
        
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success">${successMessage}</div>
        </c:if>
        
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger">${errorMessage}</div>
        </c:if>
        
        <div class="actions">
            <a href="${pageContext.request.contextPath}/admin/products/add" class="btn">Add New Product</a>
            
            <form action="${pageContext.request.contextPath}/admin/products/list" method="get" class="search-form">
                <input type="text" name="keyword" placeholder="Search by name or description" value="${keyword}" class="search-input">
                <button type="submit" class="btn">Search</button>
                <c:if test="${not empty keyword}">
                    <a href="${pageContext.request.contextPath}/admin/products/list" class="btn btn-danger">Clear</a>
                </c:if>
            </form>
        </div>
        
        <c:choose>
            <c:when test="${not empty products}">
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Image</th>
                            <th>Name</th>
                            <th>Category</th>
                            <th>Price</th>
                            <th>Stock</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="product" items="${products}">
                            <tr>
                                <td>${product.id}</td>
                                <td>
                                    <img style="width: 20%;" src="${product.image}" alt="${product.name}" class="product-image">
                                </td>
                                <td>${product.name}</td>
                                <td>
                                    <c:forEach var="category" items="${categories}">
                                        <c:if test="${category.id == product.categoryId}">
                                            <span class="category-badge">${category.name}</span>
                                        </c:if>
                                    </c:forEach>
                                </td>
                                <td class="price">$${product.price}</td>
                                <td>${product.stock}</td>
                                <td>
                                    <div class="action-buttons">
                                        <a href="${pageContext.request.contextPath}/admin/products/view/${product.id}" class="btn btn-info">View</a>
                                        <a href="${pageContext.request.contextPath}/admin/products/edit/${product.id}" class="btn">Edit</a>
                                        <a href="${pageContext.request.contextPath}/admin/products/delete/${product.id}" class="btn btn-danger" 
                                           onclick="return confirm('Are you sure you want to delete this product?')">Delete</a>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <div class="no-products">
                    <h3>No products found</h3>
                    <p>
                        <c:choose>
                            <c:when test="${not empty keyword}">
                                No products match your search criteria. Try a different search term.
                            </c:when>
                            <c:otherwise>
                                There are no products in the database. Click "Add New Product" to add one.
                            </c:otherwise>
                        </c:choose>
                    </p>
                </div>
            </c:otherwise>
        </c:choose>
        
        <div>
            <a href="${pageContext.request.contextPath}/products/list" class="btn">Back to Store</a>
        </div>
    </div>
</body>
</html>
