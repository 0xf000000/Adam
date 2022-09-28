package users;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import ATM.mysqlConnector;
import utils.Security;
import utils.Utils;
import java.sql.*;
import java.util.Random;
/**
 * this class is functional to create a new Account in our sql Database.
 * @author leon
 *
 */
public class CreateNewAccount {
private mysqlConnector sql;
private Random random;
private Utils utils;
private	String name; 
private	String Password;

	
	public CreateNewAccount() {
		sql = new mysqlConnector();
		utils = new Utils();
		random = new Random();
	}
	
	public void createAccount() throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Please enter your name: ");
		name = utils.vormatInput(in.readLine());
		
		
		
		System.out.print("please choose a password: ");
		Password = utils.vormatInput(in.readLine()); 
		
		System.out.print("please reenter your Pasword: ");
		String secondTime = in.readLine();
		
		if(!Password.equals(secondTime)) {
			System.out.println("sorry you entered not the same password twice");
			return;
		}
		
	
		int id = setUser(name);
		
		if(id == 0 ) {
			System.err.print("sorry something went wrong :( ");
			return; 
		}
		
		
		SetPassword(Password , id );
		
	}
	
	private int setUser(String name) {
		double randomBalance = 0 + ( 3000 -  0) * random.nextDouble();
		int randomCardID = 100 + (999 - 100) * random.nextInt();
		String QUERY= "INSERT INTO users(username,balance ,cardNr) VALUES ('" + name + "', " + randomBalance + ", " + randomCardID + ")";
		
		try {
			Connection con = sql.getDB();
			Statement stm = con.createStatement();
			stm.executeUpdate(QUERY);
			
			Statement stm2 = con.createStatement(); 
			String QUERY2 = "SELECT id, cardNr FROM users WHERE cardNR =  " +  randomCardID; 
			ResultSet rs  = stm2.executeQuery(QUERY2);
			int id = 0;
			while(rs.next()) {
			id =rs.getInt("id");
			}
			
			if(id == 0 ) {
				System.err.println("sorry something went wrong :( ");
				return -1; 
			}
			
			return id;
		}catch(SQLException e) {
			e.printStackTrace();
			
			return -1; 
		}
		
	}
	/**
	 * sets the password in our mysql database
	 * @param password
	 * B@97e93f1
	 */ 
	
	private void SetPassword(String password, int id ) {
		Security security = new Security(); 
		byte[] salt = security.generateSalt();
		String hash = security.generateHash(password, salt ); 
		String saltToString = new String(salt);
		try{
			String QUERY = "INSERT INTO passwords(id , salt ,hash) VALUES ( " + id + "," + saltToString + ","  + hash + ")";
			Connection con = sql.getDB();
			Statement stm = con.createStatement();
			stm.executeUpdate(QUERY);
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		
		
	}
	
	
	
	
	
	
	
	
	
}
