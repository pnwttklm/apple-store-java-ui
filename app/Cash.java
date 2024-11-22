package app;

/**
 * Name: Poonyawatt Klumnaim
 * Student ID: 6588048
 * Secion: 1
 */

public class Cash extends Payment {
	
	// TODO 13: Complete class `Cash` that inherits Payment class.
	// 		 `Cash`:
	//			Subclass "private" instance variables:
	//			- cash 
	//			Extra public interfaces
	//        	- public double getChange() 
	//				calculate and return change if cash tendered is more than the paid amount.
	//				Since the smallest coin in Thailand is 0.25 Baht, for the decimal point, the change must be rounded-down to 
	//				0.25, or 0.50, or 0.75 Baht. 
	//				For example, if the amount is 199.45 and the cash is 200.00, the change must be 0.50 Baht (not 0.55 Baht).
	//				In addition, if cash tendered is less than the required amount, return 0.00.
	//		   Overrided behaviors
	//			- public boolean isValid() if the cash tendered is more than the paid amount, return true. Otherwise, return false
	//				For example: 
	//					paid amount is 3600 and cash tendered is 4000, so this method returns true
	//					paid amount is 4000 and cash tendered is 3600, so this method returns false
	// 			- public String log() return cash payment information -> amount, cash tendered, change
	//				For example: 
	//					"[VALID] CASH::3600.25::4001.00::400.75" 
	//			
	
	
	// ============ Instance Variables ============
	private double cash;	// Cash tendered is a sum of money given in payment. It may not be equal to the exact amount owed.		
	// ============================================
	
	
	// =============== Constructors ===============
	/**
	 * Constructor initializes the payment method's name as "CASH", paid amount, and cash tendered.
	 * @param paid amount 
	 * @param cash tendered
	 */
	public Cash(double amount, double cash) {
		super("CASH", amount);
		this.cash = cash;
	}
	// ============================================


	
	// YOUR CODE GOES HERE
	/**
	 * mine
	 * first of all check whether the cash is enough or not
	 * then backup change and cut all sed then keep full and sed
	 * if full is 56 sed is 0.69 it will in sed + 0.25 >= 1 then it will return 56 + 0.75 and go on
	 * @return
	 */
	public double getChange() {
		if(cash <= amount) {
			return 0.00;
		}
		double change = cash - amount;
		int full = (int)change;
		double sed = change - full;
		if(sed + 0.25 >= 1) {
			return full + 0.75;
		}else if(sed + 0.50 >= 1) {
			return full + 0.5;
		}else if(sed + 0.75 >= 1) {
			return full + 0.25;
		}
		return full;
	}
	
	
	
	@Override
	/**
	 * mine
	 * check whether the cash is enough or not
	 */
	public boolean isValid() {
		if(cash >= amount) {
			return true;
		}
		return false;
	}
	
	@Override
	/**
	 * mine
	 * log the Cash in the properly format
	 * e.g. "[VOID] CASH::174600.00::4000.00::0.00"
	 */
	public String log() {
		String isV = "VOID";
		if(this.isValid()) {
			isV = "VALID";
		}
		return "[" + isV +"]" + " CASH::" + df.format(amount) + "::" + df.format(cash) + "::" + df.format(getChange());
	}
	
	
	
}