package ATM;

import java.io.BufferedReader;
import utils.GenerateHash;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import users.Account;
import utils.Utils;
/**
 * this class resembles an ATM
 * @author leon
 *
 */

public class ATM {
	private int ATMchange = 20000;
	Utils utils = new Utils();
	GenerateHash gh = new GenerateHash();
	public ATM() {
		
	}
	/**
	 * starts the instance of our ATM
	 * @params none
	 * @returns void 
	 */
	public void start() {
		String banner = "Banner.txt";
		utils.printASCII(banner);
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		mysqlConnector sql = new mysqlConnector(); 
		
		System.out.print("Welcome");
		while(true){
			 try {
				 System.out.println("if you want to quit please enter 'exit'");
				 System.out.print(" please Enter your cardnumber: ");
				 String cardNumber =gh.vormatInput(in.readLine()); 
				 Account current;
				
				if(cardNumber.equals("exit")) {
					
					System.out.print("Shuting down .. good bye");
					System.exit(1);
				}
				
				// this if statement should lead to a new class where you can create a new Account
				if(!sql.checkCardID(cardNumber)){
					
					System.err.print("this account doesn't exist.\n");
					System.out.print("do you want to create a account?");
					return ;
				}
				System.out.println( sql.checkCardID(cardNumber));
				
				
				System.out.print("please Enter your Password: ");
				String password = gh.vormatInput(in.readLine());

				current = sql.checkPassword(password,cardNumber);
				 
				if(current != null ) {
					menu(current);
				}
			 }catch( Exception e ) {
				 e.printStackTrace();
			 }
			
			 
			 
		}
		
	}
	
	
		
		
	
	/**
	 * this Method prints out the Menu of our Bank and lets you decide what you want to do 
	 * @throws IOException 
	 */
	private void menu(Account current) throws IOException {
		int ch = 0;
		do {
			utils.clearScreen();
			System.out.println("Hello "  + current.getName() + "!");
			System.out.println("------------------------MENU--------------------------");
			System.out.print("\tMain Menu \n\n\t1.Show Balance\n\t2.Withdraw\n\t3.Options\n\t4.Make Transfer\n\t5.exit\n"); 
			System.out.println("------------------------------------------------------");
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		
			 ch = Integer.parseInt(in.readLine());
		
			switch(ch) {
			case 1 :
				utils.clearScreen();
				System.out.print("your balance is currenty " +  current.getBalance()+"€\n");
				System.out.print("\nif you wanna return to the Menu write y ");
				String input = "";
				
					while(!input.equals("y")) {
						input = in.readLine();
					}
				
				break;
			case 2 : 
				utils.clearScreen();
				System.out.print("Okay please Enter the amount to withdraw: ");
				double money = Integer.parseInt(in.readLine());
				
				withdraw(money,current);
				break; 
			case 3: break; 
			// this method should provide a possibility to change your password or connected email adress 
			
			case 4:
				transfer(current, in);
				
				
				break; 
			// in this method it should be possible to transfer money to another account. 
			case 5: 
			System.out.print("logging out ...\n");
			System.out.print("good bye! \n");
			break;
			
			default: 
				ch = 0; 
				break;
			}
		}while(ch != 5);
	}
	
	
	
	
	/**
	 * this method  withdraws the requested amount of money 
	 * @param double money, Account current
	 * @return
	 */
	private void withdraw(double money, Account current ) {
		mysqlConnector sql = new mysqlConnector();
		if(money >  ATMchange) {
			System.err.print("Sorry there is not enough money in this ATM\n");
			return;
		}
		if(money > current.getBalance()) {
			System.out.print("sorry you dont have enough money in your Bank account , currently: " + current.getBalance()+ "€\n");
			return;
		}
		
		double currentbalance = current.getBalance() - money;
		current.setBalance(currentbalance);
		try {
			sql.withdrawFromAccount(current);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ATMchange -= money;
		System.out.print(money + "€ got sucessfully withdrawn from your bank account new balance: " + current.getBalance() + "€\n");
		
	}
	/**
	 * 
	 * @param Account current
	 * @param BufferedReader in
	 */
	private void transfer(Account current, BufferedReader in) {
		System.out.print("Please Enter the name of the person you want to transfer money to: "); 
		try {
			String name  = in.readLine();
			System.out.print("Please enter the card Id of the person you want to transfer to");
			String cardNR = in.readLine();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
