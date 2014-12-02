<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bank</title>
</head>
<body>
<form method="post">
<label>From</label>
<select name="from">
    <c:forEach items="${accounts}" var="account" varStatus="i">
        <option value="${account.id}"/>${persons[i.index].name}</option>
    </c:forEach>
</select>
<label>To</label>
<select name="to">
    <c:forEach items="${accounts}" var="account" varStatus="j">
        <option value="${account.id}"/>${persons[j.index].name}</option>
    </c:forEach>
</select>
<label>Amount in ${currency.nam}</label><input type="text" name="amount" /> <br/>
<input type="submit" value="Transfer" />
</form>
</body>
</html>