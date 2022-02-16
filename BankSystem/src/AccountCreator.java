import java.util.Scanner;
import java.sql.*;
import java.text.DecimalFormat;

public class AccountCreator {
	
	 
	public static void createAccount(String type)
	{
		Scanner input = new Scanner(System.in);
		
		System.out.println("Please enter your ssn: ");
		String ssn = input.nextLine();
		System.out.println("Please enter your initial deposit: ");
		double balance = input.nextDouble();
		
		final String url = "jdbc:mysql://mis-sql.uhcl.edu/patelh0122?useSSL=false";
		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		

		try
		{
			//connect to the database
			conn = DriverManager.getConnection(url, "patelh0122","2034984");
			stm = conn.createStatement();
			DecimalFormat df = new DecimalFormat("##.00");
			String s = DateAndTime.DateTime()+": Account opened with an initial balance $" +
						df.format(balance) + "\n";
			//get the next available account number
			rs = stm.executeQuery("Select * from nextAccountNumber");
			int nextNum = 0;
			String accountNum = "";
			// get the next account number and update the next account number
			if(rs.next())
			{
				// we found the available number
				accountNum =""+ rs.getInt(1);
				nextNum = rs.getInt(1) + 1;
			}
			
			//rolled back to here if anything is wrong
			conn.setAutoCommit(false);
			//update the next account number
			int t = stm.executeUpdate("update nextAccountNumber set nextID ='" + nextNum + "'");
			int numWithdraw = 0;
			int r = stm.executeUpdate("Insert into BankAccount values('" + accountNum + "','" + ssn +
					"','"+ balance + "', '" +s + "','"+ type + "', '" + numWithdraw +"') ") ;
					conn.commit();
					conn.setAutoCommit(true);
					
					//successful msg
					System.out.println("*****The bank account is created successfully!*****");
					
		}

		catch(SQLException e)
		{
			System.out.println("Acccount creation failed");
			e.printStackTrace(); //print out exception message
		}
		finally
		{
			//close the database
			try 
			{
			conn.close();
			stm.close();
			rs.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		
	}

}
