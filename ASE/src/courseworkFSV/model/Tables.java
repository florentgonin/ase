package courseworkFSV.model;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import courseworkFSV.view.Observer;

public class Tables extends HashMap<Integer, List<Order>> implements Observable{
	/** Observers of the tables */
	private Set<Observer> observers;
	
	/**
	 * Set up the structure : HashMap<Integer, List<Order>>
	 */
	public Tables() {
		super();
		observers = new HashSet<Observer>();
	}
	
	/**
	 * Add an order to a table and notify the observers.
	 * @param tableID Id of the table to add the order.
	 * @param order Order to add.
	 */
	public void addAnOrder(final int tableID, final Order order) {
		if (this.containsKey(tableID)) {
			List<Order> l = this.get(tableID);
			l.add(order);
		} else {
			List<Order> l = new LinkedList<Order>();
			l.add(order);
			this.put(tableID,l);
		}
		System.out.println(order.getOrderId() +" moved to Tables");
		//update view display
		notifyObservers();
	}
	
	/**
	 * Notify the observers that something change.
	 */
	@Override
	public void notifyObservers() {
		for (Observer o : observers) {
			o.update();
		}
		
	}

	/**
	 * Attaches an observer
	 * @param o Observer to attach.
	 */
	@Override
	public void addObserver(final Observer o) {
		observers.add(o);
		
	}

	/**
	 * Detaches an observer
	 * @param o Observer to detach.
	 */
	@Override
	public void removeObserver(final Observer o) {
		observers.remove(o);
		
	}
}
