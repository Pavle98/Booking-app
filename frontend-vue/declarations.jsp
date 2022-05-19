<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" scope="request" value="Servlet based Filter Demo" />
<jsp:include page="/includes/header.jsp" />

<%
	int myIntVariable = 100;
%>
<%!int myIntVariable = 2; // this is an instance variable, Web container creates an instance
	// of this JSP, from there, each request(thread) will get access to the same variable.%>

<% this.myIntVariable = 2; %>
<%
	this.myIntVariable += 2;
%>

<br />
this.myIntVariable
<%=this.myIntVariable%>
<br />
myIntVariable(jsp Expression)
<%=myIntVariable%>
<br>
this.myIntVariable(jsp Expression)
<%=this.myIntVariable%>
<br>
<%
	out.println("myIntvariable: " + myIntVariable);
%>
<jsp:include page="/includes/footer.jsp" />