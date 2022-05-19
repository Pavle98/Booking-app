<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:scriptlet>request.setAttribute("title", "Jsp Comment");</jsp:scriptlet>
<jsp:include page="/includes/header.jsp" />
Never shown
<!-- The JSP code is not skipt by the compiler
<%--<jsp:forward page="/includes/footer.jsp" />--%> 
-->
<%-- Another way to include files --%>
<jsp:directive.include file="/includes/footer.jsp" />