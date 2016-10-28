package entities;
/**
 * 
 */

/**
 * @author GUPTA
 *
 */
public class Property {

	public Property(int propertyID, String name, String description) {
		super();
		this.propertyID = propertyID;
		this.name = name;
		this.description = description;
	}
	private int propertyID;
	private String name;
	private String description;
	public int getPropertyID() {
		return propertyID;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public void setPropertyID(int propertyID) {
		this.propertyID = propertyID;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
