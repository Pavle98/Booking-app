<%-- JSP Directives import, define description of jsp, define own HTML tags, include other JSP files. --%>
<%@ page import="java.util.GregorianCalendar"%>
<%@ page import="java.util.Calendar"%>
<%@ page info="This JSP is meant to explain the syntax in JSP files"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	isELIgnored="false" pageEncoding="ISO-8859-1"%>

<%
	request.setAttribute("title", "JSP Tags");
	request.setAttribute("page_id", 1);
%>
<jsp:include page="/includes/header.jsp" />

<div class="card card-info">
	<div class="card-header bg-info">Results</div>
	<div class="card-block">
		<%-- Scriptlets --%>
		<%
			int myIntVariable = 0; // this is thread safe, each thread(request) will have its own copy
			out.println("Your IP address is " + request.getRemoteAddr());
		%>
		<br />
		<%
			if (now.getHours() > 22)
				out.println("go sleep");
			else
				out.println("keep working hard");
		%>
		<br />
		<jsp:scriptlet>out.println("some code from a scriptlet tag");</jsp:scriptlet>
		<br />
		<%-- JSP Declarations to declare instance variables and methods --%>
		<%!Date now = new Date(System.currentTimeMillis());
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");

	String getTime() {
		return sdf.format(now);
	};%>
		<jsp:declaration></jsp:declaration>

		<br />
		<%-- Expressions --%>
		Now it is
		<%=getTime()%>

		<%-- JSP Comments --%>
		<%-- This comment is ignored by the Framework, it will not be sent to client --%>
		<!-- This is a comment in HTML(client side) hence, it will be sent to the client, but then ignored by the client's browser -->

		<%-- JSP Actions --%>
		<jsp:useBean id="beanRandomNumber"
			class="com.melardev.tutsservlet.beans.BeanRandomNumber">
			<jsp:setProperty name="beanRandomNumber" property="time"
				value="<%=getTime()%>" />
		</jsp:useBean>

		<p>
			The time field from the bean is:<jsp:getProperty property="time"
				name="beanRandomNumber" />
		</p>
		<%="Random Number" + " " + beanRandomNumber.getRandomNumber() + " " + beanRandomNumber.getTime()%>
		<br />
		<jsp:text>Some text here</jsp:text>
		<br />
		<%-- Predefined Objects --%>
		<%
			Integer count = (Integer) application.getAttribute("counter");
			if (count != null) {
				out.println("Count since the server start:" + count);
			} else
				count = 0;
			count++;
			application.setAttribute("counter", count);
		%>
		<br />

		<%-- if other request executes the line 34 before the below, then the below will print 2 instead of 3--%>

		<br />
		<span>Expression Language: ${beanRandomNumber.time}</span>
	</div>
</div>
<jsp:include page="/includes/footer.jsp" />