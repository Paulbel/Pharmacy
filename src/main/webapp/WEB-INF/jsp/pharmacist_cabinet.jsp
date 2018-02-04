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
    <script src="../../js/bootstrap.min.js" type="text/javascript"></script>


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
                            <a href="FrontController?command=change_language&local=ru&query=${requestScope.ControllerConstant.COMMAND_ATTRIBUTE}">
                                <c:out value="${ru}"/>
                            </a>
                        </li>
                        <li>
                            <a href="FrontController?command=change_language&local=en&query=${requestScope.ControllerConstant.COMMAND_ATTRIBUTE}">
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
            <c:if test="${requestScope.prev_command eq 'get_manufacturer_enter_cabinet'}">
                <li class="active">
                    <a href=#>
                        get manufacturer
                    </a>
                </li>
            </c:if>
            <c:if test="${requestScope.prev_command eq 'get_drug_enter_cabinet'}">
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
            <table border="1" cellpadding="5" cellspacing="5">
                <tr>
                    <th>name</th>
                    <th>dosage</th>
                    <th>amount</th>
                    <th>price</th>
                    <th>country</th>
                    <th>manufacturer</th>
                </tr>
                <c:forEach var="item" items="${requestScope.drugs}">
                    <tr>
                        <td><a href="FrontController?command=get_drug_enter_cabinet&drug_id=${item.id}"><c:out
                                value="${item.name}"/></a>
                        </td>
                        <td><c:out value="${item.dosage}"/></td>
                        <td><c:out value="${item.amount}"/></td>
                        <td><c:out value="${item.price}"/></td>
                        <td><c:out value="${item.manufacturer.country.name}"/></td>
                        <td>
                            <a href="FrontController?command=get_manufacturer_enter_cabinet&manufacturer_id=${item.manufacturer.id}"><c:out
                                    value="${item.manufacturer.name}"/></a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <ul class="pagination">

                <c:choose>
                    <c:when test="${requestScope.currentPage != 1}">
                        <li>
                            <a href="FrontController?command=get_drug_list_enter_cabinet&page=${requestScope.currentPage - 1}">«</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="disabled"><a href="#">«</a></li>
                    </c:otherwise>
                </c:choose>
                <c:forEach begin="1" end="${requestScope.noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${requestScope.currentPage eq i}">
                            <li class="active"><a href="#"><c:out value="${i}"/><span
                                    class="sr-only">(current)</span></a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="FrontController?command=get_drug_list_enter_cabinet&page=${i}"><c:out
                                    value="${i}"/></a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:choose>
                    <c:when test="${requestScope.currentPage lt requestScope.noOfPages}">
                        <li>
                            <a href="FrontController?command=get_drug_list_enter_cabinet&page=${requestScope.currentPage + 1}">»</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="disabled"><a href="#">»</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </c:if>

        <c:if test="${requestScope.prev_command eq 'get_manufacturer_list_enter_cabinet'}">
            <table border="1" cellpadding="5" cellspacing="5">
                <tr>
                    <th>name</th>
                    <th>country</th>
                    <th>phone</th>
                    <th>address</th>
                    <th>email</th>
                </tr>
                <c:forEach var="item" items="${requestScope.manufacturer_list}">
                    <tr>
                        <td><c:out value="${item.name}"/></td>
                        <td><c:out value="${item.country.name}"/></td>
                        <td><c:out value="${item.phoneNumber}"/></td>
                        <td><c:out value="${item.address}"/></td>
                        <td><c:out value="${item.email}"/></td>
                    </tr>
                </c:forEach>
            </table>
            <ul class="pagination">

                <c:choose>
                    <c:when test="${requestScope.currentPage != 1}">
                        <li>
                            <a href="FrontController?command=get_manufacturer_list_enter_cabinet&page=${requestScope.currentPage - 1}">«</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="disabled"><a href="#">«</a></li>
                    </c:otherwise>
                </c:choose>
                <c:forEach begin="1" end="${requestScope.noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${requestScope.currentPage eq i}">
                            <li class="active"><a href="#"><c:out value="${i}"/><span
                                    class="sr-only">(current)</span></a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="FrontController?command=get_manufacturer_list_enter_cabinet&page=${i}"><c:out
                                    value="${i}"/></a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:choose>
                    <c:when test="${requestScope.currentPage lt requestScope.noOfPages}">
                        <li>
                            <a href="FrontController?command=get_manufacturer_enter_cabinet&page=${requestScope.currentPage + 1}">»</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="disabled"><a href="#">»</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </c:if>


        <c:if test="${requestScope.prev_command eq 'find_drug_enter_cabinet'}">
            <form class="navbar-form navbar-left" action="FrontController" method="get">
                <input type="hidden" name="command" value="find_drug_enter_cabinet"/>
                <div class="form-group">
                    <input id="find_drug_input" type="text" class="form-control" placeholder="Search" name="drug_name"/>
                </div>
                <button id="find_drug_button" class="btn btn-default" value="">Отправить</button>
            </form>

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
                        <li>
                            <a href="FrontController?command=find_drug_enter_cabinet&page=${requestScope.currentPage - 1}&drug_name=${requestScope.drug_name}">«</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="disabled"><a href="#">«</a></li>
                    </c:otherwise>
                </c:choose>
                <c:forEach begin="1" end="${requestScope.noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${requestScope.currentPage eq i}">
                            <li class="active"><a href="#"><c:out value="${i}"/><span
                                    class="sr-only">(current)</span></a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li>
                                <a href="FrontController?command=find_drug_enter_cabinet&page=${i}&drug_name=${requestScope.drug_name}"><c:out
                                        value="${i}"/></a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:choose>
                    <c:when test="${requestScope.currentPage lt requestScope.noOfPages}">
                        <li>
                            <a href="FrontController?command=find_drug_enter_cabinet&page=${requestScope.currentPage + 1}&drug_name=${requestScope.drug_name}">»</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="disabled"><a href="#">»</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </c:if>

        <c:if test="${requestScope.prev_command eq 'find_manufacturer_enter_cabinet'}">
            <form class="navbar-form navbar-left" action="FrontController" method="get">
                <input type="hidden" name="command" value="find_manufacturer_enter_cabinet"/>
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search" name="manufacturer_name">
                </div>
                <button type="submit" class="btn btn-default" value="">Отправить</button>
            </form>


            <table border="1" cellpadding="5" cellspacing="5">
                <tr>
                    <th>name</th>
                    <th>country</th>
                    <th>phone</th>
                    <th>address</th>
                    <th>email</th>
                </tr>
                <c:forEach var="item" items="${requestScope.manufacturer_list}">
                    <tr>
                        <td><c:out value="${item.name}"/></td>
                        <td><c:out value="${item.country.name}"/></td>
                        <td><c:out value="${item.phoneNumber}"/></td>
                        <td><c:out value="${item.address}"/></td>
                        <td><c:out value="${item.email}"/></td>
                    </tr>
                </c:forEach>
            </table>


            <ul class="pagination">
                <c:choose>
                    <c:when test="${requestScope.currentPage != 1}">
                        <li>
                            <a href="FrontController?command=find_manufacturer_enter_cabinet&page=${requestScope.currentPage - 1}&manufacturer_name=${requestScope.manufacturer_name}">«</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="disabled"><a href="#">«</a></li>
                    </c:otherwise>
                </c:choose>
                <c:forEach begin="1" end="${requestScope.noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${requestScope.currentPage eq i}">
                            <li class="active"><a href="#"><c:out value="${i}"/><span
                                    class="sr-only">(current)</span></a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li>
                                <a href="FrontController?command=find_manufacturer_enter_cabinet&page=${i}&manufacturer_name=${requestScope.manufacturer_name}"><c:out
                                        value="${i}"/></a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:choose>
                    <c:when test="${requestScope.currentPage lt requestScope.noOfPages}">
                        <li>
                            <a href="FrontController?command=find_manufacturer_enter_cabinet&page=${requestScope.currentPage + 1}&manufacturer_name=${requestScope.manufacturer_name}">»</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="disabled"><a href="#">»</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </c:if>

        <c:if test="${requestScope.prev_command eq 'get_manufacturer_enter_cabinet'}">
            <form action="FrontController" method="post">
                <div class="form-row">
                    <div>
                        <label>name</label>
                        <input placeholder="<c:out value="${requestScope.manufacturer.name}"/>">
                    </div>
                    <div>
                        <label>Password</label>
                        <input type="password" placeholder="Password">
                    </div>
                </div>
                <div>
                    <label>Address</label>
                    <input type="text" placeholder="1234 Main St">
                </div>
                <div class="form-group">
                    <label for="inputAddress2">Address 2</label>
                    <input type="text" class="form-control" id="inputAddress2"
                           placeholder="Apartment, studio, or floor">
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="inputCity">City</label>
                        <input type="text" class="form-control" id="inputCity">
                    </div>
                    <div class="form-group col-md-4">
                        <label for="inputState">State</label>
                        <select id="inputState" class="form-control">
                            <option selected>Choose...</option>
                            <option>...</option>
                        </select>
                    </div>
                    <div class="form-group col-md-2">
                        <label for="inputZip">Zip</label>
                        <input type="text" class="form-control" id="inputZip">
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="gridCheck">
                        <label class="form-check-label" for="gridCheck">
                            Check me out
                        </label>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary">Sign in</button>
            </form>
        </c:if>


        <c:if test="${requestScope.prev_command eq 'get_drug_enter_cabinet'}">
            <form class="drug_form" action="FrontController" method="post">
                <input type="hidden" name="command" value="change_drug_description_enter_cabinet"/>
                <input type="hidden" name="want_command" value="get_drug_enter_cabinet"/>
                <input type="hidden" name="drug_id" value="${requestScope.drug.id}"/>
                Change description:

                <div class="form-row ">
                    <label>name</label>
                    <input name="drug_name" value="${requestScope.drug.name}"/>
                    <input name="drug_name" value="${requestScope.drug.name}"/>
                </div>
                <div class="form-row ">
                    <label>composition</label>
                    <input name="drug_composition" value="${requestScope.drug.composition}"/>
                </div>
                    <%-- <input type="text" name="login" value=""/>--%>
                <div class="form-row">
                    <label>description</label>
                    <input type="text" name="drug_description"
                           value="${requestScope.drug.description}">

                </div>
                <input type="submit" value="Отправить">
            </form>
            <form class="drug_form" action="FrontController" method="post">
                <input type="hidden" name="command" value="change_drug_info_enter_cabinet"/>
                <input type="hidden" name="want_command" value="get_drug_enter_cabinet"/>
                <input type="hidden" name="drug_id" value="${requestScope.drug.id}"/>
                Change info:

                <div class="form-row ">
                    <label>amount</label>
                    <c:out value="${param.drug_amount}"/>
                    <input name="drug_amount" value="${requestScope.drug.amount}">

                </div>
                <div class="form-row">
                    <label>dosage</label>
                    <c:out value="${param.drug_dosage}"/>
                    <input type="text" name="drug_dosage" value="${requestScope.drug.dosage}">

                </div>


                <div class="form-check">
                    <c:choose>
                        <c:when test="${requestScope.drug.needPrescription eq true}">
                            <input type="checkbox" name="need_prescription" value="true" checked>
                        </c:when>
                        <c:otherwise>
                            <input type="checkbox" name="need_prescription" value="true">
                        </c:otherwise>
                    </c:choose>
                    <label class="form-check-label">need prescription</label>
                </div>
                <div class="form-row ">
                    <label>price</label>
                    <input name="drug_price" value="${requestScope.drug.price}">
                </div>
                <div class="form-row ">
                    <label>number</label>
                    <input name="drug_number" value="${requestScope.drug.number}">
                </div>
                <input type="submit" value="Отправить">
            </form>
        </c:if>

    </div>


    <c:if test="${requestScope.prev_command eq 'add_drug_enter_cabinet'}">
        <form class="drug_form" action="FrontController" method="post">
            <input type="hidden" name="command" value="add_drug_enter_cabinet"/>
            <div class="form-row ">
                <label>name</label>
                <input name="drug_name"/>
            </div>
            <div class="form-row ">
                <label>composition</label>
                <input name="drug_composition"/>
            </div>
            <div class="form-row">
                <label>description</label>
                <input type="text" name="drug_description">

            </div>
            <div class="form-row ">
                <label>amount</label>
                <input name="drug_amount">
            </div>
            <div class="form-row">
                <label>dosage</label>
                <input type="text" name="drug_dosage">

            </div>
            <div class="form-check">
                <input type="checkbox" name="need_prescription" value="true" checked>
                <label class="form-check-label">need prescription</label>
            </div>
            <div class="form-row ">
                <label>price</label>
                <input name="drug_price">
            </div>
            <div class="form-row ">
                <label>number</label>
                <input name="drug_number">
            </div>

            <select name="manufacturer_id">
                <option disabled>Choose manufacturer</option>
                <c:forEach var="item" items="${requestScope.manufacturer_list}">
                    <option value="${item.id}"><c:out value="${item.name}"/></option>
                </c:forEach>
            </select>

            <input type="submit" value="Отправить">
        </form>
    </c:if>


    <c:if test="${requestScope.prev_command eq 'add_manufacturer_enter_cabinet' or requestScope.prev_command eq 'get_country_list_enter_cabinet'}">
        <form class="drug_form" action="FrontController" method="post">
            <input type="hidden" name="command" value="add_manufacturer_enter_cabinet"/>
            <div class="form-row ">
                <label>name</label>
                <input name="manufacturer_name"/>
            </div>
            <div class="form-row ">
                <label>phone number</label>
                <input name="phone"/>
            </div>


            <div class="form-row ">
                <label>address</label>
                <input name="manufacturer_address"/>
            </div>

            <div class="form-row ">
                <label>email</label>
                <input name="email"/>
            </div>

            <select name="country_code">
                <option disabled>Choose manufacturer</option>
                <c:forEach var="item" items="${requestScope.country_list}">
                    <option value="${item.code}"><c:out value="${item.name}"/>
                        <c:out value="${item.code}"/></option>
                </c:forEach>
            </select>

            <input type="submit" value="Отправить">
        </form>
    </c:if>

</div>

</body>

</html>
