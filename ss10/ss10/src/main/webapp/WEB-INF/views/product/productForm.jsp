<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 18/05/2025
  Time: 11:02 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/">back</a>
    <form:form modelAttribute="product" action="/product" method="post">
        <form:input path="name"/>
        <form:input path="price"/>
        <form:input path="description"/>
        <form:button type="submit">Submit</form:button>
    </form:form>
</body>
</html>
