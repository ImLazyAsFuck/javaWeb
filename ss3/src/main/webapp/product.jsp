<%@ page import="java.util.List" %>
<%@ page import="com.example.model.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.model.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.service.CartManager" %>
<%@ page import="com.example.model.Cart" %>
<html>
<head>
    <title>Products</title>
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
        .cart-icon {
            position: relative;
            font-size: 24px;
        }
        .cart-count {
            position: absolute;
            top: -10px;
            right: -10px;
            background-color: #dc3545;
            color: white;
            border-radius: 50%;
            width: 20px;
            height: 20px;
            display: flex;
            justify-content: center;
            align-items: center;
            font-size: 12px;
        }
        h1 {
            color: #333;
            margin-bottom: 20px;
        }
        .product-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
            gap: 20px;
        }
        .product-card {
            border: 1px solid #ddd;
            border-radius: 8px;
            overflow: hidden;
            transition: transform 0.3s;
        }
        .product-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 10px 20px rgba(0,0,0,0.1);
        }
        .product-image {
            height: 200px;
            background-color: #f9f9f9;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .product-image img {
            max-width: 100%;
            max-height: 100%;
        }
        .product-details {
            padding: 15px;
        }
        .product-title {
            font-weight: bold;
            margin-bottom: 5px;
        }
        .product-price {
            color: #dc3545;
            font-weight: bold;
            margin-bottom: 15px;
        }
        .add-to-cart-form {
            display: flex;
            margin-top: 10px;
        }
        .add-to-cart-form input {
            width: 60px;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px 0 0 4px;
        }
        .add-to-cart-form button {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 8px 15px;
            border-radius: 0 4px 4px 0;
            cursor: pointer;
        }
        .add-to-cart-form button:hover {
            background-color: #0056b3;
        }
        .message {
            background-color: #d4edda;
            color: #155724;
            padding: 10px;
            margin-bottom: 20px;
            border-radius: 5px;
        }
        .error {
            background-color: #f8d7da;
            color: #721c24;
            padding: 10px;
            margin-bottom: 20px;
            border-radius: 5px;
        }
    </style>
</head>
<body>
<div class="container">
    <header>
        <h1>Our Products</h1>
        <a href="cart" class="cart-icon">
            🛒
            <% 
               List<Cart> cartItems = CartManager.getCart();
               int cartCount = cartItems.size();
            %>
            <span class="cart-count"><%= cartCount %></span>
        </a>
    </header>

    <% 
        String message = (String) session.getAttribute("message");
        String error = (String) session.getAttribute("error");
        
        if(message != null) {
    %>
        <div class="message"><%= message %></div>
    <% 
            session.removeAttribute("message");
        }
        
        if(error != null) {
    %>
        <div class="error"><%= error %></div>
    <% 
            session.removeAttribute("error");
        }
    %>

    <div class="product-grid">
        <% 
            List<Product> products = (List<Product>) request.getAttribute("products");
            if(products != null && !products.isEmpty()) {
                for(Product product : products) {
        %>
            <div class="product-card">
                <div class="product-image">
                    <span style="font-size: 48px;">📱</span>
                </div>
                <div class="product-details">
                    <div class="product-title"><%= product.getName() %></div>
                    <div class="product-price">$<%= product.getPrice() %></div>
                    
                    <form action="product" method="post" class="add-to-cart-form">
                        <input type="hidden" name="productId" value="<%= product.getId() %>">
                        <button type="submit">Add to Cart</button>
                    </form>
                </div>
            </div>
        <% 
                }
            } else {
        %>
            <p>No products available.</p>
        <% 
            }
        %>
    </div>
    <br/>
<a href="/">Back to Home</a>
</div>
</body>
</html>
