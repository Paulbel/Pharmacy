<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Problem accured!</h1>
<h4><c:out value="${requestScope.exception_message}"/></h4>
</body>
</html>
