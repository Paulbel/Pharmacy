<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://pharmacy.by/tags" prefix="m" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>

<fmt:message bundle="${loc}" key="local.address" var="address"/>
<fmt:message bundle="${loc}" key="local.email" var="email"/>
<fmt:message bundle="${loc}" key="local.phone" var="phone"/>
<fmt:message bundle="${loc}" key="local.country" var="country"/>
<fmt:message bundle="${loc}" key="local.manufacturer_name" var="manufacturer_name"/>
<table>
    <tr>
        <th><c:out value="${manufacturer_name}"/></th>
        <th><c:out value="${country}"/></th>
        <th><c:out value="${phone}"/></th>
        <th><c:out value="${address}"/></th>
        <th><c:out value="${email}"/></th>
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


