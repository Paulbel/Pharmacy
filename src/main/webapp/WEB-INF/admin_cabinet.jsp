<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1">
<c:forEach items="${requestScope.users}" var="item">
    <tr>
        <td><c:out value="${item.name}"/></td>
        <td><c:out value="${item.surname}"/></td>
        <td><c:out value="${item.phoneNumber}"/></td>
        <td><c:out value="${item.email}"/></td>
    </tr>
</c:forEach>
</table>

</body>
</html>
