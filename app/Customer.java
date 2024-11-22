package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Name: Poonyawatt Klumnaim
 * Student ID: 6588048
 * Section: 1
 */


public class Customer implements Loggable {
	
    // ============ Instance Variables ============
	
    public static int runningID = getFromLog();

    private int customerID;
    private String name;
    // ============================================

    private static int getFromLog() {
    	String filePath = "reference/customerID.txt";
    	File file = null;
		StringBuilder content = new StringBuilder();
		file = new File(filePath);
		ArrayList<String> lines = new ArrayList<>();
    	try {
    		 FileInputStream in = new FileInputStream(file);
    		 InputStreamReader read = new InputStreamReader(in);
    		 BufferedReader bf = new BufferedReader(read);
    		 String line;
    		 while((line = bf.readLine()) != null) {
    			 lines.add(line);
    		 }
    		 bf.close();
    	}catch(Exception e) {System.out.println("error unreadable");}
		return Integer.parseInt(lines.get(0));
    	
    }
    // =============== Constructors ===============
    /**
	 * Constructor initializes the customer's name.
	 * The customer's ID will be automatically assigned according to the running ID.
	 * The first customer will have ID as 1, and the second customer will have ID as 2, and so on
	 * @param name
	 */
    
    /**
     * mine
     * set ID and runningNum for each customer
     * @param name
     */
	public Customer(String name) {
		// TODO 01: Implement constructor method for Customer class according to the document.
		this.name = name;
		customerID = runningID;
		setFromLog();
	}
	
	public Customer() {
		this("");
	}
	
	public static void setFromLog() {
		String filePath = "reference/customerID.txt";
		try {
    		FileWriter out = new FileWriter(filePath);
    		out.write((runningID + 1) + "");
    		out.close();
    	}catch(Exception e) {}
	}
    // ============================================

	
    //  ============= Public Methods  =============
    public int getCustomerID() {
		return this.customerID;
	}

	public String getName() {
		return name;
	}
	
	public void setCustomerName(String name) {
		this.name = name;
	}

	/**
	 * Return ID and name in the following format
	 * 		<ID>: <Name>
	 * Examples:
	 * 	- 0: Siripen Pongpaichet
	 *
	 * @return a String representation of customer
	 */
	
	/**
	 * mine
	 * log the Customer in proper format
	 * e.g. "Customer: 4: Test Order Factory 0"
	 */
	public String log() {
		// TODO 02: Implement log() method for Customer class.
		return this.customerID +": "+this.name;
	}
    // ============================================
}
