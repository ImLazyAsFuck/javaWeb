<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Product List</title>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>Products</h1>
            <a href="/cart" class="cart-link">View Cart</a>
        </div>
        
        <div class="product-grid">
            <c:forEach items="${products}" var="product">
                <div class="product-card">
                    <img src="${product.imageUrl}" alt="${product.name}" class="product-image">
                    <div class="product-info">
                        <div class="product-name">${product.name}</div>
                        <div class="product-price">
                            <fmt:formatNumber value="${product.price}" type="currency" currencySymbol="$" />
                        </div>
                        <form action="/products" method="post">
                            <input type="hidden" name="action" value="addToCart">
                            <input type="hidden" name="productId" value="${product.id}">
                            <div style="display: flex; margin-bottom: 10px;">
                                <input type="number" name="quantity" value="1" min="1" class="quantity-input">
                                <button type="submit" class="add-to-cart">Add to Cart</button>
                            </div>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>
