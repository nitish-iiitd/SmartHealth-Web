<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>SmartHealth - Login</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="css/mycss.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="row text-center">
			<div class="col-sm-3"></div>
			<div class="col-sm-6">
				<h3>Login</h3>
				<form class="form-horizontal" action="login" method="post">

					<div class="form-group">
						<label class="control-label col-sm-3" for="email">Primary
							Email:</label>
						<div class="col-sm-9">
							<input type="email" class="form-control" id="email1" name="email"
								placeholder="Enter primary email">
						</div>
					</div>
										<div class="form-group">
						<label class="control-label col-sm-3" for="pwd">Password:</label>
						<div class="col-sm-9">
							<input type="password" class="form-control" id="password" name="password"
								placeholder="Enter password">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-3 col-sm-9">
							<button type="submit"
								class="btn btn-lg btn-primary extra-mar fix-width">Login</button>
						</div>
					</div>
				</form>

			</div>
			<div class="col-sm-3"></div>
		</div>
	</div>

</body>
</html>

