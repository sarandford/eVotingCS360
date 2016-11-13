package eVoting;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import eVoting.Voter;
import eVoting.PollingOfficial;


public class Driver {

	private int signInCounter = 0;
	private User user;
	private int voterSignInCount = 3;
	/**
	 * 
	 * @param id
	 * 
	 * Validate the user's information and access permissions
	 */
	protected boolean validate(int id, String userType) {
		//TODO @hassam check for presence in DB of the specified user type
		if(id == 1 && userType.equals("pollingOfficial")){
			return true;
		}
		else if(id == 2 && userType.equals("voter")){
			return true;
		}
		else{
			return false;
		}
	}

	/**
	 * 
	 * @param id
	 * @param birthdate
	 * @param name
	 * 
	 * Create an instance of the Voter class
	 */
	Voter createVoter(Integer id) {
		//TODO -@hassam query the voter table and pull out the name and dob for this voter to create the object
		return new Voter("10-08-1994", "Sarah Ford");
		}

	/**
	 * 
	 * @param id
	 * 
	 * Create an instance of the PollingOfficial class
	 */
	PollingOfficial createPollingOfficial(Integer id) {
		return new PollingOfficial();
	}

	/**
	 * Gather available candidates on the ballot
	 */
	String getCandidates() {
		//TODO: @hassam query for candidates, this should probably be a 
		return "Trump, Clinton";
	}

	/**
	 * 
	 * @param selection
	 * 
	 * Submit the voter's selection into the database
	 */
	void postVote(Vote selection) {
		// TODO - @hassam persist to DB
	}

	/**
	 * Confirm the voter's selection was correctly chosen 
	 */
	void confirmChoice() {
		// TODO - implement Driver.confirmChoice
		
	}

	/**
	 * 
	 * @param choice
	 * 
	 * Set the voter's candidate selection
	 */
	void setChoice(String choice) {
		// TODO - implement Driver.setChoice
	}

	/**
	 * Alert the polling official when voter id is entered incorretly
	 * 	three times. 
	 */
	void alertOfficial() {
		// TODO - implement Driver.alertOfficial
	}
	
	public static void main(String[] args){
		Driver driver = new Driver();
		while(true){
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			try{
				System.out.println("Welcome to the voting booth. Are you a Polling official or a Voter?\nType P for polling official and V for Voter");
				String userType = reader.readLine();
				if(userType.equals("P")){
					System.out.println("Please enter your id: ");
					Integer id = Integer.valueOf(reader.readLine());
					if(driver.validate(id, "pollingOfficial")){
						PollingOfficial po = driver.createPollingOfficial(id);
						System.out.println("SUCCESS: Welcome polling official. The current results of the election are displayed below: ");
						System.out.println(po.getResults());
						break;
					
					}
					else{
						System.out.println("Your id does not match that of a valid polling official. You will now be promprted to try again");
						continue;
					}
				}
				else if(userType.equals("V") && driver.voterSignInCount > 0){
					System.out.println("Please enter your id: ");
					driver.voterSignInCount--;
					Integer id = Integer.valueOf(reader.readLine());
					if(driver.validate(id, "voter")){
						Voter voter = driver.createVoter(id);
						if(voter.verifyPersonalInfo()){
							while(true){
								System.out.println("Displayed below are the choices for 2016 American Presidential Election:\n1.Hilary Clinton 2.Donald Trump\n Please enter the number corresponding to the canidate of your choice");
								Integer choice = Integer.valueOf(reader.readLine());
								voter.setVote(choice.toString());
								System.out.printf("You choose %s. Do you wish to change your choice. Enter Y or N", voter.getVote());
								if(reader.readLine().equals("N")){
									System.out.println("Congratulations! You cast your vote in the 2016 Presidential Election. Your ballot will now be printed");
									System.exit(0);
								}
								else{
									System.out.println("You choose to change your vote or did not enter either Y/N. Please try to cast your vote again");
								}
							}
						}
						else{
							System.out.printf("Since the ID did not match your infromation, you will have to start over you have %n more attempts to enter your id correctly", driver.voterSignInCount);
						}
						
						
					}
					
				}
				else if(userType.equals("V") && driver.voterSignInCount <= 0){
					System.out.println("You have entered your id incorrectly too many times\nYour device wil now emit a noise to notify a polling official");
					while(true){
						System.out.println("Polling official id: ");
						if(driver.validate(Integer.valueOf(reader.readLine()), "pollingOfficial")){
							System.out.println("The poling official will now enter the voter's id for them per the rules of the voting system");
							driver.voterSignInCount = 3;
							break;
						}
					}
					continue;
				}
				else{
					System.out.println("Please choose P or V. You will be prompted to try again.");
					continue;
				}
				
				
				
			}
			catch(IOException error){
				System.out.println("Please try again: " + error.getMessage());
			}
			
		}
	
	}

}