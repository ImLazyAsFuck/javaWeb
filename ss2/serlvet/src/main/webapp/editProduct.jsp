<%@ page import="com.example.model.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Product</title>
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
            padding: 40px 0;
        }
        .form {
            min-width: 30%;
            min-height: 30%;
            border: 1px solid #000;
            border-radius: 10px;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            gap: 20px;
            padding: 20px;
            background-color: #f9f9f9;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        .label {
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            gap: 10px;
            width: 100%;
        }
        .button {
            border: none;
            border-radius: 10px;
            padding: 10px 20px;
            cursor: pointer;
            background: #08f;
            color: #fff;
            font-weight: bold;
            transition: background-color 0.3s;
        }
        .button:hover {
            background: #0077cc;
        }
        .input {
            outline: none;
            border: 1px solid #000;
            border-radius: 10px;
            padding: 10px;
            width: 100%;
        }
        .actions {
            display: flex;
            gap: 10px;
            margin-top: 10px;
        }
        .cancel-btn {
            background-color: #dc3545;
        }
        .cancel-btn:hover {
            background-color: #c82333;
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
    
    <h1>Edit Product</h1>
    
    <form class="form" action="editproduct" method="post">
        <input type="hidden" name="id" value="<%= product.getId() %>">
        
        <label class="label">
            Name:
            <input type="text" name="name" class="input" value="<%= product.getName() %>" required/>
        </label>
        
        <label class="label">
            Price:
            <input min="1000" type="number" name="price" class="input" value="<%= product.getPrice() %>" required/>
        </label>
        
        <div class="actions">
            <button type="submit" class="button">Update Product</button>
            <a href="products" class="button cancel-btn">Cancel</a>
        </div>
    </form>
</body>
</html>
