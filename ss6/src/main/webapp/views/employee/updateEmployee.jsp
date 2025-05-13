<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Update Employee</title>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>Update Employee</h1>
            <a href="/employees" class="back-button">Back to List</a>
        </div>
        
        <form action="/employees" method="post" id="employeeForm">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="id" value="${employee.id}">
            
            <div class="form-group">
                <label for="name">Full Name:</label>
                <input type="text" id="name" name="name" value="${employee.name}" required>
            </div>
            
            <div class="form-group">
                <label for="birthday">Birthday:</label>
                <input type="date" id="birthday" name="birthday" value="${employee.birthday}" required>
            </div>
            
            <div class="form-group">
                <label for="phone">Phone Number:</label>
                <input type="tel" id="phone" name="phone" pattern="[0-9\-\+\(\)]{10,15}" value="${employee.phone}" required>
                <span class="error" id="phoneError"></span>
            </div>
            
            <div class="form-group">
                <label for="email">Email Address:</label>
                <input type="email" id="email" name="email" value="${employee.email}" required>
                <span class="error" id="emailError"></span>
            </div>
            
            <div class="form-group">
                <label for="salary">Salary:</label>
                <input type="number" id="salary" name="salary" min="0" step="0.01" value="${employee.salary}" required>
            </div>
            
            <div class="form-group">
                <label for="position">Position:</label>
                <input type="text" id="position" name="position" value="${employee.position}" required>
            </div>
            
            <button type="submit" class="submit-button">Update Employee</button>
        </form>
    </div>
    
    <script>
        // Form validation
        document.getElementById('employeeForm').addEventListener('submit', function(event) {
            let isValid = true;
            
            // Validate email
            const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
            const email = document.getElementById('email').value;
            const emailError = document.getElementById('emailError');
            
            if (!emailPattern.test(email)) {
                emailError.textContent = 'Please enter a valid email address';
                isValid = false;
            } else {
                emailError.textContent = '';
            }
            
            // Validate phone
            const phonePattern = /^[0-9\-\+\(\)]{10,15}$/;
            const phone = document.getElementById('phone').value;
            const phoneError = document.getElementById('phoneError');
            
            if (!phonePattern.test(phone)) {
                phoneError.textContent = 'Please enter a valid phone number (10-15 digits, may include -, +, (, ))';
                isValid = false;
            } else {
                phoneError.textContent = '';
            }
            
            if (!isValid) {
                event.preventDefault();
            }
        });
    </script>
</body>
</html>
