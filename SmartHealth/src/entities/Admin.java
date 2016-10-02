package entities;

public class Admin extends User{
	
	private String emer_contact;


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
	 * @param emer_contact
	 */
	public Admin(String username, String pass, String emailp, String emails,
			String fname, String lname, String about, String url1, String url2,
			String url3, String streetNumber, String streetName,
			String majorMunicipality, String governingDistrict,
			String postalArea, int type, boolean status, String emer_contact) {
		super(username, pass, emailp, emails, fname, lname, about, url1, url2,
				url3, streetNumber, streetName, majorMunicipality,
				governingDistrict, postalArea, type, status);
		this.emer_contact = emer_contact;
	}

	
	
	public String getEmer_contact() {
		return emer_contact;
	}



	public void setEmer_contact(String emer_contact) {
		this.emer_contact = emer_contact;
	}



}
