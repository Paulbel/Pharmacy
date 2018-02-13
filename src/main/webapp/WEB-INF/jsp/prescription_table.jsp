<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://pharmacy.by/tags" prefix="m" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.email" var="email"/>
<fmt:message bundle="${loc}" key="local.phone" var="phone"/>
<fmt:message bundle="${loc}" key="local.drug_name" var="drug_name"/>
<fmt:message bundle="${loc}" key="local.count" var="count"/>
<fmt:message bundle="${loc}" key="local.doctor" var="doctor"/>
<fmt:message bundle="${loc}" key="local.start_date" var="start_date"/>
<fmt:message bundle="${loc}" key="local.end_date" var="end_date"/>
<table>
    <thead>
    <tr>
        <th><c:out value="${drug_name}"/></th>
        <th><c:out value="${count}"/></th>

        <th><c:out value="${doctor}"/></th>
        <th><c:out value="${email}"/></th>
        <th><c:out value="${phone}"/></th>

        <th><c:out value="${start_date}"/></th>
        <th><c:out value="${end_date}"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${requestScope.prescription_list}">
        <tr>
            <td><m:drugName drug="${item.drug}" command="get_drug_enter_cabinet&drug_id=${item.drug.id}"/></td>
            <td><c:out value="${item.number}"/></td>
            <td><c:out value="${item.doctor.name}"/> <c:out value="${item.doctor.surname}"/></td>
            <td><c:out value="${item.doctor.email}"/></td>
            <td><c:out value="${item.doctor.phoneNumber}"/></td>
            <td><c:out value="${item.startDate}"/></td>
            <td><c:out value="${item.endDate}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>