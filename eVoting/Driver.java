package eVoting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*; 
import java.util.Hashtable;
import java.util.Properties;

import eVoting.Voter;
import eVoting.PollingOfficial;
import eVoting.DatabaseAccess; 

public class Driver {

	private final int ALLOWEDSIGNINATTEMPTS = 3;

	
	private int signInCounter = 0;
	private int attemptsLeft = this.ALLOWEDSIGNINATTEMPTS;
	private DatabaseAccess db = new DatabaseAccess(); 
	private Connection conn; 


	public Driver(Connection conn){
		this.conn = conn; 
	}

	/**
	 * 
	 * @param id
	 * @return 0: Valid user/has NOT voted; 1: Valid voter HAS voted; 2: Invalid user 
	 * Validate the user's information and access permissions
	 */
	protected int validate(String id, String userType) {
		String userCode; 

		if(userType.equalsIgnoreCase("V")){
			userCode = "0";
		}else {
			userCode = "1"; 
		}

		return db.validateUser(this.conn, id, userCode); 

	}

	/**
	 * 
	 * @param id
	 * @param birthdate
	 * @param name
	 * 
	 * Create an instance of the Voter class
	 */
	Voter createVoter(String id) {

		Hashtable<String, String> voterInfo = db.getVoterInfo(conn, id); 

		return new Voter(voterInfo.get("birthdate"), voterInfo.get("name"), id);
	}

	/**
	 * Gather available candidates on the ballot
	 */
	String getCandidates() {

		ResultSet candidate = db.getCandidates(conn); 
		String rtn = "";

		if(candidate != null){

			try {

				while(candidate.next()){
					rtn = rtn + candidate.getString("candidateId") + ",";
					rtn = rtn + candidate.getString("name") + ",";
				}

				return rtn; 

			}catch (SQLException e){
				System.out.println(e.getMessage());
				return "";
			}

		}else {
			return ""; 
		}

	}


	/**
	 * Alert the polling official when voter id is entered incorrectly
	 * 	three times. 
	 */
	void alertOfficial() {
		// TODO - implement Driver.alertOfficial
	}

	Connection getConnetion(){
		return this.conn; 
	}

	public static void main(String[] args){

		Connection conn;
		final String user = "";
		final String password = ""; 
		final String serverName = "localhost";
		final String port = "3306";
		final String dbname = "eVoting";

		//Create connection object		
		Properties connectionProps = new Properties();
		connectionProps.put("user", user);
		connectionProps.put("password", password);
		connectionProps.put("useSSL", "false");

		DatabaseAccess db = new DatabaseAccess(); 

		try {
			conn = DriverManager.getConnection("jdbc:mysql://"
					+ serverName + ":" + port + "/" + dbname,
					connectionProps);

			Driver driver = new Driver(conn);
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


			while(true){
				try{
					System.out.println("Welcome to the voting booth. Are you a Polling official or a Voter?\nType P for polling official and V for Voter");
					String userType = reader.readLine();


					if(userType.equalsIgnoreCase("P")){
						System.out.println("Please enter your id: ");
						String id = reader.readLine();

						if(driver.validate(id, "P") == 0){
							System.out.println("\nWelcome polling official. The current results of the election are displayed below: ");
							PollingOfficial po = new PollingOfficial(); 

							po.printResults(db.getTally(conn));


							System.out.println("Press [Enter/Return] to Start Over.\n");
							reader.readLine();
						}
						else{
							System.out.println("Your id does not match that of a valid polling official. You will now be promprted to try again\n");
							continue;
						}
					}

					else if(userType.equalsIgnoreCase("V") && driver.signInCounter < ALLOWEDSIGNINATTEMPTS){
						System.out.println("Please enter your id:\n");
						String id = reader.readLine();

						driver.signInCounter++;
						System.out.println("VOTER SIGN IN COUNT: " + driver.signInCounter+ "\n");


						if(driver.validate(id, "V") == 0){
							Voter voter = driver.createVoter(id);
							if(voter.verifyPersonalInfo()){
								String[] candidates = driver.getCandidates().split(",");
								while(true){

									System.out.printf("Displayed below are the choices for 2016 American Presidential Election:\n"
											+ " %s %s\n "
											+ "%s %s\n "
											+ "%s %s\n "
											+ "%s %s\n\n"
											+ "Please enter the number corresponding to the candidate of your choice\n",
											candidates[0],candidates[1],candidates[2],candidates[3],candidates[4],candidates[5],candidates[6],candidates[7]);


									try {
										Integer choice = Integer.parseInt(reader.readLine());
										if(choice > 0  && choice < 5){
											voter.setVote(choice.toString());

										}
										else {
											System.out.println("You chose an invalid vald candidate number try again.\n");
											continue;
										}
									}catch (NumberFormatException e){
										System.out.println("You chose an invalid valdidate number try again.\n");
										continue;
									}




									System.out.printf("You chose %s. Do you wish to change your choice. Enter Y or N", candidates[Integer.parseInt(voter.getVote())]);
									if(reader.readLine().equalsIgnoreCase("N") && db.postVote(conn, id, voter.getVote())){
										System.out.println("Congratulations! You cast your vote in the 2016 Presidential Election. Your ballot will now be printed");

										conn.close();
										System.exit(0);
									}
									else{
										System.out.println("You chose to change your vote or did not enter either Y/N. Please try to cast your vote again");
									}
								}
							}
							else{
								System.out.printf("Since the ID did not match your information, you will have to start over you have %d more attempts to enter your id correctly\n\n", driver.attemptsLeft);
							}
						}
						else if(driver.validate(id, "V") == 2){
							driver.attemptsLeft--;
							System.out.printf("The ID entered did not match that of a valid voter, you will have to start over you have %d more attempts to enter your id correctly\n\n", driver.attemptsLeft);
						}
						else if(driver.validate(id, "V") == 1){
							driver.attemptsLeft--; 
							System.out.printf("The ID entered matched a voter who has already voted. Please enter a different ID. You have %d more chances. IF YOU HAVE ALREADY VOTED, PLEASE LEAVE THE POLLING BOOTH. YOU MAY NOT VOTE AGAIN.\n", driver.attemptsLeft);
						}

					}

					else if(userType.equalsIgnoreCase("V") && driver.signInCounter >= ALLOWEDSIGNINATTEMPTS){
						System.out.println("You have entered your id incorrectly too many times\nYour device wil now emit a noise to notify a polling official\n");
						while(true){
							System.out.println("Polling official id:\n");

							if(driver.validate(reader.readLine(), "P") == 0){
								System.out.println("The poling official will now enter the voter's id for them per the rules of the voting system");
								driver.attemptsLeft = ALLOWEDSIGNINATTEMPTS;
								driver.signInCounter = 0;
								break;
							}
						}
						continue;
					}
					else{
						System.out.println("Please choose P or V. You will be prompted to try again.\n");
						continue;
					}



				}
				catch(IOException error){
					System.out.println("Please try again: " + error.getMessage());
				}

			}





		}catch (SQLException e){
			System.out.println(e.getMessage());
		}
	}
}
