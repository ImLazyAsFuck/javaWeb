<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
<html>
<head>
    <title>Edit Bus</title>
</head>
<body class="bg-gray-100">
<div class="container mx-auto p-6">
    <h1 class="text-3xl font-bold text-center mb-6">Add Bus</h1>
    <div class="bg-white p-6 rounded-lg shadow-md">
        <form:form modelAttribute="bus" action="/bus/add" enctype="multipart/form-data" method="post" cssClass="space-y-4">
            <div>
                <label class="block text-sm font-medium">License Plate</label>
                <form:input path="licensePlate"  cssClass="w-full p-2 border rounded"/>
                <form:errors path="licensePlate" cssClass="text-red-500 text-sm"/>
            </div>
            <div>
                <label class="block text-sm font-medium">Bus Type</label>
                <form:select path="busType"  cssClass="w-full p-2 border rounded">
                    <form:option value="VIP">VIP</form:option>
                    <form:option value="LUXURY">LUXURY</form:option>
                    <form:option value="NORMAL">NORMAL</form:option>
                </form:select>
                <form:errors path="busType" cssClass="text-red-500 text-sm"/>
            </div>
            <div>
                <label class="block text-sm font-medium">Rows</label>
                <form:input path="rowSeat" type="number" cssClass="w-full p-2 border rounded"/>
                <form:errors path="rowSeat" cssClass="text-red-500 text-sm"/>
            </div>
            <div>
                <label class="block text-sm font-medium">Columns</label>
                <form:input path="colSeat" type="number"  cssClass="w-full p-2 border rounded"/>
                <form:errors path="colSeat" cssClass="text-red-500 text-sm"/>
            </div>
            <div>
                <label class="block text-sm font-medium">Image URL</label>
                <form:input path="image" type="file" cssClass="w-full p-2 border rounded"/>
                <form:errors path="image" cssClass="text-red-500 text-sm"/>
            </div>
            <button type="submit" class="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600">Add Bus</button>
            <a href="/bus" class="bg-gray-500 text-white px-4 py-2 rounded hover:bg-gray-600">Back to List</a>
        </form:form>
    </div>
</div>
</body>
</html>