<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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

    <title>Log in with your account</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<spring:theme code='styleSheet'/>" type="text/css"/>

    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

</head>

<body>
<div class="container">
    <a class="styled-link" href="?language=en">EN</a>
    <a class="styled-link" href="?language=ru">RU</a>
</div>

<div class="container">

    <form method="POST" action="/login" class="form-signin">
        <h2 class="form-heading"><spring:message code="label.login"/></h2>

        <div class="form-group ${error != null ? 'has-error' : ''}">
            <span>${message}</span>
            <input name="username" type="text" class="form-control" placeholder=
            <spring:message code="label.username"/>
                    autofocus="true"/>
            <input name="password" type="password" class="form-control" placeholder=
            <spring:message code="label.password"/>
                    <span>${error}</span>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message
                    code="label.login2"/></button>
            <h4 class="text-center"><a href="${contextPath}/registration"><spring:message
                    code="label.registration"/></a></h4>
        </div>

    </form>

</div>
<!-- /container -->

<div class="footer" align="center">
    <spring:message code="label.theme"/> <a class="styled-link2" href="?siteTheme=regular"><spring:message
        code="label.theme-regular"/> </a> <spring:message code="label.or"/> <a class="styled-link3"
                                                                               href="?siteTheme=black"> <spring:message
        code="label.theme-black"/></a>

</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>