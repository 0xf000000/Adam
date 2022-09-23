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
	
	/**
	 * this method checks if the card ID from the userinput exists.
	 * @param cardNR
	 * @return if the cardNr exists in our database this method will return true. If not false
	 */
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
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Goes to our sql Database and changes the amount of money thats stored in our database.
	 * @param current
	 * @throws SQLException
	 */
	public void withdrawFromAccount(Account current, String cardNr ) throws SQLException {
		String QUERY = "UPDATE users SET balance = " + current.getBalance()+"WHERE cardNr = " + cardNr ;
		Connection con = getDB(); 
		Statement stm = con.createStatement();
		stm.executeUpdate(QUERY);
		stm.close();
		con.close();
	}
	/**
	 * this method checks the password from the userinput and looks if it is the same in our database
	 * @change right now the db is saving the passowrd hardcoded in the db. this should be changed into a hash that gets stored in a separate table with a connection to our other table 
	 * @param password
	 * @param cardnr
	 * @return returns a <code> account </code> object that holds the information needed for the menu. if the password is wrong it will return null.
	 */
	public boolean checkPassword(String cardnr, String password) {
		final String QUERY = "SELECT password, cardNr FROM users WHERE password  = '"+ password +"' AND cardNr = '" + cardnr + "'";
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
				return false;
			}
			if(password.equals(Password) && cardNR.equals(cardnr) ) {
				System.out.println("login sucessfully ");
				return true;
				
			}
			
		}catch(SQLException e ) {
			e.printStackTrace();
		}
		
		System.err.print("Sorry wrong Password please try it again\n"); 
		return false;
	}
	
	/**
	 * this method gets the requested account
	 * @param CardNr
	 * @param Password
	 * @return
	 * @throws SQLException
	 */
	public Account getAccount(String cardnr, String password)throws SQLException {
		final String QUERY = "SELECT username, balance, cardNr, password FROM users WHERE password = '"+ password +"' AND cardNr = '" + cardnr + "' ORDER BY username";
		String username = "";
		String cardNr = "";
		double balance = 0;
		Connection con = getDB();
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery(QUERY);
		while(rs.next()) {
			username = rs.getString("username");
			balance = rs.getDouble("balance");
			cardNr = rs.getString("cardNr");
		}
		
		if(username.equals("")) {
			System.out.print("sorry this User doesnt exit");
			return null;
		}
		
		
		return new Account(balance,username,cardNr);
		
		
	}
	
	/**
	 * transfers money from one account to another
	 * @param current
	 * @param balance
	 * @param cardnr
	 * @return
	 * @throws SQLException
	 */
	public boolean tranfer( Account current, double balance, String cardnr)throws SQLException {
		String QUERY = "SELECT cardnr,balance FROM users WHERE cardnr =  " + cardnr; 
		Connection con = getDB();
		Statement stm = con.createStatement();
		ResultSet rs  = stm.executeQuery(QUERY);
		double ReciverAccountBalance = 0;
		while(rs.next()) {
			ReciverAccountBalance  = rs.getDouble("balance");
			
		}
		double balanceReciver = ReciverAccountBalance + balance;
		String QUERY2 = "UPDATE users SET balance = " + balanceReciver +"WHERE cardnr = "+ cardnr ; 
		Statement stm2 = con.createStatement();
		stm2.executeUpdate(QUERY2);
		stm2.close();
		con.close();
		// withdraws from the account that sends it
		double currentbalance = current.getBalance() - balance;
		current.setBalance(currentbalance);
		withdrawFromAccount(current, current.getCardNr() );
		
		//sends the cash to to requested account
		
		return false; 
	}
	
	
	
	

}
