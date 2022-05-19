<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	isELIgnored="false" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="title" scope="request" value="Servlet based Filter Demo" />
<%
	request.setAttribute("title", "JSP Include demo");
	pageContext.setAttribute("page_id", 2);
%>
<c:import url="/includes/header.jsp" />

This is what I got:${my_attribute}
<c:import url="/includes/footer.jsp" />
