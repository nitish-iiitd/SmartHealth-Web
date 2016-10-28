<%@page import="entities.User,entities.Datum,java.util.*"%>
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
	ArrayList<Datum> healthdata = (ArrayList<Datum>) session.getAttribute("healthdata");
	ArrayList<User> friends = (ArrayList<User>) request.getAttribute("friends");
	ArrayList<String> friendrequests = (ArrayList<String>) session.getAttribute("friendrequests");
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
						<%
							if (user.getUrl1().equals("")) {
						%>
						<img src="images/profile-default.png" class="img-responsive"
							alt="">
						<%
							} else {
						%>
						<img src="<%=user.getUrl1()%>" class="img-responsive" alt="">
						<%
							}
						%>
					</div>
					<!-- END SIDEBAR USERPIC -->
					<!-- SIDEBAR USER TITLE -->
					<div class="profile-usertitle">
						<div class="profile-usertitle-name">
							<%
								out.print(user.getFname() + " " + user.getLname());
							%>
						</div>
						<div class="profile-usertitle-type">User</div>
					</div>
					<!-- END SIDEBAR USER TITLE -->
					<!-- SIDEBAR BUTTONS -->
					<div class="profile-userbuttons">
						<!-- 					<button type="button" class="btn btn-success btn-sm">Add Friend</button> -->
					</div>
					<!-- END SIDEBAR BUTTONS -->
					<!-- SIDEBAR MENU -->
					<div class="profile-usermenu">
						<ul class="nav nav-pills nav-stacked">
							<%
								String active = "class=\"active\"";
								String overviewact = "";
								String healthdataact = "";
								String updatedetailsact = "";
								String friendsact = "";
								String friendrequestsact = "";
								String forumact = "";
								if (friends != null) {
									friendsact = active;
								} else {
									overviewact = active;
								}
							%>
							<li <%=overviewact%>><a data-toggle="pill" href="#overview">
									<i class="glyphicon glyphicon-home"></i> Overview
							</a></li>
							<li <%=healthdataact%>><a data-toggle="pill"
								href="#healthdata"> <i
									class="glyphicon glyphicon-heart-empty"></i> Health Data
							</a></li>
							<li <%=updatedetailsact%>><a data-toggle="pill"
								href="#updatedetails"> <i
									class="glyphicon glyphicon-heart-empty"></i> Update Details
							</a></li>
							<li <%=friendsact%>><a href="friends"> <i
									class="glyphicon glyphicon-heart-empty"></i> Friends
							</a></li>
							<li <%=friendrequestsact%>><a data-toggle="pill"
								href="#friendrequests"> <i
									class="glyphicon glyphicon-heart-empty"></i> Friends Requests
							</a></li>
							<li <%=forumact%>><a data-toggle="pill" href="#"> <i
									class="glyphicon glyphicon-heart-empty"></i> Forums
							</a></li>
							<li><a data-toggle="pill" href="#sendrequest"> <i
									class="glyphicon glyphicon-heart-empty"></i> Send Friend
									Request
							</a></li>

						</ul>
					</div>
					<!-- END MENU -->
				</div>
			</div>
			<div class="col-md-9">
				<div class="profile-content">
					<div class="tab-content">
						<%
							String tabactive = " in active ";
							String overviewtabact = "";
							String healthdatatabact = "";
							String updatedetailstabact = "";
							String friendstabact = "";
							String friendrequeststabact = "";
							String forumtabact = "";
							if (friends != null) {
								friendstabact = tabactive;
							} else {
								overviewtabact = tabactive;
							}
						%>

						<div id="overview" class="tab-pane fade <%=overviewtabact%>">
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
						<div id="healthdata" class="tab-pane fade <%=healthdatatabact%>">
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
								<h4>Add new Health Data</h4>
							</div>

							<form class="form-horizontal" action="health" method="post">

								<div class="form-group">
									<label class="control-label col-sm-3">Property:</label>
									<div class="col-sm-9">
										<label class="radio-inline selected"> <input
											type="radio" name="property" value="distance">Distance
											Run
										</label> <label class="radio-inline"> <input type="radio"
											name="property" value="calories">Calories Burnt
										</label> <label class="radio-inline"> <input type="radio"
											name="property" value="bp">Blood Pressure
										</label>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-3">Value:</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="propertyvalue"
											name="propertyvalue" placeholder="Enter Property Value">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-3 col-sm-9">
										<button type="submit"
											class="btn btn-lg btn-primary extra-mar fix-width">Add</button>
									</div>
								</div>
							</form>
						</div>
						<div id="updatedetails"
							class="tab-pane fade <%=updatedetailstabact%>">
							<h3>Update Details</h3>
							<form class="form-horizontal" method="get" action="updatedetails">
								<div class="form-group">
									<label class="control-label col-sm-3" for="emails">Sec.
										Email:</label>
									<div class="col-sm-9">
										<input type="email" class="form-control" id="emails"
											name="emails" placeholder="Enter secondary email"
											value="<%=user.getEmails()%>">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-3">First Name:</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="fname"
											name="fname" placeholder="Enter first name"
											value="<%=user.getFname()%>">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-3">Last Name:</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="lname"
											name="lname" placeholder="Enter last name"
											value="<%=user.getLname()%>">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-3">About me:</label>
									<div class="col-sm-9">
										<textarea class="form-control" rows="5" id="about"
											name="about" placeholder="Enter something about yourself""><%=user.getAbout()%></textarea>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-3">Photo URL 1:</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="photourl1"
											name="photourl1" placeholder="Enter url of the photo 1"
											value="<%=user.getUrl1()%>">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-3">Photo URL 2:</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="photourl2"
											name="photourl2" placeholder="Enter url of the photo 2"
											value="<%=user.getUrl2()%>">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-3">Photo URL 3:</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="photourl3"
											name="photourl3" placeholder="Enter url of the photo 3"
											value="<%=user.getUrl3()%>">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-3">Street Number:</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="snumber"
											name="snumber" placeholder="Enter street number"
											value="<%=user.getStreetNumber()%>">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-3">Street Name:</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="sname"
											name="sname" placeholder="Enter street name"
											value="<%=user.getStreetName()%>">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-3" for="munic">Municipality:</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="munic"
											name="munic" placeholder="Enter major municipality"
											value="<%=user.getMajorMunicipality()%>">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-3" for="district">District:</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="district"
											name="district" placeholder="Enter governing district"
											value="<%=user.getGoverningDistrict()%>">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-3" for="parea">Postal
										Area:</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="parea"
											name="parea" placeholder="Enter postal area"
											value="<%=user.getPostalArea()%>">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-3 col-sm-9">
										<input type="submit"
											class="btn btn-md btn-primary extra-mar fix-width"
											value="Update">
										<!-- 								Register</input> -->
									</div>
								</div>
							</form>

						</div>
						<div id="friends" class="tab-pane fade <%=friendstabact%> ">
							<h3>Friends</h3>
							<%
								if (friends != null && friends.size() > 0) {
									for (int i = 0; i < friends.size(); i++) {
							%>
							<div class="panel panel-default">
								<div class="panel-body">
									<a href="profile?profilename=<%=friends.get(i).getUsername()%>"><%=friends.get(i).getFname() + " " + friends.get(i).getLname()%>										
									</a> 
									<a href="removeFriend?removename=<%=friends.get(i).getUsername()%>">
										<button type="button"
											class="btn pull-right btn-sm btn-danger extra-mar-sm fix-width">Remove</button>
									</a>
								</div>
							</div>
							<%
								}
								} else if (friends != null && friends.size() == 0) {
							%>
							<div class="panel panel-default">
								<div class="panel-body">No Friends.</div>
							</div>
							<%
								}
							%>
						</div>
						<div id="friendrequests"
							class="tab-pane fade <%=friendrequeststabact%>">
							<h3>Friend Requests</h3>
							<%
								if (friendrequests != null && friendrequests.size() > 0) {
									for (int i = 0; i < friendrequests.size(); i++) {
							%>
							<div class="panel panel-default">
								<div class="panel-body">

									<%=friendrequests.get(i)%>


									<a href="rejectRequest?rejectname=<%=friendrequests.get(i)%>">
										<!-- <input type="hidden" name="rejectname" value="<%=friendrequests.get(i)%>">  -->

										<button type="button"
											class="btn pull-right btn-sm btn-danger extra-mar-sm fix-width">Reject</button>
									</a> <a href="acceptRequest?acceptname=<%=friendrequests.get(i)%>">
										<!-- <input type="hidden" name="acceptname" value="<%=friendrequests.get(i)%>"> -->

										<button type="button"
											class="btn pull-right btn-sm btn-success extra-mar-sm fix-width">Accept</button>
									</a>

								</div>
							</div>
							<%
								}
								} else {
							%>
							<div class="panel panel-default">
								<div class="panel-body">No Friend Requests.</div>
							</div>
							<%
								}
							%>
						</div>
						<div id="sendrequest" class="tab-pane fade">
							<h3>Send Friend Request</h3>
							<form class="form-horizontal" action="sendRequest" method="post">
								<div class="form-group">
									<label class="control-label col-sm-3">Enter username:</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id="requested"
											name="requested" placeholder="Enter username">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-3 col-sm-9">
										<button type="submit" class="btn btn-md btn-primary extra-mar">Send
											Friend Request</button>
									</div>
								</div>
							</form>

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