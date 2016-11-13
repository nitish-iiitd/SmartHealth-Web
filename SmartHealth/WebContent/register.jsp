<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>SmartHealth - Register</title>
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
	**<jsp:include page="menu.jsp" />**
	<div class="container">

		<% 
		// For displaying an alert at top of the page
		if(request.getAttribute("message")!=null){
		String completemessage = request.getAttribute("message").toString();
		if(!completemessage.isEmpty())
		{
			String[] splitMessage = completemessage.split("_");
		%>
		<div class="alert alert-<%out.print(splitMessage[0]);%> fade in">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong><% out.print(splitMessage[0].toUpperCase() + "! : ");%></strong>
			<% out.print(splitMessage[1]);%>
		</div>
		<%
		}}
		%>

		<div class="row text-center">
			<div class="col-sm-3"></div>
			<div class="col-sm-6">
				<h3>Register</h3>
				<form class="form-horizontal" method="get" action="register">
					<div class="form-group">
						<label class="control-label col-sm-3" for="email">Username:</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="username"
								name="username" placeholder="Enter username">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3" for="pwd">Password:</label>
						<div class="col-sm-9">
							<input type="password" class="form-control" id="password"
								name="password" placeholder="Enter password">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3" for="emailp">Primary
							Email:</label>
						<div class="col-sm-9">
							<input type="email" class="form-control" id="emailp"
								name="emailp" placeholder="Enter primary email">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3" for="emails">Sec.
							Email:</label>
						<div class="col-sm-9">
							<input type="email" class="form-control" id="emails"
								name="emails" placeholder="Enter secondary email">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3">First Name:</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="fname" name="fname"
								placeholder="Enter first name">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3">Last Name:</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="lname" name="lname"
								placeholder="Enter last name">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3">About me:</label>
						<div class="col-sm-9">
							<textarea class="form-control" rows="5" id="about"
								name="about" placeholder="Enter something about yourself"></textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3">Photo URL 1:</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="photourl1"
								name="photourl1" placeholder="Enter url of the photo 1">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3">Photo URL 2:</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="photourl2"
								name="photourl2" placeholder="Enter url of the photo 2">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3">Photo URL 3:</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="photourl3"
								name="photourl3" placeholder="Enter url of the photo 3">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3">Street Number:</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="snumber"
								name="snumber" placeholder="Enter street number">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3">Street Name:</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="sname" name="sname"
								placeholder="Enter street name">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3" for="munic">Municipality:</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="munic" name="munic"
								placeholder="Enter major municipality">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3" for="district">District:</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="district"
								name="district" placeholder="Enter governing district">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3" for="parea">Postal
							Area:</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" id="parea" name="parea"
								placeholder="Enter postal area">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3" for="tuser">Type of
							User:</label>
						<div class="col-sm-9">
							<label class="radio-inline selected"> <input type="radio"
								name="usertype" value="nuser">Normal User
							</label> <label class="radio-inline"> <input type="radio"
								name="usertype" value="moderator">Moderator
							</label>
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<input type="submit"
								class="btn btn-lg btn-primary extra-mar fix-width" value="Register">
<!-- 								Register</input> -->
						</div>
					</div>
				</form>

			</div>
			<div class="col-sm-3"></div>
		</div>
	</div>

</body>
</html>

