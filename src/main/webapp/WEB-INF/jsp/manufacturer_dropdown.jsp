<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://pharmacy.by/tags" prefix="m" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>

<select class="selectpicker">
    <option disabled>Choose manufacturer</option>
    <c:forEach var="item" items="${requestScope.manufacturer_list}">

        <option value="${item.id}"><c:out value="${item.name}"/></option>
    </c:forEach>
</select>

