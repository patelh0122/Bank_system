import java.util.ArrayList;
import java.util.Scanner;

public class OnlineAccount {
	
	//attributes
    private String ssn;
    private String id;
    private String psw;
    //an arraylist of the bank accounts related to this online account
    private ArrayList<BankAccount> managedAccounts;
    //constructor
    public OnlineAccount(String s, String d, String p)
    {   
        ssn = s;
        id = d;
        psw = p;
        
        managedAccounts = new ArrayList<BankAccount> ();
        
        
    }
    
    public void welcome()
    {
        managedAccounts.clear();
        //add the related bank accounts into managedAccounts
        for(int i=0; i<BankSystem.allBankAccounts.size(); i++)
        {
            //check the ssn
          if(BankSystem.allBankAccounts.get(i).getSsn().equals(ssn))
            {
              managedAccounts.add(BankSystem.allBankAccounts.get(i));
            }
        }
        
        Scanner input = new Scanner(System.in);
        String selection = "";
        
        while(!selection.equals("x"))
        {
            System.out.println("Welcome to your online accounts");
            System.out.println("Please select your operation");
            //display the managedAccounts
            for(int i=0; i<managedAccounts.size(); i++)
            {
                System.out.printf("%s: select Account %s to view\n", i+1, 
                        managedAccounts.get(i).getAccountNum());
            }
            System.out.println("t: Account transfer");
            System.out.println("c: Order more checks");
            System.out.println("x: Sign out");
            System.out.println();
            
            selection = input.next();
            if(isInteger(selection))
            {
                //view statements
                int intSelection = Integer.parseInt(selection);
                //make sure the selection is in good range
                if(intSelection >= 1 && intSelection 
                        <= managedAccounts.size())
                {
                    //view the statement
                    managedAccounts.get(intSelection - 1).showStatement();
                }
            }
            else if(selection.equals("s"))
            {
                //reset password
            }
            else if(selection.equals("t"))
            {
                //account transfer
                transfer();
            }
            else if(selection.equals("c"))
            {
            	orderChecks();
            }
        } 
    }
    
    public void orderChecks()
    {
    	ArrayList<CheckingAccount> checkingAccounts = new ArrayList<CheckingAccount>();
    	Scanner input = new Scanner(System.in);
    	
    	for(BankAccount b: managedAccounts)
    	{
    		if(b instanceof CheckingAccount)
    		{
    			checkingAccounts.add((CheckingAccount)b);
    		}
    	}
    		
		if(checkingAccounts.size()>0)
		{
			for(int i=0; i<checkingAccounts.size(); i++)
            {
                System.out.printf("%s: select Account %s\n", i+1,
                        checkingAccounts.get(i).getAccountNum());
            }
            System.out.println();
            
            String selection = input.nextLine();
            
            if(isInteger(selection))
            {
            	int intSelection = Integer.parseInt(selection);
            	if(intSelection >=1 && intSelection <= checkingAccounts.size())
            	{
            		checkingAccounts.get(intSelection-1).orderChecks();
            	}
            }
		}
    	
    }
    
    public void transfer()
    {
        //varaibles
        Scanner input = new Scanner(System.in);
        String accountFrom, accountTo;
        double transferAmount;
        
        if(managedAccounts.size()>= 2)
        {
            //list the bank accounts
            for(int i=0; i<managedAccounts.size(); i++)
            {
                System.out.printf("%s: select Account %s\n", i+1,
                        managedAccounts.get(i).getAccountNum());
            }
            System.out.println();
            
            //get the input 
            System.out.println("Please select the account from");
            accountFrom = input.next();
            
            System.out.println("Please select the account to");
            accountTo = input.next();
            
            System.out.println("Please indicate the amount of transfer");
            transferAmount = input.nextDouble();
            
            if(isInteger(accountFrom) && isInteger(accountTo))
            {
                int intFrom = Integer.parseInt(accountFrom);
                int intTo = Integer.parseInt(accountTo);
                
                //validate the input
                if(intFrom >=1 && intFrom <= managedAccounts.size() 
                        && intTo >=1 && intTo <= managedAccounts.size()
                        && intFrom != intTo)
                {
                    if(managedAccounts.get(intFrom - 1).getBalance() 
                            >= transferAmount)
                    {
                   managedAccounts.get(intFrom - 1).withdraw(transferAmount);
                    managedAccounts.get(intTo - 1).deposit(transferAmount);
                        System.out.println("The transfer is successful!");
                        System.out.println();
                    }
                    else
                    {
                        System.out.println("No enough money");
                    }
                    
                }
                else
                {
                    System.out.println("Your input is not valid!");
                }
            }
            else
            {
                System.out.println("Your input is not valid!");
            }
            
        }
        else
        {
            System.out.println("You must have two bank "
                    + "accounts to do the transfer!");
        }
        
    }
    
    private boolean isInteger(String a)
    {
        try
        {
            int i = Integer.parseInt(a);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    
    

    //get methods and set methods
    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }
	
	

}
