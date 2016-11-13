<%@page import="entities.User,java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List,entities.Forum,java.util.ListIterator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<%
	User user = (User) session.getAttribute("user");
	if (user == null) {
		request.setAttribute("message", "danger_Please Login First !");
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
	ArrayList<String> quals = (ArrayList<String>) session.getAttribute("quals");
%>

<title><%=user.getUsername()%></title>
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
<!-- 						<div class="profile-usertitle-type">Moderator</div> -->
					</div>
					<!-- END SIDEBAR USER TITLE -->


				</div>
			</div>
			<div class="col-md-9">
				<div class="profile-content">

					<%
						List<Forum> allForum = (List<Forum>) request.getAttribute("allForums");
						ListIterator<Forum> iter = allForum.listIterator();
					%>

					<%
						while (iter.hasNext()) {
							Forum f = (Forum) iter.next();
					%>
					<div class="row">
						<div class="col-sm-3">
							<div class="well">
								<p><%=f.getCreatedByModerator_Username()%></p>	
								<!-- <img src="" class="img-circle" height="55" width="55" alt="started by ki photo"> -->
							</div>
						</div>
						<div class="col-sm-9">
							<div class="well">
								<a href="/SmartHealth/frm?fid=<%= f.getForumId()%>" ><p class="lead"><%=f.getTopic()%></p></a>
								<p class="panel-body"><%=f.getSummary()%></p>
							</div>
						</div>
					</div>
					<%
						}
					%>


				</div>
			</div>
		</div>
	</div>
	<br>
	<br>


</body>
</html>