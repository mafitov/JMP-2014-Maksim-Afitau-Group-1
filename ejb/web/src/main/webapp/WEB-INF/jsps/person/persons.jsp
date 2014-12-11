<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Persons</title>
</head>
<body>
<a href="/web/persons/new">New</a><br/>
<c:forEach items="${persons}" var="person">
    <c:out value="${person.id}"/> - <c:out value="${person.name}"/> - <a href="/web/accounts/${person.accountId}/edit"><c:out value="${person.accountId}"/></a>
    <a href="/web/persons/id?id=${person.id}">Edit</a>   <a href="/web/persons/${person.id}/edit">Delete</a><br/><br/>
</c:forEach>
</body>
</html>