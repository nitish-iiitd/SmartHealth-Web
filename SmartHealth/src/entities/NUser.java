package entities;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Date;

public class NUser extends User{
	
	int karma;
	Date dateCreated;

	
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
	 * @param karma
	 * @param dateCreated
	 */
	public NUser(String username, String pass, String emailp, String emails,
			String fname, String lname, String about, String url1, String url2,
			String url3, String streetNumber, String streetName,
			String majorMunicipality, String governingDistrict,
			String postalArea, int type, boolean status, int karma,
			Date dateCreated) {
		super(username, pass, emailp, emails, fname, lname, about, url1, url2,
				url3, streetNumber, streetName, majorMunicipality,
				governingDistrict, postalArea, type, status);
		this.karma = karma;
		this.dateCreated = dateCreated;
	}

	/**
	 * @return the karma
	 */
	public int getKarma() {
		return karma;
	}

	/**
	 * @param karma the karma to set
	 */
	public void setKarma(int karma) {
		this.karma = karma;
	}
	
    public void display(){
    	System.out.println("Fname\tLname\tAboutMe\tPostalAddress\tPassword\tURL1\tURL2\tURL3");
    	System.out.println(this.getFname()+"\t"+this.getLname()+"\t"+getAbout()+"\t"+getPass()+"\t"+getUrl1()+"\t"+getUrl2()+"\t"+getUrl3());
    	
    }
    public void updateUser(Connection con, String email) throws IOException, SQLException{
    	super.updateUser(con, email);
    	
    }
}
