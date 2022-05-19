<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:scriptlet>request.setAttribute("title", "Tomcat Conncetion pool");</jsp:scriptlet>
<jsp:include page="/includes/header.jsp" />

<c:choose>
	<c:when test="${users != null}">
		<c:forEach items="${users}" var="user">
<b>First name</b>: ${user.firstName}<br>
<b>Last name</b>: ${user.lastName} <br>
<b>User name</b>: ${user.userName} <br>
			<br>
			<br>
			
		</c:forEach>
		<a
				href="${pageContext.servletContext.contextPath}/db?action=register">Register User</a><br>
	</c:when>
	<c:otherwise>
		<c:if test="${errors}">
			<div class="alrt aler-danger">${errors}</div>
		</c:if>
		<form role="form" method="post" class="form-horizontal">
			<div class="form-group">
				<label for="first_name">First Name</label> <input type="text"
					class="control-label col-sm2" id="first_name" name="first_name"
					autocomplete="off" placeholder="Enter your first name">
			</div>

			<div class="form-group">
				<label for="last_name">Last Name</label> <input type="text"
					name="last_name" class="control-label col-sm2" id="last_name"
					autocomplete="off" placeholder="Enter your last name">
			</div>

			<div class="form-group">
				<label for="user_name">User Name</label> <input type="text"
					class="control-label col-sm2" id="user_name" name="username"
					autocomplete="off" placeholder="Enter your username">
			</div>
			<button type="submit" class="btn btn-primary">Submit</button>
			<a href="${pageContext.servletContext.contextPath}/db?action=list">List
				Users</a>
		</form>
	</c:otherwise>
</c:choose>
<jsp:directive.include file="/includes/footer.jsp" />