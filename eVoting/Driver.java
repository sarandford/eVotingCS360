package eVoting;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Properties; 

public class Driver {

	private static final int ALLOWEDSIGNINATTEMPTS = 3;

	
	protected int signInCounter = 0;
	private int attemptsLeft = Driver.ALLOWEDSIGNINATTEMPTS;
	private DatabaseAccess db = new DatabaseAccess(); 
	private Connection conn; 

	
	public Driver(){
		Connection conn;
		final String user = "root";
		final String password = "samtom1"; 
		final String serverName = "localhost";
		final String port = "3306";
		final String dbname = "eVoting";
		//Create connection object		
		Properties connectionProps = new Properties();
		connectionProps.put("user", user);
		connectionProps.put("password", password);
		connectionProps.put("useSSL", "false");

		try {
			conn = DriverManager.getConnection("jdbc:mysql://"
						+ serverName + ":" + port + "/" + dbname,
						connectionProps);
			this.conn = conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
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
	
	protected String getVoterInfo(String id){
		Voter voter = this.createVoter(id);
		return voter.getBirthdate() + " " + voter.getName();
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
	
	String getTally(){
		try{
			PollingOfficial po = new PollingOfficial(); 

			return po.printResults(db.getTally(conn));
		}
		catch(SQLException e){
			System.out.println("An error has occurred: "+ e);
		}
		return null;
	}

	
	
	
	/**
	 * Post the vote to the database 
	 * @name boolean postVote(String candidateID)
	 * @param candidateID
	 * @return boolean (True if posted successfully)
	 * 
	 * - use postVote(Connection conn, String voterId, String voterChoice) method in DBAcess class 
	 * - voterChoice param is passed into this method
	 * - voterId param must be passed in from somewhere 
	 * 
	 */
	

	
	
	//	public static void main(String[] args){
//
//		try {
//			while(true){
//
//					else if(userType.equalsIgnoreCase("V") && driver.signInCounter < ALLOWEDSIGNINATTEMPTS){
//						System.out.println("Please enter your id:\n");
//						String id = reader.readLine();
//
//						driver.signInCounter++;
//						System.out.println("VOTER SIGN IN COUNT: " + driver.signInCounter+ "\n");
//
//
//						if(driver.validate(id, "V") == 0){
//							Voter voter = driver.createVoter(id);
//							if(voter.verifyPersonalInfo()){
//								String[] candidates = driver.getCandidates().split(",");
//								while(true){
//
//									System.out.printf("Displayed below are the choices for 2016 American Presidential Election:\n"
//											+ " %s %s\n "
//											+ "%s %s\n "
//											+ "%s %s\n "
//											+ "%s %s\n\n"
//											+ "Please enter the number corresponding to the candidate of your choice\n",
//											candidates[0],candidates[1],candidates[2],candidates[3],candidates[4],candidates[5],candidates[6],candidates[7]);
//
//
//									try {
//										Integer choice = Integer.parseInt(reader.readLine());
//										if(choice > 0  && choice < 5){
//											voter.setVote(choice.toString());
//
//										}
//										else {
//											System.out.println("You chose an invalid vald candidate number try again.\n");
//											continue;
//										}
//									}catch (NumberFormatException e){
//										System.out.println("You chose an invalid valdidate number try again.\n");
//										continue;
//									}
//
//
//
//
//									System.out.printf("You chose %s. Do you wish to change your choice. Enter Y or N", candidates[Integer.parseInt(voter.getVote())]);
//									if(reader.readLine().equalsIgnoreCase("N") && db.postVote(conn, id, voter.getVote())){
//										System.out.println("Congratulations! You cast your vote in the 2016 Presidential Election. Your ballot will now be printed");
//
//										conn.close();
//										System.exit(0);
//									}
//									else{
//										System.out.println("You chose to change your vote or did not enter either Y/N. Please try to cast your vote again");
//									}
//								}
//							}
//							else{
//								System.out.printf("Since the ID did not match your information, you will have to start over you have %d more attempts to enter your id correctly\n\n", driver.attemptsLeft);
//							}
//						}
//						else if(driver.validate(id, "V") == 2){
//							driver.attemptsLeft--;
//							System.out.printf("The ID entered did not match that of a valid voter, you will have to start over you have %d more attempts to enter your id correctly\n\n", driver.attemptsLeft);
//						}
//						else if(driver.validate(id, "V") == 1){
//							driver.attemptsLeft--; 
//							System.out.printf("The ID entered matched a voter who has already voted. Please enter a different ID. You have %d more chances. IF YOU HAVE ALREADY VOTED, PLEASE LEAVE THE POLLING BOOTH. YOU MAY NOT VOTE AGAIN.\n", driver.attemptsLeft);
//						}
//
//					}
//
//					else if(userType.equalsIgnoreCase("V") && driver.signInCounter >= ALLOWEDSIGNINATTEMPTS){
//						System.out.println("You have entered your id incorrectly too many times\nYour device wil now emit a noise to notify a polling official\n");
//						while(true){
//							System.out.println("Polling official id:\n");
//
//							if(driver.validate(reader.readLine(), "P") == 0){
//								System.out.println("The poling official will now enter the voter's id for them per the rules of the voting system");
//								driver.attemptsLeft = ALLOWEDSIGNINATTEMPTS;
//								driver.signInCounter = 0;
//								break;
//							}
//						}
//						continue;
//					}
//					else{
//						System.out.println("Please choose P or V. You will be prompted to try again.\n");
//						continue;
//					}
//
//
//
//				}
//				catch(IOException error){
//					System.out.println("Please try again: " + error.getMessage());
//				}
//
//			}
//
//
//
//
//
//		}catch (SQLException e){
//			System.out.println(e.getMessage());
//		}
//	}
}
