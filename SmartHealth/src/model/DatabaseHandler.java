package model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;



public class DatabaseHandler {
	
	private static final String URL = "jdbc:mysql://localhost/smarthealthdb";
    private static final String USER = "root";
    private static final String PASSWORD = "nitish";
    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver"; 

	public static Connection createConnection() throws ClassNotFoundException
	{
		Class.forName(DRIVER_CLASS);
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	/*
	 * Prints table as given in table name 
	 */
	public static void viewTable(Connection con, String tbnm)
			throws SQLException {

		java.sql.Statement stmt = null;
		String query = "select * from "+tbnm;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String username = rs.getString(1);          
				System.out.println(username);
			}
		} catch (SQLException e ) {
			e.printStackTrace();
		} 
	}

	


}

