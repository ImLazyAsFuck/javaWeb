<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Shopping Cart</title>
</head>
<body>
    <div class="container">
        <div class="cart-header">
            <h1>Shopping Cart</h1>
            <a href="/products" class="continue-shopping">Continue Shopping</a>
        </div>
        
        <c:choose>
            <c:when test="${empty cartItems}">
                <div class="empty-cart">
                    <h2>Your cart is empty</h2>
                    <p>Looks like you haven't added any products to your cart yet.</p>
                </div>
            </c:when>
            <c:otherwise>
                <table class="cart-table">
                    <thead>
                        <tr>
                            <th>Product</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Total</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${cartItems}" var="item">
                            <tr>
                                <td>
                                    <div style="display: flex; align-items: center;">
                                        <img src="${item.product.imageUrl}" alt="${item.product.name}" class="product-image">
                                        <div style="margin-left: 15px;">
                                            <div class="product-name">${item.product.name}</div>
                                        </div>
                                    </div>
                                </td>
                                <td class="product-price">
                                    <fmt:formatNumber value="${item.product.price}" type="currency" currencySymbol="$" />
                                </td>
                                <td>
                                    <div class="quantity-cell">
                                        ${item.quantity}
                                    </div>
                                </td>
                                <td class="product-price">
                                    <fmt:formatNumber value="${item.product.price * item.quantity}" type="currency" currencySymbol="$" />
                                </td>
                                <td>
                                    <form action="/cart" method="post">
                                        <input type="hidden" name="action" value="remove">
                                        <input type="hidden" name="cartItemId" value="${item.id}">
                                        <button type="submit" class="remove-button">Remove</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                
                <div class="cart-total">
                    <div class="total-label">Total:</div>
                    <div class="total-value">
                        <fmt:formatNumber value="${cartTotal}" type="currency" currencySymbol="$" />
                    </div>
                    <button class="checkout-button">Checkout</button>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>