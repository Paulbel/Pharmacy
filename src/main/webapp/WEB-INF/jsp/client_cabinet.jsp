<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://pharmacy.by/tags" prefix="m" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta charset="UTF-8" content="">
    <title>Title</title>
    <link href="../../css/style.css" rel="stylesheet">
    <link href="../../css/bootstrap.min.css" rel="stylesheet">

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
    <fmt:message bundle="${loc}" key="local.get_orders" var="get_orders"/>
    <fmt:message bundle="${loc}" key="local.create_order" var="create_order"/>
    <fmt:message bundle="${loc}" key="local.count" var="count"/>
    <fmt:message bundle="${loc}" key="local.get_prescriptions" var="get_prescriptions"/>
    <fmt:message bundle="${loc}" key="local.order_add_problem" var="order_problem"/>


</head>
<body>
<jsp:include page="../../navbar.jsp"/>

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
                <c:when test="${requestScope.prev_command eq 'get_order_list_enter_cabinet'}">
                    <li class="active">
                        <a href=#>
                            <c:out value="${get_orders}"/>
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="FrontController?command=get_order_list_enter_cabinet">

                            <c:out value="${get_orders}"/>
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>

            <c:choose>
                <c:when test="${requestScope.prev_command eq 'get_prescription_list_enter_cabinet'}">
                    <li class="active">
                        <a href=#>
                            <c:out value="${get_prescriptions}"/>
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="FrontController?command=get_prescription_list_enter_cabinet">

                            <c:out value="${get_prescriptions}"/>
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>

            <c:if test="${requestScope.prev_command eq 'get_drug_enter_cabinet'}">
                <li class="active">
                    <a href=#>
                        <c:out value="${create_order}"/>
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

        <c:if test="${requestScope.prev_command eq 'get_order_list_enter_cabinet'}">
            <jsp:include page="order_table.jsp"/>
        </c:if>


        <c:if test="${requestScope.problem_description eq 'wrong_data'}">
            <div class="row main">
                <label class="cols-sm-10 control-label"><c:out value="${order_problem}"/></label>
            </div>
        </c:if>

        <c:if test="${requestScope.prev_command eq 'get_drug_enter_cabinet'}">
            <div class="row main">
                <form class="form-horizontal" method="post" action="FrontController">

                    <input type="hidden" name="command" value="add_order_enter_cabinet"/>
                    <input type="hidden" name="drug_id" value="${requestScope.drug.id}"/>


                    <div class="form-group">
                        <label class="cols-sm-10 control-label"><c:out value="${drug_name}"/>: <c:out
                                value="${requestScope.drug.name}"/></label>
                    </div>

                    <div class="form-group">
                        <label class="cols-sm-10 control-label"><c:out value="${composition}"/>: <c:out
                                value="${requestScope.drug.composition}"/></label>

                    </div>

                    <div class="form-group">
                        <label class="cols-sm-10 control-label"><c:out value="${description}"/>: <c:out
                                value="${requestScope.drug.description}"/></label>

                    </div>

                    <div class="form-group">
                        <label class="cols-sm-10 control-label"><c:out value="${dosage}"/>: <c:out
                                value="${requestScope.drug.dosage}"/></label>
                    </div>

                    <div class=" form-group">
                        <label class="cols-sm-10 control-label"><c:out value="${amount}"/>: <c:out
                                value="${requestScope.drug.amount}"/></label>
                    </div>


                    <div class="form-group">
                        <label class="cols-sm-10 control-label"><c:out value="${number}"/>: <c:out
                                value="${requestScope.drug.number}"/></label>

                    </div>


                    <div class="form-group">
                        <label class="cols-sm-10 control-label"><c:out value="${price}"/>: <c:out
                                value="${requestScope.drug.price}"/></label>
                    </div>

                    <div class="form-group">
                        <div class="custom-control custom-checkbox mr-sm-2">
                            <div class="form-check">
                                <label class="custom-control-label"><c:out value="${need_prescription}"/></label>
                                <c:choose>
                                    <c:when test="${requestScope.drug.needPrescription eq true}">
                                        <input type="checkbox" class="custom-control-input" name="need_prescription"
                                               value="true" checked disabled>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="checkbox" class="custom-control-input" name="need_prescription"
                                               value="true" disabled>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="cols-sm-10 control-label"><c:out value="${count}"/> </label>
                        <div class="cols-sm-10">

                            <input type="text" class="form-control" name="drug_number"
                                   placeholder="${count}"/>
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
        <c:if test="${requestScope.prev_command eq 'get_prescription_list_enter_cabinet'}">
            <jsp:include page="prescription_table.jsp"/>
        </c:if>
    </div>


</div>
<script type="text/javascript" src="../../js/jquery-3.2.1.js"></script>
<script src="../../js/bootstrap.min.js" type="text/javascript"></script>
<script src="../../js/script.js" type="text/javascript"></script>
</body>
</html>
