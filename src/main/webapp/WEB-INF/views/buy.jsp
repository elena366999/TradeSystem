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
<div class="container">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="post" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <h2>Admin Page ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a><br>
            <a href="${contextPath}/welcome">To shop main page</a><br>

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

				</tr>
		<tr>
			<td>${product.name}</td>
			<td>${product.description}</td>
			<td>${product.price}</td>
			<td><img src="data:image/jpeg;base64,${product.encode}" width="150" height="200"
                                                                        alt="product"/></td>

		</tr>
	</table>
</div>

<div>
<form method="POST" action="/addItemToOrder/${product.id}" class="form-signin">
  Quantity:<br>
  <input type="text" name="quantity" value="Quantity">
  <br>
 <input type="hidden" name="_csrf" value="${_csrf.token}" />
 <br>
  <input type="submit" value="Submit">
</form>

</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>