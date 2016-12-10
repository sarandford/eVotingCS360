package eVoting; 

import java.security.MessageDigest;
import java.util.Base64;


public class Hashing {
	
	private final String algo = "SHA-256";
	private  MessageDigest digest;

	
	public Hashing(){
		try{
			this.digest = MessageDigest.getInstance(algo);
			
		}catch (Exception e){
			System.out.println(e.getMessage());
			this.digest = null; 
		}
	}

	public String hash(String value){
		String hash = "";
		
		digest.update(value.getBytes());
		byte[] valueBytes = digest.digest();
		
		hash = Base64.getEncoder().encodeToString(valueBytes);
		
		digest.reset();
				
		return hash;
	}
	
}
