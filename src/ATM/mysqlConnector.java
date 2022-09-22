package ATM;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.HashMap;
import users.Account;
/**
 * This class opens up a connection to our mysql database.
 * @autor leon
 * @version 1.0
 */
public class mysqlConnector {
	private final String userName = "bank";
	private final String Password = "bank";
	private final String url = "jdbc:mysql://localhost:3306/Bank";
	
	public Connection getDB() throws SQLException {

		Connection con = DriverManager.getConnection(url,userName,Password);
		System.out.println("Connection established"); 
		return con;
		
	}
	
	
	public boolean checkCardID( String cardNR){
		Statement stm = null;
		Connection con = null;
		try {
			con = getDB();
			
			stm = con.createStatement();
			
			final String QUERY = "SELECT cardNr FROM users WHERE cardNr =" + cardNR;
			
			ResultSet rs = stm.executeQuery(QUERY);
			String cardNr = "";
			while(rs.next()) {
			 cardNr = rs.getString("cardNr");
				
			}
			if(cardNr.equals(cardNR)) {
				con.close();
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
	public void withdrawFromAccount(Account current, double balance) throws SQLException {
		String QUERY = "ALTER TABLE users WHERE username = " + current.getName()+ " MODIFY balance = " + balance ;
		Connection con = getDB(); 
		Statement stm = con.createStatement();
		
		
	}
	
	public Account checkPassword(String password, String cardnr) {
		final String QUERY = "SELECT password, cardNr FROM users WHERE password  = '"+ password +"' AND cardNr = '" + cardnr + "'";
		final String GETACCOUNT = "SELECT username, balance FROM users WHERE password = '"+ password +"' AND cardNr = '" + cardnr + "' ORDER BY username";
		String cardNR ="";
		String Password ="";
		Connection con = null;
		try {
			con = getDB();
			
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(QUERY);
			while(rs.next()) {
				 cardNR = rs.getString("cardNr");
				 Password = rs.getString("password");
			}
			
			rs.close();
			stm.close();
			if(Password == null || cardNR == null) {
				System.err.println("Password or cardNr is wrong sorry :("); 
				
				return null;
			}
			
		
			if(password.equals(Password) && cardNR.equals(cardnr) ) {
				Statement stm2 = con.createStatement();
				ResultSet rs2 = stm2.executeQuery(GETACCOUNT);
				String username = "";
				double balance = 0;
				while(rs2.next()) {
					username = rs2.getString("username");
					balance = rs2.getDouble("balance");
				}
				
				
				return new Account(balance,username);
			}
			
		}catch(SQLException e ) {
			e.printStackTrace();
		}
		
		System.err.print("Sorry wrong Password please try it again\n"); 
		return null;
	}
	
	
	
	

}
