package entities;
import java.sql.Date;

/**
 * 
 */

/**
 * @author 
 *
 */
public class Datum {

	
	private int DatumID; /* Unique Datum id */
	private String username; /* datum of user */
	private int PropertyID; /* property */
	private String value; /* value of the property */
	private Date whenSaved; /* date of saving datum */
	
	
	public int getDatumID() {
		return DatumID;
	}
	public String getUsername() {
		return username;
	}
	public int getPropertyID() {
		return PropertyID;
	}
	public String getValue() {
		return value;
	}
	public Date getWhenSaved() {
		return whenSaved;
	}
	public void setDatumID(int datumID) {
		DatumID = datumID;
	}
	public void setPropertyID(int propertyID) {
		PropertyID = propertyID;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public void setWhenSaved(Date whenSaved) {
		this.whenSaved = whenSaved;
	}
	
	public Datum(int datumID, String username, int propertyID, String value, Date whenSaved) {
		super();
		DatumID = datumID;
		this.username = username;
		PropertyID = propertyID;
		this.value = value;
		this.whenSaved = whenSaved;
	}
	
}
