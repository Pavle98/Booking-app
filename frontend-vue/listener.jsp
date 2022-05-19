<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	isELIgnored="false" pageEncoding="ISO-8859-1"%>
<%
	request.setAttribute("title", "JSP Include demo");
	pageContext.setAttribute("page_id", 2);
%>

<%@include file="/includes/header.jsp"%>

request.getAttribute("my_attribute") returns
<%=request.getAttribute("my_attribute")%>
and using Expression language. $\&gt;applicationScope.my_attribute &lt;
${pageScope.my_attribute} ${requestScope.my_attribute} ${sessionScope. }
${applicationSCope. }
<jsp:include page="/includes/footer.jsp"></jsp:include>
