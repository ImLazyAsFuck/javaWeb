<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product List</title>
</head>
<body>
    <div class="container">
        <div class="page-header">
            <h1>Our Products</h1>
            <a href="${pageContext.request.contextPath}/products/cart" class="cart-btn">
                <span class="cart-icon">🛒</span>
                View Cart
                <span class="cart-badge">${cartSize}</span>
            </a>
        </div>
        
        <a href="/" class="back-link">← Back to Home</a>
        
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success">${successMessage}</div>
        </c:if>
        
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger">${errorMessage}</div>
        </c:if>
        
        <div class="product-grid">
            <c:forEach var="product" items="${products}">
                <div class="product-card">
                    <img style="width: 20%" src="${product.image}" alt="${product.name}" class="product-image">
                    <div class="product-info">
                        <div class="product-name">${product.name}</div>
                        <div class="product-price">$${product.price}</div>
                        <div class="product-stock">In stock: ${product.stock}</div>
                        <a href="${pageContext.request.contextPath}/products/details?id=${product.id}" class="btn">View Details</a>
                        
                        <form action="${pageContext.request.contextPath}/products/add-to-cart" method="post" class="product-form">
                            <input type="hidden" name="productId" value="${product.id}">
                            <input type="number" name="quantity" value="1" min="1" max="${product.stock}" class="quantity-input">
                            <button type="submit" class="btn btn-add-cart">Add to Cart</button>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>
