<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% request.setAttribute("title", "JSP Include demo"); %>
<%@include file="/includes/header.jsp"%>
<div>You are authenticated</div>
<jsp:include page="/includes/footer.jsp"></jsp:include>
