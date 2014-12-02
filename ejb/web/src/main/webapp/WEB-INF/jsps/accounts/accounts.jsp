<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Accounts</title>
</head>
<body>
<a href="/web/accounts/new">New</a><br/>
<c:forEach items="${accounts}" var="account">
    <c:out value="${account.id}"/> - ${account.currencyId} - ${account.amount}
    <a href="/web/accounts/id?id=${account.id}">Edit</a>   <a href="/web/accounts/${account.id}/edit">Delete</a><br/><br/>
</c:forEach>
</body>
</html>