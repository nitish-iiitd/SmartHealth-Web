package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entities.Forum;

public class ForumDBHandler {
	
	public static Connection connection = null;
	private static final int NEW_USER = 101;
	private static final int INIT_KARMA = 0;
	private static final int NEW_MOD = 200;

	static{
		try {
			connection = DatabaseHandler.createConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * This function returns the forum from the id. 
	 * @param i
	 * @return
	 */
	public static Forum getForum(int i){
			
		
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


}
