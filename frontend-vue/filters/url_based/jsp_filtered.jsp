<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	isELIgnored="false" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" scope="request" value="Servlet based Filter Demo" />

<c:import url="/includes/header.jsp" />
This is what I got:${filter_attribute}
<br />
<%@include file="/filters/url_based/another.jsp"%>
<c:import url="/includes/footer.jsp" />

