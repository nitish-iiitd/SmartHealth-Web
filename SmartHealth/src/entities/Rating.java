package entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.ForumDBHandler;

public class Rating {
	private String Post_Username;
	private Timestamp Post_TimeCreated;
	private String Rater_Username;
	private int Stars;


	public Rating(String post_Username, Timestamp post_TimeCreated, String rater_Username, int stars) {
		super();
		Post_Username = post_Username;
		Post_TimeCreated = post_TimeCreated;
		Rater_Username = rater_Username;
		Stars = stars;
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



	public String getRater_Username() {
		return Rater_Username;
	}



	public void setRater_Username(String rater_Username) {
		Rater_Username = rater_Username;
	}



	public int getStars() {
		return Stars;
	}



	public void setStars(int stars) {
		Stars = stars;
	}



	public static  List<Rating> getRating(String username, Timestamp timeCreated) {
		// TODO Auto-generated method stub




		Connection connection = null;
		connection = ForumDBHandler.connection;
		List<Rating>allRatings = new ArrayList<Rating>();

		try {

			String query = " select * from Rating where Post_Username = ? and Post_TimeCreated = ? ";
			PreparedStatement stmt = connection.prepareStatement(query);

			stmt.setString(1,username);
			stmt.setTimestamp(2, timeCreated);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){

				rs.beforeFirst(); // to move the cursor the first position in the resultset.
				while(rs.next()){
					String uname = rs.getString(1);
					Timestamp dt = rs.getTimestamp(2);
					String rater_userName = rs.getString(3);
					int star = rs.getInt(4);

					//	System.out.println("Read Post for fid: "+fid);
					allRatings.add(new Rating(uname, dt, rater_userName,star));

				}
			}else
			{
				System.out.println("No Rating Present");
				return null;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		return allRatings;

	}
	public void storeRating() throws SQLException{
		java.sql.Connection connection = null;

		connection = ForumDBHandler.connection;

		try {
			int rs;

			String createForum = "insert into Rating values(?,?,?,?)";
			PreparedStatement stmt = connection.prepareStatement(createForum);

			stmt.setString(1, Post_Username);

			stmt.setTimestamp(2, Post_TimeCreated);
			stmt.setString(3,Rater_Username);
			stmt.setInt(4, Stars);

			rs = stmt.executeUpdate();

			if(rs > 0){
				System.out.println("New Rating Added");
			}
			else{
				System.out.println("No! Rating Added");

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		}

	}

}
