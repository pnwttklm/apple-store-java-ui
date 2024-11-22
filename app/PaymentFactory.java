package app;

/**
 * Name: Poonyawatt Klumnaim
 * Student ID: 6588048
 * Secion: 1
 */

public class PaymentFactory {


	/**
	 * Crate a Payment object according to the given parameters
	 * 
	 * @param amount
	 *            total amount that need to be paid
	 * @param args
	 *            list of parameters depends on the payment type. For example, Cash
	 *            payment only requires cash tendered, while CreditCard payment
	 *            requires CardType, and card's number
	 * @return 
	 * 		Payment	
	 * @throws IllegalArgumentException
	 * 			  - if the payment type is unavailable => not [CASH|CARD],
	 * 			    throw IllegalArgumentException exception with a specific message 
	 * 				"xx is an unavailable payment type." where xxx is the unavailable input argument
	 * 
	 * 			  - if the card type is unavailable => not [VISA|AMERICANEXPRESS|JCB|MASTERCARD] 
	 * 			    throw IllegalArgumentException exception with a specific message 
	 * 				"xxx is an unavailable card type." where xxx is the unavailable input argument
	 * 
	 * @throws IndexOutOfBoundsException
	 * 			  - if the number of arguments is insufficient to construct the payment,
	 * 			    throw IndexOutOfBoundsException exception
	 * 			    For example, Cash payment needs 2 arguments: amount and cash value, 
	 * 							 CreditCard payment needs 3 arguments: amount, card's type, and card's number
	 * 
	 */
	public static Payment createPayment(double amount, String[] args)
			throws IllegalArgumentException, IndexOutOfBoundsException {

		String type = args[0];
		
		if (type.equalsIgnoreCase("CASH")) {
			// TODO 15: Create `Cash` payment class by parsing arguments according to the
			// Cash constructor method
			if(args.length == 2) {
				return new Cash(amount, Double.parseDouble(args[1]));
			}else {
				throw new IndexOutOfBoundsException(""+args.length);
			}

		} else if (type.equalsIgnoreCase("CARD")) {
			// TODO 16: Create `CreditCard` payment class by parsing arguments according to
			// the CreditCard constructor method
			if(args[1].equalsIgnoreCase("VISA") || args[1].equalsIgnoreCase("AMERICANEXPRESS") || args[1].equalsIgnoreCase("JCB") || args[1].equalsIgnoreCase("MASTERCARD")) {
				if(args.length == 3) {
					return new CreditCard(amount, CreditCard.CardType.valueOf(args[1]), args[2]);
				}else {
					throw new IndexOutOfBoundsException(""+args.length);
				}
				
			}else{
				throw new IllegalArgumentException(args[1] + " is an unavailable card type.");
			}
		} else {
			// DO NOT MODIFY
			throw new IllegalArgumentException(type + " is an unavailable payment type.");
		}

	}
}
