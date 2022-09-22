package users;
/**
 * this class is a dataclass for our users to get their information.
 * @author leon
 *
 */
public class Account {
	private String cardNr;
	private double balance; 
	private  String username;
	private String password;
	private String ipaddr;
	
	/**
	 * no getter or setter for a password
	 * @param cardNr
	 * @param balance
	 * @param name
	 * @param password
	 * @param ipaddr
	 */
	public Account(String cardNr, double balance, String username, String password, String ipaddr) {
		
		this.setCardNr(cardNr); 
		this.setBalance(balance); 
		this.setName(username);
		this.setPassword(password); 
	}
	
	public Account (String cardNr) {
		this.cardNr = cardNr;
	}
	
	public Account (String cardNr, String Password) {
		this.cardNr = cardNr;
		this.password = Password;
	}
	public Account(String cardNr, String Password, double balance) {
		this.cardNr = cardNr;
		this.password = Password;
		this.balance = balance;
	}

	public String getCardNr() {
		return cardNr;
	}

	public void setCardNr(String cardNr) {
		this.cardNr = cardNr;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getName() {
		return username;
	}

	public void setName(String username) {
		this.username = username;
	}
	

	public String getIpaddr() {
		return ipaddr;
	}

	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
