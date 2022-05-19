<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%-- It is meant to write java code with tags instead of scriptlets, improving readibility --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- Both are equivalent --%>
<%
	request.setAttribute("title", "This is ignored");
%>

<c:set var="title" scope="request" value="JSTL Demo" />

<%-- Remove attribute from request
<c:remove var="title" scope="request" />
--%>

<%-- c:import url='' is the equivalent to jsp:include page='' --%>
<c:import url="/includes/header.jsp" />

<p>
	To use JSTL we have add the dependency ant to use the taglib directive,
	in this Demo we are going to take a look at the JSTL Core Library which
	provides tags for tasks such as loop, if/else statements, There are
	other JSTL Libraries for SQL, XML, Formatting, etc. Everything that Can
	be done with JSTL can be done with scriptlets! Documentation may be
	found at: <a href=""></a>
</p>


<c:set var="output" value="" />
<c:set var="displayForm" value="${true}" />
<c:set var="class_msg" value="" />

<c:if
	test="${\"POST\".equalsIgnoreCase(pageContext.request.method) && pageContext.request.getParameter('submit') != null}">

	<jsp:useBean id="beanLoginValidator"
		class="com.melardev.tutsservlet.beans.BeanLoginValidator">
		<jsp:setProperty name="beanLoginValidator" property="*" />

		<%--
		
		The line above will auto set the values from the form values; if the form is username=admin&password=admin
		then username in the Bean will be admin, and the password field will be set to admin as well
		<jsp:setProperty name="beanLoginValidator" property="username" value='<%=request.getParameter("username")%>' />
		<jsp:setProperty name="beanLoginValidator" property="password" value='<%=request.getParameter("password")%>' />
		
		Security Considerations: autobinding is dangerous, what if the user guesses our Java Bean has a field called
		admin?
		
 --%>
	</jsp:useBean>
	<c:choose>
		<c:when test="${!beanLoginValidator.isValidUser()}">
			<%-- Invalid credentials --%>
			<c:set var="class_msg" value="alert alert-danger" />
			<c:set var="output" value="Invalid credentials" />
		</c:when>
		<c:otherwise>
			<c:choose>
				<%-- If admin --%>
				<c:when test="${beanLoginValidator.admin}">
					<c:set var="class_msg" value="alert alert-success" />
					<c:set var="output" value="Welcome admin !" />
					<c:set var="displayForm" value="${false}" />
				</c:when>
				<c:otherwise>
					<%-- If not admin --%>
					<c:set var="class_msg" value="alert alert-success" />
					<c:set var="output" value="Welcome non-admin user!" />
					<c:set var="displayForm" value="${false}" />
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>
</c:if>


<%-- Check error message. If it is set, then display it --%>
<c:if test="${!output.isEmpty()}">
	<div class='${class_msg}'>
		<c:out value="${output}"></c:out>
	</div>
</c:if>

<c:if test="${displayForm}">
	<form method="post" role="form">
		<div class="form-group">
			<label for="username">Username: </label> <input class="form-control"
				id="username" type="text" name="username">
		</div>

		<div class="form-group">
			<label for="username">Password: </label><input id="password"
				class="form-control" type="password" name="password">
		</div>

		<button type="submit" name="submit" class="btn btn-default">Submit</button>
	</form>
</c:if>

<%
	ArrayList<String> lastTutorials = new ArrayList<String>();
	pageContext.setAttribute("channelPlaylists", "Android; Firebase, Retrofit; JSP/Servlet");

	lastTutorials.add("JSP Tags");
	lastTutorials.add("JSP Declarations");
	lastTutorials.add("JSP Include/Forward/Redirect");
	lastTutorials.add("JSP Implicit Objects");
	lastTutorials.add("JSP using Beans and sessions");
	pageContext.setAttribute("lastTutorials", lastTutorials);
%>
<br/>
<br />
<div class="card card-info">
	<div class="card-header">Some loops with jstl</div>

	<ul class="list-group">
		<c:forEach items="${lastTutorials}" var="tutorial">
			<li class="list-group-item"><c:out value="${tutorial}" /></li>
		</c:forEach>

		<c:forTokens items="${channelPlaylists}" var="playlist" delims=",;">
			<li class="list-group-item">${playlist}</li>
		</c:forTokens>
	</ul>
</div>

<jsp:useBean id="beanRandomNumber"
	class="com.melardev.tutsservlet.beans.BeanRandomNumber"></jsp:useBean>
<c:out
	value="This is an Expression language ${beanRandomNumber.getRandomNumber()}" />
<br/>
<fmt:formatDate value="${now}" pattern="dd-MMM-yy"/>
<fmt:formatNumber type="number" maxFractionDigits="3" value="992.21222" />
<fmt:setLocale value="en_US"/> 
<fmt:formatNumber value="320" type="currency"/>

<c:import url="/includes/footer.jsp" />

