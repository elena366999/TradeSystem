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
</head>

<body>
<div class="container">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="post" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <h2>Admin Page ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a>
        </h2>
    </c:if>
</div>


<div class="container">

    <form:form method="POST" action="/addItem?${_csrf.parameterName}=${_csrf.token}" modelAttribute="itemForm" class="form-signin">
        <h2 class="form-signin-heading">Create new item</h2>

        <spring:bind path="name">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="name" class="form-control" placeholder="Item name"
                            autofocus="true"></form:input>
                <form:errors path="name"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="description">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="description" class="form-control" placeholder="Sescription"></form:input>
                <form:errors path="description"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="price">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="price" class="form-control"
                            placeholder="Price"></form:input>
                <form:errors path="price"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="quantity">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="quantity" class="form-control"
                            placeholder="Quantity"></form:input>
                <form:errors path="quantity"></form:errors>
            </div>
        </spring:bind>


        <spring:bind path="image">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="file" path="image" class="form-control"
                            placeholder="Quantity"></form:input>
                <form:errors path="image"></form:errors>
            </div>
        </spring:bind>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </form:form>

    <div>
        <form method="POST" enctype="multipart/form-data" action="/addItem?${_csrf.parameterName}=${_csrf.token}">
            <table>
                <tr><td>File to upload:</td><td>
                    <input type="text" name="name" /></td></tr>
                <input type="text" name="description" /></td></tr>
                <input type="text" name="price" /></td></tr>
                <input type="text" name="quantity" /></td></tr><<br>>
                <input type="file" name="image" /></td></tr>
                <tr><td></td><td><input type="submit" value="Upload" /></td></tr>
            </table>
        </form>
    </div>

</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>