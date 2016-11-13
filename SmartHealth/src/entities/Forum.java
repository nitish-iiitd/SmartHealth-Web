package entities;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import model.DatabaseHandler;
import model.ForumDBHandler;

public class Forum {
	private int forumId;
	private String topic;
	private String URL;
	private String summary;
	private Date whenCreated;
	private Date whenClosed;
	private String createdByModerator_Username;
	private String deletedByModerator_Username;
	
	
	
	public Forum(int forumId, String topic, String uRL, String summary, Date whenCreated, Date whenClosed,
			String createdByModerator_Username, String deletedByModerator_Username) {
		super();
		this.forumId = forumId;
		this.topic = topic;
		URL = uRL;
		this.summary = summary;
		this.whenCreated = whenCreated;
		this.whenClosed = whenClosed;
		this.createdByModerator_Username = createdByModerator_Username;
		this.deletedByModerator_Username = deletedByModerator_Username;
	}
	
	public Forum(String topic, String uRL, String summary, Date whenCreated, Date whenClosed,
			String createdByModerator_Username, String deletedByModerator_Username) {
		super();
		
		this.topic = topic;
		URL = uRL;
		this.summary = summary;
		this.whenCreated = whenCreated;
		this.whenClosed = whenClosed;
		this.createdByModerator_Username = createdByModerator_Username;
		this.deletedByModerator_Username = deletedByModerator_Username;
	}
	public Forum(String topic2, String summary2, String username) {
		// TODO Auto-generated constructor stub
		topic=topic2;
		summary=summary2;
		createdByModerator_Username = username;
	}

	public void storeForum(){
		
		
		try {
		
			java.sql.Connection connection = ForumDBHandler.connection;
			String createForum = "insert into Forum (topic,summary,url,whencreated,CreatedByModerator_Username) values(?,?,?,?,?)";
			
			PreparedStatement stmt = connection.prepareStatement(createForum, Statement.RETURN_GENERATED_KEYS);
			
			long millis=System.currentTimeMillis();  
			java.sql.Date date=new java.sql.Date(millis);
			
			stmt.setString(1, this.getTopic());
			stmt.setString(2,this.getSummary());
			stmt.setString(3,"url");
			stmt.setDate(4, date);
			stmt.setString(5, this.getCreatedByModerator_Username());
						
			int rs = stmt.executeUpdate();
			
			// Auto Incremented Id
			ResultSet rs1 = stmt.getGeneratedKeys();
			rs1.next();
			int id = rs1.getInt(1);
			System.out.println("New ID is: "+id);

			if(rs > 0){
				System.out.println("New Qualification added");

			}
			else{
				System.out.println("Failed!! to add new Qualification.");
			}
				
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static List<Forum> getAllForums() throws ClassNotFoundException, SQLException{
		List<Forum> forums = new ArrayList<Forum>();
		
		java.sql.Connection connection = DatabaseHandler.createConnection();
		
		String forumQuery = "select * from Forum order by whenCreated asc limit 20";
		PreparedStatement stmt = connection.prepareStatement(forumQuery);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()){
			int id = rs.getInt(1);
			String topics = rs.getString(2);
			String url = rs.getString(3);
			String sum= rs.getString(4);
			Date createDate= rs.getDate(5);
			Date closedDate= rs.getDate(6);
			String createdBy = rs.getString(7);
			String deletedBy = rs.getString(8);
			System.out.println("Read Forum");
			forums.add(new Forum(id, topics, url, sum, createDate, closedDate, createdBy, deletedBy));
		
		}
				
		return forums;
	}
		
	public static Forum readForum(int i) {
		try {
			// check for integer
			if(i != (int)i){
				throw new Exception("Not an Integer");
			}
			String query = "select * from Forum where forumId = ? ";
			java.sql.Connection connection = DatabaseHandler.createConnection();
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1,i);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				try {
						int id = rs.getInt(1);
						String topics = rs.getString(2);
						String url = rs.getString(3);
						String sum= rs.getString(4);
						Date createDate= rs.getDate(5);
						Date closedDate= rs.getDate(6);
						String createdBy = rs.getString(7);
						String deletedBy = rs.getString(8);
						System.out.println("Read Forum");
						return new Forum(id, topics, url, sum, createDate, closedDate, createdBy, deletedBy);
						
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
			}
			else{
				System.out.println("Forum Not Present");
			}

		
			}
		catch(Exception e){
			System.out.println(e);
		}
		return null;
	}
	

	
	public boolean validate(Forum f){
		
		
		
		
		return false;
	}


	public int getForumId() {
		return forumId;
	}



	public void setForumId(int forumId) {
		this.forumId = forumId;
	}



	public String getTopic() {
		return topic;
	}



	public void setTopic(String topic) {
		this.topic = topic;
	}



	public String getURL() {
		return URL;
	}



	public void setURL(String uRL) {
		URL = uRL;
	}



	public String getSummary() {
		return summary;
	}



	public void setSummary(String summary) {
		this.summary = summary;
	}



	public Date getWhenCreated() {
		return whenCreated;
	}



	public void setWhenCreated(Date whenCreated) {
		this.whenCreated = whenCreated;
	}



	public Date getWhenClosed() {
		return whenClosed;
	}



	public void setWhenClosed(Date whenClosed) {
		this.whenClosed = whenClosed;
	}



	public String getCreatedByModerator_Username() {
		return createdByModerator_Username;
	}



	public void setCreatedByModerator_Username(String createdByModerator_Username) {
		this.createdByModerator_Username = createdByModerator_Username;
	}



	
	public String getDeletedByModerator_Username() {
		return deletedByModerator_Username;
	}



	
	public void setDeletedByModerator_Username(String deletedByModerator_Username) {
		this.deletedByModerator_Username = deletedByModerator_Username;
	}
	
	
	
	public void display() {
		// TODO Auto-generated method stub
		System.out.println("Forum ID "+forumId);
		
	}
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
