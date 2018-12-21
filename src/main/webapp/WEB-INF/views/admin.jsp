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

    <title>Admin</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<spring:theme code='styleSheet'/>" type="text/css"/>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

</head>

<body>
<div class="container">
    <a class="styled-link" href="?language=en">EN</a>
    <a class="styled-link" href="?language=ru">RU</a>
</div>

<div style="float:right;  margin-right: 2%;">
    <a class="regular-link" href="${contextPath}/welcome"><spring:message code="label.main"/></a><br>
</div>
<div class="container">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="post" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <h2><spring:message code="label.admin-page"/> | <a
                class="regular-link"
                onclick="document.forms['logoutForm'].submit()"><spring:message code="label.logout"/></a>
        </h2>
    </c:if>
</div>
<h2 align="center"><spring:message code="label.products"/></h2>

<div class="container">
    <table class="w3-table-all w3-centered w3-hoverable w3-card w3-padding" border="1">
        <tr>
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
                <td><a class="regular-link" href="${contextPath}/deleteProduct?id=${product.id}"><spring:message
                        code="label.delete"/></a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<br>

<form:form method="POST" action="/addProduct" modelAttribute="productForm"
           enctype="multipart/form-data" class="form-signin">
    <h2 class="form-signin-heading"><spring:message code="label.create-product"/></h2>
    <form:input path="" type="hidden" name="_csrf" value="${_csrf.token}"/>
    <div class="form-group ${status.error ? 'has-error' : ''}">
        <spring:message code="label.product-name"/>
        <form:input type="text" name="name" path="name" class="form-control" autofocus="true"/>
        <form:errors path="name"/>
    </div>

    <div class="form-group ${status.error ? 'has-error' : ''}">
        <spring:message code="label.product-description"/>
        <form:input type="text" name="description" path="description" class="form-control"/>
        <form:errors path="description"/>
    </div>

    <div class="form-group ${status.error ? 'has-error' : ''}">
        <th><spring:message code="label.product-price"/></th>
        <form:input type="text" name="price" path="price" class="form-control"
                    placeholder="0.0"/>
        <form:errors path="price"/>
    </div>

    <div class="form-group ${status.error ? 'has-error' : ''}">
        <input type="file" name="image" class="form-control"
               placeholder="Image"/>
    </div>
    <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="label.submit2"/></button>
</form:form>


<div align="center">
    <a class="regular-link" href="${contextPath}/showAllOrders"><spring:message code="label.orders"/></a>
</div>



<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>