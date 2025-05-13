<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Employee Management</title>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>Employee Management</h1>
            <a href="/employees?action=add" class="add-button">Add New Employee</a>
        </div>
        
        <div class="search-section">
            <h3>Search Employees</h3>
            <!-- Search type: ${param.searchType}, ID: ${param.idKeyword}, Name: ${param.nameKeyword} -->
            <div class="search-forms-container">
                <div class="search-form-wrapper">
                    <h4>Search by ID</h4>
                    <form action="/employees" method="get" class="search-form">
                        <input type="hidden" name="action" value="search">
                        <input type="hidden" name="searchType" value="id">
                        
                        <div class="form-group">
                            <label for="idKeyword">Employee ID:</label>
                            <input type="number" name="idKeyword" id="idKeyword" class="search-input" placeholder="Enter employee ID">
                        </div>
                        
                        <button type="submit" class="search-button">Search by ID</button>
                    </form>
                </div>
                
                <div class="search-form-wrapper">
                    <h4>Search by Name</h4>
                    <form action="/employees" method="get" class="search-form">
                        <input type="hidden" name="action" value="search">
                        <input type="hidden" name="searchType" value="name">
                        
                        <div class="form-group">
                            <label for="nameKeyword">Employee Name:</label>
                            <input type="text" name="nameKeyword" id="nameKeyword" class="search-input" placeholder="Enter employee name">
                        </div>
                        
                        <button type="submit" class="search-button">Search by Name</button>
                    </form>
                </div>
            </div>
        </div>
        
        <c:choose>
            <c:when test="${searchPerformed != null}">
                <c:if test="${empty employees}">
                    <p>No employees found matching your search criteria.</p>
                </c:if>
                <c:if test="${not empty employees}">
                    <table>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Birthday</th>
                                <th>Phone</th>
                                <th>Email</th>
                                <th>Salary</th>
                                <th>Position</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${employees}" var="employee">
                                <tr>
                                    <td>${employee.id}</td>
                                    <td>${employee.name}</td>
                                    <td><fmt:formatDate value="${employee.birthday}" pattern="yyyy-MM-dd" /></td>
                                    <td>${employee.phone}</td>
                                    <td>${employee.email}</td>
                                    <td><fmt:formatNumber value="${employee.salary}" type="currency" currencySymbol="$" /></td>
                                    <td>${employee.position}</td>
                                    <td class="actions">
                                        <a href="/employees?action=edit&id=${employee.id}" class="edit-button">Edit</a>
                                        <a href="/employees?action=delete&id=${employee.id}" 
                                           class="delete-button" 
                                           onclick="return confirm('Are you sure you want to delete this employee?')">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
                <a href="/employees" class="add-button">Back to All Employees</a>
            </c:when>
            <c:otherwise>
                <c:if test="${empty pageData.employees}">
                    <p>No employees found.</p>
                </c:if>
                <c:if test="${not empty pageData.employees}">
                    <table>
                        <thead>
                            <tr>
                                <th>
                                    <a href="/employees?sort=id&direction=${pageData.sortField == 'id' && pageData.sortDirection == 'ASC' ? 'DESC' : 'ASC'}&page=${pageData.currentPage}">
                                        ID
                                        <c:if test="${pageData.sortField == 'id'}">
                                            <span class="sort-icon">
                                                ${pageData.sortDirection == 'ASC' ? '▲' : '▼'}
                                            </span>
                                        </c:if>
                                    </a>
                                </th>
                                <th>
                                    <a href="/employees?sort=name&direction=${pageData.sortField == 'name' && pageData.sortDirection == 'ASC' ? 'DESC' : 'ASC'}&page=${pageData.currentPage}">
                                        Name
                                        <c:if test="${pageData.sortField == 'name'}">
                                            <span class="sort-icon">
                                                ${pageData.sortDirection == 'ASC' ? '▲' : '▼'}
                                            </span>
                                        </c:if>
                                    </a>
                                </th>
                                <th>
                                    <a href="/employees?sort=birthday&direction=${pageData.sortField == 'birthday' && pageData.sortDirection == 'ASC' ? 'DESC' : 'ASC'}&page=${pageData.currentPage}">
                                        Birthday
                                        <c:if test="${pageData.sortField == 'birthday'}">
                                            <span class="sort-icon">
                                                ${pageData.sortDirection == 'ASC' ? '▲' : '▼'}
                                            </span>
                                        </c:if>
                                    </a>
                                </th>
                                <th>Phone</th>
                                <th>Email</th>
                                <th>
                                    <a href="/employees?sort=salary&direction=${pageData.sortField == 'salary' && pageData.sortDirection == 'ASC' ? 'DESC' : 'ASC'}&page=${pageData.currentPage}">
                                        Salary
                                        <c:if test="${pageData.sortField == 'salary'}">
                                            <span class="sort-icon">
                                                ${pageData.sortDirection == 'ASC' ? '▲' : '▼'}
                                            </span>
                                        </c:if>
                                    </a>
                                </th>
                                <th>
                                    <a href="/employees?sort=position&direction=${pageData.sortField == 'position' && pageData.sortDirection == 'ASC' ? 'DESC' : 'ASC'}&page=${pageData.currentPage}">
                                        Position
                                        <c:if test="${pageData.sortField == 'position'}">
                                            <span class="sort-icon">
                                                ${pageData.sortDirection == 'ASC' ? '▲' : '▼'}
                                            </span>
                                        </c:if>
                                    </a>
                                </th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${pageData.employees}" var="employee">
                                <tr>
                                    <td>${employee.id}</td>
                                    <td>${employee.name}</td>
                                    <td>${employee.birthday}</td>
                                    <td>${employee.phone}</td>
                                    <td>${employee.email}</td>
                                    <td><fmt:formatNumber value="${employee.salary}" type="currency" currencySymbol="$" /></td>
                                    <td>${employee.position}</td>
                                    <td class="actions">
                                        <a href="/employees?action=edit&id=${employee.id}" class="edit-button">Edit</a>
                                        <a href="/employees?action=delete&id=${employee.id}" 
                                           class="delete-button" 
                                           onclick="return confirm('Are you sure you want to delete this employee?')">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    
                    <!-- Pagination -->
                    <div class="pagination">
                        <c:forEach begin="1" end="${pageData.totalPages}" var="i">
                            <div class="page-item ${pageData.currentPage == i ? 'active' : ''}">
                                <a class="page-link" href="/employees?page=${i}&sort=${pageData.sortField}&direction=${pageData.sortDirection}">
                                    ${i}
                                </a>
                            </div>
                        </c:forEach>
                    </div>
                </c:if>
            </c:otherwise>
        </c:choose>
    </div>
    
    <!-- No JavaScript toggle needed since we now have separate forms -->
</body>
</html>
