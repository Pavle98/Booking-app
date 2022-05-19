<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" scope="request" value="Forwarder Demo" />
<c:import url="/includes/header.jsp" />
We don't care about this content, it is a forward, not an include, the guy who is being sent is
the value of page attribute from the forward JSP Action.
<jsp:forward page="/forward_demo/forwarded.jsp">
<jsp:param name="tutorial" value="forward lesson"></jsp:param>
<jsp:param name="category" value="jsp"/>
</jsp:forward>
<%-- Another way to include files --%>
<jsp:directive.include file="/includes/footer.jsp"/>