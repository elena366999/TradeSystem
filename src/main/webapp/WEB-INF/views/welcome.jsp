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

    <title>Welcome</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2>Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a>
        <br>   <a href="${contextPath}/admin">To admin page (available for admins only)</a>
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
  <td><a href="${contextPath}/buyProduct?id=${product.id}">Buy</a></td>


		</tr>
		</c:forEach>
	</table>
</div>

<br>
<div class="w3-container w3-padding" >
<display:table name="products" pagesize="3" requestURI="" class="w3-table-all w3-centered w3-hoverable w3-card w3-padding" >
  <display:column property="name" title="name"/>
  <display:column property="description" sortable="true" />
  <display:column property="price" />
  <display:column property="image" />
</display:table>
</div>




<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>