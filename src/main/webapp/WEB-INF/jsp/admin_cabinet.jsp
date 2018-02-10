<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="by.pharmacy.controller.ControllerConstant" %>
<%@ page import="by.pharmacy.entity.UserRole" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>

    <meta charset="UTF-8">
    <title>Title</title>
    <link href="../../css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="../../js/jquery-3.2.1.js"></script>
    <script src="../../js/bootstrap.min.js"></script>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.signIn" var="signin"/>
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

</head>
<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#"><c:out value="${company_name}"/></a>
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
        <th>name</th>
        <th>surname</th>
        <th>role</th>
        <th>set role</th>
    </tr>
    <c:forEach items="${requestScope.users}" var="item">

        <tr>

            <td><c:out value="${item.name}"/></td>
            <td><c:out value="${item.surname}"/></td>
            <td><c:out value="${item.role}"/></td>
            <td>
                <c:choose>
                    <c:when test="${item.role == UserRole.USER}">
                        <form action="FrontController" method="get">
                            <p>
                                <input type="hidden" name="${ControllerConstant.COMMAND_ATTRIBUTE}" value="${ControllerConstant.GIVE_ROLE_COMMAND}">
                                <input type="hidden" name="${ControllerConstant.LOGIN_ATTRIBUTE}" value="${item.login}">
                                <select name="role">
                                    <option value="${UserRole.DOCTOR}">Doctor</option>
                                    <option value="${UserRole.PHARMACIST}">Pharmacist</option>
                                </select>
                                <input type="submit" value="Change">
                            </p>
                        </form>
                    </c:when>
                    <c:when test="${item.role == UserRole.PHARMACIST}">
                        <form action="FrontController" method="get">
                            <p>
                                <input type="hidden" name="${ControllerConstant.COMMAND_ATTRIBUTE}" value="${ControllerConstant.GIVE_ROLE_COMMAND}">
                                <input type="hidden" name="${ControllerConstant.LOGIN_ATTRIBUTE}" value="${item.login}">
                                <select name="role">
                                    <option value="USER">Client</option>
                                    <option value="PHARMACIST">Pharmacist</option>
                                </select>
                                <input type="submit" value="Change">
                            </p>
                        </form>
                    </c:when>
                    <c:when test="${item.role == 'PHARMACIST'}">
                        <form action="FrontController" method="get">
                            <p>
                                <input type="hidden" name="${ControllerConstant.COMMAND_ATTRIBUTE}" value="${ControllerConstant.GIVE_ROLE_COMMAND}">
                                <input type="hidden" name="${ControllerConstant.LOGIN_ATTRIBUTE}" value="${item.login}">
                                <select name="role">
                                    <option value="USER">Client</option>
                                    <option value="DOCTOR">Doctor</option>
                                </select>
                                <input type="submit" value="Change">
                            </p>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <c:out value="${item.role}"/>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
</table>









</body>
</html>
