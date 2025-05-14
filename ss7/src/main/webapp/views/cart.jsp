<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shopping Cart</title>
</head>
<body>
    <div class="container">
        <h1>Your Shopping Cart</h1>
        
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success">${successMessage}</div>
        </c:if>
        
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger">${errorMessage}</div>
        </c:if>
        
        <c:choose>
            <c:when test="${empty cartItems}">
                <div class="empty-cart">
                    <div class="empty-cart-icon">🛒</div>
                    <div class="empty-cart-message">Your cart is empty</div>
                    <a href="${pageContext.request.contextPath}/products/list" class="btn">Continue Shopping</a>
                </div>
            </c:when>
            <c:otherwise>
                <div class="cart-items">
                    <c:forEach var="item" items="${cartItems}">
                        <div class="cart-item">
                            <img style="width: 20%" src="${item.product.image}" alt="${item.product.name}" class="item-image">
                            <div class="item-details">
                                <div class="item-name">${item.product.name}</div>
                                <div class="item-price">$${item.product.price}</div>
                                <div class="item-controls">
                                    <div class="quantity-control">
                                        <form action="${pageContext.request.contextPath}/products/decrease-quantity" method="post" class="control-form">
                                            <input type="hidden" name="productId" value="${item.product.id}">
                                            <button type="submit" class="quantity-btn">-</button>
                                        </form>
                                        <span class="item-quantity">${item.quantity}</span>
                                        <form action="${pageContext.request.contextPath}/products/increase-quantity" method="post" class="control-form">
                                            <input type="hidden" name="productId" value="${item.product.id}">
                                            <button type="submit" class="quantity-btn">+</button>
                                        </form>
                                    </div>
                                    <form action="${pageContext.request.contextPath}/products/remove-from-cart" method="post" class="control-form">
                                        <input type="hidden" name="productId" value="${item.product.id}">
                                        <button type="submit" class="btn btn-remove">Remove</button>
                                    </form>
                                </div>
                            </div>
                            <div class="item-subtotal">$${item.subtotal}</div>
                        </div>
                    </c:forEach>
                </div>
                
                <div class="cart-summary">
                    <div class="cart-total">
                        <span>Total:</span>
                        <span class="total-amount">$${cartTotal}</span>
                    </div>
                    <div class="cart-actions">
                        <a href="${pageContext.request.contextPath}/products/list" class="btn">Continue Shopping</a>
                        <button class="btn" style="background-color: #2ecc71;">Proceed to Checkout</button>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>