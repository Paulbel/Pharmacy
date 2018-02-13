<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://pharmacy.by/tags" prefix="m" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>


<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">

    <title>Title</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="js/jquery-3.2.1.js"></script>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/sign_up.css">

    <title>Аптека</title>
    <link rel="shortcut icon" href="img/ico.png" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="css/index_style.css">


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
<jsp:include page="navbar.jsp"/>

<div id="main_container">
    <div class="drug_form forward">
        <div class="info">
            <div class="header"><h1>Препараты</h1></div>
            <div class="text">
                В этом разделе вы можете найти препараты, информацию о них(производитель, цена и т.д.). В нашем каталоге
                представлены наиболее современные препараты отечсественной и зарубежной медицины

            </div>

            <div class="navigation">
                <c:if test="${not empty sessionScope.login}">
                    <div class="menu_button">
                        <img src="img/search.svg">
                        <a href="FrontController?command=enter_cabinet&want_command=find_drug_enter_cabinet">Поиск</a>
                    </div>
                    <div class="menu_button">
                        <img src="img/cart.svg">
                        <a href="FrontController?command=get_drug_list_enter_cabinet">Каталог</a>
                    </div>
                </c:if>
            </div>

        </div>

        <img class="image" src="img/drug.jpg"/>

    </div>


    <div class="drug_form reverse">
        <img class="image" src="img/manufacturer.jpg"/>
        <div class="info">
            <div class="header"><h1>Производители препаратов</h1></div>
            <div class="text">
                В этом разделе вы можете найти производителей препаратов и информацию о них
            </div>
            <div class="navigation">
                <c:if test="${not empty sessionScope.login}">
                    <div class="menu_button">
                        <img src="img/search.svg">
                        <a href="FrontController?command=enter_cabinet&want_command=find_manufacturer_enter_cabinet">Поиск</a>
                    </div>
                    <div class="menu_button">
                        <img src="img/cart.svg">
                        <a href="FrontController?command=get_manufacturer_list_enter_cabinet">Каталог</a>
                    </div>
                </c:if>
            </div>
        </div>

    </div>


    <div class="drug_form forward">
        <div class="info">
            <div class="header"><h1>Витамины</h1></div>
            <div class="text">
                Скоро мы добавим раздел витамины
            </div>
            <div class="navigation">
            </div>
        </div>

        <img class="image" src="img/drug.jpg"/>

    </div>


</div>

<%--<div class="signUp">
    <div class="row main">
        <div class="panel-heading">
            <div class="panel-title text-center">
                <h1 class="title">Company Name</h1>
                <hr/>
            </div>
        </div>
        <div class="main-login main-center">
            <form class="form-horizontal" method="post" action="#">

                <div class="form-group">
                    <label for="name" class="cols-sm-2 control-label">Your Name</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                            <input type="text" class="form-control" name="name" id="name"
                                   placeholder="Enter your Name"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="email" class="cols-sm-2 control-label">Your Email</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
                            <input type="text" class="form-control" name="email" id="email"
                                   placeholder="Enter your Email"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="username" class="cols-sm-2 control-label">Username</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
                            <input type="text" class="form-control" name="username" id="username"
                                   placeholder="Enter your Username"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="password" class="cols-sm-2 control-label">Password</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                            <input type="password" class="form-control" name="password" id="password"
                                   placeholder="Enter your Password"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="confirm" class="cols-sm-2 control-label">Confirm Password</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                            <input type="password" class="form-control" name="confirm" id="confirm"
                                   placeholder="Confirm your Password"/>
                        </div>
                    </div>
                </div>

                <div class="form-group ">
                    <button type="button" class="btn btn-primary btn-lg btn-block login-button">Register</button>
                </div>
                <div class="login-register">
                    <a href="index.php">Login</a>
                </div>
            </form>
        </div>
    </div>
</div>--%>

</body>
</html>