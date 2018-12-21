<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Buy</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<spring:theme code='styleSheet'/>" type="text/css"/>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>

<body>
<div class="container">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="post" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <h2><a href="${contextPath}/welcome"><spring:message code="label.main"/></a><br>
        </h2>
    </c:if>
</div>

<h2 align="center"><spring:message code="label.about"/></h2><br>

<div class="container">
    <table align="center" style="width: auto;" class="w3-table-all w3-centered w3-hoverable w3-card w3-padding"
           border="1">
        <tr>
            <td>${product.name}<br>
                <p>------------</p>
                ${product.description}<br>
                <p>------------</p>
                ${product.price}</td>
        <tr>
            <td><img src="data:image/jpeg;base64,${product.encode}" width="150" height="200"
                     alt="product"/></td>
        </tr>
    </table>
</div><br>

<div align="center">
    <form method="POST" action="/addItemToOrder/${product.id}" class="form-signin">
        <spring:message code="label.quantity"/><br>
        <input type="text" name="quantity" placeholder="1">
        <br>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <br>
        <input type="submit" value=<spring:message
                code="label.submit3"/>>
    </form>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>