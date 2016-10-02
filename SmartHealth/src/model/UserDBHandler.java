package model;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

import entities.Admin;
import entities.Moderator;
import entities.NUser;
import entities.User;

public class UserDBHandler {
	private static Connection connection = null;
	private static final int NEW_USER = 101;
	private static final int INIT_KARMA = 0;
	private static final int NEW_MOD = 200;

	public UserDBHandler() throws ClassNotFoundException {
		connection = DatabaseHandler.createConnection();
	}

	/*
	 * Creates end user
	 */
	public static String addNUser(NUser nuser) throws IOException {
		
		String result="-1:Unknown Error";
		
		String username = nuser.getUsername();
		String emailp = nuser.getEmailp();
		String pass = nuser.getPass();
		String fname = nuser.getFname();
		String lname = nuser.getLname();
		String sno = nuser.getStreetNumber();// "A-333";//br.readLine();
		String snm = nuser.getStreetName();// "address";//br.readLine();
		String mun = nuser.getMajorMunicipality();// "about";//br.readLine();
		String dist = nuser.getGoverningDistrict();// "url1";//br.readLine();
		String parea = nuser.getPostalArea();// "url2";//br.readLine();
		String emails = nuser.getEmails();
		String about = nuser.getAbout();
		String url1 = nuser.getUrl1();
		String url2 = nuser.getUrl2();
		String url3 = nuser.getUrl3();
		// --------------------------------------
		if (checkValidEmail(emailp)) {
			if (checkUsernameAndEmailAvailable(username, emailp)) {
				long millis = System.currentTimeMillis();
				java.sql.Date date = new java.sql.Date(millis);
				try {
					String createEnduserInUserSql = "insert into User values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					PreparedStatement stmt = connection.prepareStatement(createEnduserInUserSql);
					stmt.setString(1, username);
					stmt.setString(2, pass);
					stmt.setString(3, emailp);
					stmt.setString(4, emails);
					stmt.setString(5, fname);
					stmt.setString(6, lname);
					stmt.setString(7, about);
					stmt.setString(8, url1);
					stmt.setString(9, url2);
					stmt.setString(10, url3);
					stmt.setString(11, sno);
					stmt.setString(12, snm);
					stmt.setString(13, mun);
					stmt.setString(14, dist);
					stmt.setString(15, parea);
					stmt.setInt(16, NEW_USER);
					stmt.setBoolean(17, true);
					String createNuserSql = "insert into enduser values(?,?,?)";
					PreparedStatement stmt2 = connection.prepareStatement(createNuserSql);
					stmt2.setString(1, username);
					stmt2.setInt(2, INIT_KARMA);
					stmt2.setDate(3, date);
					int rs2 = stmt2.executeUpdate();
					int rs = stmt.executeUpdate();
					if (rs > 0 && rs2 > 0) {
						System.out.println("0:New User added");
						result = "0:New User added";
					} else {
						System.out.println("-1:New User addition failed!!");
						result = "-1:New User addition failed!!";
					}
				} catch (Exception e) {
					System.out.println("-1:Error in Adding to Database");
					result = "-1:Error in Adding to Database";
					e.printStackTrace();
				}
				System.out.println("0:Successfully registered.");
				result="0:Successfully registered.";
			} else {
				System.out.println("-1:Username or email is already in use !!");
				result="-1:Username or email is already in use !!";
			}
		} else {
			System.out.println("-1:Invalid email id !!");
			result= "-1:Invalid email id !!";
		}
		return result;
	}

	/*
	 * Creates new moderator by asking user details(registration)
	 */
	public static void addModerator(Moderator moderator) throws IOException {
		String username = moderator.getUsername();
		String emailp = moderator.getEmailp();
		String pass = moderator.getPass();
		String fname = moderator.getFname();
		String lname = moderator.getLname();
		String sno = moderator.getStreetNumber();// "A-333";//br.readLine();
		String snm = moderator.getStreetName();// "address";//br.readLine();
		String mun = moderator.getMajorMunicipality();// "about";//br.readLine();
		String dist = moderator.getGoverningDistrict();// "url1";//br.readLine();
		String parea = moderator.getPostalArea();// "url2";//br.readLine();
		String phone = moderator.getEmer_contact();
		String emails = moderator.getEmails();
		String about = moderator.getAbout();
		String url1 = moderator.getUrl1();
		String url2 = moderator.getUrl2();
		String url3 = moderator.getUrl3();
		// --------------------------------------
		if (checkValidEmail(emailp)) {
			if (checkUsernameAndEmailAvailable(username, emailp)) {
				try {
					String createModeratorInUserSql = "insert into User values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					PreparedStatement stmt = connection.prepareStatement(createModeratorInUserSql);
					stmt.setString(1, username);
					stmt.setString(2, pass);
					stmt.setString(3, emailp);
					stmt.setString(4, emails);
					stmt.setString(5, fname);
					stmt.setString(6, lname);
					stmt.setString(7, about);
					stmt.setString(8, url1);
					stmt.setString(9, url2);
					stmt.setString(10, url3);
					stmt.setString(11, sno);
					stmt.setString(12, snm);
					stmt.setString(13, mun);
					stmt.setString(14, dist);
					stmt.setString(15, parea);
					stmt.setInt(16, NEW_MOD);
					stmt.setBoolean(17, true);
					String createModeratorSql = "insert into Moderator values(?,?)";
					PreparedStatement stmt2 = connection.prepareStatement(createModeratorSql);
					stmt2.setString(1, username);
					stmt2.setString(2, phone);
					int rs = stmt.executeUpdate();
					int rs2 = stmt2.executeUpdate();
					if (rs > 0 && rs2 > 0) {
						System.out.println("New Moderator added");
					} else {
						System.out.println("New Moderator addition failed!!");
					}
				} catch (Exception e) {
					System.out.println("Error in Adding to Database");
					e.printStackTrace();
				}
				System.out.println("Moderator Successfully registered.");
			} else {
				System.out.println("Username or email is already in use !!");
			}
		} else {
			System.out.println("Invalid email id !!");
		}
	}

	/*
	 * Adds a qualification to a moderator
	 */
	public static void addQualtoModerator(Moderator moderator, int qualid) throws SQLException {
		int id = qualid;
		String username = moderator.getUsername();
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		String moderatorQualSql = "insert into ModeratorQualification values(?,?,?)";
		PreparedStatement stmt13 = connection.prepareStatement(moderatorQualSql);
		stmt13.setInt(1, id);
		stmt13.setString(2, username);
		stmt13.setDate(3, date);
		int rs12 = stmt13.executeUpdate();
		if (rs12 > 0) {
			System.out.println("Successfully Added Moderator Qualification ");
		}
	}

	/*
	 * Checks if the username and email already exists
	 */
	public static boolean checkUsernameAndEmailAvailable(String username, String email) {
		try {
			String query = "select * from user where username = ? and email1 = ? ";
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, username);
			stmt.setString(2, email);
			ResultSet rs = stmt.executeQuery();
			// System.out.println(rs.getString("username"));
			if (rs.next())
				return false;
			else
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	/*
	 * Checks validity of email
	 */
	public static boolean checkValidEmail(String email) {
		String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(EMAIL_REGEX);
	}

	/*
	 * Returns the USER object after authentication
	 */
	public static User loginAndGetType(String email, String pass) throws SQLException {
		try {
			String query = "select * from user where Email1 = ? and Password = ? ";
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, email);
			stmt.setString(2, pass);
			ResultSet rs = stmt.executeQuery();
			System.out.println(rs);
			if (rs.next()) {
				String username = "";
				try {
					username = rs.getString(1);
					System.out.println(username);
				} catch (Exception e) {
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
				User ob = null;
				if (userType == 101 || userType == 103 || userType == 102) {
					String query1 = "select * from enduser where username = ? ";
					stmt1 = connection.prepareStatement(query1);
					stmt1.setString(1, username);
					try {
						ob = null;
						rs1 = stmt1.executeQuery();
						if (rs1.next()) {
							int karma = rs1.getInt(2);
							java.sql.Date date = rs1.getDate(3);
							ob = new NUser(username, pass, email, es, fname, lname, about, url1, url2, url3, sno, sname,
									mun, dist, parea, userType, status, karma, date);
							System.out.println("End USer" + ob);
						}
						return ob;
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} else if (userType == 200) {
					String query1 = "select * from moderator where username = ? ";
					stmt1 = connection.prepareStatement(query1);
					stmt1.setString(1, username);
					rs1 = stmt1.executeQuery();
					if (rs1.next()) {
						String phone = rs1.getString(2);
						ob = new Moderator(username, pass, email, es, fname, lname, about, url1, url2, url3, sno, sname,
								mun, dist, parea, userType, status, phone);
						System.out.println("Mod" + ob);
					}
					return ob;
				} else if (userType == 300) {
					System.out.println("inside admin");
					String query1 = "select * from administrator where username = ?";
					PreparedStatement stmt2 = connection.prepareStatement(query1);
					stmt2.setString(1, username);
					String phone = null;
					// System.out.println(stmt2);
					try {
						ResultSet rs2 = stmt2.executeQuery();
						while (rs2.next()) {
							phone = rs2.getString("phone");
							// System.out.println(phone);
						}
						if (rs2 != null)
							System.out.println(rs2);
						ob = new Admin(username, pass, email, es, fname, lname, about, url1, url2, url3, sno, sname,
								mun, dist, parea, userType, status, phone);
						System.out.println("admin" + ob);
						return ob;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
