package utils;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;




/**
 * This class implements Util methods used through out the programm.
 * @author leon
 *
 */
public class Utils {
private final String PREFIX = "./AsciiArt/";

public String  vormatInput(String input) {
	input = input.replaceAll("<|>|/","");
	
	return input;
}
	

/**
 * this function prints the 
 * @param file
 */
	public void printASCII(String file) {
		File ascii = new File(file); 
		try {
		BufferedReader br = new BufferedReader(new FileReader(PREFIX+ascii));
		
		String input = "";
		while((input = br.readLine()) != null) {
			Thread.sleep(100);
			System.out.println(input);	
		}
		br.close();
		}catch(IOException | InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	
	public void clearScreen() {
		System.out.print("\\033[H\\033[2J");
		System.out.flush(); 
	}
	
	
	
	
}
