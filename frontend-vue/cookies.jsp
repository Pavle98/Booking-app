<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<form role="form" method="post">
	<div class="form-group">
		<label for="color">Choose color</label> <input id="color" type="color"
			name="color" value="<%=request.getAttribute("color")%>" />
		<br />
		<label>Current selected:<%=request.getAttribute("color")%></label>
	</div>
	<button type="submit" class="btn btn-info">Submit</button>
</form>
