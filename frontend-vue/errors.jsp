<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	isELIgnored="false" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="title" scope="request" value="Servlet based Filter Demo" />
<c:import url="/includes/header.jsp" />
Something really weird happend sorry
<div class="alert alert-danger" role="alert">${errors}</div>
<c:import url="/includes/footer.jsp" />
