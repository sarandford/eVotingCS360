
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	protected ResultSet getCandidates(Connection conn) throws SQLException {
		try{
			String candidateQuery = "SELECT * FROM candidates";
			Statement stmnt = conn.createStatement();
			
			return stmnt.executeQuery(candidateQuery); 
			
			
		}catch (SQLException e){
			throw e; 
		}

	}
	
	/**
	 * @name getTally(Connection conn)
	 * @return A Hashtable<candidateId, votesCast>
	 * @throws SQLException
	 */
	protected Hashtable<Integer, Integer> getTally(Connection conn) throws SQLException {
		
		try{
			Hashtable<Integer,Integer> result = new Hashtable<Integer,Integer>(); 
				
			String candidateIdsQuery = "SELECT candidateId FROM candidates";
			String votesQuery = "SELECT candidateId FROM votes";
			Statement stmnt = conn.createStatement();	
			
			//Get candidateIds
			ResultSet candidates = stmnt.executeQuery(candidateIdsQuery); 
			//Set candidateIds as hashtable keys
			while(candidates.next()){
				result.put(candidates.getInt("candidateId"), 0); 
			}
			//Get votes 
			ResultSet votes = stmnt.executeQuery(votesQuery);
			//Tally votes into hashtable
			while(votes.next()){
				
				result.put(votes.getInt("candidateId"), 
						result.get(votes.getInt("candidateId")) + 1);
				
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
	protected boolean postVote(Connection conn, Integer voterId, Integer voterChoice){
		try{
			boolean rtn = false; 
			Statement stmnt = conn.createStatement(); 
			
			//Check if voter has already cast vote 
			String castVoters = "SELECT voterId FROM votes";
			ResultSet voters = stmnt.executeQuery(castVoters); 
			while(voters.next()){
				if (voters.getInt("voterId") == voterId){
					System.out.println("Voter has already cast their vote!");
					return false; 
				}
			}
			voters.close();
			//Create post query
			String postVoteQuery = "INSERT INTO votes VALUES (" 
			+ voterId.toString() 
			+ ","
			+ voterChoice.toString()
			+ ")";
			
			//Post votes
			int status = stmnt.executeUpdate(postVoteQuery);
			//Return
			if (status == 1){
				rtn = true; 
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
	 * @return TRUE if user is valid; FALSE if user was not found
	 *
	*/

	protected boolean validateUser(Connection conn, Integer userId) throws SQLException {
		try{
			boolean rtn = false; 
			String query = "SELECT * FROM voter WHERE voter.voterId=" + userId.toString();
			
			Statement stmnt = conn.createStatement(); 
			
			ResultSet result = stmnt.executeQuery(query);
			
			if (result.next()){
				rtn = true; 
			}
			
			return rtn; 
			
		}catch(SQLException e){
			throw e; 
		}

	}
}

