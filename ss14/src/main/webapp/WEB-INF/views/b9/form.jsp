<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 29/05/2025
  Time: 10:24 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2><spring:message code="form.title" /></h2>
<form:form method="post" modelAttribute="transaction" action="/transaction/addTransaction">
  <label><spring:message code="form.description" /></label>
  <form:input path="description" />

  <label><spring:message code="form.amount" /></label>
  <form:input path="amount" type="number" step="0.01" />

  <label><spring:message code="form.type" /></label>
  <form:select path="type">
    <form:option value="income"><spring:message code="form.income" /></form:option>
    <form:option value="expense"><spring:message code="form.expense" /></form:option>
  </form:select>

  <button type="submit"><spring:message code="form.submit" /></button>
</form:form>

</body>
</html>
