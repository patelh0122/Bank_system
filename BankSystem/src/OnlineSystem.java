import java.util.Scanner;

public class OnlineSystem {
	
private OnlineAccount theLoginAccount;
    
    public OnlineSystem()
    {
        //when the system is created, no login account yet
        theLoginAccount = null;
    }
    
    public void showMainPage()
    {
        Scanner input = new Scanner(System.in);
        String selection = "";
        
        while(!selection.equals("x"))
        {
            //menu of the online system
            System.out.println();
            System.out.println("Please make your selection");
            System.out.println("1: create a new online ID");
            System.out.println("2: Login your online account");
            System.out.println("x: Leave the online account system");
            System.out.println();
            
            //get input from user
            selection = input.next();
            
            if(selection.equals("1"))
            {
                //register right here
                register();
            }
            else if(selection.equals("2"))
            {
                //login right here
            	login();
                 
            }
            else
            {
                ;
            }
            
            
        }
        
    }
    
    public void register()
    {
        Scanner input = new Scanner(System.in);
        //get the SSN, id and password
        String ssn, accountID, password;
        System.out.println("Please enter your ssn");
        ssn = input.next();
        System.out.println("Please enter your login ID");
        accountID = input.next();
        System.out.println("Please enter your new password");
        password = input.next();
        
        //boolean variables for testing
        boolean idUsed = false;
        boolean hasID = false;
        //test for ID
        for(int i=0; i<BankSystem.allOnlineAccounts.size(); i++)
        {
        	if(accountID.equals(BankSystem.
               allOnlineAccounts.get(i).getId()))
            {
                idUsed = true;
                break;
            } 
        }
        //test for SSN
        for(int i=0; i<BankSystem.
                allOnlineAccounts.size(); i++)
        {
            if(ssn.equals(BankSystem.
                    allOnlineAccounts.get(i).getSsn()))
            {
                hasID = true;
                break;
            }
        }
        
        //if id is used
        if(idUsed == true)
        {
            System.out.println("****** The ID is used! "
                    + "Please select another one!******");
        }
        else if(hasID == true) //if ssn is used
        {
            System.out.println("****** The customer has"
                    + " an account already! ******");
        }
        else //create a new online account
        {
            OnlineAccount aNewOne = new 
        OnlineAccount(ssn, accountID, password);
            BankSystem.allOnlineAccounts.add(aNewOne);
            System.out.println("****** The registration "
                    + "is successful! ******");
        }
        
    }
    
    public void login()
    {
        //we need id and password
        Scanner input = new Scanner(System.in);
        String id="";
        String password="";
        boolean idFound = false;
        
        //get the login info.
        System.out.println("Please enter your ID");
        id = input.next();
        System.out.println("Please enter your password");
        password = input.next();
        
        for(int i=0; i<BankSystem.allOnlineAccounts.size(); i++)
        {
            if(id.equals(BankSystem.
                    allOnlineAccounts.get(i).getId()))
            {
                idFound = true;
                //check the password
       if(password.equals(BankSystem.
               allOnlineAccounts.get(i).getPsw()))
                {
                    //password is good
                  theLoginAccount = BankSystem.
                          allOnlineAccounts.get(i);
                    //show the login account welcome page
                    theLoginAccount.welcome();
                    break;
                }
                else
                {
                    //password is not good
                    System.out.println("******** Password "
                            + "is not correct!*********");
                }
                
                break;
            }
             
        }
        
        //the id is not found
        if(idFound == false)
        {
            System.out.println("********* The login "
                    + "ID was not found! **********");
        }
        
    }

}
