package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entities.Datum;

public class HealthDataDBHanlder {

	private static Connection connection = null;
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

	static public String saveDatum ( Datum data ) throws SQLException{

		long seconds = System.currentTimeMillis();
		PreparedStatement ps;
		String message;
		String query = "Insert into Datum(Username , propertyid,value, whensaved) values(  ? , ?, ? ,? )" ;
		try {
			ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, data.getUsername());
			ps.setInt(2, data.getPropertyID());
			ps.setString(3, data.getValue());
			ps.setDate(4, new Date(seconds) );
			ps.execute();
			message="0:Successfully Added";
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int id = rs.getInt(1);
			System.out.println("New ID is: "+id);

			if(ps.getUpdateCount() > 0 ) {
				System.out.println("1 row inserted");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message="-1:Error in Adding";
		}
		return message;

	}

	public static  ArrayList<Datum> getData (String username){

		PreparedStatement ps ;
		ArrayList<Datum> datumList = new ArrayList<>();

		String query = " Select * from Datum where username = (?) ";

		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, username);

			ResultSet rs = ps.executeQuery();

			while( rs.next() ){

				int propertyId = rs.getInt(3);
				String value = rs.getString(4);
				Date when = rs.getDate(5);
				int datumId= rs.getInt(1);

				Datum data = new Datum(datumId, username, propertyId, value, when);
				datumList.add(data);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datumList;
	}
}
