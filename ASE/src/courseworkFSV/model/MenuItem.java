package courseworkFSV.model;

import courseworkFSV.exception.ImpossiblePriceException;

/**
 * @author Noel Stephanie, Vasilis Tsifountidis, Florent Gonin
 * a simple class to contain and manage a menu item
 */
public class MenuItem implements Comparable<MenuItem>{
	
	/** The name of the menu item. */
	private String name;
	/** The price of the menu item. */
	private double price;
	/** The category of the menu item ie Starter, Main, Dessert or Drink. */
	private Category category;
	
    /**
     * Set up the menu item details.
     * @param name The name of the menu item.
     * @param price The price of the menu item.
     * @param category The category of the menu item ie Starter, Main, Dessert or Drink.
     * @throws ImpossiblePriceException 
     */
	public MenuItem(final String name, final double price, final Category category) throws ImpossiblePriceException{
		this.name = name;
		this.category = category;
		if (price < 0)
			throw new ImpossiblePriceException(price);
		this.price = price;
		
	}
	
    /**
     * @return The name of the item.
     */   
	public String getName() {
		return name;
	}

    /**
     * @return The price of the item.
     */   
	public double getPrice() {
		return price;
	}

    /**
     * @return The category of the item.
     */   
	public Category getCategory() {
		return category;
	}

    /**
     * Test for content equality between two menu item.
     * @param o The item to compare to this one.
     * @return true if the argument item has same content of this one.
     */
	public boolean equals(final Object o){
		if (o == null || (!(o instanceof MenuItem)))
			return false;
		MenuItem menuItem = (MenuItem)o;
		return this.name.equals(menuItem.getName()) && this.price == menuItem.getPrice() && this.category == menuItem.getCategory();
	}
	
    /**
     * @return The hash code of the item.
     */
	public int hashCode(){
		Double p = new Double (price);
		return name.hashCode() + p.hashCode() + category.hashCode();
		 
	}
	
    /**
     * Compare this MenuItem object against another, for the purpose
     * of sorting. The fields are compared by alphabetical order of the name.
     * @param mi The other item to be compared against.
     * @return a negative integer if this name comes before the parameter's name,
     *         zero if they are equal and a positive integer if this
     *         comes after the other.
     */
	@Override
	public int compareTo(final MenuItem mi) {
		return this.getName().compareTo(mi.getName());
	}
	
    /**
     * @return A string containing the name in uppercase and the price of the item.
     */
	@Override
	public String toString(){
		return this.getName().toUpperCase() + "\t" + String.valueOf(this.getPrice());	
	}

}
