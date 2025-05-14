<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Multi-Category Filter - Matrix Variables Demo</title>
</head>
<body>
    <div class="container">
        <div class="page-header">
            <h1>Multi-Category Filter</h1>
        </div>
        
        <div class="nav-links">
            <a href="${pageContext.request.contextPath}/" class="nav-link">Home</a>
            <a href="${pageContext.request.contextPath}/products/electronics" class="nav-link">Electronics</a>
            <a href="${pageContext.request.contextPath}/products/clothing" class="nav-link">Clothing</a>
            <a href="${pageContext.request.contextPath}/products/filter/electronics,clothing" class="nav-link">Multi-Category Filter</a>
        </div>
        
        <div class="matrix-example">
            <h3>Multiple Matrix Variables Example</h3>
            <p>This page demonstrates the use of matrix variables with multiple values in Spring MVC. The URL pattern is:</p>
            <div class="code-sample">
                /products/filter/{categories};colors={color1,color2};sizes={size1,size2};brands={brand1,brand2};priceRange={min-max}
            </div>
            <p>Examples:</p>
            <ul>
                <li><a href="${pageContext.request.contextPath}/products/filter/electronics,clothing;colors=black,white">/products/filter/electronics,clothing;colors=black,white</a></li>
                <li><a href="${pageContext.request.contextPath}/products/filter/clothing;sizes=M,L;colors=blue,red">/products/filter/clothing;sizes=M,L;colors=blue,red</a></li>
                <li><a href="${pageContext.request.contextPath}/products/filter/electronics;brands=Apple,Samsung;priceRange=500-1500">/products/filter/electronics;brands=Apple,Samsung;priceRange=500-1500</a></li>
            </ul>
        </div>
        
        <div class="filter-section">
            <h2>Filter Products</h2>
            <form action="#" id="filterForm" class="filter-form">
                <div class="filter-group">
                    <label>Categories</label>
                    <div class="checkbox-group">
                        <c:forEach var="category" items="${allCategories}">
                            <label class="checkbox-item">
                                <input type="checkbox" name="categories" value="${category.name}" 
                                       ${categories.contains(category.name) ? 'checked' : ''}>
                                ${category.name}
                            </label>
                        </c:forEach>
                    </div>
                </div>
                
                <div class="filter-group">
                    <label>Colors</label>
                    <div class="checkbox-group">
                        <c:forEach var="color" items="${availableColors}">
                            <label class="checkbox-item">
                                <input type="checkbox" name="colors" value="${color}" 
                                       ${selectedColors.contains(color) ? 'checked' : ''}>
                                ${color}
                            </label>
                        </c:forEach>
                    </div>
                </div>
                
                <div class="filter-group">
                    <label>Sizes</label>
                    <div class="checkbox-group">
                        <c:forEach var="size" items="${availableSizes}">
                            <label class="checkbox-item">
                                <input type="checkbox" name="sizes" value="${size}" 
                                       ${selectedSizes.contains(size) ? 'checked' : ''}>
                                ${size}
                            </label>
                        </c:forEach>
                    </div>
                </div>
                
                <div class="filter-group">
                    <label>Brands</label>
                    <div class="checkbox-group">
                        <c:forEach var="brand" items="${availableBrands}">
                            <label class="checkbox-item">
                                <input type="checkbox" name="brands" value="${brand}" 
                                       ${selectedBrands.contains(brand) ? 'checked' : ''}>
                                ${brand}
                            </label>
                        </c:forEach>
                    </div>
                </div>
                
                <div class="filter-group">
                    <label for="priceRange">Price Range ($)</label>
                    <select id="priceRange" name="priceRange" class="filter-select">
                        <option value="">All Prices</option>
                        <option value="0-50" ${priceRange eq "0-50" ? 'selected' : ''}>$0 - $50</option>
                        <option value="50-100" ${priceRange eq "50-100" ? 'selected' : ''}>$50 - $100</option>
                        <option value="100-500" ${priceRange eq "100-500" ? 'selected' : ''}>$100 - $500</option>
                        <option value="500-1000" ${priceRange eq "500-1000" ? 'selected' : ''}>$500 - $1000</option>
                        <option value="1000-2000" ${priceRange eq "1000-2000" ? 'selected' : ''}>$1000 - $2000</option>
                    </select>
                </div>
                
                <div class="filter-group" style="align-self: flex-end;">
                    <button type="button" id="applyFilters" class="btn">Apply Filters</button>
                    <button type="button" id="resetFilters" class="btn btn-reset">Reset</button>
                </div>
            </form>
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
                            <img src="${product.image}" alt="${product.name}" class="product-image">
                            <div class="product-info">
                                <div class="category-badge">
                                    <c:forEach var="category" items="${allCategories}">
                                        <c:if test="${category.id == product.categoryId}">
                                            ${category.name}
                                        </c:if>
                                    </c:forEach>
                                </div>
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
                const categoriesElements = document.querySelectorAll('input[name="categories"]:checked');
                const colorsElements = document.querySelectorAll('input[name="colors"]:checked');
                const sizesElements = document.querySelectorAll('input[name="sizes"]:checked');
                const brandsElements = document.querySelectorAll('input[name="brands"]:checked');
                const priceRange = document.getElementById('priceRange').value;
                
                // Get selected values
                const categories = Array.from(categoriesElements).map(el => el.value).join(',');
                const colors = Array.from(colorsElements).map(el => el.value).join(',');
                const sizes = Array.from(sizesElements).map(el => el.value).join(',');
                const brands = Array.from(brandsElements).map(el => el.value).join(',');
                
                // Building URL with matrix variables
                let url = '${pageContext.request.contextPath}/products/filter/' + (categories || 'all');
                
                if (colors) {
                    url += ';colors=' + colors;
                }
                
                if (sizes) {
                    url += ';sizes=' + sizes;
                }
                
                if (brands) {
                    url += ';brands=' + brands;
                }
                
                if (priceRange) {
                    url += ';priceRange=' + priceRange;
                }
                
                window.location.href = url;
            });
            
            // Reset filters button click handler
            document.getElementById('resetFilters').addEventListener('click', function() {
                window.location.href = '${pageContext.request.contextPath}/products/filter/all';
            });
        });
    </script>
</body>
</html>
