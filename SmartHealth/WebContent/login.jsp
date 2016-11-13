<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>SmartHealth - Login</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="bootstrap.min.css">
<link rel="stylesheet" href="css/mycss.css">
<link rel="stylesheet" href="css/loginform.css">
<script src="jquery-3.1.1.min.js"></script>
<script src="bootstrap.min.js"></script>
</head>
<body>
	**<jsp:include page="menu.jsp" />**
	<div class="container">

		<%
			// For displaying an alert at top of the page
			if (request.getAttribute("message") != null) {
				String completemessage = request.getAttribute("message").toString();
				if (!completemessage.isEmpty()) {
					String[] splitMessage = completemessage.split("_");
		%>
		<div class="alert alert-<%out.print(splitMessage[0]);%> fade in">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>
				<%
					out.print(splitMessage[0].toUpperCase() + "! : ");
				%>
			</strong>
			<%
				out.print(splitMessage[1]);
			%>
		</div>
		<%
			}
			}
		%>
		<div class="row text-center">
			<div class="col-sm-3"></div>
			<div class="col-sm-6">

<!-- 				<div class="modal" id="login-modal" tabindex="-1" role="dialog" -->
<!-- 					aria-labelledby="myModalLabel" aria-hidden="true" -->
<!-- 					style="display: none;"> -->
<!-- 					<div class="modal-dialog"> -->
						<div class="loginmodal-container">
							<h1>Login to Your Account</h1>
							<br>
							<form action="login" method="post">
								<input type="text" name="email" placeholder="Username"> <input
									type="password" name="password" placeholder="Password"> <input
									type="submit" name="login" class="login loginmodal-submit"
									value="Login">
							</form>

							<div class="login-help">
								<a href="register.jsp">Sign Up</a> 
							</div>
						</div>
<!-- 					</div> -->
<!-- 				</div> -->



<!-- 								<form class="form-horizontal" action="login" method="post"> -->

<!-- 									<div class="form-group"> -->
<!-- 										<label class="control-label col-sm-3" for="email">Primary -->
<!-- 											Email:</label> -->
<!-- 										<div class="col-sm-9"> -->
<!-- 											<input type="email" class="form-control" id="email" name="email" -->
<!-- 												placeholder="Enter primary email"> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 														<div class="form-group"> -->
<!-- 										<label class="control-label col-sm-3" for="pwd">Password:</label> -->
<!-- 										<div class="col-sm-9"> -->
<!-- 											<input type="password" class="form-control" id="password" name="password" -->
<!-- 												placeholder="Enter password"> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 									<div class="form-group"> -->
<!-- 										<div class="col-sm-offset-3 col-sm-9"> -->
<!-- 											<button type="submit" -->
<!-- 												class="btn btn-sm btn-primary extra-mar fix-width">Login</button> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 								</form> -->

			</div>
			<div class="col-sm-3"></div>
		</div>
	</div>

</body>
</html>

