
package eVoting; 

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Hashtable;

/**
 *
 * @author hassamsolano
 */

public class DatabaseAccess {

	public DatabaseAccess(){
	}
	
	/**
	 * 
	 * @name getCandidates(Connection conn)
	 * @param conn: Connection to the database 
	 * @return ResultSet containing information on available candidates
	 * 
	 */ 
	protected ResultSet getCandidates(Connection conn){
		try{
			String candidateQuery = "SELECT * FROM candidates";
			PreparedStatement stmnt = conn.prepareStatement(candidateQuery);
			
			return stmnt.executeQuery(); 
			
		}catch (SQLException e){
			System.out.println(e.getMessage());
			return null; 
		}

	}
	
	/**
	 * 
	 * @name getVoterInfo(Connection conn, Integer voterId)
	 * @param conn : Connection to database
	 * @param voterId : Integer identifying the voters provided id number 
	 */
	protected Hashtable<String, String>getVoterInfo(Connection conn, String voterId){
		try{
			Hashtable<String,String> voterInfo = new Hashtable<String, String>();
			
			String VOTERINFOQUERY = "SELECT * FROM voters WHERE voters.voterId = ?";
			
			PreparedStatement stmnt = conn.prepareStatement(VOTERINFOQUERY);
			stmnt.setString(1, this.getHash(conn, "0", voterId));

			
			ResultSet result = stmnt.executeQuery();
			
			if(result.next()){
				voterInfo.put("name", result.getString("name")); 
				voterInfo.put("birthdate", result.getString("birthdate"));
			}
			
			return voterInfo; 
			
		}catch (SQLException e){
			System.out.println(e.getMessage());
			return null; 
		}
		
	}
	
	/**
	 * @name getTally(Connection conn)
	 * @return A Hashtable<candidateId, votesCast>
	 * @throws SQLException
	 */
	protected Hashtable<String, Integer> getTally(Connection conn) throws SQLException {
		
		try{
			Hashtable<String,Integer> result = new Hashtable<String,Integer>(); 
				
			String candidateIdsQuery = "SELECT candidateId FROM candidates";
			String votesQuery = "SELECT * FROM votes";
			
			PreparedStatement candidateStmnt = conn.prepareStatement(candidateIdsQuery);	
			PreparedStatement voteStmnt = conn.prepareStatement(votesQuery);	

			//Get candidateIds
			ResultSet candidates = candidateStmnt.executeQuery(); 
			//Set candidateIds as hashtable keys
			while(candidates.next()){
				result.put(candidates.getString("candidateId"), 0); 
			}
			//Get votes 
			ResultSet votes = voteStmnt.executeQuery();
			//Tally votes into hashtable
			int Count1 = 0;
			int Count2 = 0;
			int Count3 = 0;
			int Count4 = 0;
			int Count5 = 0; 
			while(votes.next()){
				
				switch (votes.getString("candidateId")){
				case "1":
					result.put(votes.getString("candidateId"), Count1 + 1);
					Count1++; 
					break; 
				case "2":
					result.put(votes.getString("candidateId"), Count2 + 1);
					Count2++; 
					break; 
				case "3":
					result.put(votes.getString("candidateId"), Count3 + 1);
					Count3++; 
					break; 
				case "4":
					result.put(votes.getString("candidateId"), Count4 + 1);
					Count4++; 
					break; 
				case "5":
					result.put(votes.getString("candidateId"), Count5 + 1);
					Count5++; 
					break;
					
				}
				
			}
			
			return result; 	

		}catch (SQLException e) {
			throw e; 
		}
	}
	
	/**
	 * 
	 * @name postVote(Connection conn, Int voterChoice )
	 * @param conn: Connection to the database
	 * @param voterChoice: Integer identifying the candidate chosen by the voter
	 * @return TRUE if posted correctly; FALSE if post failed 
	 * 
	 */ 
	protected boolean postVote(Connection conn, String voterId, String voterChoice){
		try{
			boolean rtn = false; 			
			//Check if voter has already cast vote 
			int voterStatus = this.validateUser(conn, voterId, "0"); 
			
			if(voterStatus == 1){//Voter is found to have voted already
				rtn = false; 
			}
			else{
				//Create post query
				String postVoteQuery = "INSERT INTO votes VALUES (?)";
				
				PreparedStatement postVoteStmnt = conn.prepareStatement(postVoteQuery);
				postVoteStmnt.setString(1, voterChoice);
				
				//Create hasVoted Query 
				String hasVotedQuery = "UPDATE voters SET hasVoted = 1 WHERE voterId = ?";
				
				PreparedStatement hasVotedStmnt = conn.prepareStatement(hasVotedQuery); 
				hasVotedStmnt.setString(1, this.getHash(conn, "0", voterId));
				
				//Post votes
				int postVoteStatus = postVoteStmnt.executeUpdate();
				int hasVotedUpdateStatus = hasVotedStmnt.executeUpdate(); 
				//Return
				if (postVoteStatus == 1 && hasVotedUpdateStatus == 1){
					rtn = true; 
				}
			}
		
			return rtn; 
			
		}catch (SQLException e){
			System.out.println(e.getMessage());
			return false ; 
		}
		
	}

	/**
	 * 
	 * @name: validateUser(Connection conn, Int userId)
	 * @param conn:Connection to the database
	 * @param userId: The user id number provided at polling check-in
	 * @return 0: User is valid/voter has NOT voted ; 1: Voter valid/has voted; 2: User NOT Valid
	 *
	*/
	protected int validateUser(Connection conn, String userId, String userType){
		int rtn = 2; 
		
		if(userType == "1"){//User is polling official
			try {
				String officialQuery = "SELECT * FROM pollingOfficial WHERE pollingOfficial.id = ? "; 
				
				
				if(!this.getHash(conn, userType, userId).equalsIgnoreCase("NOTFOUND")){
					
					System.out.println("I reached here!");
					
					PreparedStatement stmnt = conn.prepareStatement(officialQuery);
					userId = this.getHash(conn, userType, userId); 
					
					stmnt.setString(1, userId);
					
					ResultSet result = stmnt.executeQuery(); 
					if(result.next()){
						rtn = 0; 
					}
					
					result.close();	
				}
				
			}catch (SQLException e){
				System.out.println(e.getMessage());
			}
			
			return rtn; 
		}
		
		else if (userType == "0" ){//User is a voter
			try{
				if (!this.getHash(conn, userType, userId).equalsIgnoreCase("NOTFOUND")){
					userId = this.getHash(conn, userType, userId); 
					
					String voterQuery = "SELECT * FROM voters WHERE voters.voterId = ?";
					
					PreparedStatement stmnt = conn.prepareStatement(voterQuery); 
					stmnt.setString(1, userId);
					ResultSet result = stmnt.executeQuery();
					
					if (result.next()){
						if (result.getString("hasVoted").equalsIgnoreCase("1")){
							rtn = 1; 
						}
						else {
							rtn = 0; 
						}
					}
					else{
						rtn = 2; 
					}
					result.close();
				}
								
			}catch(SQLException e){
				System.out.println(e.getMessage());
				rtn = 2;
				return rtn; 
			}
		}
		
		return rtn;  
	}
	
	private String getHash(Connection conn, String userType, String userId){
		final String allVoters = "SELECT * FROM voters;"; 
		final String allOfficials = "SELECT * FROM pollingOfficial;";
		final String  INVALIDUSER = "NOTFOUND"; 
		final Hashing hasher = new Hashing(); 
		
		String rtn = INVALIDUSER;
		String salt = ""; 
		String hash = ""; 

		try{
			if (userType.equalsIgnoreCase("0")){
				PreparedStatement allVotersStmnt = conn.prepareStatement(allVoters);
				ResultSet allVotersResults = allVotersStmnt.executeQuery(); 
				
				while (allVotersResults.next()){
					salt = allVotersResults.getString("salt");
					hash = hasher.hash(userId + salt);					
					
					
					if(hash.equalsIgnoreCase(allVotersResults.getString("voterId"))){
						allVotersResults.close();
						rtn = hash; 
					}
					else if(allVotersResults.isLast()){
						rtn = INVALIDUSER;
					}
				}
			}
			
			else if(userType.equalsIgnoreCase("1")){
				PreparedStatement allOfficialsStmnt = conn.prepareStatement(allOfficials);
				ResultSet allOfficialsResults = allOfficialsStmnt.executeQuery(); 
				
				while (allOfficialsResults.next()){
					salt = allOfficialsResults.getString("salt");
					hash = hasher.hash(userId + salt);
					
					if(hash.equalsIgnoreCase(allOfficialsResults.getString("id"))){
						allOfficialsResults.close();
						rtn = hash; 
					}
					else if(allOfficialsResults.isLast()){
						rtn = INVALIDUSER;
					}
				}
				
			}
			
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		return rtn; 
	}
}

