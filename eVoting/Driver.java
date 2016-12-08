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
	
	protected int pollingOfficialSignInCounter = 0;
	protected int voterSignInCounter = 0;
	private DatabaseAccess db = new DatabaseAccess(); 
	private Connection conn; 

	
	public Driver(){
		Connection conn;
		final String user = "root";
		final String password = ""; 
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
	 * @name boolean postVote
	 * @param candidateID, voterId
	 * @return boolean (True if posted successfully)
	 * 
	 * - use postVote(Connection conn, String voterId, String voterChoice) method in DBAcess class 
	 * - voterChoice param is passed into this method
	 * - voterId param must be passed in from somewhere 
	 * 
	 */
	boolean postVote(String canidateId, String voterId){
		if (db.postVote(getConnetion(), voterId, canidateId)){
			return true;
		}
		return false;
	}
}
