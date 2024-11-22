package app;

import java.util.HashMap;
import java.util.Map;

/**
 * Name: Poonyawatt Klumnaim
 * Student ID: 6588048
 * Secion: 1
 */


public class OnlineCustomer extends Customer {
	
	// TODO 03: Complete class `OnlineCustomer` that inherits Customer class.
	// 		 `OnlineCustomer`:
	//			Subclass "private" instance variables:
	//			- email 
	//			- zipcode 
	//			- SHIPPING <String, double> table storing shipping fee according to the zipcode
	//			Extra public interfaces
	//        	- public OnlineCustomer(String name, String email, String zipcode)
	//			- public String getEmail()
	// 			- public String getZipCode()
	//			- public double getShippingFee() returns 
	//		   Overrided behaviors
	//			- public String log() will also return email and zipcode in parenthesis
	//				For example: "2: Siripen Pongpaichet (siripen.pon@mahidol.edu, 73170)"
	//			- Public boolean equals()

	
	// ============ Instance Variables ============
	// This map table store the key value pair of zipcode and shipping fee
	private static final Map<String, Double> SHIPPING; 
	static {
	 	SHIPPING = new HashMap<>();
		SHIPPING.put("73170",  50.0);
		SHIPPING.put("10700",  20.0);
		SHIPPING.put("50230", 210.0);
		SHIPPING.put("83120", 250.0);
		SHIPPING.put("20120", 150.0);
	}
		
	private String email = "";
	private String zipcode = "";
	
	
	// ============================================
	
	
	
	// ============== DO NOT MODIFY ===============
	
	public OnlineCustomer(String name, String email, String zipcode) {
		super(name);
		this.email = email;
		this.zipcode = zipcode;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getZipcode() {
		return zipcode;
	}

	// ============================================
	
	
	// =========== YOUR CODE GOES HERE ===========
	
	/**
	 * mine
	 * log the Online Customer in the proper format
	 */
	public String log() {
		return super.getCustomerID()  +": "+super.getName() + " ("+this.email+", " + this.zipcode +")";
	}
	
	/**
	 * check whether they are equal or not by comparing their log
	 * @param o
	 * @return
	 */
	public boolean equals(Object object) {
		OnlineCustomer o = (OnlineCustomer) object;
		return this.log().equalsIgnoreCase(o.log());
	}
	/**
	 * Lookup for the shipping fee from the SHIPPING table based on the customer's zipcode,
	 * if the zipcode does't exist, returns 99.00 as a default shipping fee.
	 * @return shipping fee 
	 */
	
	/**
	 * mine
	 * getter method of shipping fee that depends on zipcode
	 * @return
	 */
	public double getShippingFee() {
		// TODO 04: Implement getShippingFee() method for this class
		if(SHIPPING.get(this.zipcode) == null) {
			return 99.00;
		}
		return SHIPPING.get(this.zipcode);
	}
	
	
	
	
}
