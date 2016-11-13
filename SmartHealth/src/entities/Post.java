package entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

import model.ForumDBHandler;

public class Post {
	private String username;
	private Timestamp timeCreated;
	private int forumId;
	private String TextEntry;
	private String PhotoLocation;
	private String LinkLocation;
	private String VideoLocation;
	
		public Post(String username, Timestamp timeCreated, int forumId, String textEntry, String photoLocation,
			String linkLocation, String videoLocation) {
		super();
		this.username = username;
		this.timeCreated = timeCreated;
		this.forumId = forumId;
		TextEntry = textEntry;
		PhotoLocation = photoLocation;
		LinkLocation = linkLocation;
		VideoLocation = videoLocation;
		
	}
	
	public boolean storePost() throws SQLException, ClassNotFoundException {


			java.sql.Connection connection = null;
			
				connection = ForumDBHandler.connection;
				
			String createForum = "insert into Post values(?,?,?,?,?,?,?)";
			PreparedStatement stmt = connection.prepareStatement(createForum);
					
			long millis=System.currentTimeMillis();  
			java.sql.Date date=new java.sql.Date(millis);  
			stmt.setString(1, username);
			Timestamp timestamp = new Timestamp(date.getTime());
			stmt.setTimestamp(2, timestamp);
			stmt.setInt(3,this.forumId);
			stmt.setString(4, this.TextEntry);
			stmt.setString(5, this.PhotoLocation);
			stmt.setString(6, this.LinkLocation);
			stmt.setString(7, this.VideoLocation);
			int rs = stmt.executeUpdate();
			if(rs > 0){
				System.out.println("New Post Added");
				return true;
			}
			else{
				System.out.println("No! Post Added");
				return false;
				
			}
			
		
	}
	public static List<Post> getPosts(int fid) {
		// TODO Auto-generated method stub
		Connection connection = null;
		connection = ForumDBHandler.connection;
		List<Post>allPost = new ArrayList<Post>();

		try {
			
		String query = " select * from Post where forumID = ? ";
		PreparedStatement stmt = connection.prepareStatement(query);
		
		stmt.setInt(1,fid );
		
		ResultSet rs = stmt.executeQuery();
		if(rs.next()){
			
			rs.beforeFirst(); // to move the cursor the first position in the resultset.
			while(rs.next()){
				String uname = rs.getString(1);
				Timestamp dt = rs.getTimestamp(2);
				int id = rs.getInt(3);
				String TextEnt = rs.getString(4);
				String PhotLoc =rs .getString(5);
				String LinkLoc = rs.getString(6);
				String VideoLoc	= rs.getString(7);
				System.out.println("Read Post for fid: "+fid);
				 allPost.add(new Post(uname, dt, id, TextEnt,PhotLoc, LinkLoc, VideoLoc));
				
			}
		}else
		{
			System.out.println("No Post Present");
			return null;
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
		return allPost;
	}
	/**
	 * This function reads post corresponding a user
	 * @param username
	 * @param d
	 */
	public Post readPost(String username,Timestamp d){
		
		Connection connection = null;
		connection = ForumDBHandler.connection;
		

		try {
			
		String query = " select * from Post where username = ? and timeCreated =? ";
		PreparedStatement stmt = connection.prepareStatement(query);
		
		stmt.setString(1, username);
		stmt.setTimestamp(2, d);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()){
			String uname = rs.getString(1);
			Timestamp dt = rs.getTimestamp(2);
			int id = rs.getInt(3);
			String TextEnt = rs.getString(4);
			String PhotLoc =rs .getString(5);
			String LinkLoc = rs.getString(6);
			String VideoLoc	= rs.getString(7);
			System.out.println("Read Post");
			return new Post(uname, dt, id, TextEnt,PhotLoc, LinkLoc, VideoLoc);
			
			
			
		}else
		{
			System.out.println("Post Not Present");
		}
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
		
		
		
		
		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public Timestamp getTimeCreated() {
			return timeCreated;
		}

		public void setTimeCreated(Timestamp timeCreated) {
			this.timeCreated = timeCreated;
		}

		public int getForumId() {
			return forumId;
		}

		public void setForumId(int forumId) {
			this.forumId = forumId;
		}

		public String getTextEntry() {
			return TextEntry;
		}

		public void setTextEntry(String textEntry) {
			TextEntry = textEntry;
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

		

public void readAllComments(){
	//String readAll = "select * from Comment where post_username = ?";
	
}
	

	
}

