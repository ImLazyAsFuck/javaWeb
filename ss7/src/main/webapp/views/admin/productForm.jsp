<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${isNewProduct ? 'Add New Product' : 'Edit Product'}</title>
</head>
<body>
    <div class="container">
        <h1>${isNewProduct ? 'Add New Product' : 'Edit Product'}</h1>
        
        <form action="${pageContext.request.contextPath}/admin/products/save" method="post">
            <c:if test="${not isNewProduct}">
                <input type="hidden" name="id" value="${product.id}">
            </c:if>
            
            <div class="form-group">
                <label for="name">Product Name<span class="required">*</span></label>
                <input type="text" id="name" name="name" value="${product.name}" required>
            </div>
            
            <div class="form-group">
                <label for="categoryId">Category<span class="required">*</span></label>
                <select id="categoryId" name="categoryId" required>
                    <option value="">Select a category</option>
                    <c:forEach var="category" items="${categories}">
                        <option value="${category.id}" ${category.id == product.categoryId ? 'selected' : ''}>
                            ${category.name}
                        </option>
                    </c:forEach>
                </select>
            </div>
            
            <div class="form-group">
                <label for="price">Price ($)<span class="required">*</span></label>
                <input type="number" id="price" name="price" value="${product.price}" step="0.01" min="0" required>
            </div>
            
            <div class="form-group">
                <label for="stock">Stock Quantity<span class="required">*</span></label>
                <input type="number" id="stock" name="stock" value="${product.stock}" min="0" required>
            </div>
            
            <div class="form-group">
                <label for="description">Description<span class="required">*</span></label>
                <textarea id="description" name="description" required>${product.description}</textarea>
            </div>
            
            <div class="form-group">
                <label for="image">Image URL<span class="required">*</span></label>
                <input type="url" id="image" name="image" value="${product.image}" required>
                <small>Enter a valid image URL. Example: https://example.com/image.jpg</small>
            </div>
            
            <div class="form-actions">
                <button type="submit" class="btn">${isNewProduct ? 'Add Product' : 'Update Product'}</button>
                <a href="${pageContext.request.contextPath}/admin/products/list" class="btn btn-secondary">Cancel</a>
            </div>
        </form>
        
        <a href="${pageContext.request.contextPath}/admin/products/list" class="back-btn">← Back to Product List</a>
    </div>
    
    <script>
        document.querySelector('form').addEventListener('submit', function(e) {
            const price = document.getElementById('price').value;
            
            if (parseFloat(price) <= 0) {
                e.preventDefault();
                alert('Price must be greater than zero.');
            }
        });
    </script>
</body>
</html>
