package eVoting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import eVoting.Vote;

public class Voter extends User {

	private String birthdate;
	private Vote vote;
	private String name;

	/**
	 * Constructor method
	 */
	Voter(String bday, String name,String id) {
		this.birthdate = bday;
		this.name = name;
		super.setId(id);
	}
	
	String getBirthdate(){
		return this.birthdate;
	}
	
	String getName(){
		return this.name;
	}

	/**
	 * Confirm personal information displayed on screen
	 */
	boolean verifyPersonalInfo() {
		while(true){
		System.out.printf("Our records indicate, you are a voter and your name is %s and your date of birth is %s \n If the above information is correct, please type Y, if not type N", this.name, this.birthdate);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			String input = reader.readLine();
			if(input.equalsIgnoreCase("Y")){
				return true;
			}
			else if(input.equalsIgnoreCase("N")){
				return false;
			}
			else{
				System.out.println("Your input was faulty. Please enter only Y/N");
			}
			} catch (IOException e) {
				System.out.println("Your input was faulty. Please enter only Y/N");
			}
		}
	}
	
	/**
	 * Get the vote object containing the user's selections. 
	 */
	String getVote() {
		return this.vote.getChoice(); 
	}
	
	void setVote(String choice){
		this.vote = new Vote(choice, super.getId());
	}
	

}