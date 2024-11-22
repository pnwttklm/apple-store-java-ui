package app;
/**
 * Name: Poonyawatt Klumnaim
 * Student ID: 6588048
 * Secion: 1
 */


public class Item implements Loggable {
    // TODO 06: Create two new classes that inherit this class
    //       The new classes should be in its own file, but in the same package.


    // ============ Instance Variables ============
    private String name;
    private double price;
    private int quantity;
    // ============================================

    // =============== Constructors ===============

    /**
     * Constructor to initialize name, price, and quantity
     * @param name
     * @param price
     * @param quantity
     */
    public Item(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.setQuantity(quantity);
    }

    /**
     * Constructor to initialize name and price
     * @param name
     * @param price
     */
    public Item(String name, double price) {
    	// TODO 05: Implement this constructor method with have default quantity as 1
        this.name = name;
        this.price = price;
        this.quantity = 1;
    }

    // ============================================

    //  ============= Public Methods  =============

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return "units";
    }

    public double getTotal() {
        return this.getQuantity() * this.getPrice();
    }
    
    public int getBarcode() {
    	return 0;
    }

    @Override
    public String toString() {
        return "Item [name=" + name + ", price=" + price + ", quantity=" + quantity + ", unit=" + this.getUnit() + "]";
    }

    /**
     * Return name, price, quantity (unit), and total seprated by a tab.
     * For example,
     * - name: "mRNA Vaccine"
     * - price: 1800.0
     * - quantity: 2
     * - unit: "doses"
     * - total: 3600.0
     * Return:
     *  "mRNA Vaccine\t1800.00\t2 (doses)\t3600.00"
     * @return a String description of an item.
     */
    
    /**
	 * mine
	 * log the Item in the proper format
	 */
    public String log() {
        // TODO 07: Implement log method for Item
        // Hint: use df.format(x) to format x to have 2 decimal-point numbers.
        return this.name + "\t" + df.format(this.price) + "\t" + this.quantity + " (" + this.getUnit() + ")\t" + df.format(this.price * this.quantity);
    }

    // ============================================

}
