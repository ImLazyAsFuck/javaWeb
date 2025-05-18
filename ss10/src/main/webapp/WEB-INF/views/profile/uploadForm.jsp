<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 19/05/2025
  Time: 12:46 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <a href=">back">back</a>
    <form:form action="/profile" method="post" modelAttribute="profile" enctype="multipart/form-data">
        <form:input path="username"/>
        <form:input type="file" path="avatar"/>
        <form:button>Submit</form:button>
    </form:form>
</body>
</html>
