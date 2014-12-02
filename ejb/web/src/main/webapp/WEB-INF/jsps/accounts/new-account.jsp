<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add account</title>
</head>
<body>
<form method="post">
    <label>Currency</label>
    <select name="currencyId">
        <c:forEach items="${currencies}" var="currency">
            <option value="${currency.id}"/>${currency.name}</option>
        </c:forEach>
    </select><br/>
    <label>Amount</label><input type="text" name="amount" /><br/>

    <input type="submit" value="Save" />
</form>
</body>
</html>