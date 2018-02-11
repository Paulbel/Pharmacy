<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.signIn" var="sign_in"/>
<fmt:message bundle="${loc}" key="local.registration" var="registration"/>
<fmt:message bundle="${loc}" key="local.signOut" var="signout"/>
<fmt:message bundle="${loc}" key="local.cabinet" var="cabinet"/>
<fmt:message bundle="${loc}" key="local.companyName" var="company_name"/>
<fmt:message bundle="${loc}" key="local.name" var="name"/>
<fmt:message bundle="${loc}" key="local.surname" var="surname"/>
<fmt:message bundle="${loc}" key="local.email" var="email"/>
<fmt:message bundle="${loc}" key="local.phone" var="phone"/>
<fmt:message bundle="${loc}" key="local.password" var="password"/>
<fmt:message bundle="${loc}" key="local.login" var="login"/>
<fmt:message bundle="${loc}" key="local.repeat_password" var="repeat_password"/>
<fmt:message bundle="${loc}" key="local.cancel" var="cancel"/>
<fmt:message bundle="${loc}" key="local.ru" var="ru"/>
<fmt:message bundle="${loc}" key="local.en" var="en"/>
<fmt:message bundle="${loc}" key="local.language" var="language"/>


<table>
    <thead>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>dosage</th>
        <th>amount</th>
        <th>price</th>
        <th>manufacturer</th>
        <th>country</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${requestScope.drugs}">

        <tr>
            <td><c:out value="${item.id}"/></td>
            <td><c:out value="${item.name}"/></td>
            <td><c:out value="${item.dosage}"/></td>
            <td><c:out value="${item.amount}"/></td>
            <td><c:out value="${item.price}"/></td>
            <td><a href="FrontController?command="><c:out value="${item.manufacturer.name}"/></a>
            </td>
            <td><a href="FrontController?command="><c:out value="${item.manufacturer.country}"/></a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>