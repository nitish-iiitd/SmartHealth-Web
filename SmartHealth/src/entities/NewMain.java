package entities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import entities.Admin;
import entities.Moderator;
import entities.NUser;
import entities.User;
//import preprocess.Preprocess;
import model.DatabaseHandler;

public class NewMain {

	private static Connection connection = null;
	private static final int NEW_USER = 101 ;
	private static final int INIT_KARMA = 0 ;
	private static final int NEW_MOD = 200;


	public static void main(String[] args) throws ClassNotFoundException, SQLException, NumberFormatException, IOException {
		// TODO Auto-generated method stub
		connection = DatabaseHandler.createConnection();
		// Creating the usertype table
		//Preprocess.createUserType();
		// Creating admin
		//Preprocess.createAdmin();

		int ch ; 
		User u = null;
		// Main menu
		do{
			System.out.println("<=============  Smart Health  ==============>");
			System.out.println("1.Login\n2.Register as User\n3.Register as Moderator\n4.Exit\nEnter your choice: ");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			ch = Integer.parseInt(br.readLine());
			switch(ch){

			case 1:  	
				System.out.println("Enter Primary Email:"); 
				String email = br.readLine();
				System.out.println("Enter Password:"); 
				String pass = br.readLine();
				u = loginAndGetType(email,pass);
				if (u==null){
					System.out.println("Authentication Faliure!!");


				}

				else if ( u.getType() == 101 || u.getType() == 102  || u.getType() == 103 || u.getType()== 200 ) 	{
					int ch1;
					do
					{
						System.out.println("Logged in as "+u.getUsername()+"\n1.Update Profile\n2.Quit\n3.Logout");
						if(u.getType()!=200)
							System.out.println("4.Send Friend Request \n5.List Friends \n6.See Friend Requests Received \n7.Cancel Friend Request \n8.Remove Friends");

						System.out.println("Enter your choice: ");
						ch1 = Integer.parseInt(br.readLine());
						if(ch1==3)
						{
							u=null;
							System.out.println("Logged out.");
							break;
						}
						else if(ch1 == 1)
						{
							u.updateUser(connection, email);

						}
						else if(ch1 == 2)
						{
							u.quitUser(connection);
							break;
						} 
						else if(ch1==4 && u.getType()!=200){

							u.sendRequest(u.getUsername(),connection);

						}
						else if(ch1==5 && u.getType()!=200){
							u.listFriends(u.getUsername(),connection);


						}
						else if(ch1==6 && u.getType()!=200){
							u.seeFriendRequests(u.getUsername(),connection);



						}else if(ch1==7 && u.getType()!=200){
							u.cancelFriendRequest(u.getUsername(),connection);

						}
						else if(ch1==8 && u.getType()!=200){
							u.removeFriends(u.getUsername(),connection);

						}else{
							System.out.println("Invalid option");
						}
					}while(ch1!=2 || ch!=3);
				}
				else if ( u.getType() == 300)
				{
					//System.out.println("Logged in as admin.");
					//u.display();
					int ch1=0;
					do
					{
						System.out.println("Logged in as "+u.getUsername()+" as admin\n1.Display All Users \n2.Logout\nEnter your choice: ");
						ch1 = Integer.parseInt(br.readLine());

						if(ch1==2)
						{
							u=null;
							System.out.println("Logged out.");
							break;
						}
						else if(ch1 == 1)
						{
							DatabaseHandler.viewTable(connection, "User");

						}
						else
						{
							System.out.println("Invalid option");
						}
					}while(ch1!=2);
				}

				else {
					System.out.println("Invalid details !");
				}
				break;
			case 2:	
				createNUser();
				break;
			case 3: createModerator();
			break;
			case 4: 
				connection.close();	System.exit(0);
			default : 
				System.out.println("No such choice");
			}
		}while(ch!=4 || u==null);

		connection.close();
	}// End of Main

	
	
	/*
	 * Creates new moderator by asking user details(registration) 
	 */
	public static void createModerator() throws IOException, SQLException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter username:"); 
		String username = br.readLine();		
		System.out.println("Enter primary email:"); 
		String emailp = br.readLine();
		System.out.println("Enter password:"); 
		String pass = br.readLine();
		System.out.println("Enter firstname:"); 
		String fname = br.readLine();
		System.out.println("Enter lastname:"); 
		String lname = br.readLine();	

		System.out.println("Enter street number : "); 
		String sno = "A-333";//br.readLine();
		System.out.println("Enter street name  : "); 
		String snm = "address";//br.readLine();
		System.out.println("Enter municipality: "); 
		String mun = "about";//br.readLine();
		System.out.print("Enter district : "); 
		String dist = "url1";//br.readLine();
		System.out.println("Enter postal area : "); 

		String parea = "url2";//br.readLine();
		//		//System.out.println("Enter url3:"); 
		//		String url3 = "url3";//br.readLine();
		//		//--------------------------------------
		//---------------------------------------

		System.out.println("Enter Qualification( Use , to seperate Degrees");
		String temp = br.readLine();
		String qual[]=temp.split(",");

		System.out.println("Enter Phone Number");
		String phone = br.readLine();


		System.out.println("Enter secondary email:"); 
		String emails = br.readLine();
		//System.out.println("Enter address:"); 
		//String paddress = br.readLine();
		System.out.println("Enter about you:"); 
		String about =br.readLine();
		System.out.println("Enter url1 for profile pic:"); 
		String url1 = br.readLine();
		System.out.println("Enter url2 for profile pic:"); 
		String url2 = br.readLine();
		System.out.println("Enter url3 for profile pic:"); 
		String url3 = br.readLine();
		//--------------------------------------

		if(checkValidEmail(emailp))
		{
			if(checkUsernameAndEmailAvailable(username, emailp))
			{
				long millis=System.currentTimeMillis();  
				java.sql.Date date=new java.sql.Date(millis);  
				//NUser newnuser = new NUser(username , pass , emailp , emails, fname ,lname , about , url1, url2, 
				//	url3 ,sno,snm, mun, dist,parea, NEW_USER, true, INIT_KARMA , date  );
				try
				{			
					String createAdminInUserSql  = "insert into User values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					PreparedStatement stmt = connection.prepareStatement(createAdminInUserSql);

					String createModeratorSql  = "insert into Moderator values(?,?)";
					PreparedStatement stmt2 = connection.prepareStatement(createModeratorSql);
					stmt2.setString(1,username);
					stmt2.setString(2,phone);

					stmt.setString(1,username);
					stmt.setString(2,pass);
					stmt.setString(3,emailp);
					stmt.setString(4,emails);
					stmt.setString(5,fname);
					stmt.setString(6,lname);
					stmt.setString(7,about);
					stmt.setString(8,url1);
					stmt.setString(9,url2);
					stmt.setString(10,url3);
					stmt.setString(11,sno);
					stmt.setString(12,snm);
					stmt.setString(13,mun);
					stmt.setString(14,dist);
					stmt.setString(15,parea);
					stmt.setInt(16,NEW_MOD);
					stmt.setBoolean(17,true);
					//System.out.println(stmt);

					int rs = stmt.executeUpdate();
					int rs2 = stmt2.executeUpdate();
					if(rs > 0 && rs2>0){
						System.out.println("New User added");
					}
					else{
						System.out.println("New Moderator addition failed!!");
					}
				}catch(Exception e)
				{
					System.out.println("Error in Adding to Database");
					e.printStackTrace();
				}
				System.out.println("Moderator Successfully registered.");


				//  search in the qualification table if qualification is not preset then insert it into the table.
				String  qualQuery = "select * from qualification";
				PreparedStatement stmnt3= connection.prepareStatement(qualQuery);
				ResultSet rs3 = stmnt3.executeQuery(qualQuery);

				boolean flag=true;

				for(int i=0;i<qual.length;i++){
					qualQuery = "select * from qualification";
					stmnt3= connection.prepareStatement(qualQuery);
					rs3 = stmnt3.executeQuery(qualQuery);

					flag=true;
					int id = 0;
					String ql;
					while(rs3.next()){
						id= rs3.getInt(1);
						ql = rs3.getString(2);
						if(qual[i].equals(ql)){
							flag=false;
							break;
						}
					}
					if(flag){
						// finding the auto increment value
						/*	String findAutoIncrement= "SELECT AUTO_INCREMENT FROM qualification";
									PreparedStatement stmt6 = connection.prepareStatement(findAutoIncrement);
									ResultSet rs6 = stmt6.executeQuery();
									while(rs6.next()){
										id = rs6.getInt(1);
										System.out.println("Auto Incremented value: "+id);

									}
						 */

						System.out.println("Inserting  qualification: "+qual[i]);
						String createModeratorSql  = "insert into qualification (description) values(?)";
						PreparedStatement stmt5 = connection.prepareStatement(createModeratorSql, Statement.RETURN_GENERATED_KEYS);
						stmt5.setString(1,qual[i]);
						int rs5 = stmt5.executeUpdate();
						// Auto Incremented Id
						ResultSet rs = stmt5.getGeneratedKeys();
						rs.next();
						id = rs.getInt(1);
						System.out.println("New ID is: "+id);

						if(rs5 > 0){
							System.out.println("New Qualification added");

						}
						else{
							System.out.println("Failed!! to add new Qualification.");
						}

					}
					else{
						System.out.println("Qualification Already present");
					}

					String ModeratorQual  = "insert into ModeratorQualification values(?,?,?)";
					PreparedStatement stmt13 = connection.prepareStatement(ModeratorQual);
					stmt13.setInt(1,id);
					stmt13.setString(2,username);
					stmt13.setDate(3, date);
					int rs12 = stmt13.executeUpdate();

					if(rs12>0){
						System.out.println("Successfully Added Moderator Qualification ");
					}


				}

			}
			else
			{
				System.out.println("Username or email is already in use !!");
			}



		}
		else
		{
			System.out.println("Invalid email id !!");
		}



	}

	/*
	 * Creates end user
	 */
	public static void createNUser() throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter username:"); 
		String username = br.readLine();		
		System.out.println("Enter primary email:"); 
		String emailp = br.readLine();
		System.out.println("Enter password:"); 
		String pass = br.readLine();
		System.out.println("Enter firstname:"); 
		String fname = br.readLine();
		System.out.println("Enter lastname:"); 
		String lname = br.readLine();	

		//		//---------------------------------------
		System.out.println("Enter street number : "); 
		//String sno = "A-333";
		String sno = br.readLine();
		System.out.println("Enter street name  : "); 
		//String snm = "address";
		String snm = br.readLine();
		System.out.println("Enter municipality: "); 
		//String mun = "about";
		String mun = br.readLine();
		System.out.print("Enter district : "); 
		//String dist = "url1";
		String dist = br.readLine();
		System.out.println("Enter postal area : "); 
		//String parea = "url2";
		String parea = br.readLine();
		//		//System.out.println("Enter url3:"); 
		//		String url3 = "url3";//br.readLine();
		//		//--------------------------------------
		//---------------------------------------
		System.out.println("Enter secondary email:"); 
		String emails = br.readLine();
		if(emails.equals(emailp))
		{
			System.out.println("Primaray and secondry email can't be same.");
			return;
		}
		//System.out.println("Enter address:"); 
		//String paddress = br.readLine();
		System.out.println("Enter about you:"); 
		String about =br.readLine();
		System.out.println("Enter url1 for profile pic:"); 
		String url1 = br.readLine();
		System.out.println("Enter url2 for profile pic:"); 
		String url2 = br.readLine();
		System.out.println("Enter url3 for profile pic:"); 
		String url3 = br.readLine();
		//--------------------------------------

		if(checkValidEmail(emailp))
		{
			if(checkUsernameAndEmailAvailable(username, emailp))
			{
				long millis=System.currentTimeMillis();  
				java.sql.Date date=new java.sql.Date(millis);  
				//NUser newnuser = new NUser(username , pass , emailp , emails, fname ,lname , about , url1, url2, 
				//	url3 ,sno,snm, mun, dist,parea, NEW_USER, true, INIT_KARMA , date  );
				try
				{
					String createAdminInUserSql  = "insert into User values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					PreparedStatement stmt = connection.prepareStatement(createAdminInUserSql);

					String createNuserSql  = "insert into enduser values(?,?,?)";
					PreparedStatement stmt2 = connection.prepareStatement(createNuserSql);

					stmt.setString(1,username);
					stmt.setString(2,pass);
					stmt.setString(3,emailp);
					stmt.setString(4,emails);
					stmt.setString(5,fname);
					stmt.setString(6,lname);
					stmt.setString(7,about);
					stmt.setString(8,url1);
					stmt.setString(9,url2);
					stmt.setString(10,url3);
					stmt.setString(11,sno);
					stmt.setString(12,snm);
					stmt.setString(13,mun);
					stmt.setString(14,dist);
					stmt.setString(15,parea);
					stmt.setInt(16,NEW_USER);
					stmt.setBoolean(17,true);
					//System.out.println(stmt);


					stmt2.setString(1,username);
					stmt2.setInt(2,INIT_KARMA);
					stmt2.setDate(3,date);

					int rs = stmt2.executeUpdate();

					int rs2 = stmt.executeUpdate();

					if(rs > 0 && rs2>0){
						System.out.println("New User added");
					}
					else{
						System.out.println("New User addition failed!!");
					}
				}
				catch(Exception e)
				{
					System.out.println("Error in Adding to Database");
					e.printStackTrace();
				}


				System.out.println("Successfully registered.");
			}
			else
			{
				System.out.println("Username or email is already in use !!");
			}
		}
		else
		{
			System.out.println("Invalid email id !!");
		}



	}


	/*
	 * Checks if the username and email already exists
	 */
	public static boolean checkUsernameAndEmailAvailable(String username,String email)
	{

		try {

			String query = "select * from user where username = ? and email1 = ? ";
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1,username);
			stmt.setString(2,email);
			ResultSet rs = stmt.executeQuery();
			//System.out.println(rs.getString("username"));

			if(rs.next())
				return false;
			else
				return true;

		}catch (SQLException e ) {
			e.printStackTrace();
		}

		return true;
	}

	/*
 * Checks validity of email
 */
public static boolean checkValidEmail(String email)
{
	String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	return email.matches(EMAIL_REGEX);
}
/*
 * Returns the USER object after authentication
 */
public static User loginAndGetType  (String email ,String pass) throws SQLException
{

	try {

		String query = "select * from user where Email1 = ? and Password = ? ";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setString(1,email);
		stmt.setString(2,pass);
		ResultSet rs = stmt.executeQuery();
		System.out.println(rs);
		if(rs.next()) {
			String username="";
			try{
				username = rs.getString(1);
				System.out.println(username);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			String es = rs.getString(4);
			String fname = rs.getString(5);
			String lname = rs.getString(6);
			String about = rs.getString(7);
			String url1 = rs.getString(8);
			String url2 = rs.getString(9);
			String url3 = rs.getString(10);
			String sno = rs.getString(11);
			String sname = rs.getString(12);
			String mun = rs.getString(13);
			String dist = rs.getString(14);
			String parea = rs.getString(15);
			Boolean status = rs.getBoolean(17);
			int userType = rs.getInt(16);
			ResultSet rs1;
			PreparedStatement stmt1;
			User ob=null;

			if(userType ==101 || userType ==103 ||userType ==102 ){
				String query1 = "select * from enduser where username = ? ";

				stmt1 = connection.prepareStatement(query1);
				stmt1.setString(1, username);
				try{
					ob=null;
					rs1 = stmt1.executeQuery();
					if(rs1.next()){
						int karma = rs1.getInt(2);
						java.sql.Date date = rs1.getDate(3);
						ob = new NUser( username , pass, email,es,fname,lname,about,url1,url2,url3,sno,sname,mun,dist,parea,userType,status,karma, date);
						System.out.println("End USer"+ob);	
					}

					return ob;
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
			else if ( userType == 200 ) {

				String query1 = "select * from moderator where username = ? ";
				stmt1 = connection.prepareStatement(query1);
				stmt1.setString(1, username);
				rs1 = stmt1.executeQuery();
				if(rs1.next()){
					String phone = rs1.getString(2);
					ob = new Moderator( username , pass, email,es,fname,lname,about,url1,url2,url3,sno,sname,mun,dist,parea,userType,status, phone);
					System.out.println("Mod"+ob);
				}
				return ob;
			}
			else if ( userType == 300 ) {
				System.out.println("inside admin");
				String query1 = "select * from administrator where username = ?";
				PreparedStatement stmt2 = connection.prepareStatement(query1);
				stmt2.setString(1, username);
				String phone = null;
				//System.out.println(stmt2);
				try
				{
					ResultSet rs2 = stmt2.executeQuery();

					while (rs2.next()) {
						phone = rs2.getString("phone");
						//  System.out.println(phone);
					}
					if(rs2!=null)
						System.out.println(rs2);

					ob = new Admin( username , pass, email,es,fname,lname,about,url1,url2,url3,sno,sname,mun,dist,parea,userType,status, phone );
					System.out.println("admin"+ob);
					return ob;
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	} catch (SQLException e ) {
		e.printStackTrace();
	} 

	return null;
}


}
