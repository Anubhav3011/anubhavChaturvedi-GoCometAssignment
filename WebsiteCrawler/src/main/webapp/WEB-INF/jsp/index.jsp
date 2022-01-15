<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<title>Website Crawler</title>
</head>
<body>
	<div class="container">
		<br>
		<h3>
			Welcome to Website Crawler
			<c:choose>
				<c:when test="${LoggedInUser != null}">
					<p style="text-align: right;">
						Hi, ${LoggedInUser} &nbsp;&nbsp;&nbsp; <a href="/logout"
							class="btn btn-primary btn-sm mb-3 float-right"> Logout </a>
					</p>
				</c:when>
				<c:otherwise>
					<a href="/login" class="btn btn-primary btn-sm mb-3 float-right">
						Login </a>
				</c:otherwise>
			</c:choose>
		</h3>
		<hr>
		<a href="/medium" class="btn btn-primary btn-sm mb-3"> medium.com </a> <!-- <a
			href="/h2-console" class="btn btn-primary btn-sm mb-3 float-right">
			H2 - Console </a> -->
		<hr>
	</div>
</body>
</html>