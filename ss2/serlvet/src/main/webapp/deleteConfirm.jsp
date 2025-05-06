<%@ page import="com.example.model.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Confirm Delete</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body {
            width: 100%;
            height: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            gap: 30px;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            padding: 60px 0;
        }
        .confirm-box {
            width: 500px;
            padding: 30px;
            border: 1px solid #ccc;
            border-radius: 10px;
            background-color: #f9f9f9;
            box-shadow: 0 2px 15px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        .confirm-title {
            font-size: 24px;
            color: #dc3545;
            margin-bottom: 20px;
        }
        .product-info {
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            border: 1px solid #eee;
            margin-bottom: 25px;
            text-align: left;
        }
        .product-property {
            margin: 10px 0;
            font-size: 16px;
        }
        .product-property span {
            font-weight: bold;
        }
        .confirm-message {
            font-size: 18px;
            margin-bottom: 25px;
            color: #333;
        }
        .button-group {
            display: flex;
            justify-content: center;
            gap: 15px;
        }
        .button {
            border: none;
            border-radius: 6px;
            padding: 12px 24px;
            cursor: pointer;
            font-weight: bold;
            font-size: 16px;
            transition: background-color 0.3s;
        }
        .delete-btn {
            background-color: #dc3545;
            color: white;
        }
        .delete-btn:hover {
            background-color: #c82333;
        }
        .cancel-btn {
            background-color: #6c757d;
            color: white;
        }
        .cancel-btn:hover {
            background-color: #5a6268;
        }
    </style>
</head>
<body>
    <%
        Product product = (Product) request.getAttribute("product");
        if (product == null) {
            response.sendRedirect("products");
            return;
        }
    %>
    
    <div class="confirm-box">
        <h1 class="confirm-title">Confirm Deletion</h1>
        
        <div class="product-info">
            <div class="product-property">
                <span>ID:</span> <%= product.getId() %>
            </div>
            <div class="product-property">
                <span>Name:</span> <%= product.getName() %>
            </div>
            <div class="product-property">
                <span>Price:</span> <%= product.getPrice() %>
            </div>
        </div>
        
        <p class="confirm-message">Are you sure you want to delete this product?</p>
        
        <div class="button-group">
            <form action="delete-product" method="post">
                <input type="hidden" name="id" value="<%= product.getId() %>">
                <input type="hidden" name="confirmed" value="yes">
                <button type="submit" class="button delete-btn">Yes, Delete</button>
            </form>
            
            <a href="products">
                <button class="button cancel-btn">Cancel</button>
            </a>
        </div>
    </div>
</body>
</html>
