<%@page import="entities.CombinedForumDetail,entities.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List,entities.Forum,java.util.ListIterator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<%
	//NUser user = (NUser) session.getAttribute("user");
	User user = (User) session.getAttribute("user");
	if (user == null) {
		request.setAttribute("message", "danger_Please Login First !");
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
%>


<title>SingleForum</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="bootstrap.min.css">
<link rel="stylesheet" href="css/mycss.css">
<link rel="stylesheet" href="css/loginform.css">
<link rel="stylesheet" href="css/profile.css">
<script src="jquery-3.1.1.min.js"></script>
<script src="bootstrap.min.js"></script>
<script>
	//saveData(url,addComment);
	//saveData(url,addRating);

	function saveData() {

		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				//  document.getElementById("demo").innerHTML = this.responseText;
			/*  	var para = document.createElement("div");
				var node = document.createTextNode("This is new.");
				para.appendChild(node);
				

				var element = document.getElementById("content");
				var child = document.getElementById("topPost");
				element.insertBefore(para,child);
 
				alert("Comment Saved" + this.responseText); */
				 location.reload(); 
			}
		};

		//var comm = document.forms["myForms"]["topic"].value;
		//alert(comm);

		var fid = document.forms["myForms"]["myFid"].value;
		var x = document.forms["myForms"]["topic"].value;
		var photoLocation = document.forms["myForms"]["photoLocation"].value;
		var linkLocation = document.forms["myForms"]["linkLocation"].value;
		var videoLocation = document.forms["myForms"]["videoLocation"].value;
		//alert("Validate Form"+fid+x+photoLocation+linkLocation+videoLocation);

		var parameters = "comment=" + x + "&fid=" + fid + "&photoLocation="
				+ photoLocation + "&linkLocation=" + linkLocation
				+ "&videoLocation=" + videoLocation;
		//alert(parameters);

		//alert("http://localhost:8080/SmartHealth/storeComment?"+parameters);

		xhttp.open("POST", "http://localhost:8080/SmartHealth/storeComment?"
				+ parameters, true);
		//  xmlhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
		xhttp.send(null);

	}

	
	function saveComment(p){
	//	alert("Save Comment");
		
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				 location.reload();
				//alert("Comment Saved!!");
			}
		};
		var myComtform2 = "myComnt";
		var myComtform = myComtform2.concat(p);
		//alert(temp.concat(p));
		
		var temp="myFid";
		var fid =document.forms[myComtform][temp.concat(p)].value;
		
		var temp="myComment";
		var x = document.forms[myComtform][temp.concat(p)].value;
		
		if(x=="")
			{
			alert('Field Empty');
			}
		else
			{
		
		var temp="photoLocation";
		temp.concat(p);

		var photoLocation = document.forms[myComtform][temp.concat(p)].value;

		var temp="linkLocation";
		temp.concat(p);

		var linkLocation = document.forms[myComtform][temp.concat(p)].value;

		var temp="videoLocation";
		temp.concat(p);

		var videoLocation = document.forms[myComtform][temp.concat(p)].value;
		

		var temp="myPostUserName";
		temp.concat(p);

		var myPostUserName = document.forms[myComtform][temp.concat(p)].value;
		

		var temp="myPostTime";
		temp.concat(p);

		var myPostTime= document.forms[myComtform][temp.concat(p)].value;
		

		//alert("Coment From: "+x+photoLocation+myPostUserName+myPostTime+linkLocation+videoLocation);

		 var parameters = "comment=" + x + "&fid=" + fid + "&photoLocation="
				+ photoLocation + "&linkLocation=" + linkLocation
				+ "&videoLocation=" + videoLocation+"&myPostUserName="+myPostUserName+"&myPostTime="+myPostTime;
		//alert(parameters);

		//alert("http://localhost:8080/SmartHealth/savecomment?"+parameters);

		xhttp.open("POST", "http://localhost:8080/SmartHealth/savecomment?"
				+ parameters, true);
		//  xmlhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
		xhttp.send(null);
			}
	}
	function validateForm() {

		var x = document.forms["myForms"]["topic"].value;
		var photoLocation = document.forms["myForms"]["photoLocation"].value;
		var linkLocation = document.forms["myForms"]["linkLocation"].value;
		var videoLocation = document.forms["myForms"]["videoLocation"].value;
		if(x=="")
			{
			alert('Field Empty!');
			
			}
		else
			{
		// Enable it for Forum values authentication
		/* 	//alert("hi");
		if (x == null || x == "" || photoLocationx == "" || photoLocation == null || linkLocation == "" || linkLocation == null || videoLocation == "" || videoLocation == null) {
			alert("First Add Complete Details!!");
			//return false;
		} else {
		 */
		//	alert("saved Data");
		saveData();

		return true;
		}
	}
		function saveRating(p){
			
			
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					alert(this.responseText);
					location.reload();
					alert(this.responseText);
					
				}
			};
			//alert(p);
			temp = "custom-select";
			temp2 = temp.concat(p);
			var e = document.getElementById(temp2);
			var strUser = e.options[e.selectedIndex].value;
			//alert(strUser);
			
			var myComtform2 = "myComnt";
			var myComtform = myComtform2.concat(p);
			//alert(temp.concat(p));
						

	
			var temp="myPostUserName";
			//temp.concat(p);

			var myPostUserName = document.getElementById(temp.concat(p)).value;
			//alert(myPostUserName);

			var temp="myPostTime";
			//temp.concat(p);

			var myPostTime=  document.getElementById(temp.concat(p)).value;
			
			 var parameters = "rating=" + strUser + "&myPostUserName="+myPostUserName+"&myPostTime="+myPostTime;
			//alert(parameters);

			//alert("http://localhost:8080/SmartHealth/saveRating?"+parameters);

			xhttp.open("POST", "http://localhost:8080/SmartHealth/saveRating?"
					+ parameters, true);
			//  xmlhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
			xhttp.send(null); 

			
		}
		
		
		function ratingOver(p){
			
			//alert(p);
			var i = 0;
			
			while(i<=p){
				temp = "star-";
				id=temp.concat(p);
				var x = document.getElementById(id);
				x.style.color = '#ffff00';
				i++;
			}
			
			return true;
		}
		
		function resetStar(){
			get=document.getElementsByClassName("star");
			for(i=0; i<get.length; i++){
			   	get[i].sytle.color='#000000';
				// get[i].removeEventListener("mouseover", yourFunction, false);
			   }
		}
	
</script>
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
				<div class="profile-content" id="content">
					<!--  Reading Data -->
					<%
						ListIterator<CombinedForumDetail> iter = null;
						List<CombinedForumDetail> allPosts = null;
						Forum currentForum = (Forum) request.getAttribute("currentForum");
						allPosts = (List<CombinedForumDetail>) request.getAttribute("Posts");
						if (allPosts != null) {
							iter = allPosts.listIterator();
						} else {
							System.out.println("Post is not empty");
						}
						if (currentForum != null) {
							System.out.println("Current Forum is not null");
					%>
					<!-- Displaying Forum -->

					<div class="row">
						<div class="col-sm-3">
							<div class="well">
								<p><%=currentForum.getCreatedByModerator_Username()%></p>
								<!-- <img src="" class="img-circle" height="55" width="55" alt="started by ki photo"> -->
							</div>
						</div>
						<div class="col-sm-9">
							<div class="well">
								<a href="/SmartHealth/frm?fid=<%=currentForum.getForumId()%>">
									<p class="lead"><%=currentForum.getTopic()%></p>
								</a>
								<p class="panel-body"><%=currentForum.getSummary()%></p>
							</div>
						</div>
					</div>


					<!-- Post Forum -->
					<div class="row">
						<div class="col-sm-3"></div>
						<div class="col-sm-9">
							<div class="well">

								<form class="form-horizontal" name="myForms">
									<!-- method="POST" onsubmit="return validateForm()"> -->
									<div class="form-group">
										<label class="control-label col-sm-3">Post*:</label>
										<div class="col-sm-9">
											<input type="hidden" id="myFid"
												value="<%=currentForum.getForumId()%>"> <input
												type="text" class="form-control" id="topic" name="comment"
												placeholder="New Post... ">
										</div>
									</div>

									<div class="form-group">
										<label class="control-label col-sm-3">Image URL:</label>
										<div class="col-sm-9">
											<input type="text" class="form-control" id="photoLocation"
												name="comment" placeholder="Image Link Please...">
										</div>
									</div>

									<div class="form-group">
										<label class="control-label col-sm-3">Link URL:</label>
										<div class="col-sm-9">
											<input type="text" class="form-control" id="linkLocation"
												name="comment" placeholder="Link Please...">
										</div>
									</div>

									<div class="form-group">
										<label class="control-label col-sm-3">Video URL:</label>
										<div class="col-sm-9">
											<input type="text" class="form-control" id="videoLocation"
												name="comment" placeholder="Video Link Please...">
										</div>
									</div>

									<div class="form-group">
										<div class="col-sm-offset-3 col-sm-7">
											<input type="button"
												class="btn btn-md btn-primary extra-mar fix-width"
												value="Add Post" onclick="validateForm()">
										</div>
									</div>
								</form>

								<!-- End of Add New Post -->

							</div>
						</div>
					</div>
					<!-- End of row -->


					<%
						} else {
					%>
					<!-- Forum Not present Error!  -->
					<div class="row">
						<div class="col-sm-9">
							<div class="well">
								<p>Forum not present!!</p>
								<!-- <img src="" class="img-circle" height="55" width="55" alt="started by ki photo"> -->
							</div>
						</div>
					</div>

					<%
						}
					%>
					<h3>Posts</h3>
					
					<!-- Adding the Posts -->
					<%
						int i;
						if (allPosts != null) {
							i = 1;

							System.out.println("Size of All Post: " + allPosts.size());
							while (iter.hasNext()) {
								//System.out.println("New Post!!");
								CombinedForumDetail f = (CombinedForumDetail) iter.next();
					%>
<hr>
					<hr>
					<div class="row" id="topPost">
						<div class="col-sm-3"></div>
						<div class="col-sm-6">

							<div class="well">
								<p><%=f.getPost().getTextEntry()%></p>
								<%
									if (f.getRat() != 0) {
								%>
								<p class="panel-body"><%=f.getRat()%></p>
								<%
									} else {
								%>
								Final Rating: 0.0
								<%
									}
								%>

								<!-- <img src="" class="img-circle" height="55" width="55" alt="started by ki photo"> -->
							</div>
						</div>
						<div class="col-sm-3">
							<div class="final_rating">
								<!-- <span id="star-0" class="star" onmouseover="ratingOver(0)"
									mouseout="resetStar()" onclick="saveRating(0)">★</span> <span
									id="star-1" class="star" onmouseover="ratingOver(1)"
									mouseout="resetStar()" onclick="saveRating(1)">★</span> <span
									id="star-2" class="star" onmouseover="ratingOver(2)"
									mouseout="resetStar()" onclick="saveRating(2)">★</span> <span
									id="star-3" class="star" onmouseover="ratingOver(3)"
									mouseout="resetStar()" onclick="saveRating(3)">★</span> <span
									id="star-4" class="star" onmouseover="ratingOver(4)"
									mouseout="resetStar()" onclick="saveRating(4)">★</span> -->
							</div>
						</div>
						<div>
							<select id="custom-select<%=i%>">
								<option selected>Select Ratings!</option>
								<option value="1">One</option>
								<option value="2">Two</option>
								<option value="3">Three</option>
								<option value="4">Four</option>
								<option value="5">Five</option>
							</select>
							<input	type="hidden" id="myPostUserName<%=i%>"
									value="<%=f.getPost().getUsername()%>"> <input
									type="hidden" id="myPostTime<%=i%>"
									value="<%=f.getPost().getTimeCreated().toString()%>">
							 <input type="button"
								class="btn btn-md btn-primary extra-mar fix-width"
								value="Rate it!" onclick="saveRating(<%=i%>)">
						</div>
					</div>
					
					<!-- Comment Input Box -->
					<div class="row">
						<div class="col-sm-3"></div>
						<div class="col-sm-9">
						<h4>Comments</h4>
						</div>
					</div>
					<%
							if (f.getComm() != null) {
								int size = f.getComm().size(),h=0;
							while(h < size){
						%>
						
					<div class="row">
						<div class="col-sm-3"></div>
						
						<div class="col-sm-2"><%=((f.getComm().get(h)).getCommenter_Username()) %>
						
						</div>
						<div class="col-sm-7">
							<div class="well"><%=(f.getComm().get(h)).getCommentText()%>

							</div>
							<hr>
						</div>
						
					</div>
					<%
							h++;
							}
							}
						%>
					<%
						if (true) {
					%>
					<hr>
					<div class="row">
						<div class="col-sm-3"></div>
						<div class="col-sm-9">
						<h4>Add a Comment</h4>
						</div>
					</div>
					
					<form class="form-horizontal" name="myComnt<%=i%>">
						<div class="form-group">
							<!-- <label class="control-label col-sm-3">Comment:
							</label> -->
							<label class="control-label col-sm-3">Comment:</label>
							<div class="col-sm-9">
								<input type="hidden" id="myFid<%=i%>"
									value="<%=currentForum.getForumId()%>"> <input
									type="hidden" id="myPostUserName<%=i%>"
									value="<%=f.getPost().getUsername()%>"> <input
									type="hidden" id="myPostTime<%=i%>"
									value="<%=f.getPost().getTimeCreated().toString()%>"> <input
									type="text" class="form-control" id="myComment<%=i%>"
									name="comment" placeholder="New Comment... ">
							</div>

						</div>

						<div class="form-group">
							<label class="control-label col-sm-3">Comment Image URL:</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="photoLocation<%=i%>"
									name="comment" placeholder="Image Link Please...">
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-3">Comment Link URL:</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="linkLocation<%=i%>"
									name="comment" placeholder="Link Please...">
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-3">Comment Video URL:</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="videoLocation<%=i%>"
									name="comment" placeholder="Video Link Please...">
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-offset-3 col-sm-7">
								<input type="button"
									class="btn btn-md btn-primary extra-mar fix-width"
									value="Add Comment" onclick="saveComment(<%=i%>)">
							</div>
						</div>

					</form>

					<%
						}
					%>
					

					<%
						i++;
							}
						} /* All Post not null check */
						else {
					%>
					<!--  If no posts present -->
					<div class="row">
						<div class="col-sm-3"></div>
						<div class="col-sm-9">

							<div class="well">
								<p>No posts!</p>

							</div>
						</div>
					</div>
					<%
						}
					%>
					<hr><hr>
					


				</div>
			</div>
		</div>
	</div>

</body>
</html>