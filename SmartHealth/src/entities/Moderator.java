package entities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Statement;


public class Moderator extends User{


	String emer_contact; /* emergency contact number of moderator*/


	/**
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
	 * @param qual
	 * @param emer_contact
	 */
	public Moderator(String username, String pass, String emailp,
			String emails, String fname, String lname, String about,
			String url1, String url2, String url3, String streetNumber,
			String streetName, String majorMunicipality,
			String governingDistrict, String postalArea, int type,
			boolean status, String emer_contact) {
		super(username, pass, emailp, emails, fname, lname, about, url1, url2,
				url3, streetNumber, streetName, majorMunicipality,
				governingDistrict, postalArea, type, status);

		this.emer_contact = emer_contact;
	}
	public void updateContact() {
		System.out.println("Enter new contact number: ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			this.setEmer_contact( br.readLine());
		} catch (IOException e) {
			System.out.println("Error in Updating Contact.");
		}
	}


	public void updateUser(Connection con, String email ) throws IOException, SQLException{

		super.updateUser(con, email);
		String query = "Select * from Qualification where Description = ?";
		PreparedStatement ps = null;
		String qual;


		try {

			String query2 = "select * from user where Email1 = ?";
			PreparedStatement stmt = con.prepareStatement(query2);
			stmt.setString(1,email);
			ResultSet rs = stmt.executeQuery();
			System.out.println(rs);
			String username="";
			if(rs.next()) {
				username = rs.getString(1);
				//System.out.println(username);
			}
			String delQuery = "delete  from moderatorQualification where username = ?";
			ps=con.prepareStatement(delQuery);
			ps.setString(1, username);
			int rs12=ps.executeUpdate();
			if(rs12>0)
				System.out.println("Old Qualification is deleted!");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}



		System.out.println("Enter new qualification(split by , ) ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		qual = br.readLine();
		String q[] = qual.split(",");
		int id;
		for ( int i = 0 ; i < q.length ; i++){
			ps = con.prepareStatement(query);
			ps.setString(1 , q[i] );
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				id = rs.getInt(1);
			}
			else{
				System.out.println("Inserting  qualification: "+q[i]);
				String createModeratorSql  = "insert into qualification (description) values(?)";
				PreparedStatement stmt5 = con.prepareStatement(createModeratorSql, Statement.RETURN_GENERATED_KEYS);
				stmt5.setString(1,q[i]);
				int rs5 = stmt5.executeUpdate();
				// Auto Incremented Id
				ResultSet rs2 = stmt5.getGeneratedKeys();
				rs2.next();
				id = rs2.getInt(1);
				System.out.println("New ID is: "+id);

				if(rs5 > 0){
					System.out.println("New Qualification added");

				}
				else{
					System.out.println("Failed!! to add new Qualification.");
				}

			}


			long millis=System.currentTimeMillis();  
			java.sql.Date date=new java.sql.Date(millis);  
			String ModeratorQual  = "insert into ModeratorQualification values(?,?,?)";
			PreparedStatement stmt13 = con.prepareStatement(ModeratorQual);
			stmt13.setInt(1,id);
			stmt13.setString(2,username);
			stmt13.setDate(3, date);
			int rs13 = stmt13.executeUpdate();

			if(rs13>0){
				System.out.println("Successfully Added Moderator Qualification ");
			}

		}



	}
	/***
	 * Update the qualification of moderator
	 */

	public void updateQual(){
		// to be updated with class qualification

	}

	public void display(){
		System.out.println("Fname\tLname\tAboutMe\tPassword\tURL1\tURL2\tURL3\tQualification\tContact");
		//System.out.println(getFname()+"\t"+getLname()+"\t"+getAbout()+"\t"+"\t"+getPass()+"\t"+getUrl1()+"\t"+getUrl2()+"\t"+getUrl3()+"\t"+getEmer_contact());

	}

	public String getEmer_contact() {
		return emer_contact;
	}
	public void setEmer_contact(String emer_contact) {
		this.emer_contact = emer_contact;
	}


}
