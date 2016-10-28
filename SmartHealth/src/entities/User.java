/*
 * 	Copyright (c) 2016-2016 RNR(Raveena Nitish Rohit) Technologies, Inc. All Rights Reserved.
 *  This software is the confidential and proprietary information of RNR, Inc. ("Confidential Information"). 
 *  You shall not disclose such Confidential Information and shall use it only in accordance with the terms
 *  of the license agreement you entered into with RNR.
 *  
 *  RNR MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED,
 *  INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE,
 *  OR NON-INFRINGEMENT. RNR SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING,
 *  MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 * 
 */
package entities;
import entities.NewMain; 
/**
 * This abstract class represents the user of the smart health system.
 *
 * @version 1.10 04 Sept 2016
 * @author Raveena Nitish Rohit
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public  class User {

	protected String username;		   /*  Stores the handle of user. */
	private String pass; 			   /*  Password: make sure to encrypt it in webapp */
	private String emailp;			   /*  Primary Email. */ 
	private String emails;			   /*  Secondary Email. */
	private String fname;			   /*  First Name. */
	private String lname;			   /*  Last Name. */
	private String about;			   /*  About user */
	private String url1;			   /*  URL of 1st Profile Picture. */
	private String url2;			   /*  URL of 2nd Profile Picture. */
	private String url3;			   /*  URL of 3rd Profile Picture. */
	private String StreetNumber;	   /*  Street Number */
	private String StreetName;		   /*  Street Name */
	private String MajorMunicipality;  /*  Major Municipality*/
	private String GoverningDistrict;  /*  Governing District */
	private	String PostalArea;		   /*  Postal Address */
	private int type; 				   /*  Describe the type of the user - new middle old*/
	private boolean status; 		   /*  Tells whether the user is active or inactive. check before displaying users */


	/**
	 *  Default Constructor
	 */
	public User () {}

	/**
	 *  Parameterized Constructor
	 * @param username
	 * @param pass
	 * @param emailp
	 * @param emails
	 * @param fname
	 * @param lname
	 * @param about
	 * @param url1
	 * @param url2
	 * @param url3
	 * @param streetNumber
	 * @param streetName
	 * @param majorMunicipality
	 * @param governingDistrict
	 * @param postalArea
	 * @param type
	 * @param status
	 */

	public User(String username, String pass, String emailp, String emails,
			String fname, String lname, String about, String url1, String url2,
			String url3, String streetNumber, String streetName,
			String majorMunicipality, String governingDistrict,
			String postalArea, int type, boolean status) {
		super();
		this.username = username;
		this.pass = pass;
		this.emailp = emailp;
		this.emails = emails;
		this.fname = fname;
		this.lname = lname;
		this.about = about;
		this.url1 = url1;
		this.url2 = url2;
		this.url3 = url3;
		StreetNumber = streetNumber;
		StreetName = streetName;
		MajorMunicipality = majorMunicipality;
		GoverningDistrict = governingDistrict;
		PostalArea = postalArea;
		this.type = type;
		this.status = status;
	}


	


	/**
	 * Makes user inactive
	 */
	public void quitUser ( Connection conn) {
		this.status = false ;
		String query = "Update User set status = 0  where username = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			//ps.setInt(1, 0);
			//System.out.println(this.username);
			ps.setString(1, this.username);
			if( ps.executeUpdate()>0){
				System.out.println("Status updated");

			}
			else{
				System.out.println("Status not updated");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Checks if the username is present
	 */
	public boolean verifyUser(Connection con,String username) throws SQLException{
		String query1 = "select * from user where username = ? ";
		boolean userExist = false;

		try{
			PreparedStatement stmt1 = con.prepareStatement(query1);
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


	

	/**
	 * Updates the Qualification of User
	 */
	public void updateQual() {

	}
	/**
	 * Updates the Contact of User
	 */
	public void updateContact() {

	}

	/**
	 * Updates a user by asking updation details
	 * @param con
	 * @param email
	 * @throws IOException
	 * @throws SQLException
	 */
	public void updateUser(Connection con, String email ) throws IOException, SQLException{
		User u;
		int ch;
		System.out.println("Verify your password ");
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		pass = br.readLine();
		u = NewMain.loginAndGetType(email,pass);
		if (u ==null){
			System.out.println("Authentication Faliure!!");

		}
		else{

			do
			{
				u.display();
				PreparedStatement ps;
				String query;
				String change;
				System.out.println("\n1.First Name \n2.Last Name\n3.Password\n4.Address\n5.About Me\n6.URL1 of profile photo\n7.URL2 of profile photo\n8.URL3 of profile photo\n9.Exit\nEnter your choice: ");

				ch = Integer.parseInt(br.readLine());
				switch(ch)
				{

				case 1: System.out.println("Enter the new First Name");
				change= br.readLine();

				query = "Update User set Firstname = ? where email1= ?";
				ps = con.prepareStatement(query);
				ps.setString(1, change);
				ps.setString(2, email);
				if ( ps.executeUpdate()  > 0 ){
					System.out.println("First Name Updated ");

				}
				else{
					System.out.println("Firstname bnot upodated");
				}
				ps.close();
				break;
				case 2: 
					System.out.println("Enter the new Last Name");
					change= br.readLine();
					query = "Update User set lastname = ? where email1= ?";
					ps = con.prepareStatement(query);
					ps.setString(1, change);
					ps.setString(2, email);

					if ( ps.executeUpdate()  > 0 ){
						System.out.println("Last Name Updated ");

					}
					else{
						System.out.println("Lastname bnot upodated");
					}
					ps.close();
					break;
				case 3:
					System.out.println("Enter the new Password");
					change= br.readLine();
					query = "Update User set password = ? where email1= ?";
					ps = con.prepareStatement(query);
					ps.setString(1, change);
					ps.setString(2, email);

					if ( ps.executeUpdate()  > 0 ){
						System.out.println("Password Updated ");

					}
					else{
						System.out.println("Lastname not upodated");
					}
					ps.close();
					break;
				case 4: System.out.println("Enter the new Street NO. ");
				String sno= br.readLine();
				System.out.println("Enter the new Street Name. ");
				String sna= br.readLine();
				System.out.println("Enter the new Municipality. ");
				String munsi= br.readLine();
				System.out.println("Enter the new District. ");
				String dx= br.readLine();
				System.out.println("Enter the new Postal Area. ");
				String parea= br.readLine();

				query = "Update User set streetNumber = ? ,  streetName= ? , majorMunicipality = ? , GoverningDistrict = ? , PostalArea = ? where email1= ?";

				ps = con.prepareStatement(query);
				ps.setString(1, sno);
				ps.setString(2, sna);
				ps.setString(3, munsi);
				ps.setString(4, dx);
				ps.setString(5, parea);
				ps.setString(6, email);

				if ( ps.executeUpdate()  > 0 ){
					System.out.println("Address  Updated ");

				}
				else{
					System.out.println("Address Not upodated");
				}
				ps.close();
				break;
				case 5: System.out.println("Enter something new about Yourself: ");
				change= br.readLine();
				query = "Update User set aboutme = ? where email1= ?";
				ps = con.prepareStatement(query);
				ps.setString(1, change);
				ps.setString(2, email);

				if ( ps.executeUpdate()  > 0 ){
					System.out.println("About Me Updated ");

				}
				else{
					System.out.println("About Me upodated");
				}
				ps.close();

				break;
				case 6:System.out.println("Enternew URL1: ");
				change= br.readLine();
				query = "Update User set photourl1 = ? where email1= ?";
				ps = con.prepareStatement(query);
				ps.setString(1, change);
				ps.setString(2, email);

				if ( ps.executeUpdate()  > 0 ){
					System.out.println("URL1 Updated ");

				}
				else{
					System.out.println("URL not updated");
				}
				ps.close();

				break;

				case 7: System.out.println("Enternew URL2: ");
				change= br.readLine();
				query = "Update User set photourl2 = ? where email1= ?";
				ps = con.prepareStatement(query);
				ps.setString(1, change);
				ps.setString(2, email);

				if ( ps.executeUpdate()  > 0 ){
					System.out.println("URL2 Updated ");

				}
				else{
					System.out.println("URL not updated");
				}
				ps.close();

				break;
				case 8: System.out.println("Enternew URL3: ");
				change= br.readLine();
				query = "Update User set photourl3 = ? where email1= ?";
				ps = con.prepareStatement(query);
				ps.setString(1, change);
				ps.setString(2, email);

				if ( ps.executeUpdate()  > 0 ){
					System.out.println("URL3 Updated ");

				}
				else{
					System.out.println("URL not updated");
				}
				ps.close();

				break;
				case 9: break;
				default: break;	
				}

			}while(ch!=9);
		}//else end

	}

	/**
	 * Displays the credentials of the user.
	 */
	private void display() {
		
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getEmailp() {
		return emailp;
	}

	public void setEmailp(String emailp) {
		this.emailp = emailp;
	}

	public String getEmails() {
		return emails;
	}

	public void setEmails(String emails) {
		this.emails = emails;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getUrl1() {
		return url1;
	}

	public void setUrl1(String url1) {
		this.url1 = url1;
	}

	public String getUrl2() {
		return url2;
	}

	public void setUrl2(String url2) {
		this.url2 = url2;
	}

	public String getUrl3() {
		return url3;
	}

	public void setUrl3(String url3) {
		this.url3 = url3;
	}

	public String getStreetNumber() {
		return StreetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		StreetNumber = streetNumber;
	}

	public String getStreetName() {
		return StreetName;
	}

	public void setStreetName(String streetName) {
		StreetName = streetName;
	}

	public String getMajorMunicipality() {
		return MajorMunicipality;
	}

	public void setMajorMunicipality(String majorMunicipality) {
		MajorMunicipality = majorMunicipality;
	}

	public String getGoverningDistrict() {
		return GoverningDistrict;
	}

	public void setGoverningDistrict(String governingDistrict) {
		GoverningDistrict = governingDistrict;
	}

	public String getPostalArea() {
		return PostalArea;
	}

	public void setPostalArea(String postalArea) {
		PostalArea = postalArea;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		// TODO Auto-generated method stub
		return type;
	}

	/**
	 * Sends request to a enduser from username
	 * @param requester
	 * @param connection
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public void sendRequest(String requester,Connection connection) throws NumberFormatException, IOException {

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			System.out.println("Enter the username to send request");
			String requested = br.readLine();
			if(requested.equals(requester))
			{
				System.out.println("You can't send the request to yourself.");
				return;
			}
			if(!verifyUser(connection, requested)){
				System.out.println("User Verification Failed");
				return;
			}

			if(!endUser(connection,requested)){
				System.out.println("User is not a valid user");
				return;
			}

			String query = "select * from friendship";
			PreparedStatement stmt  = connection.prepareStatement(query);
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				Friendship fr = new Friendship(rs.getString(1), rs.getString(2), rs.getTimestamp(3), rs.getTimestamp(4), rs.getTimestamp(5), rs.getTimestamp(6), rs.getTimestamp(7));
				String requesterUser = fr.getRequester_username();
				String requestedUsername = fr.getRequested_username(); 
				Timestamp withdrawn = fr.getWhenWithdrawn();
				if(requester.equals(requesterUser)&& requested.equals(requestedUsername))
				{
					System.out.println("Not allowed to send multiple request.");
					return;
				}
				if(requester.equals(requestedUsername)&& requested.equals(requesterUser)){
					
					if(withdrawn==null)
					{System.out.println("You already have a friend request from this user");
						return;
					}
					else
					{
						String query1 = "update friendship set whenRequested = ? , whenWithdrawn = ? where requester_username = ? and requested_username = ?";
						PreparedStatement preparedStmt = connection.prepareStatement(query1);

						long millis=System.currentTimeMillis();  
						java.sql.Date date=new java.sql.Date(millis);  

						Timestamp timestamp = new Timestamp(date.getTime());

						preparedStmt.setTimestamp(1, timestamp);
						preparedStmt.setTimestamp(2, null);
						preparedStmt.setString(3, requesterUser);
						preparedStmt.setString(4, requestedUsername);

						// execute the java preparedstatement
						preparedStmt.executeUpdate();
						break;


					
					}
				}
			}

			long millis=System.currentTimeMillis();  
			java.sql.Date date=new java.sql.Date(millis);  

			String friends  = "insert into friendship values(?,?,?,?,?,?,?)";
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
				System.out.println("Friend Request Send");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

	/**
	 * Function for checking if a user exists or not
	 * @param connection
	 * @param requested
	 * @return
	 */
	private boolean endUser(Connection connection, String requested) {

		String query1 = "select * from enduser where username = ? ";
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
	 * Function for listing friends
	 * @param username2 
	 * @param connection
	 * @throws SQLException
	 */
	public void listFriends(String username2,Connection connection) throws SQLException {


		String query = "select * from friendship";
		PreparedStatement stmt  = connection.prepareStatement(query);
		ResultSet rs = stmt.executeQuery(query);

		System.out.println("Your Friend List.");
		while (rs.next()) {
			String username = rs.getString(1);
			String username3 = rs.getString(2);
			Timestamp tm = rs.getTimestamp(6);
			Timestamp reject = rs.getTimestamp(7);

			if(username.equals(username2))
			{ if(tm!=null && reject==null)
				System.out.println("Friend: "+username3);
			//System.out.println(username+username3);
			}
			if(username3.equals(username2)){
				if(tm!=null && reject==null)
					System.out.println("Friend: "+username);
			}
		}


	}

	/**
	 * Function for displaying friend requests received 
	 * @param username2
	 * @param connection
	 * @throws SQLException
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public void seeFriendRequests(String username2, Connection connection) throws SQLException, NumberFormatException, IOException 
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String query = "select * from friendship";
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
						String query1 = "update friendship set whenConfirmed = ? where requester_username = ? and requested_username = ?";
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
						String query11 = "update friendship set whenRejected = ? where requester_username = ? and requested_username = ?";
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
	public void removeFriends(String username2, Connection connection) throws SQLException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the username to remove");
		String removeUser = br.readLine();
		if(!verifyUser(connection, removeUser)){
			System.out.println("User Verification Failed!");
			return;
		}
		if(!endUser(connection, removeUser)){
			System.out.println("User is not a end User");
			return;
		}

		String query11 = "update friendship set whenConfirmed = ? , whenUnfriended = ? where requester_username = ? and requested_username = ?";
		PreparedStatement preparedStmt1 = connection.prepareStatement(query11);

		long millis1=System.currentTimeMillis();  
		java.sql.Date date1=new java.sql.Date(millis1);  

		Timestamp timestamp1 = new Timestamp(date1.getTime());

		preparedStmt1.setTimestamp(1, null);
		preparedStmt1.setTimestamp(2, timestamp1);
		preparedStmt1.setString(3, username2);
		preparedStmt1.setString(4, removeUser);
		System.out.println(preparedStmt1);
		// execute the java preparedstatement
		if(preparedStmt1.executeUpdate()>0){
			System.out.println("Successfully removed friend");
		}
		else{
				String query14 = "update friendship set whenConfirmed = ? and whenUnfriended = ? where requester_username = ? and requested_username = ?";
				PreparedStatement preparedStmt14 = connection.prepareStatement(query14);
	
				long millis14=System.currentTimeMillis();  
				java.sql.Date date14=new java.sql.Date(millis14);  
	
				Timestamp timestamp14 = new Timestamp(date14.getTime());
	
				preparedStmt14.setTimestamp(1, null);
				preparedStmt14.setTimestamp(2, timestamp14);
				preparedStmt14.setString(3,  removeUser);
				preparedStmt14.setString(4,username2);
				System.out.println(preparedStmt14);
				// execute the java preparedstatement
				if(preparedStmt14.executeUpdate()>0){
					System.out.println("Successfully removed friend");
				}
				else	    	  
					System.out.println("Can't remove  friend");
			}
	}

	/**
	 * For cancelling friend request
	 * @param username2
	 * @param connection
	 * @throws IOException
	 * @throws SQLException
	 */
	public void cancelFriendRequest(String username2, Connection connection) throws IOException, SQLException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String cancelUname;		
		System.out.println("Enter the username to cancel friend request");
		cancelUname=br.readLine();

		if(!verifyUser(connection, cancelUname)){
			System.out.println("User Verification Failed!");
			return;
		}
		if(!endUser(connection, cancelUname)){
			System.out.println("User is not a end User");
			return;
		}
		String query11 = "Update friendship set WhenWithdrawn = ?  where requester_username = ? and requested_username = ? and whenConfirmed is ? and whenRejected is ? and whenWithdrawn is ?";
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
			System.out.println("Successfully canceled request");
		}
		else
			System.out.println("Can't cancel request");




	}
}
