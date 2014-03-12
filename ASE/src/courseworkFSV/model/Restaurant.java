package courseworkFSV.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import courseworkFSV.view.TestView;
import courseworkFSV.exception.ImpossiblePriceException;
import courseworkFSV.exception.ImpossibleQuantityException;
import courseworkFSV.exception.NoGoodStructureDocumentException;
import courseworkFSV.exception.NoMatchingDishException;

/**
 * @author Noel Stephanie, Vasilis Tsifountidis, Florent Gonin
 * a simple class to contain and manage the restaurant details
 */
public class Restaurant {

	/** The orders of the night sort by table. */
	private Tables tables;
	/** The orders in the kitchen. */
	private Kitchen kitchen;
	/** The menu of the restaurant. */
	private Menu menu;
	/** List of orders to treat */
	private List<Order> orders;
	/**
	 * Set up the restaurant details.
	 * @param menuFile The name of the file containing the menu.
	 * @param ordersFile The name of the file containing the orders.
	 */
	private Restaurant(final String menuFile, final String ordersFile){
		tables = new Tables();
		kitchen = new Kitchen();
		orders = new LinkedList<Order>();
		importMenu(menuFile);
		importOrders(ordersFile);
	}
	
	/** Instance of the Restaurant class. */
	private static boolean instanced= false;
	
	/**
	 * Singleton
	 * @return instance of Restaurant
	 */
	public static Restaurant getInstance(final String menuFile, final String orderFile){
		if (!instanced)
			return new Restaurant (menuFile, orderFile);
		else
			return null;
	}

	/**
	 * @return The orders of the night sort by table.
	 */ 
	public Tables getOrders() {
		return tables;
	}
	
	/**
	 * @return The orders in the kitchen.
	 */ 
	public List<Order> getKitchenOrders() {
		return kitchen;
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
			String inputLine = buff.readLine(); 
			//read first line
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
			System.out.println("Program stopped.");
			System.exit(1);
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
			System.out.println("Program stopped.");
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
			System.out.println("Program stopped.");
			System.exit(-1);
		}
		catch(NoGoodStructureDocumentException ngsde) {
			System.out.println (ngsde.getMessage());
			System.out.println("Program stopped.");
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
				Order  order = new Order(item, quantity, tableID);

				//add to the list of orders to treat
				orders.add(order);

				//read next line
				inputLine = buff.readLine();

			}
		}
		catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.out.println("Program stopped.");
			System.exit(1);
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
			System.out.println("Program stopped.");
			System.exit(1);        	
		}
		catch (NumberFormatException nfe) {
			System.out.println("Table id ("+data[0]+") or quantity ("+data[2]+") not a number.");
			System.out.println("Program stopped.");
			System.exit(1);
		}
		catch (NoMatchingDishException nmde) {
			System.out.println (nmde.getMessage());
			System.out.println("Program stopped.");
			System.exit(-1);
		}
		catch (ImpossibleQuantityException iqe) {
			System.out.println (iqe.getMessage());
			System.out.println("Program stopped.");
			System.exit(-1);
		}
		catch(NoGoodStructureDocumentException ngsde) {
			System.out.println (ngsde.getMessage());
			System.out.println("Program stopped.");
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
	
	

	/**
	 * Generate a report and export it as a text file
	 * @param filename, string name of the exported report
	 */
	public void export(final String filename){
		//important "Report:\n\n" is detected by writeReport to delete the last report
		//do not remove
		writeReport(filename,"Report:\n\n" );
		//write menu
		writeReport(filename, "Menu:\n\n" + menu.toString());
		System.out.println("Menu:\n\n" + menu.toString());
		System.out.println("Table Summary:\n");
		
		//for each table get summary
		String report = "\nTable Summaries:\n\n";
		for(Integer key : tables.keySet()){

			report+=getTableSummary(key)+"\n";
		}
		
		//write table summaries
		writeReport(filename, report);
		//write frequencies
		writeReport(filename, getFrequencyReport());
		
		//finish report
		writeReport(filename, "\nFinish");
	}

	/**
	 * Generate a summary of orders from a particular table
	 * @param tableId Unique Integer corresponding to a table.
	 */
	public String getTableSummary(final int tableId){
		String summary = "";
		double total = 0;
		//check if the id is correct
		if(tables.containsKey(tableId)){

			List<Order> currentTable = tables.get(tableId);
			//check if the list of orders is not empty
			if(!currentTable.isEmpty()){

				System.out.println("TableId: "+tableId);
				summary += "Table "+ tableId + " summary:\n"; 
				//for each order of the current table, get info
				for(Order tableOrder : currentTable){

					System.out.println(tableOrder.toString());
					summary += tableOrder.toString() + "\n";
					total += tableOrder.totalCost();
				}
				//total cost
		        DecimalFormat format = new DecimalFormat("#.00");
				System.out.println("Total for this table: " + format.format(total)+"\n");
				summary += "Total for this table: " + format.format(total) +"\nDiscount(s) included\n";

			}
		}
		return summary;

	}

	/**
	 * Generate a report with frequencies of each menu items
	 */
	public String getFrequencyReport(){
		
		String frequencyReport = "Frequency Report:\n\n";
		System.out.println(frequencyReport);
		//Initialize a map and set each menu item with a null frequency 
		Map<MenuItem, Integer> frequency =  new HashMap<MenuItem,Integer>();
		Iterator<MenuItem> it = menu.iterator();
		while(it.hasNext()){
			frequency.put(it.next(), 0);
		}
		
		//loop over orders 
		for (Entry<Integer, List<Order>> entry : tables.entrySet())
		{
		    //System.out.println(entry.getKey() + "/" + entry.getValue());
		    //loop over List<Order>
		    Iterator<Order> itOrder = entry.getValue().iterator();
		    while(itOrder.hasNext()){
		    	//get current item and quantity corresponding to the current order
		    	Order currentOrder = itOrder.next();
		    	MenuItem currentItem = currentOrder.getItem();
		    	int currentQuantity = currentOrder.getQuantity();
		    	//add quantity to frequency map
		    	frequency.put(currentItem, frequency.get(currentItem)+currentQuantity);
		    }
		    

		}
		
	    //print frequency details from frequency map
	    for(Map.Entry<MenuItem, Integer> entry1 : frequency.entrySet()){
	    	System.out.println(entry1.getKey().getName() +": "+ entry1.getValue());
	    	//store frequencies
	    	frequencyReport += entry1.getKey().getName() +": "+ entry1.getValue()+"\n";
	    }

		return frequencyReport;

	}

	/**
	 * Write a text file with given filename and string content
	 * @param filename, string name of the text file
	 * @param s, string content of the text file
	 */
	public void writeReport(final String fileName, final String s) {
		
		try{
 
    		File file = new File("testExport.txt");
    		
    		//if a new report is detected (string "Report:\n\n" is detected 
    		//then the last report is deleted
    		if(s.equals("Report:\n\n")){
    			file.delete();
    		}
    		
    		//if file doesnt exists, then create it
    		if(!file.exists()){
    			file.createNewFile();
    		}
    		
    		//true = append file
    		FileWriter fileWritter = new FileWriter(file.getName(),true);
    	        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
    	        bufferWritter.write(s);
    	        bufferWritter.close();
 
	        System.out.println("Done");
 
    	}catch(IOException e){
    		e.printStackTrace();
    	}

	}
	
	public void start () {
		System.out.println("Running ");
		
		Thread thread4 = new Thread(new ToTables(kitchen,tables));
		thread4.start();
		
		Thread thread3 = new Thread(new ToKitchen(kitchen,orders));
		thread3.start();
		TestView t = new TestView(kitchen);
		t.run();
	}
	
}
