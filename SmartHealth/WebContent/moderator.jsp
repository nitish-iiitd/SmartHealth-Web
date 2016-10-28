<%@page import="entities.User,java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<%
	User user = (User) session.getAttribute("user");
	if (user == null) {
		request.setAttribute("message", "danger_Please Login First !");
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
	ArrayList<String> quals = (ArrayList<String>)session.getAttribute("quals");
%>

<title><%=user.getUsername()%></title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="css/mycss.css">
<link rel="stylesheet" href="css/profile.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
			<strong> <%
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
		<div class="row profile">
			<div class="col-md-3">
				<div class="profile-sidebar">
					<!-- SIDEBAR USERPIC -->
					<div class="profile-userpic">
						<img src="<%=user.getUrl1()%>" class="img-responsive" alt="">
					</div>
					<!-- END SIDEBAR USERPIC -->
					<!-- SIDEBAR USER TITLE -->
					<div class="profile-usertitle">
						<div class="profile-usertitle-name">
							<%
								out.print(user.getFname() + " " + user.getLname());
							%>
						</div>
						<div class="profile-usertitle-type">Moderator</div>
					</div>
					<!-- END SIDEBAR USER TITLE -->
					<!-- SIDEBAR BUTTONS -->

					<!-- END SIDEBAR BUTTONS -->
					<!-- SIDEBAR MENU -->
					<div class="profile-usermenu">
						<ul class="nav nav-pills nav-stacked">
							<li class="active"><a data-toggle="pill" href="#overview">
									<i class="glyphicon glyphicon-home"></i> Overview
							</a></li>
							<li><a data-toggle="pill" href="#addqual"> <i
									class="glyphicon glyphicon-heart-empty"></i> Add Qualification
							</a></li>
							<li><a data-toggle="pill" href="#createforum"
								target="_blank"> <i class="glyphicon glyphicon-user"></i>
									Create Forum
							</a></li>

						</ul>
						<form class="form-horizontal" action="forum" method="post">
							<div class="form-group">
								<div class="col-sm-offset-3 col-sm-6 col-sm-offset-3">
									<button type="submit" class="btn btn-md btn-primary">Forums</button>
								</div>
							</div>
						</form>

						<form class="form-horizontal" action="logout" method="post">
							<div class="form-group">
								<div class="col-sm-offset-3 col-sm-6 col-sm-offset-3">
									<button type="submit" class="btn btn-md btn-primary">Logout</button>
								</div>
							</div>
						</form>
					</div>
					<!-- END MENU -->
				</div>
			</div>
			<div class="col-md-9">
				<div class="profile-content">

					<div class="tab-content">
						<div id="overview" class="tab-pane fade in active">
							<h3>Overview</h3>

							<p>
								Firstname :
								<%=user.getFname()%></p>
							<p>
								Lastname :
								<%=user.getLname()%></p>
							<p>
								Email :
								<%=user.getEmailp()%></p>
							<p>
								Street Name :
								<%=user.getStreetName()%></p>
							<p>
								Street Number :
								<%=user.getStreetNumber()%></p>
						</div>
						<div id="addqual" class="tab-pane fade">
						<h3>Qualifications</h3>
						<%
								if (quals != null && quals.size() > 0) {
									for (int i = 0; i < quals.size(); i++) {
							%>
							<div class="panel panel-default">
								<div class="panel-body">
									<p><%= quals.get(i).toUpperCase() %></p>
								
								</div>
							</div>
							<%
								}
								} else if (quals != null && quals.size() == 0) {
							%>
							<div class="panel panel-default">
								<div class="panel-body">No Friends.</div>
							</div>
							<%
								}
							%>
							
							
							<h3>Add Qualification</h3>
							
							<form class="form-horizontal" method="get" action="storeQual">
								<div class="form-group">
									<label class="control-label col-sm-3">Qualification:</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="fname"
											name="qualifications" placeholder="Enter comma separated qualifications"
											value="">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-3 col-sm-9">
										<input type="submit"
											class="btn btn-md btn-primary extra-mar fix-width"
											value="Add">
										<!-- 								Register</input> -->
									</div>
								</div>


							</form>

						</div>
						<div id="createforum" class="tab-pane fade">
							<h3>Create Forum</h3>
							<p></p>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
	<br>
	<br>


</body>
</html>