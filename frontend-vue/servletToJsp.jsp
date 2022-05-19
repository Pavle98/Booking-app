<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	request.setAttribute("title", "JSP To Servlet");
%>
<%@include file="/includes/header.jsp"%>
<%
	// receiving Objects from Servlet
	Object message = request.getAttribute("message");
	if (message != null) {
%>
<h5><%=message%></h5>
<span>Did you see the above message? it comes from the servlet!!!</span>
<%
	}
%>
<br />
From Session: ${msgSession} <br />
App Context : ${msgAppContext} <br />

<jsp:include page="/includes/footer.jsp"></jsp:include>
