<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>


<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Title</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="js/jquery-3.2.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="fonts.css" type="text/css" charset="utf-8"/>
    <link rel="stylesheet" type="text/css" href="MainPageStyles.css">

    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="local" var = "loc"/>
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
    <fmt:message bundle="${loc}" key="local.language" var="language"/>
</head>
<body>


<div id="signInForm" class="modal">
  <span onclick="document.getElementById('signInForm').style.display='none'"
        class="close" title="Close Modal">&times;</span>

    <form class="modal-content animate" action="FrontController" method="post">
        <div class="container">
            <input type="hidden" name="command" value="sign_in"/>

            <label><b><c:out value="${login}"/></b></label>
            <input type="text" name="login" value=""/>
            <br/>

            <label><b><c:out value="${password}"/></b></label>
            <input type="password" name="password" value=""/>
            <br/>
        </div>

        <div class="container">

            <input type="submit" value="<c:out value="${signin}"/>"/><br/>
            <button type="reset" onclick="document.getElementById('signInForm').style.display='none'" class="cancelbtn">
                <c:out value="${cancel}"/>
            </button>
        </div>
    </form>
</div>

<div id="registrationForm" class="modal">
  <span onclick="document.getElementById('registrationForm').style.display='none'"
        class="close" title="Close Modal">&times;</span>

    <form class="modal-content animate" action="FrontController" method="post">
        <div class="container">
            <input type="hidden" name="command" value="sign_up"/>


            <label><b><c:out value="${name}"/></b></label>
            <input type="text" name="name" value=""/>
            <br/>
            <label><b><c:out value="${surname}"/></b></label>
            <input type="text" name="surname" value=""/>
            <br/>

            <label><b><c:out value="${login}"/></b></label>
            <input type="text" name="login" value=""/>
            <br/>

            <label><b><c:out value="${password}"/></b></label>
            <input type="password" name="password" value=""/>
            <br/>

            <label><b><c:out value="${repeat_password}"/></b></label>
            <input type="password" name="repeatedPassword" value=""/>
            <br/>

            <label><b><c:out value="${phone}"/></b></label>
            <input type="text" name="phone" value=""/>
            <br/>

            <label><b><c:out value="${email}"/></b></label>
            <input type="text" name="email" value=""/>
            <br/>
        </div>

        <div class="container">

            <input type="submit" value="<c:out value="${registration}"/>"/><br/>
            <button type="reset" onclick="document.getElementById('registrationForm').style.display='none'" class="cancelbtn">
                <c:out value="${cancel}"/>
            </button>

        </div>
    </form>
</div>




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
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><c:out value="${cabinet}"/><b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <c:choose>
                        <c:when test="${empty sessionScope.login}">
                            <li>
                                <a href="#" onclick="document.getElementById('signInForm').style.display='block'"><c:out value="${signin}" /></a>
                                <a href="#" onclick="document.getElementById('registrationForm').style.display='block'"><c:out value="${registration}"/></a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="FrontController?command=sign_out"><c:out value="${signout}" /></a></li>
                        </c:otherwise>
                        </c:choose>
                    </ul>
                </li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown"><c:out value="${language}" /><b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="FrontController?command=change_language&local=ru"><c:out value="${ru}"/></a></li>
                        <li><a href="FrontController?command=change_language&local=en"><c:out value="${en}"/></a></li>
                    </ul>
                </li>

            </ul>
        </div>
    </div>
</nav>

</h>
</body>
</html>