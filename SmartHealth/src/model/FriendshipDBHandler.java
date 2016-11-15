package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import entities.Friendship;
import entities.User;

public class FriendshipDBHandler {

	private static Connection connection = null;

	static{
		try {
			connection = DatabaseHandler.createConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Function for listing friends
	 * @param username2 
	 * @throws SQLException
	 */
	public static ArrayList<User> listFriends(String username2) throws SQLException {

		ArrayList<User> friends =   new ArrayList<User>();
		String query = "select * from Friendship";
		PreparedStatement stmt  = connection.prepareStatement(query);
		ResultSet rs = stmt.executeQuery(query);

		System.out.println("Your Friend List.");
		while (rs.next()) {
			String username = rs.getString(1);
			String username3 = rs.getString(2);
			Timestamp tm = rs.getTimestamp(6);
			Timestamp unfriended = rs.getTimestamp(7);

			if(username.equals(username2))
			{ 
				if(tm!=null && unfriended==null)
				{
					System.out.println("Friend: "+username3);
					friends.add(UserDBHandler.getUser(username3));
				}
			}
			if(username3.equals(username2))
			{
				if(tm!=null && unfriended==null)
				{
					System.out.println("Friend: "+username);
					friends.add(UserDBHandler.getUser(username));
				}
			}
		}
		return friends;


	}


	/*
	 * Gets friend requests
	 */
	public static ArrayList<String> getFriendRequests(String username2) throws SQLException
	{
		String query = "select * from Friendship";
		ArrayList<String> friendrequests = new ArrayList<String>();
		PreparedStatement stmt  = connection.prepareStatement(query);
		ResultSet rs = stmt.executeQuery(query);

		System.out.println("Your Request List.");
		while (rs.next()) {
			String username = rs.getString(1);
			String username3 = rs.getString(2);
			Timestamp tmWithd = rs.getTimestamp(4);
			Timestamp tmRej = rs.getTimestamp(5);
			Timestamp tmConf= rs.getTimestamp(6);
			Timestamp tmUnFrnd= rs.getTimestamp(7);
			if(username2.equals(username3))
			{ 
				if((tmConf == null)&&(tmUnFrnd==null)&&(tmWithd==null)&&(tmRej==null))
				{
					System.out.println(username);
					friendrequests.add(username);
				}
			}
			
		}
		return friendrequests;
	}


	/*
	 * Accepts a friend request
	 */
	public static String acceptFriendRequest(String requester,String requested) throws SQLException
	{
		String result = "-1:Unknown Error";
		String query1 = "update Friendship set whenConfirmed = ? , whenRejected = ?, whenunfriended = ?, whenwithdrawn = ? where requester_username = ? and requested_username = ?";
		PreparedStatement preparedStmt = connection.prepareStatement(query1);

		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis);  

		Timestamp timestamp = new Timestamp(date.getTime());

		preparedStmt.setTimestamp(1, timestamp);
		preparedStmt.setTimestamp(2, null);
		preparedStmt.setTimestamp(3, null);
		preparedStmt.setTimestamp(4, null);
		preparedStmt.setString(5, requester);
		preparedStmt.setString(6, requested);

		// execute the java preparedstatement
		int rs = preparedStmt.executeUpdate();
		if(rs>0)
		{
			result = "0:Friend Request Accepted";
		}
		else
		{
			result = "-1:Error in Request Acceptance";
		}
		return result;
	}

	/*
	 * Rejects a friend request
	 */
	public static String rejectFriendRequest(String requester,String requested) throws SQLException
	{
		String result = "-1:Unknown Error";
		String query11 = "update Friendship set whenconfirmed = ? , whenunfriended = ? ,whenwithdrawn = ? ,whenRejected = ? where requester_username = ? and requested_username = ?";
		PreparedStatement preparedStmt = connection.prepareStatement(query11);

		long millis1=System.currentTimeMillis();  
		java.sql.Date date1=new java.sql.Date(millis1);  

		Timestamp timestamp1 = new Timestamp(date1.getTime());

		preparedStmt.setTimestamp(1, null);
		preparedStmt.setTimestamp(2, null);
		preparedStmt.setTimestamp(3, null);
		preparedStmt.setTimestamp(4, timestamp1);
		preparedStmt.setString(5, requester);
		preparedStmt.setString(6, requested);


		// execute the java preparedstatement
		int rs = preparedStmt.executeUpdate();
		if(rs>0)
		{
			result = "0:Friend Request Rejected";
		}
		else
		{
			result = "-1:Error in Request Rejection";
		}
		return result;
	}


	/**
	 * Function for displaying friend requests received 
	 * @param username2
	 * @throws SQLException
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public void seeFriendRequests(String username2) throws SQLException, NumberFormatException, IOException 
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String query = "select * from Friendship";
		PreparedStatement stmt  = connection.prepareStatement(query);
		ResultSet rs = stmt.executeQuery(query);

		System.out.println("Your Request List.");
		while (rs.next()) {
			String username = rs.getString(1);
			String username3 = rs.getString(2);
			Timestamp tmWithd = rs.getTimestamp(4);
			Timestamp tmRej = rs.getTimestamp(5);
			Timestamp tmConf= rs.getTimestamp(6);
			if(username2.equals(username3))
			{ 
				if(tmWithd == null && tmRej == null && tmConf == null)
				{
					System.out.println(username);
					System.out.println("1. Accept \n2. Reject \n3.Ignore");


					switch(Integer.parseInt(br.readLine())){
					case 1:
						String query1 = "update Friendship set whenConfirmed = ? where requester_username = ? and requested_username = ?";
						PreparedStatement preparedStmt = connection.prepareStatement(query1);

						long millis=System.currentTimeMillis();  
						java.sql.Date date=new java.sql.Date(millis);  

						Timestamp timestamp = new Timestamp(date.getTime());

						preparedStmt.setTimestamp(1, timestamp);
						preparedStmt.setString(2, username);
						preparedStmt.setString(3, username2);

						// execute the java preparedstatement
						preparedStmt.executeUpdate();
						break;

					case 2:
						String query11 = "update Friendship set whenRejected = ? where requester_username = ? and requested_username = ?";
						PreparedStatement preparedStmt1 = connection.prepareStatement(query11);

						long millis1=System.currentTimeMillis();  
						java.sql.Date date1=new java.sql.Date(millis1);  

						Timestamp timestamp1 = new Timestamp(date1.getTime());

						preparedStmt1.setTimestamp(1, timestamp1);
						preparedStmt1.setString(2, username);
						preparedStmt1.setString(3, username2);

						// execute the java preparedstatement
						preparedStmt1.executeUpdate();
						break;

					case 3: continue;
					default: System.out.println("Wrong Choice");

					}

				}


			}

		}
	}


	/**
	 * Function for removing friends
	 * @param username2
	 * @param connection
	 * @throws SQLException
	 * @throws IOException
	 */
	public static String removeFriend(String username2,String removeUser) throws SQLException, IOException {

		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//System.out.println("Enter the username to remove");
		//String removeUser = br.readLine();
		String result="-1:Unknown Error";
		if(!verifyUser(removeUser)){
			System.out.println("User Verification Failed!");
			result = "-1:User Verification Failed!";
			return result;
		}
		if(!endUser( removeUser)){
			System.out.println("User is not a end User");
			result = "-1:User is not a end User";
			return result;
		}

		String query11 = "update Friendship set whenrejected= ? , whenwithdrawn = ? , whenUnfriended = ? where requester_username = ? and requested_username = ?";
		PreparedStatement preparedStmt1 = connection.prepareStatement(query11);

		long millis1=System.currentTimeMillis();  
		java.sql.Date date1=new java.sql.Date(millis1);  

		Timestamp timestamp1 = new Timestamp(date1.getTime());
		System.out.println("whenunfriended:"+timestamp1);
		//preparedStmt1.setTimestamp(1, null);
		preparedStmt1.setTimestamp(1, null);
		preparedStmt1.setTimestamp(2, null);
		preparedStmt1.setTimestamp(3, timestamp1);
		preparedStmt1.setString(4, username2);
		preparedStmt1.setString(5, removeUser);
		//System.out.println(preparedStmt1);
		// execute the java preparedstatement
		if(preparedStmt1.executeUpdate()>0){
			System.out.println("1:Successfully removed friend");
			result = "0:Successfully removed friend";
		}
		else{
			String query14 = "update Friendship set  whenrejected= ? , whenwithdrawn = ? ,whenunfriended = ? where requester_username = ? and requested_username = ?";
			PreparedStatement preparedStmt14 = connection.prepareStatement(query14);

			long millis14=System.currentTimeMillis();  
			java.sql.Date date14=new java.sql.Date(millis14);  

			Timestamp timestamp14 = new Timestamp(date14.getTime());
			System.out.println("whenunfriended:"+timestamp14);
			//preparedStmt14.setTimestamp(1, null);
			preparedStmt14.setTimestamp(1, null);
			preparedStmt14.setTimestamp(2, null);
			preparedStmt14.setTimestamp(3, timestamp14);
			preparedStmt14.setString(4,  removeUser);
			preparedStmt14.setString(5,username2);
			//System.out.println(preparedStmt14);
			// execute the java preparedstatement
			if(preparedStmt14.executeUpdate()>0){
				System.out.println("2:Successfully removed friend");
				result = "0:Successfully removed friend";
			}
			else
			{
				System.out.println("Can't remove  friend");
				result = "-1:Can't remove  friend";
			}
		}
		return result;
	}

	/**
	 * For cancelling friend request
	 * @param username2
	 * @param connection
	 * @throws IOException
	 * @throws SQLException
	 */
	public void cancelFriendRequest(String username2,String cancelUname) throws IOException, SQLException {
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//String cancelUname;		
		//System.out.println("Enter the username to cancel friend request");
		//cancelUname=br.readLine();

		if(!verifyUser(cancelUname)){
			System.out.println("User does not exist!");
			return;
		}
		if(!endUser( cancelUname)){
			System.out.println("User is not a end User");
			return;
		}
		String query11 = "Update Friendship set WhenWithdrawn = ?  where requester_username = ? and requested_username = ? and whenConfirmed is ? and whenRejected is ? and whenWithdrawn is ?";
		//String query11 = "update friendship set whenWithdrawn = ? where requester_username = ? and requested_username = ? and whenWithdrawn = ? and whenRejected = ? and whenConfirmed = ?";
		PreparedStatement preparedStmt1 = connection.prepareStatement(query11);

		long millis1=System.currentTimeMillis();  
		java.sql.Date date1=new java.sql.Date(millis1);  

		Timestamp timestamp1 = new Timestamp(date1.getTime());

		preparedStmt1.setTimestamp(1, timestamp1);
		preparedStmt1.setString(2, username2);
		preparedStmt1.setString(3, cancelUname);
		preparedStmt1.setString(4, null);
		preparedStmt1.setString(5, null);
		preparedStmt1.setString(6, null);
		System.out.println(preparedStmt1);
		if(preparedStmt1.executeUpdate()>0){
			System.out.println("Successfully cancelled request");
		}
		else
			System.out.println("Can't cancel request");

	}

	/**
	 * Sends request to a enduser from username
	 * @param requester
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static String sendRequest(String requester,String requested) throws NumberFormatException, IOException {

		String result="-1:Unknown error";
		try {
			/*BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			System.out.println("Enter the username to send request");
			String requested = br.readLine();*/
			if(requested.equals(requester))
			{
				System.out.println("You can't send the request to yourself.");
				result= "-1:You can't send the request to yourself.";
				return result;
			}
			if(!verifyUser(requested)){
				System.out.println("User does not exist!");
				result= "-1:User does not exist!";
				return result;
			}

			if(!endUser(requested)){
				System.out.println("User is not a valid user");
				result= "-1:User is not a valid user";
				return result;
			}

			String query = "select * from Friendship";
			PreparedStatement stmt  = connection.prepareStatement(query);
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				Friendship fr = new Friendship(rs.getString(1), rs.getString(2), rs.getTimestamp(3), rs.getTimestamp(4), rs.getTimestamp(5), rs.getTimestamp(6), rs.getTimestamp(7));
				String requesterUser = fr.getRequester_username();
				String requestedUsername = fr.getRequested_username(); 
				Timestamp withdrawn = fr.getWhenWithdrawn();
				Timestamp confirmed = fr.getWhenConfirmed();
				Timestamp unfriended = fr.getWhenUnfriended();
				Timestamp rejected = fr.getWhenRejected();
				// if there is an entry in table
				if((requester.equals(requesterUser)&& requested.equals(requestedUsername)) || (requester.equals(requestedUsername)&& requested.equals(requesterUser)))
				{
					 // if already friend
					if(confirmed!=null && unfriended==null)
					{
						System.out.println("You are already friend with this user.");
						result= "-1:You are already friend with this user.";
						return result;
					}
					else if(requester.equals(requestedUsername)&& requested.equals(requesterUser))
					{
						if(withdrawn==null && confirmed==null && unfriended==null && rejected==null)
						{
							System.out.println("You already have a friend request from this user");
							result= "-1:You already have a friend request from this user";
							return result;
						}
						else
						{
							String query1 = "update Friendship set requester_username = ?, requested_username = ?, whenRequested = ? , whenWithdrawn = ? , whenUnfriended = ? , whenconfirmed = ? , whenrejected = ? where requester_username = ? and requested_username = ?";
							PreparedStatement preparedStmt = connection.prepareStatement(query1);

							long millis=System.currentTimeMillis();  
							java.sql.Date date=new java.sql.Date(millis);  

							Timestamp timestamp = new Timestamp(date.getTime());

							preparedStmt.setString(1, requester);
							preparedStmt.setString(2, requested);
							preparedStmt.setTimestamp(3, timestamp);
							preparedStmt.setTimestamp(4, null);
							preparedStmt.setTimestamp(5, null);
							preparedStmt.setTimestamp(6, null);
							preparedStmt.setTimestamp(7, null);
							preparedStmt.setString(8, requesterUser);
							preparedStmt.setString(9, requestedUsername);
							// execute the java preparedstatement
							preparedStmt.executeUpdate();
							System.out.println("Friend request sent.");
							result= "0:Friend request sent.";
							return result;				
						}
					}
					else if(confirmed==null && unfriended==null && rejected==null && withdrawn == null) // if request already sent
					{
						System.out.println("You are not allowed to send multiple requests.");
						result= "-1:You are not allowed to send multiple requests.";
						return result;
					}
					
					else // send the request
					{
						System.out.println("-----unfriended---------");
						String query1 = "update Friendship set whenRequested = ? , whenWithdrawn = ? , whenUnfriended = ? , whenconfirmed = ? , whenrejected = ? where requester_username = ? and requested_username = ?";
						PreparedStatement preparedStmt = connection.prepareStatement(query1);

						long millis=System.currentTimeMillis();  
						java.sql.Date date=new java.sql.Date(millis);  

						Timestamp timestamp = new Timestamp(date.getTime());

					
						preparedStmt.setTimestamp(1, timestamp);
						preparedStmt.setTimestamp(2, null);
						preparedStmt.setTimestamp(3, null);
						preparedStmt.setTimestamp(4, null);
						preparedStmt.setTimestamp(5, null);
						preparedStmt.setString(6, requesterUser);
						preparedStmt.setString(7, requestedUsername);
						// execute the java preparedstatement
						preparedStmt.executeUpdate();
						System.out.println("Friend request sent.");
						result= "0:Friend request sent.";
						return result;		
					}


				}
//				else // new request
//				{
//					System.out.println("-----if new friend---------");
//					long millis=System.currentTimeMillis();  
//					java.sql.Date date=new java.sql.Date(millis);  
//
//					String friends  = "insert into Friendship values(?,?,?,?,?,?,?)";
//					PreparedStatement stmt13 = connection.prepareStatement(friends);
//					stmt13.setString(1,requester);
//					stmt13.setString(2,requested);
//					Timestamp timestamp = new Timestamp(date.getTime());
//					stmt13.setTimestamp(3, timestamp);
//					stmt13.setTimestamp(4, null);
//					stmt13.setTimestamp(5, null);
//					stmt13.setTimestamp(6, null);
//					stmt13.setTimestamp(7, null);
//
//					int rs12 = stmt13.executeUpdate();
//
//					if(rs12>0){
//						System.out.println("Friend Request Send");
//						result=  "0:Friend Request Send";
//						return result;
//					}
//				}
			}
			System.out.println("-----if new friend---------");
			long millis=System.currentTimeMillis();  
			java.sql.Date date=new java.sql.Date(millis);  

			String friends  = "insert into Friendship values(?,?,?,?,?,?,?)";
			PreparedStatement stmt13 = connection.prepareStatement(friends);
			stmt13.setString(1,requester);
			stmt13.setString(2,requested);
			Timestamp timestamp = new Timestamp(date.getTime());
			stmt13.setTimestamp(3, timestamp);
			stmt13.setTimestamp(4, null);
			stmt13.setTimestamp(5, null);
			stmt13.setTimestamp(6, null);
			stmt13.setTimestamp(7, null);

			int rs12 = stmt13.executeUpdate();

			if(rs12>0){
				System.out.println("Friend request sent.");
				result=  "0:Friend request sent.";
				return result;
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Function for checking if a user exists or not
	 * @param connection
	 * @param requested
	 * @return
	 */
	public static boolean endUser(String requested) {

		String query1 = "select * from EndUser where username = ? ";
		boolean userExist =false;

		try{
			PreparedStatement stmt1 = connection.prepareStatement(query1);
			stmt1.setString(1, requested);
			ResultSet rs1 = stmt1.executeQuery();
			if(rs1.next()){
				userExist = true;	
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return userExist;
	}

	/**
	 * Checks if the username is present
	 */
	public static boolean verifyUser(String username) throws SQLException{
		String query1 = "select * from User where username = ? and status = 1";
		boolean userExist = false;

		try{
			PreparedStatement stmt1 = connection.prepareStatement(query1);
			stmt1.setString(1, username);
			ResultSet rs1 = stmt1.executeQuery();
			if(rs1.next()){
				if(rs1.getInt(17)==1)
					userExist = true;	
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return userExist;


	}
}
