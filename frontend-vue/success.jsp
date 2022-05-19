<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:scriptlet>request.setAttribute("title", "JSP Upload demo");</jsp:scriptlet>
<%@include file="/includes/header.jsp"%>

<div class="alert alert-success">
Task completed
</div>
<jsp:include page="/includes/footer.jsp"></jsp:include>
