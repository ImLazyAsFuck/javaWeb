<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 19/05/2025
  Time: 12:58 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/">back</a>
  <form:form modelAttribute="document" action="/document/upload" enctype="multipart/form-data" method="post">
    <form:input path="title"/>
    <form:input path="description"/>
    <form:input path="file" type="file"/>
    <form:button type="submit">Submit</form:button>
  </form:form>
</body>
</html>
