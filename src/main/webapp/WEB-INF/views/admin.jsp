<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
     <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>

<a href="${contextPath}/showAllOrders"><spring:message code="label.orders"/></a>

<div class="container">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="post" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <h2>Admin Page ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a><br>
            <a href="${contextPath}/welcome">To shop main page</a><br>
                        <a href="${contextPath}/admin">To admin panel main page</a>

        </h2>
    </c:if>
</div>

</div>
 <div class="w3-container w3-padding">
	<table class="w3-table-all w3-centered w3-hoverable w3-card w3-padding" border="1">
		<tr class="w3-teal">
			<th>Name</th>
			<th>Description</th>
			<th>Price</th>
			<th>Image</th>
			<th>Action</th>

				</tr>
		<c:forEach items="${products}" var="product" >
		<tr>
			<td>${product.name}</td>
			<td>${product.description}</td>
			<td>${product.price}</td>
			<td><img src="data:image/jpeg;base64,${product.encode}" width="150" height="200"
                                                                        alt="item"/></td>

			<td><a href="${contextPath}/deleteProduct?id=${product.id}">Delete product</a></td>
		</tr>
		</c:forEach>
	</table>
</div>

<h6>This one</h6>
    <div class="container">

    <form:form method="POST" action="/addProduct?${_csrf.parameterName}=${_csrf.token}" modelAttribute="productForm" enctype="multipart/form-data"  class="form-signin">
            <h2 class="form-signin-heading">Create new item</h2>

                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" name="name"  path="name" class="form-control" placeholder="Item name"
                                autofocus="true"></form:input>
                    <form:errors path="name"></form:errors>
                </div>

                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" name="description"  path="description" class="form-control" placeholder="Description"></form:input>
                    <form:errors path="description"></form:errors>
                </div>

                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" name="price" path="price" class="form-control"
                                placeholder="0.0"></form:input>
                    <form:errors path="price"></form:errors>
                </div>

            <div class="form-group ${status.error ? 'has-error' : ''}">
                    <input type="file" name="image" class="form-control"
                                placeholder="Image"></input>

                </div>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
        </form:form>
    </div>

</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>