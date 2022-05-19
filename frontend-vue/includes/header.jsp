<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title><%=request.getAttribute("title")%></title>
<link rel="stylesheet"
	href="<%=getServletContext().getContextPath()%>/css/font-awesome.css" />
<link href="<%=getServletContext().getContextPath()%>/css/bootstrap.css"
	rel="stylesheet" type="text/css" />
<link href="<%=getServletContext().getContextPath()%>/css/main.css"
	rel="stylesheet" type="text/css" />

</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
			<a class="navbar-brand" href="${servletContext.contextPath}">Servlet/JSP
				Tutorials</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarCollapse" aria-controls="navbarCollapse"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarCollapse">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item active"><a class="nav-link" href="${servletContext.contextPath}">Home
							<span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item"><a class="nav-link" target="_blank"
						href="https://www.youtube.com/MelarDev">Youtube Channel</a></li>
					<li class="nav-item"><a class="nav-link" target="_blank"
						href="http://www.melardev.com">Blog</a></li>
				</ul>
				<div class="inline-group">
					<a href="" class="btn btn-social-icon btn-google-plus"
						target="_blank"><i class="fa fa-youtube"></i></a> <a
						href="https://www.instagram.com/melar_dev/" target="_blank"
						class="btn btn-social-icon btn-instagram"><i
						class="fa fa-instagram"></i></a> <a target="_blank"
						href="https://twitter.com/melardev"
						class="btn btn-social-icon btn-twitter"><i
						class="fa fa-twitter"></i></a> <a href="http://melardev.com" target="_blank"><img
						src="<%=getServletContext().getContextPath()%>/img/logo.png"
						height="34px" width="34px"
						onmouseover="this.src='<%=getServletContext().getContextPath()%>/img/logo_inverse.png'"
						onmouseout="<%=getServletContext().getContextPath()%>/img/logo.png" /></a>
				</div>
			</div>
		</nav>
	</header>

	<main role="main" class="container text-center">
	<div class="mt-3">
		<h1><%=request.getAttribute("title")%></h1>
	</div>