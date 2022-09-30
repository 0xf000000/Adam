package users;

import java.io.IOException;

/**
 * this Interface should be used to create a new Account for our bank account. <br> 
 * the class that Implement this Interface should also use a instance of  <code>mysqlConnector</code>
 * @author leon
 *
 */
public interface ICreateNewAccount  {

	
	/**
	 *	creates the Account our database if the user has no account <br> 
	 * @throws IOException
	 */
		public void createAccount() throws IOException;
		
		
			
		
}
