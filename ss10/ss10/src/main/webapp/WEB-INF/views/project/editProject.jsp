<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 19/05/2025
  Time: 9:21 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form modelAttribute="project"  enctype="multipart/form-data" action="/project/edit?${project.id}" method="post">
    <form:input path="id" type="hidden"/>
    <form:input path="name"/>
    <form:input path="description"/>
    <img src="${project.documents}" alt=""/>
    <input type="file" name="file">
    <form:button type="submit">Submit</form:button>
    <button type="button" onclick="window.location.href='/product'">Cancel</button>
</form:form>
</body>
</html>
