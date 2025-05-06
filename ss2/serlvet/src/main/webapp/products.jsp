<%@ page import="com.example.model.Product" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        *{
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body{
            width: 100%;
            height: 100%;
            /*background-color: #f5f5f5;*/
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            gap: 30px;
        }
        .form{
            min-width: 30%;
            min-height: 30%;
            border: 1px solid #000;
            border-radius: 10px;
            /*background: ;*/
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            gap: 20px;
            padding: 20px;
        }

        .label{
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            gap: 10px;
        }

        .button{
            border: none;
            border-radius: 10px;
            padding: 10px 20px;
            cursor: pointer;
            background: #08f;
            color: #fff;
        }

        .input{
            outline: none;
            border: 1px solid #000;
            border-radius: 10px;
            padding: 10px;
        }

        table {
            width: 80%;
            border-collapse: collapse;
            margin: 20px auto;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        th, td {
            padding: 12px 16px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #007BFF;
            color: white;
            text-transform: uppercase;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        caption {
            caption-side: top;
            font-size: 1.5em;
            margin-bottom: 10px;
            color: #333;
            font-weight: bold;
        }

        form {
            width: 60%;
            margin: 0 auto 20px;
            padding: 16px;
            border: 1px solid #ccc;
            border-radius: 8px;
            background-color: #f9f9f9;
        }

        input[type="text"],
        input[type="number"] {
            width: 100%;
            padding: 8px 10px;
            margin: 8px 0;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        button {
            background-color: #28a745;
            color: white;
            padding: 10px 16px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-weight: bold;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
    <form class="form" action="products" method="post">
        <h1>Add Product</h1>
        <label class="label">
            Name:
            <input type="text" name="name" class="input" required/>
        </label>
        <label class="label">
            Price:
            <input min="1000" type="number" name="price" class="input" required/>
        </label>
        <button type="submit" class="button">Add</button>
    </form>

    <h2>Danh Sách Sản Phẩm</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th colspan="2">Action</th>
        </tr>
        <%
            List<Product> products = (List<Product>) request.getAttribute("products");
            if (products != null && !products.isEmpty()) {
                for (Product p : products) {
        %>
        <tr>
            <td><%= p.getId() %></td>
            <td><%= p.getName() %></td>
            <td><%= p.getPrice() %></td>
            <td><a href="products?action=edit&id=<%= p.getId() %>"><button class="button">Edit</button></a></td>
            <td><a href="${pageContext.request.contextPath}/delete-product?id=<%= p.getId() %>"><button class="button">Delete</button></a></td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="5">No products available.</td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>
