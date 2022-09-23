package users;
/**
 * this is a dataclass for our users to store userdata temporary in our programm
 * @TODO this method should store the ip adress of the user so we can be safe it is our user.
 * 
 * @author leon
 *
 */
public class Account {
	private double balance; 
	private  String username;
	private String ipaddr;
	private String cardNr;
	
	public Account( double balance, String username) {
	
		this.setBalance(balance); 
		this.setName(username); 
	}
	
	public Account(double balance , String username, String cardNr) {
		this.balance = balance; 
		this.username = username; 
		this.cardNr = cardNr;
		
	}
	public String getCardNr() {
		return cardNr;
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
	
	

}
