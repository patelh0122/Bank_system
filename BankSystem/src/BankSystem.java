import java.util.ArrayList;
import java.util.Scanner;

public class BankSystem {
	
	//we need to create two arrayLists
	public static ArrayList<BankAccount> allBankAccounts = new ArrayList<BankAccount>();
	public static ArrayList<OnlineAccount> allOnlineAccounts = new ArrayList<OnlineAccount>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 
		//main menu
		Scanner input = new Scanner(System.in);
		String selection = "";
		
		while(!selection.equals("x"))  //while not x, keep displaying the menu
		{
			//display the menu
			System.out.println();
			System.out.println("Please make your selection: ");
			System.out.println("1: Open a new checking account");
			System.out.println("2: Open a new savings account");
			System.out.println("3: Go to online system");
			System.out.println("d: display all created accounts");
			System.out.println("x: Finish");
			
			//get the selection from the user
			selection = input.nextLine();
			System.out.println();
			
			//based on the input, go to different function
			if(selection.equals("1"))
			{
				//open a new checking 
				AccountCreator.createAccount("Checking");
			}
			else if(selection.equals("2"))
			{
				AccountCreator.createAccount("Savings");
			}
			else if(selection.equals("3"))
			{
				//online system
				new OnlineSystem().showMainPage();
			}
			else if(selection.equals("d"))
			{
				for(BankAccount b: allBankAccounts)
				{
					if(b instanceof CheckingAccount)
					{
						System.out.println();
						System.out.println("Checking Account: ");
						System.out.println("Account number: " + b.getAccountNum());
						System.out.println("SSN: " + b.getSsn());
						System.out.println("Balance: " + b.getBalance());
						System.out.println("Check-Ordering price: $" + ((CheckingAccount)b).getChecksPrice());
						System.out.println();
					}
					
					if(b instanceof SavingsAccount)
					{
						System.out.println();
						System.out.println("Savings Account: ");
						System.out.println("Account number: " + b.getAccountNum());
						System.out.println("SSN: " + b.getSsn());
						System.out.println("Balance: " + b.getBalance());
						System.out.println("Over withdraw fee: $" + ((SavingsAccount)b).getOverWithdrawFee());
						System.out.println();
					}
				}
			}
					 
					 
		}

	}

}
