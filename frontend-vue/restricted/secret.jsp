<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% request.setAttribute("title", "JSP Include demo"); %>
<%@include file="/includes/header.jsp"%>
<div>
This tutorial is about the so called container-manager security or
container-manager-authentication, to implement that we have to write a
"security constraint" in web.xml file, the user has to identify himself before
the server grants access, go ahead and look at web.xml for security-constraint tag.

You can choose between two types of authentication: Basic and Digest.
- Digest authentication is more secure since it hashes the credentials before sending them
which avoids to send the credentials in plain text over the scary internet...
- Basic
- Form-based, you have to write the form to be shown and used to login, it is not encrypted, unless
we are using a secure transport (SSL).

To restrict access we have to define:

- define the url pattern to be restricted	
- define the allowed security roles (list of roles available must be in tomcat-users.xml)
- define the auth-method, possible values BASIC, DIGEST, FORM, CLIENT-CERT

If you use FORM based then you create a form for login and a view for login failed, the login form has
to follow some rules
the username input text field has to be named j_username, the one for the password j_password
the action would be j_security_check
</div>
<jsp:include page="/includes/footer.jsp"></jsp:include>
