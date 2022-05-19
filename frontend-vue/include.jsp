<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	request.setAttribute("title", "JSP Include demo");
%>
<%@include file="/includes/header.jsp"%>
<span>Really useful feature, it is also available in other
	platforms I know of(PHP, Django, Flask, ASP.NET, etc.)</span>
<jsp:include page="/includes/footer.jsp"></jsp:include>
