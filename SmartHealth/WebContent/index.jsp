<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>SmartHealth</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="bootstrap.min.css">
<link rel="stylesheet" href="css/mycss.css">
<link rel="stylesheet" href="css/loginform.css">
<link rel="stylesheet" href="css/profile.css">
<script src="jquery-3.1.1.min.js"></script>
<script src="bootstrap.min.js"></script>
<style>
body {
	background-image: url("images/back.jpg");
}
.col-sm-4
{
    background-color: #fff;
    background-color: rgba(255,255,255,0.7);
}

</style>
</head>
<body>



	<div class="container">
		<div class="jumbotron text-center">
			<h1>SmartHealth</h1>
			<p>Let's do something good with social networking !</p>
		</div>
		<div class="row text-center">
			<div class="col-sm-4">
				<h3>Discuss Health Issues</h3>
				<p></p>
			</div>
			<div class="col-sm-4">
				<h3>Advice from Doctors</h3>
				<p></p>
			</div>
			<div class="col-sm-4">
				<h3>Maintain Your Health</h3>
				<p></p>
			</div>
		</div>
		<div class="container">
			<div class="row text-center">
				<div class="col-sm-3"></div>
				<div class="col-sm-3">
					<a href="login.jsp">
						<button type="button"
							class="btn btn-sm btn-primary extra-mar fix-width"
							onClick="location.href='/login.jsp';" />Login
						</button>
					</a>

				</div>
				<div class="col-sm-3">
					<a href="register.jsp">
						<button type="button"
							class="btn btn-sm btn-success extra-mar fix-width">Register</button>
					</a>
				</div>
				<div class="col-sm-3"></div>
			</div>
		</div>
	</div>

</body>
</html>

