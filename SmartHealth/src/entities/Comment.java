package entities;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.ForumDBHandler;


public class Comment {
	private	String Post_Username;
	private	Timestamp Post_TimeCreated;
	private String  Commenter_Username;
	private Timestamp CommentTime;
	private String CommentText;
	private String PhotoLocation;
	private String LinkLocation;
	private String VideoLocation;

	public Comment(String post_Username, Timestamp post_TimeCreated, String commenter_Username, Timestamp commentTime,
			String commentText, String photoLocation, String linkLocation, String videoLocation) {
		super();
		Post_Username = post_Username;
		Post_TimeCreated = post_TimeCreated;
		Commenter_Username = commenter_Username;
		CommentTime = commentTime;
		CommentText = commentText;
		PhotoLocation = photoLocation;
		LinkLocation = linkLocation;
		VideoLocation = videoLocation;
	}
	
public void storeComment() throws ClassNotFoundException, SQLException{
	
	java.sql.Connection connection = ForumDBHandler.connection;
	String query = "insert into Comment values(?,?,?,?,?,?,?,?)";
	PreparedStatement stmt =connection.prepareStatement(query);
	long time = System.currentTimeMillis();
	Date d = new Date(time);
	stmt.setString(1, Post_Username);
	stmt.setTimestamp(2, Post_TimeCreated);
	stmt.setString(3, Commenter_Username);
	Timestamp timestamp = new Timestamp(d.getTime());
	stmt.setTimestamp(4,timestamp);
	stmt.setString(5, CommentText);
	stmt.setString(6,PhotoLocation);
	stmt.setString(7, LinkLocation);
	stmt.setString(8, VideoLocation);
	int rs = stmt.executeUpdate();
	if(rs > 0){
		System.out.println("New Comment added");
	}
	else{
		System.out.println("New Comment addition failed!!");
	}
	
	
}
public static List<Comment> getComment(int id){
	
	//List<Comment> allComments = new ArrayList<Comment>();
	//
	
	return null;
}
public static List<Comment> getComment(String username, Timestamp timeCreated) {
	// TODO Auto-generated method stub
	
			Connection connection = null;
			connection = ForumDBHandler.connection;
			
			List<Comment> allComments= new ArrayList<Comment>();
			try {
				
			String query = " select * from Comment where Post_Username = ? and Post_TimeCreated =? ORDER BY CommentTime ASC;";
			PreparedStatement stmt = connection.prepareStatement(query);
			
			stmt.setString(1, username);
			stmt.setTimestamp(2, timeCreated);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				
				rs.beforeFirst();
			while(rs.next()){
				String uname = rs.getString(1);
				Timestamp dt = rs.getTimestamp(2);
				String commenterUser = rs.getString(3);
				Timestamp commentTime = rs.getTimestamp(4);
				String TextEnt = rs.getString(5);
				String PhotLoc =rs .getString(6);
				String LinkLoc = rs.getString(7);
				String VideoLoc	= rs.getString(8);
				System.out.println("Read Post");
				
				Comment com = new  Comment(uname,dt, commenterUser, commentTime,TextEnt, PhotLoc,LinkLoc, VideoLoc);
				allComments.add(com);
				
			}
				
				
			}else
			{
				System.out.println("Comment Not Present at: "+username);
				return null;
			}
			
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return allComments;
			
			
			
}

public String getPost_Username() {
	return Post_Username;
}

public void setPost_Username(String post_Username) {
	Post_Username = post_Username;
}

public Timestamp getPost_TimeCreated() {
	return Post_TimeCreated;
}

public void setPost_TimeCreated(Timestamp post_TimeCreated) {
	Post_TimeCreated = post_TimeCreated;
}

public String getCommenter_Username() {
	return Commenter_Username;
}

public void setCommenter_Username(String commenter_Username) {
	Commenter_Username = commenter_Username;
}

public Timestamp getCommentTime() {
	return CommentTime;
}

public void setCommentTime(Timestamp commentTime) {
	CommentTime = commentTime;
}

public String getCommentText() {
	return CommentText;
}

public void setCommentText(String commentText) {
	CommentText = commentText;
}

public String getPhotoLocation() {
	return PhotoLocation;
}

public void setPhotoLocation(String photoLocation) {
	PhotoLocation = photoLocation;
}

public String getLinkLocation() {
	return LinkLocation;
}

public void setLinkLocation(String linkLocation) {
	LinkLocation = linkLocation;
}

public String getVideoLocation() {
	return VideoLocation;
}

public void setVideoLocation(String videoLocation) {
	VideoLocation = videoLocation;
}



}

