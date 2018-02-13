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
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <c:out value="${cabinet}"/>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="FrontController?command=sign_out">
                                <c:out value="${signout}"/>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown">
                        <c:out value="${language}"/>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="FrontController?command=change_language&local=ru&query=${requestScope.prev_command}">
                                <c:out value="${ru}"/>
                            </a>
                        </li>
                        <li>
                            <a href="FrontController?command=change_language&local=en&query=${requestScope.prev_command}">
                                <c:out value="${en}"/>
                            </a>
                        </li>
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
                <c:when test="${requestScope.prev_command eq 'get_drug_list_enter_cabinet'}">
                    <li class="active">
                        <a href=#>
                            <c:out value="${drugs}"/>
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="FrontController?command=get_drug_list_enter_cabinet">
                            <c:out value="${drugs}"/>
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>

            <c:choose>
                <c:when test="${requestScope.prev_command eq 'find_drug_enter_cabinet'}">
                    <li class="active">
                        <a href=#>
                            <c:out value="${find_drug}"/>
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="FrontController?command=enter_cabinet&want_command=find_drug_enter_cabinet">
                            <c:out value="${find_drug}"/>
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${requestScope.prev_command eq 'get_manufacturer_list_enter_cabinet'}">
                    <li class="active">
                        <a href=#>
                            <c:out value="${manufacturers}"/>
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="FrontController?command=get_manufacturer_list_enter_cabinet">
                            <c:out value="${manufacturers}"/>
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${requestScope.prev_command eq 'find_manufacturer_enter_cabinet'}">
                    <li class="active">
                        <a href=#>
                            <c:out value="${find_manufacturer}"/>
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="FrontController?command=enter_cabinet&want_command=find_manufacturer_enter_cabinet">
                            <c:out value="${find_manufacturer}"/>
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${requestScope.prev_command eq 'add_manufacturer_enter_cabinet'}">
                    <li class="active">
                        <a href=#>
                            <c:out value="${add_manufacturer}"/>
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="FrontController?command=get_country_list_enter_cabinet&want_command=add_manufacturer_enter_cabinet">
                            <c:out value="${add_manufacturer}"/>
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${requestScope.prev_command eq 'add_drug_enter_cabinet'}">
                    <li class="active">
                        <a href=#>
                            <c:out value="${add_drug}"/>
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="FrontController?command=get_manufacturer_list_enter_cabinet&want_command=add_drug_enter_cabinet">
                            <c:out value="${add_drug}"/>
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>
            <c:if test="${requestScope.prev_command eq 'get_drug_enter_cabinet'} ">
                <li class="active">
                    <a href=#>
                        <c:out value="${change_drug_info}"/>
                    </a>
                </li>
            </c:if>
        </ul>
    </div>


    <div id="info">
        <c:if test="${requestScope.prev_command eq 'get_drug_list_enter_cabinet'}">
            <jsp:include page="drug_table.jsp"/>
            <m:pagination command="get_drug_list_enter_cabinet" currentPage="${requestScope.currentPage}"
                          pageNumber="${requestScope.noOfPages}"/>
        </c:if>

        <c:if test="${requestScope.prev_command eq 'get_manufacturer_list_enter_cabinet'}">

            <jsp:include page="manufacturer_table.jsp"/>
            <m:pagination command="get_manufacturer_list_enter_cabinet" currentPage="${requestScope.currentPage}"
                          pageNumber="${requestScope.noOfPages}"/>
        </c:if>


        <c:if test="${requestScope.prev_command eq 'find_drug_enter_cabinet'}">
            <input id="find_drug_input" type="text" class="form-control" placeholder="${find_drug}"/>
            <button id="find_drug_button" class="btn btn-default" value=""><c:out value="${find_drug}"/></button>
            <div id="responseTable">

            </div>
        </c:if>

        <c:if test="${requestScope.prev_command eq 'find_manufacturer_enter_cabinet'}">
            <input id="find_manufacturer_input" type="text" class="form-control" placeholder="${find_manufacturer}">
            <button id="find_manufacturer_button" type="submit" class="btn btn-default" value=""><c:out
                    value="${find_manufacturer}"/></button>
            <div id="responseTable">

            </div>
        </c:if>


        <c:if test="${requestScope.prev_command eq 'get_drug_enter_cabinet'}">
            <div class="row main">
                <form class="form-horizontal" method="post" action="FrontController">
                    <input type="hidden" name="command" value="change_drug_description_enter_cabinet"/>
                    <input type="hidden" name="drug_id" value="${requestScope.drug.id}"/>


                    <div class="form-group">
                        <label class="cols-sm-2 control-label"><c:out value="${drug_name}"/> <c:out
                                value="${current}"/>, <c:out value="${requestScope.drug.name}"/></label>
                        <div class="cols-sm-10">
                            <input type="text" class="form-control" name="drug_name"
                                   value="${requestScope.drug.name}"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="cols-sm-2 control-label"><c:out value="${composition}"/> <c:out
                                value="${current}"/>, <c:out value="${requestScope.drug.composition}"/></label>
                        <div class="cols-sm-10">
                            <input type="text" class="form-control" name="drug_composition"
                                   value="${requestScope.drug.composition}"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="cols-sm-2 control-label"><c:out value="${description}"/>, <c:out
                                value="${current}"/> <c:out
                                value="${requestScope.drug.description}"/></label>
                        <div class="cols-sm-10">
                            <input type="text" class="form-control" name="drug_description"
                                   value="${requestScope.drug.description}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="cols-sm-10">
                            <input class="form-control" type="submit" value="${create}"/>
                        </div>
                    </div>
                </form>


                <form class="form-horizontal" method="post" action="FrontController">
                    <input type="hidden" name="drug_id" value="${requestScope.drug.id}"/>
                    <input type="hidden" name="command" value="change_drug_info_enter_cabinet"/>

                    <div class="form-group">
                        <label class=" control-label"><c:out value="${dosage}"/>, <c:out
                                value="${current}"/> <c:out value="${requestScope.drug.dosage}"/></label>
                        <div class="cols-sm-10">
                            <input type="text" class="form-control" name="drug_dosage"
                                   value="${requestScope.drug.dosage}"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class=" control-label"><c:out value="${amount}"/> <c:out
                                value="${current}"/>, <c:out value="${requestScope.drug.amount}"/></label>
                        <div class="cols-sm-10">
                            <input type="text" class="form-control" name="drug_amount"
                                   value="${requestScope.drug.amount}"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="cols-sm-4 control-label"><c:out value="${number}"/>, <c:out
                                value="${current}"/> <c:out value="${requestScope.drug.number}"/></label>
                        <div class="cols-sm-10">

                            <input type="text" class="form-control" name="drug_number"
                                   value="${requestScope.drug.number}"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="cols-sm-4 control-label"><c:out value="${price}"/>, <c:out
                                value="${current}"/> <c:out value="${requestScope.drug.price}"/></label>
                        <div class="cols-sm-10">

                            <input type="text" class="form-control" name="drug_price"
                                   value="${requestScope.drug.price}"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="custom-control custom-checkbox mr-sm-2">
                            <div class="form-check">
                                <label class="custom-control-label"><c:out value="${need_prescription}"/></label>
                                <c:choose>
                                    <c:when test="${requestScope.drug.needPrescription eq true}">
                                        <input type="checkbox" class="custom-control-input" name="need_prescription"
                                               value="true" checked>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="checkbox" class="custom-control-input" name="need_prescription"
                                               value="true">
                                    </c:otherwise>
                                </c:choose>
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


        <c:if test="${requestScope.prev_command eq 'add_drug_enter_cabinet'}">

            <div class="row main">
                <form class="form-horizontal" method="post" action="FrontController">
                    <input type="hidden" name="command" value="add_drug_enter_cabinet"/>
                    <div class="form-group">
                        <label class="cols-sm-2 control-label"><c:out value="${drug_name}"/></label>
                        <div class="cols-sm-10">
                            <input type="text" class="form-control" name="drug_name"
                                   placeholder="${drug_name}"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="cols-sm-2 control-label"><c:out value="${composition}"/></label>
                        <div class="cols-sm-10">
                            <input type="text" class="form-control" name="drug_composition"
                                   placeholder="${composition}"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="cols-sm-2 control-label"><c:out value="${description}"/></label>
                        <div class="cols-sm-10">
                            <input type="text" class="form-control" name="drug_description"
                                   placeholder="${description}"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class=" control-label"><c:out value="${dosage}"/></label>
                        <div class="cols-sm-10">
                            <input type="text" class="form-control" name="drug_dosage"
                                   placeholder="${dosage}"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class=" control-label"><c:out value="${amount}"/></label>
                        <div class="cols-sm-10">
                            <input type="text" class="form-control" name="drug_amount"
                                   placeholder="${amount}"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="cols-sm-4 control-label"><c:out value="${number}"/></label>
                        <div class="cols-sm-10">

                            <input type="text" class="form-control" name="drug_number"
                                   placeholder="${number}"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="cols-sm-4 control-label"><c:out value="${price}"/></label>
                        <div class="cols-sm-10">

                            <input type="text" class="form-control" name="drug_price"
                                   placeholder="${price}"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <div class="custom-control custom-checkbox mr-sm-2">
                            <input type="checkbox" class="custom-control-input" name="need_prescription" value="true">
                            <label class="custom-control-label"><c:out value="${need_prescription}"/></label>
                        </div>
                    </div>

                    <div class="form-group">

                        <label class="custom-control-label"><c:out value="${find_manufacturer}"/></label>

                        <div class="row">
                            <div class="col-sm-6">
                                <input id="find_manufacturer_select_input" type="text"
                                       class="form-control"
                                       placeholder="Search"/>
                            </div>
                            <div class="col-sm-3">
                                <button type="button" id="find_manufacturer_select_button" class="btn btn-default"
                                        value="">
                                    <c:out value="${find}"/>
                                </button>
                            </div>
                            <div id="responseSelect" class="col-sm-3">
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


        <c:if test="${requestScope.prev_command eq 'add_manufacturer_enter_cabinet' or requestScope.prev_command eq 'get_country_list_enter_cabinet'}">
            <div class="row main">
                <form class="form-horizontal" method="post" action="FrontController">
                    <input type="hidden" name="command" value="add_manufacturer_enter_cabinet"/>
                    <div class="form-group">
                        <label class="cols-sm-2 control-label"><c:out value="${drug_name}"/></label>
                        <div class="cols-sm-10">
                            <input type="text" class="form-control" name="manufacturer_name"
                                   placeholder="${manufacturer_name}"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="cols-sm-2 control-label"><c:out value="${phone}"/></label>
                        <div class="cols-sm-10">
                            <input type="text" class="form-control" name="phone"
                                   placeholder="${phone}"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="cols-sm-2 control-label"><c:out value="${address}"/></label>
                        <div class="cols-sm-10">
                            <input type="text" class="form-control" name="address"
                                   placeholder="${address}"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class=" control-label"><c:out value="${email}"/></label>
                        <div class="cols-sm-10">
                            <input type="text" class="form-control" name="email"
                                   placeholder="${email}"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <div class="cols-sm-10">
                            <label class="cols-sm-4 control-label"><c:out value="${country}"/></label>
                            <select name="country_code" class="form-control">
                                <option disabled>Choose manufacturer</option>
                                <c:forEach var="item" items="${requestScope.country_list}">
                                    <option value="${item.code}"><c:out value="${item.name}"/></option>
                                </c:forEach>
                            </select>
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
