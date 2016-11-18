package eVoting;

public class Vote {

	private String choice;
	
	 Vote(String choice, String voterId) {
		 this.choice = choice;
	 }

	/**
	 * Get the candidate selection
	 */
	String getChoice() {
		return this.choice;
	}
	


}