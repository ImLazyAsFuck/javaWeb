<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${product.name} - Product Details</title>
</head>
<body>
    <div class="container">
        <div class="product-header">
            <h1>${product.name}</h1>
            <span class="product-category">${category.name}</span>
        </div>
        
        <div class="product-content">
            <div class="product-image-container">
                <img style="width: 20%" src="${product.image}" alt="${product.name}" class="product-image">
            </div>
            
            <div class="product-info">
                <div class="product-price">$${product.price}</div>
                
                <c:choose>
                    <c:when test="${product.stock > 10}">
                        <div class="product-stock in-stock">In Stock: ${product.stock} units</div>
                    </c:when>
                    <c:when test="${product.stock > 0}">
                        <div class="product-stock low-stock">Low Stock: ${product.stock} units</div>
                    </c:when>
                    <c:otherwise>
                        <div class="product-stock out-of-stock">Out of Stock</div>
                    </c:otherwise>
                </c:choose>
                
                <div class="section-heading">Description:</div>
                <div class="product-description">${product.description}</div>
                
                <div class="section-heading">Product ID:</div>
                <div>${product.id}</div>
                
                <div class="action-buttons">
                    <a href="${pageContext.request.contextPath}/admin/products/edit/${product.id}" class="btn">Edit Product</a>
                    <a href="${pageContext.request.contextPath}/admin/products/delete/${product.id}" class="btn btn-danger"
                       onclick="return confirm('Are you sure you want to delete this product?')">Delete Product</a>
                </div>
            </div>
        </div>
        
        <a href="${pageContext.request.contextPath}/admin/products/list" class="back-btn">← Back to Product List</a>
    </div>
</body>
</html>
