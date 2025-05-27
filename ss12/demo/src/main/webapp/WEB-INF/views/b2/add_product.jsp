<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Product Form</title>
  <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 p-10">
<div class="max-w-xl mx-auto bg-white p-8 rounded shadow">
  <h1 class="text-2xl font-bold mb-6">Product Form</h1>

  <form:form modelAttribute="product" action="/product/add" method="post" class="space-y-4">

    <div>
      <label class="block text-gray-700 font-semibold mb-1">Name</label>
      <form:input path="name" cssClass="w-full border border-gray-300 p-2 rounded"/>
      <form:errors path="name" cssClass="text-red-500 text-sm mt-1"/>
    </div>

    <div>
      <label class="block text-gray-700 font-semibold mb-1">Price</label>
      <form:input path="price" type="number" step="0.01" cssClass="w-full border border-gray-300 p-2 rounded"/>
      <form:errors path="price" cssClass="text-red-500 text-sm mt-1"/>
    </div>

    <div>
      <label class="block text-gray-700 font-semibold mb-1">Quantity</label>
      <form:input path="quantity" type="number" cssClass="w-full border border-gray-300 p-2 rounded"/>
      <form:errors path="quantity" cssClass="text-red-500 text-sm mt-1"/>
    </div>

    <div>
      <button type="submit" class="bg-blue-500 hover:bg-blue-600 text-white font-semibold py-2 px-4 rounded">
        Submit
      </button>
      <button type="button" class="bg-red-500 hover:bg-red-600 text-white font-semibold py-2 px-4 rounded" onclick="window.location.href='/product'">
        Back
      </button>
    </div>
  </form:form>
</div>
</body>
</html>
