<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page trimDirectiveWhitespaces="true" %>
<html>
<head>
    <title>Quản lý sản phẩm</title>
</head>
<body>

<%!
    public class Product {
        private int id;
        private String productName;
        private double price;
        private String description;
        private int stock;
        private boolean status;

        public Product(int id, String productName, double price, String description, int stock) {
            this.id = id;
            this.productName = productName;
            this.price = price;
            this.description = description;
            this.stock = stock;
            this.status = stock > 0;
        }

        public int getId() { return id; }
        public String getProductName() { return productName; }
        public double getPrice() { return price; }
        public String getDescription() { return description; }
        public int getStock() { return stock; }
        public boolean isStatus() { return status; }
    }
%>

<%
    List<Product> products = (List<Product>) session.getAttribute("products");
    if (products == null) {
        products = new ArrayList<>();
    }

    String action = request.getParameter("action");
    if ("add".equals(action)) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("productName");
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");
        int stock = Integer.parseInt(request.getParameter("stock"));

        Product newProduct = new Product(id, name, price, description, stock);
        products.add(newProduct);

        session.setAttribute("products", products);
    }
%>

<h2>Thêm sản phẩm mới</h2>
<form method="post">
    <input type="hidden" name="action" value="add">
    ID: <input type="number" name="id" required><br>
    Tên sản phẩm: <input type="text" name="productName" required><br>
    Giá: <input type="number" step="0.01" name="price" required><br>
    Mô tả: <input type="text" name="description"><br>
    Tồn kho: <input type="number" name="stock" required><br><br>
    <input type="submit" value="Thêm sản phẩm">
</form>

<h2>Danh sách sản phẩm</h2>
<table border="1" cellpadding="5">
    <tr>
        <th>ID</th>
        <th>Tên</th>
        <th>Giá</th>
        <th>Mô tả</th>
        <th>Tồn kho</th>
        <th>Trạng thái</th>
    </tr>
    <%
        if (products != null && !products.isEmpty()) {
            for (Product p : products) {
    %>
    <tr>
        <td><%= p.getId() %></td>
        <td><%= p.getProductName() %></td>
        <td><%= p.getPrice() %></td>
        <td><%= p.getDescription() %></td>
        <td><%= p.getStock() %></td>
        <td><%= p.isStatus() %></td>
    </tr>
    <%
        }
    } else {
    %>
    <tr><td colspan="6">Chưa có sản phẩm nào!</td></tr>
    <% } %>
</table>
<br/>
<a href="/">back</a>
</body>
</html>
