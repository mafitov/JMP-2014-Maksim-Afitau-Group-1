<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit person</title>
</head>
<body>
<form method="post">
    <input type="hidden" name="id" value="${person.id}" />
    <label>Person Name</label><input type="text" name="name" value="${person.name}" />
    <input type="submit" value="Save" />
</form>
</body>
</html>