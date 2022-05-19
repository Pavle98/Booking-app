<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	request.setAttribute("title", "JSP Implicit Objects");
%>
<%@include file="/includes/header.jsp"%>

<div class="card card-info">
	<div class="card-header">Results</div>
	<div class="panel-block">
		From JSP we have access to multiple Objects, this is
		dependency-injection like, we don't create those objects, instead they
		are injected by the JSP Engine, and are available out of the box,
		these objects are:
		<ul>
		
			<li><b>request</b>: This is the HttpServletRequest object
				associated with the request, example usage, getting the headers<br />
				<%
					Enumeration<String> headers = request.getHeaderNames();
					while (headers.hasMoreElements()) {
						String name = headers.nextElement();
						out.println(name + ": " + request.getHeader(name) + "<br />");
					}
				%>
				<hr /></li>
			<li><b>response</b> it is an HttpServletResponse object, it
				represents the response, example usage would be to set content-type
				header with setContentType, can also use setHeader('Content-Type',
				'text/html); <%
				response.setContentType("text/html");
			%></li>
			<li>out It is a <b>PrintWriter</b> object used to send text to
				the client, this one can also be retrieved with
				response.getWriter(); if you want to output binary data then use
				response.getOutputStream() returned Object
			</li>
			<%
				out.println("this is a text sent from out object");
			%>
			<li><b>session</b> It is an HttpSession object, it stores
				key/value pairs, see JSP Sessions tutorial, example usage: <%="Creation Time" + session.getCreationTime() + "Last accessed " + session.getLastAccessedTime()%></li>
			<li>application It is a ServletContext object <%
				
			%></li>
			<li>config It is a <b>ServletConfig</b> object, example usage <%="Servlet name : " + config.getServletName()
					+ " and yeah, JSP Engine creates a Servlet in the fly when a JSP is requested"%>
			</li>
			<li><b>pageContext</b> Context of this page(jsp), it has some
				resources that we may need such as JspWriter <%
				pageContext.getOut().println(
						"this message is printing accessing the JspWriter reference stored in pageContext");
			%></li>

			<li>page It is the same as the this keyword, and is used to call
				the methods defined by the translated servlet class.</li>

			<li><b>Exception</b> contains exception related data(other tutorial)</li>
		</ul>
	</div>
</div>
<jsp:include page="/includes/footer.jsp"></jsp:include>
