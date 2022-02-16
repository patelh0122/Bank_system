import java.text.DecimalFormat;

public class SavingsAccount extends BankAccount{
	
	private int numberOfWithdraw = 0; //at the beginning, it is 0
	private double overWithdrawFee = 10.0;
	
	//constructor
	public SavingsAccount(String s, double b)
	{
		super(s, b);
	}
	
	//override the withdraw method
	@Override
	public void withdraw(double amount)
	{
		//why override? because in the savings account, the withdraw method is different 
		//than other bank account withdraw method
		//there is an over withdraw fee that may be charged
		if(super.getBalance() - amount >= 0.0)  //to check if it has enough money to withdraw
		{
			super.setBalance(super.getBalance() - amount); //update the balance
			//update the statement
			DecimalFormat df = new DecimalFormat("##.00");
			super.getStatement().add(DateAndTime.DateTime() + ": Withdraw $" + df.format(amount) 
			+ ". New Balance: $" + df.format(super.getBalance()));
			//update for the fee
			if(numberOfWithdraw >= 1)
			{
				super.setBalance(super.getBalance() - overWithdrawFee);  //withdraw fee charged
				super.getStatement().add(DateAndTime.DateTime() + ": a $" + df.format(overWithdrawFee) 
				+ " fee is charged for over the limit of number of withdrawals. New Balance: $" + df.format(super.getBalance()));
			}
			
			//update the number of withdraw
			numberOfWithdraw++;
		}
	}

	public int getNumberOfWithdraw() {
		return numberOfWithdraw;
	}

	public void setNumberOfWithdraw(int numberOfWithdraw) {
		this.numberOfWithdraw = numberOfWithdraw;
	}

	public double getOverWithdrawFee() {
		return overWithdrawFee;
	}

	public void setOverWithdrawFee(double overWithdrawFee) {
		this.overWithdrawFee = overWithdrawFee;
	}
	
	
	 

}
