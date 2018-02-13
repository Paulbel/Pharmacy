<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://pharmacy.by/tags" prefix="m" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>

<select name="drug_id" class="form-control">
    <option disabled>Choose manufacturer</option>
    <c:forEach var="item" items="${requestScope.drugs}">
        <option value="${item.id}"><c:out value="${item.name}"/></option>
    </c:forEach>
</select>

