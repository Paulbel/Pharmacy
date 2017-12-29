<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="js/jquery-3.2.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="fonts.css" type="text/css" charset="utf-8"/>
    <link rel="stylesheet" type="text/css" href="MainPageStyles.css">
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.signIn" var="signin"/>
    <fmt:message bundle="${loc}" key="local.registration" var="registration"/>
    <fmt:message bundle="${loc}" key="local.signOut" var="signout"/>
    <fmt:message bundle="${loc}" key="local.cabinet" var="cabinet"/>
    <fmt:message bundle="${loc}" key="local.opticsName" var="opticsname"/>
    <fmt:message bundle="${loc}" key="local.name" var="name"/>
    <fmt:message bundle="${loc}" key="local.surname" var="surname"/>
    <fmt:message bundle="${loc}" key="local.email" var="email"/>
    <fmt:message bundle="${loc}" key="local.phone" var="phone"/>
    <fmt:message bundle="${loc}" key="local.password" var="password"/>
    <fmt:message bundle="${loc}" key="local.login" var="login"/>
    <fmt:message bundle="${loc}" key="local.repeatPassword" var="repeatPassword"/>
    <fmt:message bundle="${loc}" key="local.cancel" var="cancel"/>
    <fmt:message bundle="${loc}" key="local.ru" var="ru"/>
    <fmt:message bundle="${loc}" key="local.en" var="en"/>
    <fmt:message bundle="${loc}" key="local.language" var="language"/>

</head>
<%--<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#"><c:out value="${opticsname}"/></a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <c:if test="${not empty sessionScope.login}">
                        <a>
                            <c:out value="${sessionScope.name}"/>
                            <c:out value="${sessionScope.surname}"/>
                        </a>
                    </c:if>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><c:out value="${cabinet}"/><b
                            class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="FrontController?command=sign_out"><c:out value="${signout}"/></a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown"><c:out value="${language}"/><b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="FrontController?command=change_language&local=russian"><c:out value="${ru}"/></a></li>
                        <li><a href="FrontController?command=change_language&local=english"><c:out value="${en}"/></a></li>
                    </ul>
                </li>

            </ul>
        </div>
    </div>
</nav>
<table border="1">
    <tr>
        <th>id</th>
        <th>name</th>
        <th>dosage</th>
        <th>amount</th>
        <th>price</th>
    </tr>
    <c:forEach items="${requestScope.drugs}" var="item">

        <tr>
            <td><c:out value="${item.id}"/></td>
            <td><c:out value="${item.name}"/></td>
            <td><c:out value="${item.dosage}"/></td>
            <td><c:out value="${item.amount}"/></td>
            <td><c:out value="${item.price}"/></td>
        </tr>
    </c:forEach>
</table>

</body>--%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Employees</title>
</head>
<body>
<table border="1" cellpadding="5" cellspacing="5">
    <tr>
        <th>id</th>
        <th>name</th>
        <th>dosage</th>
        <th>amount</th>
        <th>price</th>
    </tr>

    <c:forEach var="item" items="${requestScope.drugs}">
        <tr>
            <td><c:out value="${item.id}"/></td>
            <td><c:out value="${item.name}"/></td>
            <td><c:out value="${item.dosage}"/></td>
            <td><c:out value="${item.amount}"/></td>
            <td><c:out value="${item.price}"/></td>
        </tr>
    </c:forEach>
</table>

<%--For displaying Previous link except for the 1st page --%>
<c:if test="${requestScope.currentPage != 1}">
    <td><a href="FrontController?command=show_all_drugs&page=${currentPage - 1}">Previous</a></td>
</c:if>
//show_all_drugs
<%--For displaying Page numbers.
The when condition does not display a link for the current page--%>
<table border="1" cellpadding="5" cellspacing="5">
    <tr>
        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td><a href="FrontController?command=show_all_drugs&page=${i}">${i}</a></td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </tr>
</table>

<%--For displaying Next link --%>
<c:if test="${currentPage lt noOfPages}">
    <td><a href="FrontController?command=show_all_drugs&page=${currentPage + 1}">Next</a></td>
</c:if>
<%----%>
</body>
</html>
