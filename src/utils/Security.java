package utils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static java.nio.charset.StandardCharsets.UTF_8;
/**
 * will name this class Security or smth in order to have a place 
 * @author leon
 *
 */
public class Security {

	
	public String  vormatInput(String input) {
		input = input.replaceAll("<|>|/","");
		
		return input;
	}
	
	

	/**
	 * This method generates a Hash from our password with 'SHA-256'
	 * @return the hashed Password 
	 */
	
	public String generateHash(String hash) {
		MessageDigest digest = null;
		byte[]  bytes= null; 
		try {
			digest = MessageDigest.getInstance("SHA-256");
			bytes = digest.digest(hash.getBytes(UTF_8));
			
		}catch(NoSuchAlgorithmException i ) {
			i.printStackTrace();
		}
		StringBuffer buffer = new StringBuffer();
        for(byte b : bytes){
            buffer.append(String.format("%02x", b));
        }
        
	return buffer.toString();
	}
}
