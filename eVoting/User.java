package eVoting;

public class User {

	private String id = "0000000000";

	protected String getId() {
		return this.id;
	}

	/**
	 * 
	 * @param id
	 * 
	 * Set the current user id number
	 */
	protected void setId(String id) {
		this.id = id;
	}


}
