package eVoting;

public class PollingOfficial extends User {

	PollingOfficial() {
		// TODO - implement PollingOfficial.PollingOfficial
	}

	/**
	 * Print the current tally of submitted votes on the machine
	 */
	void printResults() {
		// TODO -LOW PRIORITY implement PollingOfficial.printResults, interface with printer methods
	}

	/**
	 * Retrieve current ballot tally
	 */
	String getResults() {
		// TODO - @hassam, should we put this here? It seems potentially problematic because we shouldn't really do DB queries here
		return "Clinton: 0, Trump: 0";
	}

}