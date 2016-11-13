<%@page import="entities.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%
	User user = (User) session.getAttribute("user");
%>
<title>menu</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="bootstrap.min.css">
<link rel="stylesheet" href="css/mycss.css">
<link rel="stylesheet" href="css/loginform.css">
<link rel="stylesheet" href="css/profile.css">
<script src="jquery-3.1.1.min.js"></script>
<script src="bootstrap.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">SmartHealth</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="/SmartHealth">Home</a></li>
				<% if (user != null) {
				
				if(user.getType()==101 || user.getType()==102 || user.getType()==103){
				%>

				<li><a href="nuser.jsp">My Account</a></li>
				<% 
				}else if(user.getType()==200){
				%>
				<li><a href="moderator.jsp">My Account</a></li>
				<%} %>
				<li><a href="forum">Forums</a></li>
				<%}else{ %>

				<% }%>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<% if (user != null) {%>

				<li><a href="logout">Logout</a></li>
				<li><a href="quituser">Quit User</a></li>
				<%}else{ %>
				<li><a href="register.jsp">Sign up</a></li>
				<li><a href="login.jsp">Login</a></li>
				<% }%>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
	</nav>
	<br>
	<br>
</body>
</html>