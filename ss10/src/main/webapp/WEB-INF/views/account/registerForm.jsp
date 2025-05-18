<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 18/05/2025
  Time: 11:11 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/">back</a>
    <form:form modelAttribute="account" method="post" action="/account">
        <form:input path="username"/>
        <form:input type="password" path="password"/>
        <form:input type="email" path="email"/>
        <form:button type="submit">Submit</form:button>
    </form:form>
</body>
</html>
