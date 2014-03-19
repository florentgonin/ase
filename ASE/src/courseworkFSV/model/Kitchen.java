package courseworkFSV.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import courseworkFSV.view.Observer;

public class Kitchen extends ArrayList<Order> implements Observable {
	/** Observers of the tables */
	private Set<Observer> observers;
	/** state of the kitchen */
	private boolean finished =false;
	/** 
	 * Set up the structure :  LinkedList<Order> 
	 */
	public Kitchen() {
		super();
		observers = new HashSet<Observer>();
	}
	
	/**
	 * Notifies that all the orders has been sent to the kitchen
	 * @return true if all the orders has been sent to the kitchen.
	 */
	public void setFinished () {
		finished = true;
	}
	
	/**
	 * Tests if all the orders has been sent to the kitchen.
	 * @return true if all the orders has been sent to the kitchen.
	 */
	public boolean getFinished() {
		return finished;
	}

	/**
	 * Get an order of the kitchen.
	 */
	@Override
	public synchronized Order get(int index) {
		return super.get(index);
	}

	/**
	 * Adds an order of the kitchen and notify the observers.
	 */
	@Override
	public synchronized boolean add(Order o) {
		System.out.println(o.getOrderId() +" moved to kitchen");
		notifyObservers();
		return super.add(o);
	}
	
	/**
	 * Removes an order of the kitchen and notify the observers.
	 */
	@Override
	public synchronized Order remove(int index) {
		Order o = this.get(index);
		notifyObservers();
		return super.remove(index);
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
	public void addObserver(Observer o) {
		observers.add(o);
		
	}

	/**
	 * Detaches an observer
	 * @param o Observer to detach.
	 */
	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);
		
	}
	
	
}
