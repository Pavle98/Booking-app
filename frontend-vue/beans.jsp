<%@page import="java.awt.DisplayMode"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	isELIgnored="false" pageEncoding="ISO-8859-1"%>

<%
	request.setAttribute("title", "Beans Demo");
	request.setAttribute("page_id", 1);
%>
<jsp:include page="/includes/header.jsp" />
<%!String message = "";
	boolean showForm = true;%>
<%
	if ("POST".equalsIgnoreCase(request.getMethod())) {
%>
<%-- page (pageContext), request (HttpServletRequest), session (HttpSession), application (ServletContext) --%>

<jsp:useBean id="beanLoginValidator"
	class="com.melardev.tutsservlet.beans.BeanLoginValidator">
	<jsp:setProperty name="beanLoginValidator" property="*" />
	<!-- The line above will auto set the values from the formm values if the form is username=admin&password=admin
		then username in the Bean will be admin, and the password field will be set to admin as well
		<jsp:setProperty name="beanLoginValidator" property="username" value='<%=request.getParameter("username")%>' />
		<jsp:setProperty name="beanLoginValidator" property="password" value='<%=request.getParameter("password")%>' />
		-->
	<jsp:setProperty name="beanLoginValidator" property="password"
		param="password" />
</jsp:useBean>


<%
	if (!beanLoginValidator.isValidUser()) {
			message = "Invalid credentials";
		} else {
			out.println("<h2>Welcome admin !</h2>");
			out.println("<div class='alert alert-success'>Yor aresuccessfully logged in</div>");
			showForm = false;
		}
	}
%>

<%
	if (showForm) {
%>

<%
	if (!message.isEmpty()) {
%>

<div class="alert alert-danger"><%=message%></div>
<%
	}
%>

<form role="form" method="POST">
	<div class="form-group">
		<label for="name">Username:</label> <input
			placeholder="Enter Username" class="form-control" type="text"
			name="username">
	</div>

	<div class="form-group">
		<label for="name">Password:</label> <input
			placeholder="Enter Password" class="form-control" type="password"
			name="password">
	</div>
	<button class="btn btn-default" type="submit" name="submit">Login</button>
</form>
<%
	}
%>

<jsp:useBean id="userValidator"
	class="com.melardev.tutsservlet.beans.BeanLoginValidator" />
<jsp:useBean id="userbean"
	class="com.melardev.tutsservlet.beans.BeanUser" scope="session" /><br>

<jsp:setProperty name="userValidator" property="username"
	value="melardev" />
<jsp:setProperty name="userValidator" property="password"
	value="password" />

<div class="panel panel-info">
	<div class="panel-heading">Results</div>
	<div class="panel-body">
		isValid :
		<%=userValidator.isValidUser()%>
		<br />
		username ${userValidator.username}
		<br />
		password
		<jsp:getProperty name="userValidator" property="password" />
		<br />
		isValid2 ${userValidator.validUser}
		<br />
		Default: ${user.name} pageScope, requestScope, sessionScope, applicationScope
		<br /><% %>
		Request: ${requestScope.user.name}
		<br />
		Page : ${pageScope.user.name}
		<br />
		Session scope: ${sessionScope.user.name}
		<br />
		<%
			out.println("Bean Session scope " + userbean.getName());
		%><br />
		Bean Session Scope:
		<jsp:getProperty name="userbean" property="name" /><br>
		<%
			out.println("Bean Session Scope " + userbean.getName());
		%>
	</div>
</div>

<jsp:include page="/includes/footer.jsp" />