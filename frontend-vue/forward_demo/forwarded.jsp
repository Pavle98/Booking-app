<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Enumeration"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" scope="request" value="Forwarded Demo" />
<c:import url="/includes/header.jsp" />
You are learning : ${param.category}, you got stuck in <%=request.getParameter("tutorial")%>
<c:import url="/includes/footer.jsp" />