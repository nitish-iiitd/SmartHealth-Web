<%@page import="entities.User,entities.Datum,java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<% 
User profile = (User)request.getAttribute("profile");
ArrayList<Datum> healthdata = (ArrayList<Datum>) session.getAttribute("profilehealthdata");
%>

<title><%=profile.getUsername()%></title>
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

	<div class="container">
    <div class="row profile">
		<div class="col-md-3">
			<div class="profile-sidebar">
				<!-- SIDEBAR USERPIC -->
				<div class="profile-userpic">
					<img src="<%=profile.getUrl1() %>" class="img-responsive" alt="">
				</div>
				<!-- END SIDEBAR USERPIC -->
				<!-- SIDEBAR USER TITLE -->
				<div class="profile-usertitle">
					<div class="profile-usertitle-name">
						<% out.print(profile.getFname()+" "+profile.getLname()); %>
					</div>
					<div class="profile-usertitle-type">
						User
					</div>
				</div>
				<!-- END SIDEBAR USER TITLE -->
				<!-- SIDEBAR BUTTONS -->
<!-- 				<div class="profile-userbuttons"> -->
<!-- 					<button type="button" class="btn btn-success btn-sm">Add Friend</button> -->
<!-- 				</div> -->
				<!-- END SIDEBAR BUTTONS -->
				<!-- SIDEBAR MENU -->
				<div class="profile-usermenu">
						<ul class="nav nav-pills nav-stacked">
							<li class="active"><a data-toggle="pill" href="#overview">
									<i class="glyphicon glyphicon-home"></i> Overview
							</a></li>
							<li><a data-toggle="pill" href="#healthdata"> <i
									class="glyphicon glyphicon-heart-empty"></i> See Health Data
							</a></li>


						</ul>
		
					</div>
				<!-- END MENU -->
			</div>
		</div>
		<div class="col-md-9">
            <div class="profile-content">
			   <div class="tab-content">
						<div id="overview" class="tab-pane fade in active">
							<h3>Overview</h3>
							
							<p>Firstname : <%=profile.getFname() %></p>
							<p>Lastname : <%=profile.getLname() %></p>
							<p>Email : <%=profile.getEmailp() %></p>
							<p>Street Name : <%= profile.getStreetName() %></p>
							<p>Street Number : <%= profile.getStreetNumber() %></p>
						</div>
						<div id="healthdata" class="tab-pane fade ">
							<div class="text-center">
								<h3>Health Data</h3>
								<hr>
								<h4>Stored Health Data</h4>
								<%
									for (int i = 0; i < healthdata.size(); i++) {
								%>
								<p><%=healthdata.get(i).getWhenSaved()%>
									:
									<%=healthdata.get(i).getPropertyID()%>
									:
									<%=healthdata.get(i).getValue()%></p>
								<%
									}
								%>
								<hr>
								
							</div>
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