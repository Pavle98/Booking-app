<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	isELIgnored="false" pageEncoding="ISO-8859-1"%>

<%
	request.setAttribute("title", "Expression Language");
%>
<%@include file="/includes/header.jsp"%>
<table cellspacing="5" cellpadding="5" border="l">
	<tr>
		<td align="right">First name:</td>
		<p>I am accessing the User Bean, but ... where did it find it? I
			have not say anything ... Wow this is magic</p>
		<span>page -> request -> session -> application</span>
		<br>
		<%-- pageScope, requestScope, sessionScope, applicationScope --%>
		requestScope.user.username: ${requestScope.user.username}
		<br>
		userSession[username]: ${userSession["user_session"]}
		<br/>
		applicationScope.user: ${applicationScope.user}
		<br>
		We can also work with [] operator so User["username"] is
		${user["username"]}

		<p>
			HTTP request method: pageContext.request.method gives
			${pageContext.request.method}
			<br>
			HTTP response type: ${pageContext.response.contentType}
			<br>
			HTTP session ID: ${pageContext.session.id}
			<br>
			HTTP contextPath: $ {pageContext. servletContext. contextPath}
			<br>
		</p>

		<h2>Operators</h2>

		<h2>Implicit Objects</h2>
		${header}
	</tr>
</table>

<jsp:include page="/includes/footer.jsp"></jsp:include>