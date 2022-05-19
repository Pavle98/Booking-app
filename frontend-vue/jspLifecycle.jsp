<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:scriptlet>request.setAttribute("title", "JspLifecycle");</jsp:scriptlet>>
<jsp:include page="/includes/header.jsp" />
<%
	if (this instanceof HttpServlet)
		out.println("Yes, Jsp is a Servlet");
%>

<%!public void jspInit() {
		System.out.println(getServletContext().getRealPath("/"));
		System.out.println("jspInit called()");
	}%>
<%!public void jspDestroy() {
	
		System.out.println("jspDestroy called()");
	}

	public void jspService() {
		System.out.println("jspService called()");
	}%>
<%

%>
<jsp:directive.include file="/includes/footer.jsp" />