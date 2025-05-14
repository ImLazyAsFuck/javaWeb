<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${product.name} - Details</title>
</head>
<body>
    <div class="container">
        <div class="page-header">
            <div class="product-title">${product.name}</div>
            <a href="${pageContext.request.contextPath}/products/cart" class="cart-btn">
                <span class="cart-icon">🛒</span>
                View Cart
                <span class="cart-badge">${cartSize}</span>
            </a>
        </div>
        
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success">${successMessage}</div>
        </c:if>
        
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger">${errorMessage}</div>
        </c:if>
        
        <div class="product-content">
            <div class="product-image-container">
                <img style="width: 20%" src="${product.image}" alt="${product.name}" class="product-image">
            </div>
            <div class="product-details">
                <div class="product-price">$${product.price}</div>
                
                <c:choose>
                    <c:when test="${product.stock > 10}">
                        <div class="product-stock">In Stock: ${product.stock} units</div>
                    </c:when>
                    <c:when test="${product.stock > 5}">
                        <div class="product-stock low">Low Stock: ${product.stock} units</div>
                    </c:when>
                    <c:otherwise>
                        <div class="product-stock critical">Very Low Stock: ${product.stock} units</div>
                    </c:otherwise>
                </c:choose>
                
                <div class="product-id">Product ID: ${product.id}</div>
                <div class="product-description">${product.description}</div>
                
                <form action="${pageContext.request.contextPath}/products/add-to-cart" method="post" class="product-form">
                    <input type="hidden" name="productId" value="${product.id}">
                    <label for="quantity">Quantity:</label>
                    <input type="number" id="quantity" name="quantity" value="1" min="1" max="${product.stock}" class="quantity-input">
                    <button type="submit" class="btn btn-add-cart">Add to Cart</button>
                </form>
                
                <div style="margin-top: 20px;">
                    <a href="${pageContext.request.contextPath}/products/list" class="btn">Back to Products</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
