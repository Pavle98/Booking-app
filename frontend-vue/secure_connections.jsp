<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% request.setAttribute("title", "JSP Include demo"); %>
<%@include file="/includes/header.jsp"%>
<div>
We are going to create a self signed certificate just for our testing environment.
All the framework code that deals with communicating with servers using secure connections
is provided by JSSE (Java Secure Socket Extension) API
Create a keystore file
<span>keytool -genkey -alias tomcat -keyalg RSA</span>

</div>
<jsp:include page="/includes/footer.jsp"></jsp:include>
