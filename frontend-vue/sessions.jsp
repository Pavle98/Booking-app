<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	isELIgnored="false" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:set var="title" scope="request" value="Servlet based Filter Demo" />
<c:import url="/includes/header.jsp" />

<c:choose>
	<c:when test="${username == null}">
		<form method="post" role="form">
			<div class="form-group">
				<label for="username">Username: </label> <input class="form-control"
					id="username" type="text" name="username">
			</div>

			<div class="form-group">
				<label for="username">Password: </label><input id="password"
					class="form-control" type="password" name="password">
			</div>

			<button type="submit" name="submit" class="btn btn-info">Login</button>
		</form>
	</c:when>
	<c:otherwise>
	<div class="alert alert-success" role="alert">
        <strong>You are signed in ${username}</strong>
    </div>
    <a href="${pageContext.request.servletContext.contextPath}/logout">Logout</a>
	</c:otherwise>
</c:choose>
<c:import url="/includes/footer.jsp" />