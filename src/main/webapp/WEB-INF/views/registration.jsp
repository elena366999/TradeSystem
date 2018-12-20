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

    <title>Create an account</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<spring:theme code='styleSheet'/>" type="text/css"/>

    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

</head>

<body>

<div class="container">

    <form:form method="POST" modelAttribute="userForm" class="form-signin">
        <h2 class="form-signin-heading"><spring:message code="label.account"/></h2>
        <spring:bind path="username">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <spring:message code="label.username"/>
                <form:input type="text" path="username" class="form-control"
                            autofocus="true"></form:input>
                <form:errors path="username"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="password">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <spring:message code="label.password"/>
                <form:input type="password" path="password" class="form-control"></form:input>
                <form:errors path="password"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="confirmPassword">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <spring:message code="label.confirm"/>
                <form:input type="password" path="confirmPassword" class="form-control"/>
                <form:errors path="confirmPassword"/>
            </div>
        </spring:bind>

        <spring:bind path="role">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:select path="role" class="styled-select slate">
                    <option value="" disabled selected hidden><spring:message code="label.role"/></option>
                    <c:forEach var="r" items="${roles}">
                        <form:option value="${r}" label="${r.value}"/>
                    </c:forEach>
                </form:select>
                <form:errors path="role"/>
            </div>
        </spring:bind>

        <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="label.submit"/>
        </button>
    </form:form>

</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>