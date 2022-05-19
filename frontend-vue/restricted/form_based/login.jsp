<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<% request.setAttribute("title", "JSP Include demo"); %>

<jsp:include page="/includes/header.jsp"></jsp:include>
<p>
the username input text field has to be named j_username, the one for the password j_password
the action would be j_security_check
</p>
<form method="POST"
	action='<%= response.encodeURL("j_security_check") %>'>
	<table border="0" cellspacing="5">
		<tr>
			<th align="right">Username:</th>
			<td align="left"><input type="text" name="j_username"></td>
		</tr>
		<tr>
			<th align="right">Password:</th>
			<td align="left"><input type="password" name="j_password"></td>
		</tr>
		<tr>
			<td align="right"><input type="submit" value="Log In"></td>
			<td align="left"><input type="reset"></td>
		</tr>
	</table>
</form>
<jsp:include page="/includes/footer.jsp"></jsp:include>
