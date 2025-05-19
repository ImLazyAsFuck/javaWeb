<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 19/05/2025
  Time: 1:36 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/">back</a>
<form:form method="post" action="/upload/result" enctype="multipart/form-data" modelAttribute="upload">
    <form:input path="file" type="file"/>
    <form:textarea path="description"/>
    <form:button>Upload</form:button>
 </form:form>
</body>
</html>
