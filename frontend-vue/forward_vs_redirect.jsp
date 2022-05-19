<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	request.setAttribute("title", "JSP To Servlet");
%>
<%@include file="/includes/header.jsp"%>

<div>
<p>
To send objects from Servlet we use one of the Implicit Objects that are available from JSP, which are:
all of them have a method called setAttribute('key', valueObject), you use it, then since you have
access to that same object in JSP, you call getAttribute('key') and you will get the valueObject, finishing by
accessing the object sent from Servlet !!;
Extra of this tutorial : NOTE the difference between forward() and response.redirect() do you note that
redirect() does not take request and response as parameters? it makes sense, the user will make another request
so the context will change completely, another Servlet instance will be created and another HttpServletRequest will be created, etc
so NEVER send Objects from Servlet to JSP if you are using response.redirect() because you will never receive them actually,
this can get even worst, see the form example. It gets processed differently !!!</p>
</div>

<%
	if (request.getMethod().equalsIgnoreCase("post"))
		out.println("<h2>JSP has processed the form</h2> <br/>");
%>

<a href="<%=application.getContextPath()%>/forward-vs-redirect?action=redirect"><button class="btn btn-info">Redirect</button></a>
<a href="<%=application.getContextPath()%>/forward-vs-redirect?action=forward"><button class="btn btn-warning">Forward</button></a>

<%
	Object action = request.getParameter("action");
	if (action != null) {
%>
<br /><br />
<form method="POST">
	<button value="submit" type="submit" name="btn" class="btn btn-default">Common Click Me</button>
</form>

<%
	}
%>

<jsp:include page="/includes/footer.jsp"></jsp:include>
