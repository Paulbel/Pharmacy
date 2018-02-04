<?xml version="1.0" encoding="UTF-8"?>
<%@page contentType="application/xml" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<data>
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
                <td>
                    <a href="FrontController?command="><c:out value="${item.manufacturer.name}"/></a>
                </td>
                <td>
                    <a href="FrontController?command="><c:out value="${item.manufacturer.country}"/></a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <ul class="pagination">
        <c:choose>
            <c:when test="${requestScope.currentPage != 1}">
                <li>
                    <a href="FrontController?command=find_drug_enter_cabinet&page=${requestScope.currentPage - 1}&drug_name=${requestScope.drug_name}">
                        «
                    </a>
                </li>
            </c:when>
            <c:otherwise>
                <li class="disabled">
                    <a href="#">«</a>
                </li>
            </c:otherwise>
        </c:choose>
        <c:forEach begin="1" end="${requestScope.noOfPages}" var="i">
            <c:choose>
                <c:when test="${requestScope.currentPage eq i}">
                    <li class="active">
                        <a href="#">
                            <c:out value="${i}"/>
                            <span
                                    class="sr-only">(current)
                            </span>
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="FrontController?command=find_drug_enter_cabinet&page=${i}&drug_name=${requestScope.drug_name}">
                            <c:out
                                    value="${i}"/></a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:choose>
            <c:when test="${requestScope.currentPage lt requestScope.noOfPages}">
                <li>
                    <a href="FrontController?command=find_drug_enter_cabinet&page=${requestScope.currentPage + 1}&drug_name=${requestScope.drug_name}">
                        »
                    </a>
                </li>
            </c:when>
            <c:otherwise>
                <li class="disabled">
                    <a href="#">»</a>
                </li>
            </c:otherwise>
        </c:choose>
    </ul>
</data>