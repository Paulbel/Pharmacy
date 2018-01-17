<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
    <meta charset="UTF-8" content="">
    <title>Title</title>
    <link href="../../css/style.css" rel="stylesheet">


    <link href="../../css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="../../js/jquery-3.2.1.js"></script>
    <script src="../../js/bootstrap.min.js"></script>


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
    <fmt:message bundle="${loc}" key="local.repeat_password" var="repeat_password"/>
    <fmt:message bundle="${loc}" key="local.cancel" var="cancel"/>
    <fmt:message bundle="${loc}" key="local.ru" var="ru"/>
    <fmt:message bundle="${loc}" key="local.en" var="en"/>
    <fmt:message bundle="${loc}" key="local.drugs" var="drugs"/>
    <fmt:message bundle="${loc}" key="local.language" var="language"/>
    <fmt:message bundle="${loc}" key="local.find_drug" var="find_drug"/>

</head>
<body>

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
                        <c:choose>
                            <c:when test="${empty sessionScope.login}">
                                <li>
                                    <a href="#"
                                       onclick="document.getElementById('signInForm').style.display='block'"><c:out
                                            value="${signin}"/></a>
                                    <a href="#"
                                       onclick="document.getElementById('registrationForm').style.display='block'"><c:out
                                            value="${registration}"/></a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="FrontController?command=sign_out"><c:out value="${signout}"/></a></li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown"><c:out value="${language}"/><b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="FrontController?command=change_language&local=ru&query=<c:out value="${requestScope.command}"/>"><c:out
                                    value="${ru}"/></a></li>
                        <li>
                            <a href="FrontController?command=change_language&local=en&query=<c:out value="${requestScope.command}"/>"><c:out
                                    value="${en}"/></a></li>
                    </ul>
                </li>

            </ul>
        </div>
    </div>
</nav>

<div id="container">
    <div id="nav_menu">
        <ul class="nav nav-pills nav-stacked" style="max-width: 260px;">

            <c:choose>
                <c:when test="${requestScope.command eq 'show_all_drugs'}">
                    <li class="active">
                        <a href=#>
       <%--                     <span class="badge pull-right">42</span>--%>
                            <c:out value="${drugs}"/>
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="FrontController?command=show_all_drugs">
  <%--                          <span class="badge pull-right">42</span>--%>
                            <c:out value="${drugs}"/>
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>

            <c:choose>
                <c:when test="${requestScope.command eq 'find_drug'}">
                    <li class="active">
                        <a href=#>
<%--                            <span class="badge pull-right">42</span>--%>
                            <c:out value="${find_drug}"/>
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="FrontController?command=find_drug">
<%--                            <span class="badge pull-right">42</span>--%>
                            <c:out value="${find_drug}"/>
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${requestScope.command eq 'get_language_drugs'}">
                    <li class="active">
                        <a href=#>
                                <%--                            <span class="badge pull-right">42</span>--%>
                            <c:out value="${find_drug}"/>
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="FrontController?command=get_language_drugs">
                                <%--                            <span class="badge pull-right">42</span>--%>
                            <c:out value="${find_drug}"/>
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>

        </ul>

    </div>
    <div id="info">
<c:if test="${requestScope.command eq 'get_language_drugs'}">
    <div class="dropdown">
        <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Dropdown Example
            <span class="caret"></span></button>
        <ul class="dropdown-menu">
            <c:forEach var="item" items="${requestScope.languages}">
                <li><a href="#"><c:out value="${item.key}"/></a></li>
            </c:forEach>
        </ul>
    </div>
</c:if>


<c:if test="${requestScope.command eq 'show_all_drugs'}">
            <c:choose>
                <c:when test="${requestScope.drugs.size() gt 0}">
                    <table border="1" cellpadding="5" cellspacing="5">
                        <tr>
                            <th>id</th>
                            <th>name</th>
                            <th>dosage</th>
                            <th>amount</th>
                            <th>price</th>
                            <th>manufacturer</th>
                            <th>country</th>
                            <th>add description</th>
                        </tr>

                        <c:forEach var="item" items="${requestScope.drugs}">
                            <tr>
                                <td><c:out value="${item.id}"/></td>
                                <td><c:out value="${item.name}"/></td>
                                <td><c:out value="${item.dosage}"/></td>
                                <td><c:out value="${item.amount}"/></td>
                                <td><c:out value="${item.price}"/></td>
                                <td><c:out value="${item.price}"/></td>
                                <td><a href="FrontController?command="><c:out value="${item.manufacturer.name}"/></a>
                                </td>
                                <td><a href="FrontController?command="><c:out value="${item.manufacturer.country}"/></a>
                                </td>
                                <td><a href="FrontController?command=add_drug_description">Add description</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <ul class="pagination">

                        <c:choose>
                            <c:when test="${requestScope.currentPage != 1}">
                                <li><a href="FrontController?command=show_all_drugs&page=${currentPage - 1}">«</a></li>
                            </c:when>
                            <c:otherwise>
                                <li class="disabled"><a href="#">«</a></li>
                            </c:otherwise>
                        </c:choose>
                        <c:forEach begin="1" end="${noOfPages}" var="i">
                            <c:choose>
                                <c:when test="${currentPage eq i}">
                                    <li class="active"><a href="#"><c:out value="${i}"/><span
                                            class="sr-only">(current)</span></a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li><a href="FrontController?command=show_all_drugs&page=${i}"><c:out
                                            value="${i}"/></a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <c:choose>
                            <c:when test="${currentPage lt noOfPages}">
                                <li><a href="FrontController?command=show_all_drugs&page=${currentPage + 1}">»</a></li>
                            </c:when>
                            <c:otherwise>
                                <li class="disabled"><a href="#">»</a></li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </c:when>
                <c:otherwise>
                    Nothing
                </c:otherwise>
            </c:choose>
        </c:if>

        <c:if test="${requestScope.command eq 'find_drug'}">
                <form class="navbar-form navbar-left" action="FrontController" method="get">
                    <input type="hidden" name="command" value="find_drug"/>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search" name="drug_name">
                    </div>
                    <button type="submit" class="btn btn-default" value="">Отправить</button>
                </form>

            <c:choose>
                <c:when test="${requestScope.drugs.size() gt 0}">
                    <table border="1" cellpadding="5" cellspacing="5">
                        <tr>
                            <th>id</th>
                            <th>name</th>
                            <th>dosage</th>
                            <th>amount</th>
                            <th>price</th>
                            <th>manufacturer</th>
                            <th>country</th>
                        </tr>

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
                    </table>
                    <ul class="pagination">
                        <c:choose>
                            <c:when test="${requestScope.currentPage != 1}">
                                <li><a href="FrontController?command=find_drug&page=${currentPage - 1}&drug_name=${requestScope.drug_name}">«</a></li>
                            </c:when>
                            <c:otherwise>
                                <li class="disabled"><a href="#">«</a></li>
                            </c:otherwise>
                        </c:choose>
                        <c:forEach begin="1" end="${noOfPages}" var="i">
                            <c:choose>
                                <c:when test="${currentPage eq i}">
                                    <li class="active"><a href="#"><c:out value="${i}"/><span
                                            class="sr-only">(current)</span></a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li><a href="FrontController?command=find_drug&page=${i}&drug_name=${requestScope.drug_name}"><c:out
                                            value="${i}"/></a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <c:choose>
                            <c:when test="${currentPage lt noOfPages}">
                                <li><a href="FrontController?command=find_drug&page=${currentPage + 1}&drug_name=${requestScope.drug_name}">»</a></li>
                            </c:when>
                            <c:otherwise>
                                <li class="disabled"><a href="#">»</a></li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </c:when>
                <c:otherwise>
                    Nothing
                </c:otherwise>
            </c:choose>
        </c:if>

    </div>

</div>
</body>
</html>
