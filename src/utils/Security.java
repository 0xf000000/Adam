package utils;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.crypto.spec.PBEKeySpec;

import static java.nio.charset.StandardCharsets.UTF_8;
/**
 * will name this class Security or smth in order to have a place 
 * @author leon
 *
 */
public class Security {
	
	
	
	

	/**
	 * This method generates a Hash from our password with 'SHA-256'
	 * @return the hashed Password 
	 */
	
	public String generateHash(String hash, byte[] salt) {
		
		MessageDigest digest = null;
		byte[]  bytes= null; 
		byte[] combined = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			bytes = digest.digest(hash.getBytes(UTF_8));
			combined = new byte[ bytes.length + salt.length];
			
			
			// adding the salt and SHA-256 the twoo byte arrays 
			for(int i = 0; i < combined.length; i++) {
				combined[i] = i< bytes.length ? bytes[i] : salt[i - bytes.length];
			}
			
			
		}catch(NoSuchAlgorithmException i ) {
			System.err.println("there was a problem with generating the hash");
			i.printStackTrace();
		}
		StringBuffer buffer = new StringBuffer();
        for(byte b : combined){
            buffer.append(String.format("%02x", b));
        }
       
	return buffer.toString();
	}
	
	
	/**
	 * returns a random Salt that gets added to our hash function.
	 * @return returns a 16 bit hash
	 */
	public byte[] generateSalt() {
		Random ran = new SecureRandom();
		byte[] salt = new byte[16];
		ran.nextBytes(salt);
		return salt; 
	}
	
	
	
}
