package eVoting;

public class Screen {

	private int windowHeight = 100;
	private int windowWidth = 100;

	/**
	 * Display initial sign-in screen
	 */
	protected void showStart() {
		// TODO - implement Screen.showStart
	}

	/**
	 * 
	 * @param candidates
	 * @param voter
	 * 
	 * Display voter ballot on screen
	 */
	protected void showBallot(String candidates, Voter voter) {
		// TODO - implement Screen.showBallot
	}

	/**
	 * 
	 * @param voter
	 * 
	 * Display voter's choices and confirm or deny buttons
	 */
	protected void showConfirmVote(Voter voter) {
		// TODO - implement Screen.showConfirmVote
	}

	/**
	 * 
	 * @param tallyResults
	 * @param candidates
	 * 
	 * Show the current tally count 
	 * 	- Used only by PollingOfficial
	 */
	protected void showCurrentTally(String tallyResults, String candidates) {
		// TODO - implement Screen.showCurrentTally
	}

	/**
	 * 
	 * @param id
	 * 
	 * Get typed in id from user input
	 */
	protected void recieveId(int id) {
		// TODO - implement Screen.recieveId
	}

}