package eVoting; 

import java.security.MessageDigest;
import sun.misc.BASE64Encoder;


public class Hashing {
	
	private final String algo = "SHA-256";
	private  MessageDigest digest;
	private BASE64Encoder encoder;

	
	public Hashing(){
		try{
			this.digest = MessageDigest.getInstance(algo);
			this.encoder = new BASE64Encoder();
			
		}catch (Exception e){
			System.out.println(e.getMessage());
			this.digest = null; 
		}
	}

	public String hash(String value){
		String hash = "";
		
		digest.update(value.getBytes());
		byte[] valueBytes = digest.digest();
		
		hash = encoder.encode(valueBytes);
		
		digest.reset();
				
		return hash;
	}
	
}
