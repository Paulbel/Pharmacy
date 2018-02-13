<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://pharmacy.by/tags" prefix="m" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>

<fmt:message bundle="${loc}" key="local.language" var="language"/>
<fmt:message bundle="${loc}" key="local.drug_name" var="drug_name"/>
<fmt:message bundle="${loc}" key="local.dosage" var="dosage"/>
<fmt:message bundle="${loc}" key="local.amount" var="amount"/>
<fmt:message bundle="${loc}" key="local.description" var="description"/>
<fmt:message bundle="${loc}" key="local.composition" var="composition"/>
<fmt:message bundle="${loc}" key="local.country" var="country"/>
<fmt:message bundle="${loc}" key="local.price" var="price"/>
<fmt:message bundle="${loc}" key="local.manufacturers" var="manufacturer"/>

<table>
    <thead>
    <tr>
        <th><c:out value="${drug_name}"/></th>
        <th><c:out value="${dosage}"/></th>
        <th><c:out value="${amount}"/></th>
        <th><c:out value="${price}"/></th>
        <th><c:out value="${country}"/></th>
        <th><c:out value="${manufacturer}"/></th>
        <th><c:out value="${description}"/></th>
        <th><c:out value="${composition}"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${requestScope.drugs}">
        <tr>
            <td><m:drugName drug="${item}" command="get_drug_enter_cabinet&drug_id=${item.id}"/></td>
            <td><c:out value="${item.dosage}"/></td>
            <td><c:out value="${item.amount}"/></td>
            <td><c:out value="${item.price}"/></td>
            <td><m:drugCountry drug="${item}"/></td>
            <td><m:drugManufacturer drug="${item}"
                                    command="get_manufacturer_enter_cabinet&manufacturer_id=${item.manufacturer.id}"/></td>
            <td><c:out value="${item.description}"/></td>
            <td><c:out value="${item.composition}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>









