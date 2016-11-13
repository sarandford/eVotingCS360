package eVoting;

public class Vote {

	private String choice;
	
	 Vote(String choice) {
		 this.choice = choice;
	 }
	 
	/**
	 * 
	 * @param newChoice
	 * 
	 * Change the candidate selection
	 */
	void updateChoice(String newChoice) {
		// TODO - implement Vote.updateChoice
	}

	/**
	 * Get the candidate selection
	 */
	String getChoice() {
		return this.choice;
		// TODO - implement Vote.getChoice
	}

}