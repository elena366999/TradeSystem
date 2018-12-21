<%@ page contentType="text/html; charset=UTF-8"
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

    <title>Welcome</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<spring:theme code='styleSheet'/>" type="text/css"/>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>

<div class="container">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <h2><spring:message code="label.welcome"/> ${pageContext.request.userPrincipal.name} | <a
                onclick="document.forms['logoutForm'].submit()"><spring:message code="label.logout"/></a>
            <br> <a href="${contextPath}/admin"></a>
        </h2>
    </c:if>
</div>

<br>

</div>
<div class="container">
    <table class="w3-table-all w3-centered w3-hoverable w3-card w3-padding" border="1">
        <tr class="w3-blue">
            <th><spring:message code="label.product-name"/></th>
            <th><spring:message code="label.product-description"/></th>
            <th><spring:message code="label.product-price"/></th>
            <th><spring:message code="label.product-image"/></th>
            <th><spring:message code="label.product-action"/></th>
        </tr>
        <c:forEach items="${products}" var="product">
            <tr>
                <td>${product.name}</td>
                <td>${product.description}</td>
                <td>${product.price}</td>
                <td><img src="data:image/jpeg;base64,${product.encode}" width="150" height="200"
                         alt="item"/></td>
                <td><a href="${contextPath}/buyProduct?id=${product.id}"><spring:message code="label.buy"/></a></td>
            </tr>
        </c:forEach>
    </table>
</div>

<div class="footer" align="center">
    <a href="${contextPath}/admin"><spring:message code="label.to-admin-page"/></a>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>