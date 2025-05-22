<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Form</title>
    <script>
        function toggleFields() {
            const role = document.getElementById("roleSelect").value;
            const emailField = document.getElementById("emailField");
            const phoneField = document.getElementById("phoneField");
            const passwordField = document.getElementById("passwordField");

            if (role === "ADMIN") {
                emailField.style.display = "block";
                phoneField.style.display = "none";
                passwordField.style.display = "none";
            } else if (role === "USER") {
                emailField.style.display = "block";
                phoneField.style.display = "block";
                passwordField.style.display = "block";
            } else {
                emailField.style.display = "none";
                phoneField.style.display = "none";
                passwordField.style.display = "none";
            }
        }

        window.addEventListener("DOMContentLoaded", toggleFields);
    </script>
</head>
<body>
<h1>User Form</h1>
<a href="/">back</a>
<form:form cssStyle="display: flex; flex-direction: column" modelAttribute="user" action="/b123456" method="post">

    <form:input path="name" placeholder="name"/>
    <form:errors cssStyle="color: red" path="name"/>

    <div id="passwordField">
        <form:input type="password" path="password" placeholder="password"/>
        <form:errors cssStyle="color: red" path="password"/>
    </div>

    <div id="emailField">
        <form:input path="email" placeholder="email" />
        <form:errors path="email" cssStyle="color: red" />
    </div>

    <div id="phoneField">
        <form:input path="phone" placeholder="phone" />
        <form:errors path="phone" cssStyle="color: red" />
    </div>

    <form:select path="status">
        <form:option value="true"/>
        <form:option value="false"/>
    </form:select>
    <form:errors cssStyle="color: red" path="status"/>

    <form:select path="role" id="roleSelect" onchange="toggleFields()">
        <form:option value="USER" label="User" />
        <form:option value="ADMIN" label="Admin" />
    </form:select>
    <form:errors path="role" cssStyle="color: red"/>

    <form:button type="submit">Submit</form:button>
</form:form>
</body>
</html>
