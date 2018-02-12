<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://pharmacy.by/tags" prefix="m" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>


<table>
    <tr>
        <th>name</th>
        <th>country</th>
        <th>phone</th>
        <th>address</th>
        <th>email</th>
    </tr>
    <c:forEach var="item" items="${requestScope.manufacturer_list}">
        <tr>
            <td><c:out value="${item.name}"/></td>
            <td><c:out value="${item.country.name}"/></td>
            <td><c:out value="${item.phoneNumber}"/></td>
            <td><c:out value="${item.address}"/></td>
            <td><c:out value="${item.email}"/></td>
        </tr>
    </c:forEach>
</table>


