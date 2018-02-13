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
    <fmt:message bundle="${loc}" key="local.country" var="country"/>
    <fmt:message bundle="${loc}" key="local.price" var="price"/>
    <fmt:message bundle="${loc}" key="local.manufacturers" var="manufacturer"/>
    <fmt:message bundle="${loc}" key="local.role" var="role"/>
    <fmt:message bundle="${loc}" key="local.client" var="client"/>
    <fmt:message bundle="${loc}" key="local.doctor" var="doctor"/>
    <fmt:message bundle="${loc}" key="local.administrator" var="admin"/>
    <fmt:message bundle="${loc}" key="local.user_admin" var="user_admin"/>
    <fmt:message bundle="${loc}" key="local.select_role" var="select_role"/>
    <fmt:message bundle="${loc}" key="local.change" var="change"/>
    <fmt:message bundle="${loc}" key="local.users" var="users"/>
    <fmt:message bundle="${loc}" key="local.change_user_role" var="change_role"/>
</head>
<body>

<jsp:include page="../../navbar.jsp"/>

<div id="container">
    <div id="nav_menu">
        <ul class="nav nav-pills nav-stacked" style="max-width: 260px;">
            <c:choose>
                <c:when test="${requestScope.prev_command eq 'get_user_list_enter_cabinet'}">
                    <li class="active">
                        <a href=#>
                            <c:out value="${users}"/>
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="FrontController?command=get_user_list_enter_cabinet">
                            <c:out value="${users}"/>
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>
            <c:if test="${requestScope.prev_command eq 'get_user_enter_cabinet'}">
                <li class="active">
                    <a href=#>
                        <c:out value="${change_role}"/>
                    </a>
                </li>
            </c:if>


        </ul>
    </div>


    <div id="info">


        <c:if test="${requestScope.prev_command eq 'get_user_list_enter_cabinet'}">
            <table border="1">
                <thead>
                <tr>

                    <th><c:out value="${login}"/></th>
                    <th><c:out value="${name}"/></th>
                    <th><c:out value="${surname}"/></th>
                    <th><c:out value="${role}"/></th>
                    <th><c:out value="${email}"/></th>
                    <th><c:out value="${phone}"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.users}" var="item">

                    <tr>
                        <td><m:userLogin user="${item}"
                                         command="get_user_enter_cabinet&login=${item.login}"/></td>
                        <td><c:out value="${item.name}"/></td>
                        <td><c:out value="${item.surname}"/></td>

                        <td><c:out value="${item.role}"/></td>
                        <td><c:out value="${item.email}"/></td>
                        <td><c:out value="${item.phoneNumber}"/></td>
                            <%--              <td><c:out value="${item.role}"/></td>--%>
                            <%--                 <td>
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
                                             </td>--%>
                    </tr>

                </c:forEach>
                </tbody>
            </table>
        </c:if>


        <c:if test="${requestScope.prev_command eq 'get_user_enter_cabinet'}">
            <div class="row main">
                <form class="form-horizontal" method="post" action="FrontController">
                    <input type="hidden" name="command" value="give_role_enter_cabinet"/>
                    <input type="hidden" name="login" value="${requestScope.user.login}"/>

                    <div class="form-group">
                        <label class="cols-sm-2 control-label"><c:out value="${name}"/>: <c:out
                                value="${requestScope.user.name}"/></label>
                    </div>
                    <div class="form-group">
                        <label class="cols-sm-2 control-label"><c:out value="${surname}"/>: <c:out
                                value="${requestScope.user.surname}"/></label>
                    </div>
                    <div class="form-group">
                        <label class="cols-sm-2 control-label"><c:out value="${login}"/>: <c:out
                                value="${requestScope.user.login}"/></label>
                    </div>
                    <div class="form-group">
                        <label class="cols-sm-6 control-label"><c:out value="${role}"/>: <c:out
                                value="${requestScope.user.role}"/></label>
                    </div>

                    <div class="form-group">


                        <div class="row">

                            <div id="responseDrugSelect" class="col-sm-10">
                                <c:choose>
                                    <c:when test="${requestScope.user.role == 'ADMIN'}">

                                        <label class="cols-sm-12 control-label"><c:out
                                                value="${user_admin}"/></label>

                                    </c:when>
                                    <c:otherwise>
                                        <label class="cols-sm-4 control-label"><c:out value="${select_role}"/></label>

                                        <select name="role" class="cols-sm-3  form-control">

                                            <c:if test="${requestScope.user.role != 'DOCTOR'}">
                                                <option value="DOCTOR"><c:out
                                                        value="${doctor}"/></option>
                                            </c:if>


                                            <c:if test="${requestScope.user.role != 'CLIENT'}">
                                                <option value="CLIENT"><c:out
                                                        value="${client}"/></option>
                                            </c:if>
                                            <option value="ADMIN"><c:out value="${admin}"/></option>
                                        </select>


                                        <input class="cols-sm-9 form-control" type="submit" value="${change}"/>

                                    </c:otherwise>
                                </c:choose>

                            </div>
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









