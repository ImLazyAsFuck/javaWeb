<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <table>
        <thead>
            <tr>
                <td>Tháng</td>
                <td>Doanh thu</td>
            </tr>
        </thead>
        <tbody>
            <c:set var="totalRevenue" value="0"/>
            <c:set var="count" value="0" />
            <c:forEach var="r" items="${revenues}">
                <tr>
                    <td>${r.key}</td>
                    <td>${r.value}</td>
                </tr>
                <c:set var="count" value="${count + 1}"/>
                <c:set var="totalRevenue" value="${totalRevenue + r.value}"/>
            </c:forEach>
        </tbody>
    </table>

    <c:choose>
        <c:when test="${totalRevenue > 10000}">
            <p>Tổng doanh thu lớn hơn 10.000: ${totalRevenue}</p>
        </c:when>
        <c:otherwise>
            <p>Tổng doanh thu không lớn hơn 10.000: ${totalRevenue}</p>
        </c:otherwise>
    </c:choose>
    <a href="/">back</a>
</body>
</html>
