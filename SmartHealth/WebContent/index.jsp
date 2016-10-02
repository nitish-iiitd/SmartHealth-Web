<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>SmartHealth</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="mycss.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="jumbotron text-center">
		<h1>SmartHealth</h1>
		<p>Let's do something good with social networking !</p>
	</div>

	<div class="container">
		<div class="row text-center">
			<div class="col-sm-4">
				<h3>Discuss Health Issues</h3>
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit...</p>
			</div>
			<div class="col-sm-4">
				<h3>Advice from Doctors</h3>
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit...</p>
			</div>
			<div class="col-sm-4">
				<h3>Maintain Your Health</h3>
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit...</p>
			</div>
		</div>
		<div class="container">
			<div class="row text-center">
				<div class="col-sm-3"></div>
				<div class="col-sm-3">
					<a href="login.jsp">
					<button type="button" class="btn btn-lg btn-primary extra-mar fix-width" onClick="location.href='/login.jsp';"/>Login</button>
					</a>
					
				</div>
				<div class="col-sm-3">
					<a href="register.jsp">
					<button type="button" class="btn btn-lg btn-success extra-mar fix-width">Register</button>
					</a>
				</div>
				<div class="col-sm-3"></div>
			</div>
		</div>
	</div>

</body>
</html>

