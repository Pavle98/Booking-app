<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% request.setAttribute("title", "JSP Include demo"); %>
<%@include file="/includes/header.jsp"%>
<div>
Invalid username and/or password, please try
<a href='<%= response.encodeURL("index.jsp") %>'>again</a>
</div>
<jsp:include page="/includes/footer.jsp"></jsp:include>

