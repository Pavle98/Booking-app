<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:scriptlet>request.setAttribute("title", "Exception Handler");</jsp:scriptlet>
<jsp:include page="/includes/header.jsp" />
<c:set var="exception"
	value="${requestScope['javax.servlet.error.exception']}" />

<div class="card text-center">
	<div class="card-header"><%=exception.getClass().getName()%></div>
	<div class="card-block">
		<h4 class="card-title">${exception.message}</h4>
		<p class="card-text">
			<c:forEach var="trace" items="${pageContext.exception.stackTrace}">
				<p>${trace}</p>
			</c:forEach>
		</p>

	</div>
	<div class="card-footer text-muted">
		<%=exception.getMessage()%>
	</div>
</div>

<jsp:include page="/includes/footer.jsp" />
