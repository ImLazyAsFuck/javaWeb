<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${category} Products - Matrix Variables Demo</title>
</head>
<body>
    <div class="container">
        <div class="page-header">
            <h1>${category} Products</h1>
        </div>
        
        <div class="nav-links">
            <a href="${pageContext.request.contextPath}/" class="nav-link">Home</a>
            <a href="${pageContext.request.contextPath}/products/electronics" class="nav-link">Electronics</a>
            <a href="${pageContext.request.contextPath}/products/clothing" class="nav-link">Clothing</a>
            <a href="${pageContext.request.contextPath}/products/filter/electronics,clothing" class="nav-link">Multi-Category Filter</a>
        </div>
        
        <div class="matrix-example">
            <h3>Matrix Variables Example</h3>
            <p>This page demonstrates the use of matrix variables in Spring MVC. The URL pattern is:</p>
            <div class="code-sample">
                /products/{category};color={color};size={size};brand={brand};minPrice={min};maxPrice={max}
            </div>
            <p>Examples:</p>
            <ul>
                <li><a href="${pageContext.request.contextPath}/products/electronics;color=black">/products/electronics;color=black</a></li>
                <li><a href="${pageContext.request.contextPath}/products/clothing;size=M;color=blue">/products/clothing;size=M;color=blue</a></li>
                <li><a href="${pageContext.request.contextPath}/products/electronics;minPrice=1000;maxPrice=2000">/products/electronics;minPrice=1000;maxPrice=2000</a></li>
            </ul>
        </div>
        
        <div class="filter-section">
            <h2>Filter Products</h2>
            <div class="filter-form">
                <div class="filter-group">
                    <label for="color">Color</label>
                    <select id="color" class="filter-select">
                        <option value="">All Colors</option>
                        <c:forEach var="color" items="${availableColors}">
                            <option value="${color}" ${color eq selectedColor ? 'selected' : ''}>${color}</option>
                        </c:forEach>
                    </select>
                </div>
                
                <div class="filter-group">
                    <label for="size">Size</label>
                    <select id="size" class="filter-select">
                        <option value="">All Sizes</option>
                        <c:forEach var="size" items="${availableSizes}">
                            <option value="${size}" ${size eq selectedSize ? 'selected' : ''}>${size}</option>
                        </c:forEach>
                    </select>
                </div>
                
                <div class="filter-group">
                    <label for="brand">Brand</label>
                    <select id="brand" class="filter-select">
                        <option value="">All Brands</option>
                        <c:forEach var="brand" items="${availableBrands}">
                            <option value="${brand}" ${brand eq selectedBrand ? 'selected' : ''}>${brand}</option>
                        </c:forEach>
                    </select>
                </div>
                
                <div class="filter-group">
                    <label for="minPrice">Min Price ($)</label>
                    <input type="number" id="minPrice" class="filter-input" value="${minPrice}" min="0" step="0.01">
                </div>
                
                <div class="filter-group">
                    <label for="maxPrice">Max Price ($)</label>
                    <input type="number" id="maxPrice" class="filter-input" value="${maxPrice}" min="0" step="0.01">
                </div>
                
                <button id="applyFilters" class="btn">Apply Filters</button>
                <button id="resetFilters" class="btn btn-reset">Reset</button>
            </div>
        </div>
        
        <div class="product-grid">
            <c:choose>
                <c:when test="${empty products}">
                    <div class="no-products">
                        <h3>No products match your filter criteria</h3>
                        <p>Try adjusting your filters to see more products.</p>
                    </div>
                </c:when>
                <c:otherwise>
                    <c:forEach var="product" items="${products}">
                        <div class="product-card">
                            <img style="width: 20%;" src="${product.image}" alt="${product.name}" class="product-image">
                            <div class="product-info">
                                <div class="product-name">${product.name}</div>
                                <div class="product-price">$${product.price}</div>
                                <div class="product-attributes">
                                    <c:if test="${not empty product.color}">
                                        <span class="product-attribute">Color: ${product.color}</span>
                                    </c:if>
                                    <c:if test="${not empty product.size}">
                                        <span class="product-attribute">Size: ${product.size}</span>
                                    </c:if>
                                    <c:if test="${not empty product.brand}">
                                        <span class="product-attribute">Brand: ${product.brand}</span>
                                    </c:if>
                                </div>
                                <a href="#" class="btn">View Details</a>
                            </div>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            // Apply filters button click handler
            document.getElementById('applyFilters').addEventListener('click', function() {
                const color = document.getElementById('color').value;
                const size = document.getElementById('size').value;
                const brand = document.getElementById('brand').value;
                const minPrice = document.getElementById('minPrice').value;
                const maxPrice = document.getElementById('maxPrice').value;
                
                let url = '${pageContext.request.contextPath}/products/${category}';
                
                // Add matrix variables
                let hasMatrixVar = false;
                
                if (color) {
                    url += ';color=' + color;
                    hasMatrixVar = true;
                }
                
                if (size) {
                    url += ';size=' + size;
                    hasMatrixVar = true;
                }
                
                if (brand) {
                    url += ';brand=' + brand;
                    hasMatrixVar = true;
                }
                
                if (minPrice) {
                    url += ';minPrice=' + minPrice;
                    hasMatrixVar = true;
                }
                
                if (maxPrice) {
                    url += ';maxPrice=' + maxPrice;
                    hasMatrixVar = true;
                }
                
                window.location.href = url;
            });
            
            // Reset filters button click handler
            document.getElementById('resetFilters').addEventListener('click', function() {
                window.location.href = '${pageContext.request.contextPath}/products/${category}';
            });
        });
    </script>
</body>
</html>
