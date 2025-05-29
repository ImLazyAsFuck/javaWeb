<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta charset="UTF-8">
    <title><spring:message code="b3_title"/></title>
</head>
<body>
<a href="/">index</a>
<h1><spring:message code="b3_greeting"/></h1>

<form action="/b3/change-language" method="post">
    <label for="lang"><spring:message code="b3_selectLang"/>:</label>
    <select name="lang" onchange="this.form.submit()">
        <option value="">-- <spring:message code="b3_selectLang"/> --</option>
        <option value="en">English</option>
        <option value="vi">Tiếng Việt</option>
    </select>
</form>
</body>
</html>
