<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://pharmacy.by/tags" prefix="m" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>

<fmt:message bundle="${loc}" key="local.drug_name" var="drug_name"/>
<fmt:message bundle="${loc}" key="local.dosage" var="dosage"/>
<fmt:message bundle="${loc}" key="local.amount" var="amount"/>
<table border="1" cellpadding="5" cellspacing="5">
    <tr>
        <th><c:out value="${drug_name}"/></th>
        <th><c:out value="${dosage}"/></th>
        <th><c:out value="${amount}"/></th>
    </tr>
    <c:forEach var="item" items="${requestScope.order_list}">
        <tr>
            <td><m:drugName drug="${item.drug}" command="get_drug_enter_cabinet&drug_id=item.drug.id"/></td>
            <td><c:out value="${item.number}"/></td>
            <td><c:out value="${item.date}"/></td>
        </tr>
    </c:forEach>
</table>
<m:pagination command="get_order_list_enter_cabinet" currentPage="${requestScope.currentPage}"
              pageNumber="${requestScope.noOfPages}"/>


