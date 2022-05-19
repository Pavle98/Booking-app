<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	request.setAttribute("title", "JSP To Servlet");
%>
<%@include file="/includes/header.jsp"%>

The means to communicate a JSP with a Servlet and in the opposite
direction is by using common objects in both, in JSP we have the so
called implicit Objects which are session, request,application,
which are HttpSession, HttpServletRequest, ServletContext respectively.
all of them have the setAttribute where you put
key/value pairs, so if you store a key value pair and then forward the
request to a Servlet, the HttpServlet object in the Servlet is the same
as the one in this JSP so it will contain that same key/value pair, that
is it, our Servlet accesses data set from within JSP, please note the
difference between include and forward!. forward is like include + exit,
whereas include includes a file and continues execution until the end of
the current file does this make sense?
<%
	request.setAttribute("requestVar", "fromJsp");
	session.setAttribute("sessionVar", "fromJsp");
	application.setAttribute("appVar", "fromJsp");
%>

<jsp:include page="/jsp-to-servlet" />
<%-- <jsp:forward page="/login" /> --%>
<!-- This is not ignored by compiler so any jsp tags here get executed -->
<jsp:include page="/includes/footer.jsp"></jsp:include>
