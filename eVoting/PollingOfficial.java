package eVoting;

import java.util.Hashtable;
import java.util.Enumeration;


public class PollingOfficial extends User {

	PollingOfficial() {
	}

	/**
	 * Print the current tally of submitted votes on the machine
	 */
	String printResults(Hashtable<String,Integer> results) {

		return ("Current Tally:\n" + this.getResults(results) + "\nTotal Votes = " + this.getTotalVotes(results));
	}

	/**
	 * Retrieve current ballot tally
	 */
	String getResults(Hashtable<String,Integer> results) {
		String rtn = "";

		Enumeration<String> keys =  results.keys();
		String currentKey; 
		String candidateName = ""; 

		while(keys.hasMoreElements()){
			currentKey = keys.nextElement();
			if(currentKey == "0") break; 

			switch (currentKey){

			case "1":
				candidateName = "Hillary Clinton"; 
				break;

			case "2":
				candidateName = "Donald Trump"; 
				break;

			case "3":
				candidateName = "Gary Johnson"; 
				break;

			case "4":
				candidateName = "Jill Stein"; 
				break;
			case "5":
				candidateName = "No Vote"; 
				break;
			}

			rtn = rtn + candidateName +": ";
			rtn = rtn + results.get(currentKey);

			if(keys.hasMoreElements()) rtn = rtn +"\n";

		}
		return rtn; 


	}

	/**
	 * @name getTotalVotes(Connection conn)
	 * @param conn: Connection object to the database 
	 */
	String getTotalVotes(Hashtable<String,Integer> results){

		Enumeration<String> keys = results.keys(); 
		String currentKey = ""; 
		Integer count = 0; 

		while(keys.hasMoreElements()){
			currentKey = keys.nextElement(); 
			count += results.get(currentKey);
		}

		return count.toString();



	}



}




