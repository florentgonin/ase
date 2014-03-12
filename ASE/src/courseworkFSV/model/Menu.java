package courseworkFSV.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import courseworkFSV.exception.NoMatchingDishException;

/**
 * @author Noel Stephanie, Vasilis Tsifountidis, Florent Gonin
 * a simple class to contain and manage the menu
 */
public class Menu extends HashSet<MenuItem> {
	
	public Menu(){
		super();
	}
	
    /**
     * Searching for an menu item according to its name. 
     * @param name The name of the the menu item sought.
     * @throw NoMatchingDishException If no item corresponds to the name.
     */
	public MenuItem foundByName(final String name) throws NoMatchingDishException{
		Iterator<MenuItem> it = this.iterator();
		MenuItem item = null;
		boolean found = false;
		while (!found && it.hasNext()) {
			item = it.next();
			if (item.getName().toLowerCase().equals(name.toLowerCase()))
				found = true;
		}
		if (!found) 
			throw new NoMatchingDishException(name);
		
		return item;
	}
	
    /**
     * @return A string containing the menu items sort by their category and by alphabetical order.
     */
	public String toString(){
		Set<MenuItem> starters = new TreeSet<MenuItem>();
		Set<MenuItem> mains = new TreeSet<MenuItem>();
		Set<MenuItem> desserts = new TreeSet<MenuItem>();
		Set<MenuItem> drinks = new TreeSet<MenuItem>();
		String newLine = System.getProperty("line.separator");
		String s = "";
		
		/* Sorting menu items by their category */
		for (MenuItem item : this) {
			switch (item.getCategory()) {
				case STARTER :
					starters.add(item);
				break;
				case MAIN :
					mains.add(item);
				break;
				case DESSERT :
					desserts.add(item);
				break;
				case DRINK :
					drinks.add(item);
				break;
			}
		}
		
		/* display menu item according to their category */
		s += "STARTER";
		s += newLine;
		for (MenuItem item : starters) {
			s += item.toString();
			s += newLine;
		}
		s += newLine;
		s += "MAIN";
		s += newLine;
		for (MenuItem item : mains) {
			s += item.toString();
			s += newLine;
		}
		s += newLine;
		s += "DESSERT";
		s += newLine;
		for (MenuItem item : desserts) {
			s += item.toString();
			s += newLine;
		}
		s += newLine;
		s += "DRINK";
		s += newLine;
		for (MenuItem item : drinks) {
			s += item.toString();
			s += newLine;
		}
		return s;
	}
}
