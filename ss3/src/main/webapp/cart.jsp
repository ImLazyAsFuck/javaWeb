<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.model.Cart" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.DecimalFormat" %>
<html>
<head>
    <title>Shopping Cart</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: sans-serif;
        }
        body {
            width: 100%;
            background-color: #f4f4f4;
            padding: 20px;
        }
        .container {
            max-width: 1000px;
            margin: 0 auto;
            background: #fff;
            padding: 20px;
            border-radius: 12px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
        header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
            padding-bottom: 20px;
            border-bottom: 1px solid #eee;
        }
        h1 {
            color: #333;
            margin-bottom: 20px;
        }
        .back-link {
            color: #007bff;
            text-decoration: none;
            display: flex;
            align-items: center;
        }
        .back-link:hover {
            text-decoration: underline;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
            font-weight: bold;
        }
        .product-name {
            font-weight: bold;
        }
        .quantity-input {
            width: 60px;
            padding: 6px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        .update-btn {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 6px 10px;
            border-radius: 4px;
            cursor: pointer;
            margin-left: 5px;
        }
        .update-btn:hover {
            background-color: #0056b3;
        }
        .remove-btn {
            background-color: #dc3545;
            color: white;
            border: none;
            padding: 6px 10px;
            border-radius: 4px;
            cursor: pointer;
        }
        .remove-btn:hover {
            background-color: #c82333;
        }
        .total-section {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-top: 20px;
            padding-top: 20px;
            border-top: 1px solid #ddd;
        }
        .total-price {
            font-size: 1.2em;
            font-weight: bold;
        }
        .checkout-btn {
            background-color: #28a745;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            cursor: pointer;
            font-weight: bold;
        }
        .checkout-btn:hover {
            background-color: #218838;
        }
        .empty-cart {
            text-align: center;
            padding: 40px 0;
        }
        .empty-cart h2 {
            margin-bottom: 20px;
            color: #666;
        }
        .continue-shopping {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
        }
        .continue-shopping:hover {
            background-color: #0056b3;
        }
        .clear-cart {
            background-color: #6c757d;
            color: white;
            border: none;
            padding: 8px 15px;
            border-radius: 4px;
            cursor: pointer;
            margin-right: 10px;
        }
        .clear-cart:hover {
            background-color: #5a6268;
        }
        .message {
            background-color: #d4edda;
            color: #155724;
            padding: 10px;
            margin-bottom: 20px;
            border-radius: 5px;
        }
    </style>
</head>
<body>
<div class="container">
    <header>
        <h1>Shopping Cart</h1>
        <a href="product" class="back-link">← Continue Shopping</a>
    </header>

    <% 
        String message = (String) session.getAttribute("message");
        if(message != null) {
    %>
        <div class="message"><%= message %></div>
    <% 
            session.removeAttribute("message");
        }
    %>

    <% 
        List<Cart> cartItems = (List<Cart>) request.getAttribute("cartItems");
        if(cartItems != null && !cartItems.isEmpty()) {
            DecimalFormat df = new DecimalFormat("#,##0.00");
    %>
        <table>
            <thead>
                <tr>
                    <th>Product</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Subtotal</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <% for(Cart cartItem : cartItems) { %>
                <tr>
                    <td class="product-name"><%= cartItem.getProduct().getName() %></td>
                    <td>$<%= df.format(cartItem.getProduct().getPrice()) %></td>
                    <td>
                        <form action="cart" method="post" style="display: flex; align-items: center;">
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="productId" value="<%= cartItem.getProduct().getId() %>">
                            <input type="number" name="quantity" value="<%= cartItem.getQuantity() %>" min="1" class="quantity-input">
                            <button type="submit" class="update-btn">Update</button>
                        </form>
                    </td>
                    <td>$<%= df.format(cartItem.getSubtotal()) %></td>
                    <td>
                        <form action="cart" method="post">
                            <input type="hidden" name="action" value="remove">
                            <input type="hidden" name="productId" value="<%= cartItem.getProduct().getId() %>">
                            <button type="submit" class="remove-btn">Remove</button>
                        </form>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>

        <div class="total-section">
            <form action="cart" method="post">
                <input type="hidden" name="action" value="clear">
                <button type="submit" class="clear-cart">Clear Cart</button>
            </form>
            <div class="total-price">
                Total: $<%= df.format(request.getAttribute("totalPrice")) %>
            </div>
            <button class="checkout-btn">Proceed to Checkout</button>
        </div>
    <% } else { %>
        <div class="empty-cart">
            <h2>Your cart is empty</h2>
            <p>Looks like you haven't added any products to your cart yet.</p>
            <a href="product" class="continue-shopping">Start Shopping</a>
        </div>
    <% } %>
</div>
</body>
</html>