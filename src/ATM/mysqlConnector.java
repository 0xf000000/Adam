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
		try {
			Connection con = getDB();
			
			stm = con.createStatement();
			
			final String QUERY = "SELECT cardNr FROM users WHERE cardNr =" + cardNR;
			
			ResultSet rs = stm.executeQuery(QUERY);
			String cardNr = "";
			while(rs.next()) {
			 cardNr = rs.getString("cardNr");
				
			}
			if(cardNr.equals(cardNR)) {
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public Account checkPassword(String password) {
		
		
		
		return null;
	}
	
	
	
	

}
