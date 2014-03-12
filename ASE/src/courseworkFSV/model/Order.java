package courseworkFSV.model;

import courseworkFSV.exception.ImpossibleQuantityException;

/**
 * @author Noel Stephanie, Vasilis Tsifountidis, Florent Gonin
 * a simple class to contain and manage an order
 */

public class Order {
	
	/** The id of the order. */
	private int orderId;
	
	/** The id of the table. */
	private int tableId;
	/** The menu item ordered. */
	private MenuItem item;	

	/** The quantity ordered. */
	private int quantity;
	
	/** The last id assigned to a table. */
	private static int maxID = 0;
	
	/** Order discount. */
	private double[] discount = {3,0.1};
	
    /**
     * Set up the order details. The id is assigned according to the last id assigned and this one is updated.
     * @param item The menu item ordered.
     * @param quantity The quantity ordered.
     * @throws ImpossibleQuantityException 
     */
	public Order(final MenuItem item, final int quantity, final int tableId) throws ImpossibleQuantityException{
		this.item = item;
		this.tableId = tableId;
		this.orderId = Order.maxID + 1;
		if (quantity <= 0) 
			throw new ImpossibleQuantityException(quantity);
		this.quantity = quantity;
		maxID = this.orderId;
	}
	
	/** Return the menu item ordered*/
	public MenuItem getItem() {
		return item;
	}

	/** Return the quantity ordered*/
	public int getQuantity() {
		return quantity;
	}
	
	/**
     * @return The id of the order.
     */
    public synchronized int getOrderId() {
		return orderId;
	}
    
	/**
     * @return The id of the table.
     */
    public int getTableId() {
		return tableId;
	}

	/**
     * @return A string containing all details of the order.
     */
	@Override
	public String toString(){
		String s= item.getName().toUpperCase() + "\t" + 
				String.valueOf(quantity) + " * " + 
				String.valueOf(item.getPrice()) + " = " 
				+ String.valueOf(quantity*item.getPrice());
		//add discount if necessary
		if(quantity>=discount[0]){
				s+= " (-"+String.valueOf(getDiscount())+")";	
		}
		return s;
	}
	
    /**
     * @return the total cost of the order with a discount if necessary
     */
	public double totalCost(){
		double totalCost = quantity*item.getPrice();
		//apply discount if necessary
		if(quantity>=discount[0]){
			totalCost-=getDiscount();
		}
		return totalCost;
	}
	
    /**
     * @return discount associated with the order
     */
	public double getDiscount(){
		return (double) Math.round(quantity*item.getPrice()*discount[1]*100)/100;
	}

    /**
     * @return the name of the menu item
     */
	public String getMenuItemName(){
		return item.getName();
	}
	
    /**
     * @return the price of the menu item
     */
	public double getItemPrice(){
		return item.getPrice();
	}
}
