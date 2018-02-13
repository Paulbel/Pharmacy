<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://pharmacy.by/tags" prefix="m" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta charset="UTF-8" content="text/html">
    <title>Title</title>

    <link href="../../css/bootstrap.min.css" rel="stylesheet">
    <link href="../../css/style.css" rel="stylesheet">
    <link href="../../css/table.css" rel="stylesheet">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.address" var="address"/>
    <fmt:message bundle="${loc}" key="local.registration" var="registration"/>
    <fmt:message bundle="${loc}" key="local.signIn" var="signIn"/>
    <fmt:message bundle="${loc}" key="local.name" var="name"/>
    <fmt:message bundle="${loc}" key="local.surname" var="surname"/>
    <fmt:message bundle="${loc}" key="local.email" var="email"/>
    <fmt:message bundle="${loc}" key="local.phone" var="phone"/>
    <fmt:message bundle="${loc}" key="local.login" var="login"/>
    <fmt:message bundle="${loc}" key="local.cancel" var="cancel"/>
    <fmt:message bundle="${loc}" key="local.password" var="password"/>
    <fmt:message bundle="${loc}" key="local.companyName" var="company_name"/>

</head>
<body>


<jsp:include page="../../navbar.jsp"/>
<div class="container">
    <div class="row main">
        <div class="panel-heading">
            <div class="panel-title text-center">
                <h1 class="title"><c:out value="${company_name}"/></h1>
                <hr/>
            </div>
        </div>
        <div class="main-login main-center">
            <form class="form-horizontal" method="post" action="FrontController">
                <input type="hidden" name="command" value="sign_up_enter_cabinet"/>
                <div class="form-group">
                    <label for="name" class="cols-sm-2 control-label"><c:out value="${name}"/></label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user fa"></i></span>
                            <input type="text" class="form-control" name="name" id="name"
                                   placeholder="${name}"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="surname" class="cols-sm-2 control-label"><c:out value="${surname}"/></label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user fa"></i></span>
                            <input type="text" class="form-control" name="surname" id="surname"
                                   placeholder="${surname}"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="username" class="cols-sm-2 control-label"><c:out value="${login}"/></label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-users fa"></i></span>
                            <input type="text" class="form-control" name="login" id="username"
                                   placeholder="${login}"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="phone" class="cols-sm-2 control-label"><c:out value="${phone}"/></label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-phone fa"></i></span>
                            <input type="tel" class="form-control" name="phone" id="phone"
                                   placeholder="${phone}"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="email" class="cols-sm-2 control-label"><c:out value="${email}"/></label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-envelope fa"></i></span>
                            <input type="email" class="form-control" name="email" id="email"
                                   placeholder="${email}"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="password" class="cols-sm-2 control-label"><c:out value="${password}"/></label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-lock fa-lg"></i></span>
                            <input type="password" class="form-control" name="password" id="password"
                                   placeholder="${password}"/>
                        </div>
                    </div>
                </div>

                <div class="form-group ">
                    <button type="submit" class="btn btn-primary btn-lg btn-block login-button"><c:out
                            value="${registration}"/></button>
                </div>
                <div class="login-register">
                    <a href="FrontController?command=get_sign_in_page"><c:out value="${signIn}"/></a>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript" src="../../js/jquery-3.2.1.js"></script>
<script src="../../js/bootstrap.min.js" type="text/javascript"></script>
<script src="../../js/script.js" type="text/javascript"></script>
</body>

</html>
