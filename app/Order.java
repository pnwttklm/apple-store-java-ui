package app;
/**
 * Name: Poonyawatt Klumnaim
 * Student ID: 6588048
 * Secion: 1
 */


import java.util.ArrayList;


public class Order implements Loggable{

	// ============ Instance Variables ============
	

	private int orderID;
	private Customer customer;
	private ArrayList<Item> items;
	private Payment payment;
	// ============================================

    // =============== Constructors ===============

	/**
	 * Constructor to initialize orderID according to the running ID
	 * The first order's ID is 1. The second order's ID is 2, and so on.
	 * @param c : Customer
	 */
	
	/**
	 * mine
	 * when a customer order set their ID
	 * and create an empty list of item
	 * @param c
	 */
	public Order(Customer c) {
		// TODO 17: Implement a constructor method of Order
		this.orderID = Customer.runningID;
//		Customer.setFromLog();
		this.customer = c;
		this.items = new ArrayList<>();
	}

	// ============================================

	//  ============= DO NOT MODIFY  =============
	
	public int getOrderID() {
		return orderID;
	}

	public Customer getCustomer() {
		return customer;
	}
	
	public ArrayList<Item> getItems(){
		return this.items;
	}

	public int numItems() {
		return this.items.size();
	}
	
	public Payment getPayment() {
		return this.payment;
	}
	
	public boolean checkPaymentStatus() {
		if(payment != null) {
			return this.payment.isValid();
		} else {
			return false;
		}
	}

	@Override
	public boolean equals(Object object) {
		Order o = (Order) object;
		return this.log().equalsIgnoreCase(o.log());
	}
	
	// ============================================

	/**
	 * Return total price of all items in the `items` array list.
	 * If the customer is an OnlineCustomer, include his shipping fee into the total too.
	 * Note that if there is no order, nothing to ship, the total price will always be zero.
	 * @return
	 */
	
	/**
	 * mine
	 * calculate total price from item list of the customer
	 * @return
	 */
	public double getTotalPrice() {
		// TODO 18: Implement getTotalPrice() method
		double total = 0.00;
		for(Item i : items) {
			total = total + i.getTotal();
		}
		if(customer instanceof OnlineCustomer ) {
			if(items.size() > 0) {
				total = total + ((OnlineCustomer)customer).getShippingFee();
			}
		}
		return total;
	}
	
	public double getShippingFee() {
		double total = ((OnlineCustomer)customer).getShippingFee();
		return total;
	}
	
	
	/**
	 * Return an item in the `items` array list if the `name` exists
	 * otherwise return null
	 *
	 * @param name
	 * @return Item | null
	 */
	
	/**
	 * mine
	 * find item by its name
	 * @param name
	 * @return
	 */
	public Item findItem(String name) {
		// TODO 19: Implemenent findItem(String name) method
		for(Item i : items) {
			if(i.getName().equals(name)) {
				return i;
			}
		}
		return null;
	}


	/**
	 * Add `newItem` to the `items` array list.
	 * If the name exists, add only the quantity to the existing item, ignoring the price.
	 * Otherwise add `newItem` as an object.
	 *
	 * For example:
	 * items:
	 *  0: Sinovac 1 doses
	 *
	 * Add Sinovac 1 doses
	 * 		-> items:
	 * 			0: Sinovac 2 doses
	 * Add AstraZeneca 1 doses
	 * 		-> items:
	 * 			0: Sinovac 2 doses
	 * 			1: AstraZeneca 1 doses
	 *
	 * @param newItem
	 */
	
	/**
	 * mine
	 * add new item and setQuantity by using setquantity
	 * @param newItem
	 */
	public void addItem(Item newItem) {
		// TODO 20: Implement addItem(Item newItem) method
		//		items.add(newItem);
		boolean boo =false;
		for(int i = 0; i < items.size(); i++) {
			if(items.get(i).getName().equals(newItem.getName())) {
				Item newi = items.get(i);
				newi.setQuantity(newi.getQuantity()+newItem.getQuantity());
				items.set(i, newi);
				boo =true;
				break;
			}
		}
		if(!boo) {
			items.add(newItem);
		}
		
	}

	/**
	 * Add an item using `barcode`. See `addItem(Item newItem)`	for the documentation
	 * The `barcode` of items is defined in `ItemFactory`
	 *
	 * @param barcode
	 * @param quantity
	 */
	
	/**
	 * mine
	 * when it already has its quantity use this method to add an item
	 * @param barcode
	 * @param quantity
	 */
	public void addItem(int barcode, int quantity) {
		// TODO 21: Implement addItem(int barcode, int quantity) method
		// Hint:
		// - Use ItemFactory to create an item
		// - Use the above method addItem(Item newItem) to add the item
		this.addItem(ItemFactory.createItem(barcode, quantity));
	}

	/**
	 * Reduce `reducingItem` to the `items` array list.
	 * If the name exists, reduce the quantity to the existing item.
	 * Otherwise do nothing.
	 *
	 * After the reduction, if the quantity is less than or equal to 0,
	 * remove the item from the list.
	 *
	 *
	 * For example:
	 * items:
	 *  0: Sinovac 2 doses
	 *
	 * Reduce Sinovac 1 doses
	 * 		-> items:
	 * 			0: Sinovac 1 doses
	 * Reduce Sinovac 1 doses
	 * 		-> items: (empty)
	 *
	 * @param reducingItem
	 */
	
	/**
	 * mine
	 * reduce item similar the add item
	 * @param reducingItem
	 */
	public void reduceItem(Item reducingItem) {
		// TODO 22: Implement reduceItem(Item reducingItem) method
		for(int i = 0; i < items.size(); i++) {
			if(items.get(i).getName().equals(reducingItem.getName())) {
				if(items.get(i).getQuantity() - reducingItem.getQuantity() <= 0) {
					this.items.remove(i);
				}else {
					Item newi = items.get(i);
					newi.setQuantity(newi.getQuantity() - reducingItem.getQuantity());
					items.set(i, newi);
					break;
				}
				
			}
		}
	}

	/**
	 * Reduce item using `barcode`. See `reduceItem(Item reducingItem)` for the documentation.
	 * The `barcode` of items is defined in `ItemFactory`
	 *
	 * @param barcode
	 * @param deductQuantity
	 */
	
	/**
	 * mine
	 * when it already has its quantity use this method to reduce an item
	 * @param barcode
	 * @param quantity
	 */
	public void reduceItem(int barcode, int deductQuantity) {
		// TODO 23: Implement reduceItem(int barcode, int deductQuantity) method
		this.reduceItem(ItemFactory.createItem(barcode, deductQuantity));
	}

	
	/**
	 * Set a payment method for this order
	 * @param args
	 */
	
	/**
	 * mine
	 * from args[] which contains strings
	 * send to createpayment of paymentfactory
	 * @param args
	 */
	public void setPayment(String[] args) {
		// TODO 24: Implement setPayment(String type, Object[] params) method 
		// Hint:
		// - Use PaymentFactory to create an payment object
		// - Use getTotalPrice() to get an total order amount
		this.payment = PaymentFactory.createPayment(getTotalPrice(), args);
	}
	
	
	/**
	 * Return a string representation of an order
	 * 
	 * Example:
	 * Customer: 1: Siripen Pongpaichet
	 * - Sinovac\t3000.00\t2 (doses)\t6000.00
	 * - AstraZeneca\t300.00\t1 (doses)\t300.00
	 * Total: 6300.00
	 * [VALID] CASH::6300::7000::700
	 * 
	 * If there is not payment yet, return [PENDING] in the last line
	 * 
	 * Example:
	 * Customer: 1: Siripen Pongpaichet
	 * - Sinovac\t3000.00\t2 (doses)\t6000.00
	 * - AstraZeneca\t300.00\t1 (doses)\t300.00
	 * Total: 6300.00
	 * [PENDING]
	 * 
	 * 
	 * @return String
	 */
	
	/**
	 * mine
	 * log the Order in proper format
	 */
	public String log() {
		// TODO 25: Implement log() method for Order class.
		String result = "Customer: " + customer.log() + "\n";
		for(Item i : items) {
			result = result + "- "+ i.log() + "\n";
		}
		result = result + "Total: " + df.format(this.getTotalPrice()) + "\n";
		if(payment == null) {
			result = result + "[PENDING]";
		}else {
			result = result + payment.log();
		}
		return result;
		
	}
	
	
	
	// ============================================
}
