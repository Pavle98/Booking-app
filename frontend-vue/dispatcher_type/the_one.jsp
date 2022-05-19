<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"
%>
<% request.setAttribute("title", "Request Type"); %>
<jsp:include page="/includes/header.jsp"/>
<%
    out.println("The DispatcherType on the_one.jsp " + request.getDispatcherType().toString() + "<br>");
	if(true)
		throw new RuntimeException();
%>
<%--<jsp:include page="the_other.jsp"/>--%>
<jsp:forward page="the_other.jsp" />
<jsp:include page="/includes/footer.jsp"/>