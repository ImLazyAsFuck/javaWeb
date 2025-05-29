<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 29/05/2025
  Time: 10:25 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2><spring:message code="form.title" /></h2>
<a href="/">index</a>
<a href="/transaction/form">+ <spring:message code="form.submit" /></a>
<table border="1">
  <tr><th>#</th><th><spring:message code="form.description"/></th><th><spring:message code="form.amount"/></th><th><spring:message code="form.type"/></th><th>Action</th></tr>
  <c:forEach var="t" items="${transactions}" varStatus="loop">
    <tr>
      <td>${loop.index}</td>
      <td>${t.description}</td>
      <td>${t.amount}</td>
      <td>${t.type}</td>
      <td><a href="${pageContext.request.contextPath}/transaction/delete?index=${loop.index}">Xoá</a></td>
    </tr>
  </c:forEach>
</table>

<a href="${pageContext.request.contextPath}/transaction/setLang?lang=vi">Tiếng Việt</a> | <a href="${pageContext.request.contextPath}/transaction/setLang?lang=en">English</a>

</body>
</html>
