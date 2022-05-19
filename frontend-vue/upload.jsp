<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:scriptlet>request.setAttribute("title", "JSP Upload demo");</jsp:scriptlet>
<%@include file="/includes/header.jsp"%>

<form role="form" method="post" enctype="multipart/form-data" class="col-8">
	<div class="form-group">
		<input type="file" name="file" id="file" />
		<br />
	</div>
	<button type="submit" class="btn btn-primary">Upload</button>
</form>

<jsp:include page="/includes/footer.jsp"></jsp:include>
