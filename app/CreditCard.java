package app;

/**
 * Name: Poonyawatt Klumnaim
 * Student ID: 6588048
 * Secion: 1
 */

public class CreditCard extends Payment {
	
	// TODO 14: Complete class `CreditCard` that inherits Payment class.
	// 		 `CreditCard`:
	//			Subclass "private" instance variables:
	//			 - CardType type
	//			 - String number
	//
	//			Extra public interfaces
	//        	 - public String getFormattedCardNumber()	
	//					The card's number must be in the actual format with space between group of number based on card type
	// 
	//		    Overrided behaviors
	//			 - public boolean isValid() if card number is valid return true. Otherwise, return false
	//				For example: 
	//					if the card is VISA and has number "4234567890123456", this card is valid.
	//					If the card is JCB and has number "4234567890123456", this card is invalid. 
	//			 - public String log() return creditcard payment information
	//				For example: 
	//					"[VALID] CARD::3600.00::VISA::3434 567890 12345"
	//				
	
	// ============ Instance Variables ============
	public static enum CardType{ VISA, AMERICANEXPRESS, JCB, MASTERCARD }; 
					// different types of credit card 
		
	private CardType type;		// card's type
	private String number;		// card's number
	// ============================================

	
	// =============== Constructors ===============
	
	public CreditCard(double amount, CardType type, String number) {
		super("CARD", amount);
		this.type = type;
		this.number = number;		// i.e., 343456789012345
	}
	public CreditCard(double amount, String number) {
		this(amount, checkType(number), number);
	}
	// ============================================
	
	
	/**
	 * Verify the validity of the card information. Different card type has different format of card number as follow
	 * VISA -> the number must be 16 digits, and start with number 4
	 * AMERICANEXPRESS -> the number must be 15 digits, and start with either 34 or 37
	 * JCB -> the number must be 16 digits, and start with 3528 to 3589
	 * MASTERCARD -> the number must be 16 digits, and start with 51 or 52
	 * 
	 * For example, if the card is VISA and has number "4234567890123456", this card is valid.
	 * If the card is JCB and has number "4234567890123456", this card is invalid.
	 * 
	 * @return True if the card is valid, otherwise return false
	 */
	@Override
	/**
	 * mine
	 * this method is used for check whether the credit card is valid or not
	 * for VISA, AMERICANEXPRESS, and MASTERCARD are so simple
	 * for JCB I have to change the 2 and 3 index of number to int and check whether it is in 28 - 89 or not
	 */
	public boolean isValid() {
		// TODO 14.1: implement isValid() method to validate card number
		int forJCB = 0;
		if(number.length() == 16) {
			forJCB = Character.getNumericValue(number.charAt(2))*10 + Character.getNumericValue(number.charAt(3));
		}
		
		switch(this.type){
		case VISA:
			if(number.length() == 16 && number.charAt(0)== '4') {
				return true;
			}
			return false;
		case AMERICANEXPRESS:
			if(number.length() == 15 && number.charAt(0)== '3' && (number.charAt(1) == '4' || number.charAt(1) == '7')) {
				return true;
			}
			return false;
		case JCB:
			if(number.length() == 16 && number.charAt(0)== '3' && number.charAt(1)== '5' && forJCB >= 28 && forJCB <= 89) {
				return true;
			}
			return false;
		case MASTERCARD:
			if(number.length() == 16 && number.charAt(0)== '5' && (number.charAt(1) == '1' || number.charAt(1) == '2')) {
				return true;
			}
			return false;
		default:
			return false;
		}
	}
	
	public static CardType checkType(String number) {
		
		int forJCB = 0;
		
		
		if(number.length() == 16) {
			forJCB = Character.getNumericValue(number.charAt(2))*10 + Character.getNumericValue(number.charAt(3));
		
		}
		
		try {
			Long longValue = Long.parseLong(number);
//			System.out.println("Parsed value: " + longValue);
		} catch (NumberFormatException e) {
//			System.out.println("ERROR");
			return null;
			
		}
		
		if(number.length() == 16 && number.charAt(0)== '4') {
//			System.out.println("VISA");
				return CardType.VISA;
		}
		if(number.length() == 15 && number.charAt(0)== '3' && (number.charAt(1) == '4' || number.charAt(1) == '7')) {
//			System.out.println("AMERICANEXPRESS");
				return CardType.AMERICANEXPRESS;
		}
		
		if(number.length() == 16 && number.charAt(0)== '3' && number.charAt(1)== '5' && forJCB >= 28 && forJCB <= 89) {
//			System.out.println("JCB");
				return CardType.JCB;
		}
		if(number.length() == 16 && number.charAt(0)== '5' && (number.charAt(1) == '1' || number.charAt(1) == '2')) {
//			System.out.println("MASTERCARD");
				return CardType.MASTERCARD;
		}
		
			return null;
		
	}
	
	
	/**
	 * If the card is valid, formats the card's number according to the card's type.
	 * AMERICANEXPRESS (15 digits): #### ###### ##### (4-6-5)
	 * VISA, JCB, MASTERCARD (16 digits): #### #### #### #### (4-4-4-4)
	 * 
	 * For example, if the card is VISA and has number "4234567890123456", 
	 * the string value "4234 5678 9012 3456" will be returned.
	 * 
	 * if the card is AMERICANEXPRESS and has number "343456789012345", 
	 * the string value "3434 567890 12345" will be returned.
	 * 
	 * If the card information is invalid, returns "invalid card number".
	 * 
	 * @return a string of formatted card's number | "invalid card number"
	 */
	
	/**
	 * mine
	 * simply delicious
	 * @return
	 */
	public String getFormattedCardNumber() {
		// TODO 14.2: implement getFormattedCardNumber() to return card number in a beautify format				
		if(isValid()) {
			if(type == CardType.AMERICANEXPRESS) {
				return number.substring(0, 4) + " " + number.substring(4, 10) + " " + number.substring(10);
			}else {
				return number.substring(0, 4) + " " + number.substring(4, 8) + " " + number.substring(8, 12) + " " + number.substring(12);
			}
		}else {
			return "invalid card number";
		}
	}
	
	/**
	 * @Override log
	 * return credit card payment information
	 * 
	 * For example, 
	 * 	"[VALID] CARD::3600.00::VISA::3434 567890 12345"
     * @return string to provide information of this payment
	 */
	
	/**
	 * mine
	 * log the credit card in the proper format
	 * e.g. "[VALID] CARD::174600.00::VISA::4234 5678 9012 3456"
	 */
	public String log() {
		// TODO 14.3: implement log() method of credit card
		String isV = "VOID";
		if(this.isValid()) {
			isV = "VALID";
		}
		return "["+isV+"] CARD::" + df.format(amount) + "::" + type + "::" + getFormattedCardNumber();
	}


}
