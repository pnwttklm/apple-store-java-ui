package app;
/**
 * Name: Poonyawatt Klumnaim
 * Student ID: 6588048
 * Secion: 1
 */

import java.io.*;
import java.nio.file.Paths;
import java.util.*;

import com.google.gson.*;

public class OrderFactory {
	public static final String OUTPUT_PATH = Paths.get("").toAbsolutePath().toString() 
			+ File.separator + "data" +  File.separator + "outputs" + File.separator ;
    
    public static Order createOneOfEachOrder(String customerName, String[] paymentInfo) {
    	
    	Customer c = new Customer(customerName);
        Order order = new Order(c);
        for (int barcode = 1; barcode <= ItemFactory.MAX_BARCODE; barcode++) {
            order.addItem(ItemFactory.createItem(barcode, 1));
        }
        
        if(paymentInfo != null) {
        	order.setPayment(paymentInfo);
    	}
    	
    	return order;
    }
    
    

    /**
     * Read the text file at `filepath` and create Order using the information in the file.
     *
     * The format of the file is:
     * Customer Name
     * barcode1 quantity1
     * barcode2 qunatity2
     * . . .
     * NONE or Payment Data (at the last line)
     *
     * If the quantity is negative, it means that the customer wants to reduce the items.
     *
     * For example:
     * Hesitant Customer
     * 1 20
     * 3 10
     * 2 5
     * 1 -10
     * 1 -5
     * 2 5
     * 1 -10
     * 1 -100
     * 2 -10
     * NONE
     *
     * In this example, only items with the barcode of 3 is left (quantity = 10) and there is no payment method.
     *
     * @param filepath
     * @return Order | null
     * @throws FileNotFoundException 
     */
    @SuppressWarnings("resource")
    /**
	 * mine
	 * get a file then convert each string into a string arraylist
	 * for online customer they will contain '(' in their firstline
	 * 		then split the first line from ' (' the thing before ' (' is name
	 * 		then split the rest of first line by ', ' the thing before ', ' is mail
	 * 		then split the rest of mail by ')' the thing before ')' is zipcode
	 * for onsite customer we can just use first line
	 * 
	 * then check the 0 char for barcode and the 2 for - or number
	 * if - use reduce method in the other hand if number get the rest number and use add method
	 */
	public static Order createOrderFromFile(String filepath) {
        // TODO 26: Implement createOrderFromFile(String filepath)
     	File file = new File(filepath);
    	Scanner in = null;
    	String input;
    	ArrayList<String> lines = new ArrayList<>();
    	
    	
    	try {
    		in = new Scanner(file);
    		while(in.hasNext()){
    			input = in.nextLine();
    			lines.add(input);
    		}
    		Customer cus;
    		if(lines.get(0).contains("(")) {
    			String[] listName = lines.get(0).split(" \\(");
    			String[] mailto = listName[1].split(", ");
    			String[] zipcode = mailto[1].split("\\)");
    			cus = new OnlineCustomer(listName[0], mailto[0] , zipcode[0]);
    		}else {
    			cus = new Customer(lines.get(0));
    		}
    		
    		Order ord = new Order(cus);
    		for(int i = 1; i < lines.size()-1; i++) {
    			String[] listOr = lines.get(i).split(" ");
    			if(listOr[1].charAt(0) == '-') {
    				ord.reduceItem(Math.abs(Integer.parseInt(listOr[0])), Math.abs(Integer.parseInt(listOr[1])));
    			}else {
    				ord.addItem(Math.abs(Integer.parseInt(listOr[0])), Math.abs(Integer.parseInt(listOr[1])));
    			}
    		}
    		
    		if(!lines.get(lines.size()-1).equals("NONE")) {
    			String[] pay = lines.get(lines.size()-1).split("::");
        		ord.setPayment(pay);
    		}
    		return ord;
    		
    	}catch(FileNotFoundException e){}
    	
    	return null;
        
    	
    }
    
    /**
     * Write `order` into a file at `filepath`. The format of the output is:
     *
     * Sale Person: Student Full Name (Student ID)
     * <order log()>
     *
     * For example:
     * Sale Person: Siripen Pongpaichet (6488000)
	 * Customer: 1: Thanapon Noraset
	 * - Sinovac\t3000.00\t2 (doses)\t6000.00
	 * - AstraZeneca\t300.00\t1 (doses)\t300.00
	 * Total: 6300.00
	 * [VALID] CASH::6300.00::7000.00::700.00
     *
     * @param order
     * @param filepath
     * @throws IOException
     */
    
    /**
     * mine
     * write order into text
     * get filepath to write a file
     * just write order.log()
     * @param order
     * @param filepath
     * @throws IOException
     */
    public static void writeOrderText(Order order, String filepath) throws IOException {
        // TODO 27: Implement writeOrderText(Order order, String filepath)
    	try {
    		FileWriter out = new FileWriter(filepath);
    		out.write("Sale Person: Poonyawatt Klumnaim (6588048)\n");
    		out.write(order.log());
    		out.close();
    	}catch(FileNotFoundException e) {}
    }
    
    
    /**
     * Write `order` into a file at `filepath`. The format of the output is in JSON:
     * 
-----------------------------------------------------
{
  "orderID": 4,
  "customer": {
    "customerID": 1,
    "name": "Thanapon Noraset"
  },
  "items": [
    {
      "name": "Sinovac",
      "price": 3000.0,
      "quantity": 2
    },
    {
      "name": "AstraZeneca",
      "price": 300.0,
      "quantity": 1
    },
    
  ],
  "payment": {
    "type": "Cash",
    "properties": {
      "cash": 7000.0,
      "amount": 6300.0,
      "method": "CASH"
    }
  }
}
-----------------------------------------------------
     * 
     * @param order
     * @param filepath
     * @throws JsonIOException
     * @throws IOException
     */
    
    /**
     * mine
     * hard as hell!
     * create gsonbuilder
     * registertypeadapter from paymentadapter
     * get json of order
     * write file in the json format
     * done :)
     * @param order
     * @param filepath
     * @throws JsonIOException
     * @throws IOException
     */
    public static void writeOrderJson(Order order, String filepath) throws JsonIOException, IOException {
    	// TODO 28: Implement writeOrderJson(Order order, String filepath)
    	// Hint. Since the Payment is an abstract class which cannot be constructed,
    	// so you will need to apply your custom serializer and deserializer.
    	// If you use Gson library, you can register GsonBuilder with PaymentAdapter class  
    	// (already provided - see the PaymentAdapter.java file for more detail).
    	// However, feel free to explore other methods and create any additional classes or methods as needed.
    	
    	try {
    		GsonBuilder gsonBuilder = new GsonBuilder();
    		gsonBuilder.registerTypeAdapter(Payment.class, new PaymentAdapter());
    		Gson gson = gsonBuilder.create();
    		Order ord = order;
    		String json = gson.toJson(ord);
    		FileWriter out = new FileWriter(filepath);
        	out.write(json);
        	out.close();
    	}catch(Exception e) {}
    	
    }
		
}
