package eVoting;

public class User {

	private int id = 0000000000;
	private String type;

	protected int getId() {
		return this.id;
	}

	/**
	 * 
	 * @param id
	 * 
	 * Set the current user id number
	 */
	protected void setId(int id) {
		this.id = id;
	}

	/**
	 * Get type of user 
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * 
	 * @param type
	 * 
	 * Set the type of user accessing the system
	 */
	public void setType(String type) {
		this.type = type;
	}

}
