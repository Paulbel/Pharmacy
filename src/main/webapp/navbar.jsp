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
<fmt:message bundle="${loc}" key="local.main" var="main"/>


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
                        <c:choose>
                            <c:when test="${empty sessionScope.login}">
                                <li>
                                    <a href="FrontController?command=get_sign_in_page"><c:out
                                            value="${sign_in}"/></a>
                                </li>
                                <li>
                                    <a href="FrontController?command=get_registration_page"><c:out
                                            value="${registration}"/></a>
                                </li>

                            </c:when>
                            <c:otherwise>
                                <li><a href="FrontController?command=sign_out"><c:out value="${signout}"/></a></li>
                            </c:otherwise>
                        </c:choose>
                        <li>
                            <a href="FrontController?command=get_main_page"><c:out
                                    value="${main}"/></a>
                        </li>
                    </ul>
                </li>
                <c:if test="${not empty sessionScope.login}">
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown"><c:out value="${language}"/><b
                                class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="FrontController?command=change_language&local=ru"><c:out value="${ru}"/></a>
                            </li>
                            <li><a href="FrontController?command=change_language&local=en"><c:out value="${en}"/></a>
                            </li>
                        </ul>
                    </li>
                </c:if>

            </ul>
        </div>
    </div>
</nav>




