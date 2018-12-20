<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="<spring:theme code='styleSheet'/>" type="text/css"/>

    <title>Orders</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2><spring:message code="label.welcome"/> ${pageContext.request.userPrincipal.name} | <a
                onclick="document.forms['logoutForm'].submit()"><spring:message
                code="label.logout"/></a>
            <br> <a href="${contextPath}/admin"><spring:message
                    code="label.admin-page"/></a>
        </h2>

    </c:if>

</div>

</div>
<div class="w3-container w3-padding">
    <table class="w3-table-all w3-centered w3-hoverable w3-card w3-padding" border="1">
        <tr class="w3-teal">
            <th><spring:message code="label.order-id"/></th>
            <th><spring:message code="label.status"/></th>
            <th><spring:message code="label.username"/></th>
        </tr>
        <c:forEach items="${orders}" var="order">
            <tr>
                <td>${order.id}</td>
                <td>${order.orderStatus}</td>
                <td>${order.user.username}</td>
            </tr>
        </c:forEach>
    </table>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>