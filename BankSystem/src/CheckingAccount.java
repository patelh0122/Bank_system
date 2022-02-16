import java.text.DecimalFormat;

public class CheckingAccount extends BankAccount {
	
	private double checksPrice;
	
	public CheckingAccount(String s, double b, double p)
	{
		super(s, b); //explicitly call the super class constructor
		checksPrice = p;
	}

	public double getChecksPrice() {
		return checksPrice;
	}

	public void setChecksPrice(double checksPrice) {
		this.checksPrice = checksPrice;
	}
	
	//orderChecks method
	public void orderChecks()
	{
		System.out.println("A book of checks will be sent to your address within 3 business days");
		System.out.println();
		double newBalance = super.getBalance() - checksPrice;  //charge for the checks'
		super.setBalance(newBalance);
		//update the statement
		DecimalFormat df = new DecimalFormat("##.00");
		super.getStatement().add(DateAndTime.DateTime() + ": $" + df.format(checksPrice) 
		+ " is charged for ordering new checks. New Balance: $" + df.format(super.getBalance()));
	}

}
