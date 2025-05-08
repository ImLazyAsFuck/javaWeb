<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Order Form</title>
  <style>
    body {
      font-family: 'Segoe UI', sans-serif;
      background: #f9f9fb;
      display: flex;
      flex-direction: column;
      align-items: center;
      padding: 50px 20px;
    }

    h2 {
      font-size: 32px;
      font-weight: bold;
      margin-bottom: 30px;
    }

    form {
      background: white;
      padding: 30px;
      border-radius: 20px;
      box-shadow: 0 15px 25px rgba(0,0,0,0.05);
      width: 320px;
      display: flex;
      flex-direction: column;
      gap: 15px;
    }

    input[type="text"], input[type="number"], select {
      padding: 10px 15px;
      border: 1px solid #ddd;
      border-radius: 10px;
      font-size: 14px;
      outline: none;
      transition: border-color 0.2s;
    }

    input[type="text"]:focus, input[type="number"]:focus, select:focus {
      border-color: #007bff;
    }

    input[type="submit"] {
      background: #007bff;
      color: white;
      border: none;
      padding: 12px;
      border-radius: 10px;
      font-weight: bold;
      cursor: pointer;
      transition: background 0.2s;
    }

    input[type="submit"]:hover {
      background: #0056b3;
    }

    table {
      margin-top: 40px;
      border-collapse: collapse;
      width: 90%;
      max-width: 700px;
      text-align: center;
      box-shadow: 0 10px 20px rgba(0,0,0,0.05);
    }

    th, td {
      padding: 12px 15px;
      border: 1px solid #eee;
      background: white;
    }

    th {
      background: #f1f1f1;
    }
  </style>
</head>
<body>
<h2>Order Form</h2>
<form method="post" action="b7">
  <input type="text" name="productName" placeholder="Product" required />
  <input type="number" name="quantity" placeholder="Quantity" required />
  <input type="number" step="0.01" name="price" placeholder="Price per Unit" required />
  <input type="submit" value="Submit" />
</form>

<%
  List<String> resultList = (List<String>) request.getAttribute("resultList");
  Double total = (Double) request.getAttribute("total");
  if (resultList != null && total != null) {
%>
<table>
  <tr>
    <th>Product</th><th>Quantity</th><th>Price</th><th>Subtotal</th>
  </tr>
  <% for (String row : resultList) { %>
  <tr><%= row %></tr>
  <% } %>
  <tr>
    <td colspan="3"><strong>Total</strong></td>
    <td><strong><%= String.format("%.2f", total) %> VND</strong></td>
  </tr>
</table>
<% } else if (request.getMethod().equalsIgnoreCase("POST")) { %>
  <div style="margin-top: 20px; color: #e74c3c; background: #fceae9; padding: 15px; border-radius: 5px;">
    <strong>Lỗi:</strong> Không có dữ liệu hiển thị. Có thể do:
    <ul>
      <li>Form không được gửi đến đúng Servlet</li>
      <li>Dữ liệu đầu vào không hợp lệ</li>
      <li>Có lỗi xảy ra khi xử lý dữ liệu</li>
    </ul>
  </div>
<% } %>

<div style="margin-top: 20px;">
  <a href="/" style="text-decoration: none; color: #007bff; margin-right: 20px;">back</a>
  <% if (resultList != null && !resultList.isEmpty()) { %>
    <button onclick="window.location.href='b7?clear=true'" style="background: #dc3545; color: white; border: none; padding: 10px 15px; border-radius: 5px; margin-left: 10px; cursor: pointer;">
      Xóa tất cả sản phẩm
    </button>
  <% } %>
</div>
</body>
</html>
