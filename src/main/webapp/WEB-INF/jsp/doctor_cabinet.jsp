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

    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.signIn" var="signin"/>
    <fmt:message bundle="${loc}" key="local.address" var="address"/>
    <fmt:message bundle="${loc}" key="local.registration" var="registration"/>
    <fmt:message bundle="${loc}" key="local.signOut" var="signout"/>
    <fmt:message bundle="${loc}" key="local.cabinet" var="cabinet"/>
    <fmt:message bundle="${loc}" key="local.companyName" var="company_name"/>
    <fmt:message bundle="${loc}" key="local.name" var="name"/>
    <fmt:message bundle="${loc}" key="local.surname" var="surname"/>
    <fmt:message bundle="${loc}" key="local.email" var="email"/>
    <fmt:message bundle="${loc}" key="local.phone" var="phone"/>
    <fmt:message bundle="${loc}" key="local.login" var="login"/>
    <fmt:message bundle="${loc}" key="local.cancel" var="cancel"/>
    <fmt:message bundle="${loc}" key="local.ru" var="ru"/>
    <fmt:message bundle="${loc}" key="local.en" var="en"/>
    <fmt:message bundle="${loc}" key="local.drugs" var="drugs"/>
    <fmt:message bundle="${loc}" key="local.language" var="language"/>
    <fmt:message bundle="${loc}" key="local.find_drug" var="find_drug"/>
    <fmt:message bundle="${loc}" key="local.add_drug" var="add_drug"/>
    <fmt:message bundle="${loc}" key="local.change_drug_info" var="change_drug_info"/>
    <fmt:message bundle="${loc}" key="local.add_manufacturer" var="add_manufacturer"/>
    <fmt:message bundle="${loc}" key="local.manufacturers" var="manufacturers"/>
    <fmt:message bundle="${loc}" key="local.find_manufacturer" var="find_manufacturer"/>
    <fmt:message bundle="${loc}" key="local.drug_name" var="drug_name"/>
    <fmt:message bundle="${loc}" key="local.dosage" var="dosage"/>
    <fmt:message bundle="${loc}" key="local.amount" var="amount"/>
    <fmt:message bundle="${loc}" key="local.number" var="number"/>
    <fmt:message bundle="${loc}" key="local.country" var="country"/>
    <fmt:message bundle="${loc}" key="local.price" var="price"/>
    <fmt:message bundle="${loc}" key="local.manufacturers" var="manufacturer"/>
    <fmt:message bundle="${loc}" key="local.description" var="description"/>
    <fmt:message bundle="${loc}" key="local.composition" var="composition"/>
    <fmt:message bundle="${loc}" key="local.need_prescription" var="need_prescription"/>
    <fmt:message bundle="${loc}" key="local.create" var="create"/>
    <fmt:message bundle="${loc}" key="local.find_manufacturer" var="find_manufacturer"/>
    <fmt:message bundle="${loc}" key="local.find" var="find"/>
    <fmt:message bundle="${loc}" key="local.manufacturer_name" var="manufacturer_name"/>
    <fmt:message bundle="${loc}" key="local.address" var="address"/>
    <fmt:message bundle="${loc}" key="local.current" var="current"/>
</head>
<body>

<jsp:include page="../../navbar.jsp"/>

<div id="container">
    <div id="nav_menu">
        <ul class="nav nav-pills nav-stacked" style="max-width: 260px;">
            <c:choose>
                <c:when test="${requestScope.prev_command eq 'get_client_list_enter_cabinet'}">
                    <li class="active">
                        <a href=#>
                            <c:out value="${drugs}"/>
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="FrontController?command=get_client_list_enter_cabinet">
                            <c:out value="${drugs}"/>
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>


            <c:if test="${requestScope.prev_command eq 'get_client_enter_cabinet'}">
                <li class="active">
                    <a href=#>
                        get user
                    </a>
                </li>
            </c:if>

        </ul>
    </div>


    <div id="info">


        <c:if test="${requestScope.prev_command eq 'get_client_list_enter_cabinet'}">
            <table border="1">
                <thead>
                <tr>

                    <th>login</th>
                    <th>name</th>
                    <th>surname</th>
                    <th>email</th>
                    <th>phone</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.users}" var="item">

                    <tr>
                        <td><m:userLogin user="${item}"
                                         command="get_client_enter_cabinet&login=${item.login}"/></td>
                        <td><c:out value="${item.name}"/></td>
                        <td><c:out value="${item.surname}"/></td>

                        <td><c:out value="${item.email}"/></td>
                        <td><c:out value="${item.phoneNumber}"/></td>
                    </tr>

                </c:forEach>
                </tbody>
            </table>
        </c:if>


        <c:if test="${requestScope.prev_command eq 'get_client_enter_cabinet'}">
            <div class="row main">
                <form class="form-horizontal" method="post" action="FrontController">
                    <input type="hidden" name="command" value="add_prescription_enter_cabinet"/>
                    <input type="hidden" name="login" value="${requestScope.user.login}"/>

                    <div class="form-group">
                        <label class="cols-sm-2 control-label"><c:out value="${requestScope.user.name}"/> <c:out
                                value="${requestScope.user.surname}"/></label>
                    </div>

                    <div class="form-group">
                        <label class="cols-sm-2 control-label">day count</label>
                        <div class="cols-sm-10">
                            <input type="text" class="form-control" name="day_count"
                                   placeholder="${amount}"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="cols-sm-2 control-label"><c:out value="${amount}"/></label>
                        <div class="cols-sm-10">
                            <input type="text" class="form-control" name="drug_number"
                                   placeholder="${amount}"/>
                        </div>
                    </div>

                    <div class="form-group">

                        <label class="custom-control-label"><c:out value="${find_drug}"/></label>

                        <div class="row">
                            <div class="col-sm-6">
                                <input id="find_drug_select_input" type="text"
                                       class="form-control"
                                       placeholder="Search"/>
                            </div>
                            <div class="col-sm-3">
                                <button type="button" id="find_drug_select_button" class="btn btn-default"
                                        value="">
                                    <c:out value="${find_drug}"/>
                                </button>
                            </div>
                            <div id="responseDrugSelect" class="col-sm-3">
                            </div>
                        </div>
                    </div>


                    <div class="form-group">
                        <div class="cols-sm-10">
                            <input class="form-control" type="submit" value="${create}"/>
                        </div>
                    </div>
                </form>

            </div>
        </c:if>


    </div>
</div>
<script type="text/javascript" src="../../js/jquery-3.2.1.js"></script>
<script src="../../js/bootstrap.min.js" type="text/javascript"></script>
<script src="../../js/script.js" type="text/javascript"></script>
</body>

</html>









