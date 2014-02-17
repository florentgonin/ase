package courseworkFSV;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import courseworkFSV.exception.ImpossiblePriceException;
import courseworkFSV.exception.NoGoodStructureDocumentException;
import courseworkFSV.exception.NoMatchingDishException;

/**
 * @author Noel Stephanie, Vasilis Tsifountidis, Florent Gonin
 * a simple class to contain and manage the restaurant details
 */
public class Restaurant {
	
	/** The orders of the night sort by table. */
	private Map<Integer, List<Order>> orders;
	/** The menu of the restaurant. */
	private Menu menu;
	
    /**
     * Set up the restaurant details.
     * @param menuFile The name of the file containing the menu.
     * @param ordersFile The name of the file containing the orders.
     */
	public Restaurant(final String menuFile, final String ordersFile){
		orders = new HashMap<Integer, List<Order>>();
		importMenu(menuFile);
		importOrders(ordersFile);
	}
	
    /**
     * @return The orders of the night sort by table.
     */ 
	public Map<Integer, List<Order>> getOrders() {
		return orders;
	}

    /**
     * @return The menu of the restaurant.
     */ 
	public Menu getMenu() {
		return menu;
	}

    /**
     * Imports the menu.
     * @param filename The name of the file containing the menu.
     */
	public void importMenu(final String filename){
		//initialise empty  menu
        menu = new Menu();
        
        BufferedReader buff = null;
    	String data [] = new String[3];
        try {
        	buff = new BufferedReader(new FileReader(filename));
	    	String inputLine = buff.readLine();  //read first line
	    	while(inputLine != null){  
	    		//split line into parts
	    		data  = inputLine.split(";");
	    		// check if all part are filled
	    		if (data.length < 3) 
	    			throw new NoGoodStructureDocumentException(filename);
	    		//create MenuItem object
	    		double price = Double.parseDouble(data[1].trim());
	    		Category category = Category.valueOf(data[2].trim().toUpperCase());
	    		MenuItem item = new MenuItem(data[0].trim(), price, category);
	    		//add to the menu
	            menu.add(item);
	            //read next line
	            inputLine = buff.readLine();
	            
	        }
        }
        catch(FileNotFoundException e) {
        	System.out.println(e.getMessage());
            System.exit(1);
        }
        catch (IOException e) {
        	e.printStackTrace();
            System.exit(1);        	
        }
        catch (NumberFormatException nfe) {
        	System.out.println("Price item not a number: " + data[1]+".");
        	System.out.println("Program stopped.");
        	System.exit(1);
        }
        catch (IllegalArgumentException iae) {
        	System.out.println ("Category does not exist: "+data[2]+".");
        	System.out.println("Program stopped.");
        	System.exit(1);
        }
        catch (ImpossiblePriceException ipe) {
        	System.out.println (ipe.getMessage());
        	System.exit(-1);
        }
        catch(NoGoodStructureDocumentException ngsde) {
        	System.out.println (ngsde.getMessage());
        	System.exit(-1);
        }
        finally  {
        	try{
        		buff.close();
        	}
        	catch (IOException ioe) {
        		//don't do anythingnu
        	}
        }
	}
	
    /**
     * Imports the orders.
     * @param filename The name of the file containing the orders.
     */
	public void importOrders(final String filename){
        BufferedReader buff = null;
    	String data [] = new String[3];
        try {
        	buff = new BufferedReader(new FileReader(filename));
	    	String inputLine = buff.readLine();  //read first line
	    	while(inputLine != null){  
	    		//split line into parts
	    		data  = inputLine.split(";");
	    		// check if all part are filled
	    		if (data.length < 3) 
	    			throw new NoGoodStructureDocumentException(filename);
	    		//create Order object
	    		int tableID = Integer.parseInt(data[0].trim());
	    		MenuItem item = menu.foundByName(data[1].trim());
	    		int quantity = Integer.parseInt(data[2].trim());
	    		Order  order = new Order(item, quantity);
	    		
	    		//add to the orders map
	    		if (orders.containsKey(tableID)) {
	    			List<Order> l = orders.get(tableID);
	    			l.add(order);
	    		} else {
	    			List<Order> l = new LinkedList<Order>();
	    			l.add(order);
	    			orders.put(tableID,l);
	    		}
	    			
	            //read next line
	            inputLine = buff.readLine();
	            
	        }
        }
        catch(FileNotFoundException e) {
        	System.out.println(e.getMessage());
            System.exit(1);
        }
        catch (IOException e) {
        	e.printStackTrace();
            System.exit(1);        	
        }
        catch (NumberFormatException nfe) {
        	System.out.println("Table id ("+data[0]+") or quantity ("+data[2]+") not a number.");
        	System.out.println("Program stopped.");
        	System.exit(1);
        }
        catch (NoMatchingDishException nmde) {
        	System.out.println (nmde.getMessage());
        	System.exit(-1);
        }
        catch(NoGoodStructureDocumentException ngsde) {
        	System.out.println (ngsde.getMessage());
        	System.exit(-1);
        }
        finally  {
        	try{
        		buff.close();
        	}
        	catch (IOException ioe) {
        		//don't do anything
        	}
        }
	}
	
	public void export(String filename){
		
	}
	
	public String getTableSummary(int tableId){
		return null;
		
	}
	
	public String getFrequencyReport(){
		return null;
		
	}
}
