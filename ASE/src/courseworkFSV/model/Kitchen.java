package courseworkFSV.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import courseworkFSV.view.Observer;

public class Kitchen extends ArrayList<Order> implements Observable {
	/** Observers of the tables */
	private Set<Observer> observers;
	private boolean finished =false;
	/** 
	 * Set up the structure :  LinkedList<Order> 
	 */
	public Kitchen() {
		super();
		observers = new HashSet<Observer>();
	}
	
	public void setFinished () {
		finished = true;
	}
	
	public boolean getFinished() {
		return finished;
	}

	@Override
	public synchronized Order get(int index) {
		return super.get(index);
	}

	@Override
	public synchronized boolean add(Order o) {
		System.out.println(o.getOrderId() +" added");
		notifyObservers();
		return super.add(o);
	}
	
	@Override
	public synchronized Order remove(int index) {
		Order o = this.get(index);
		System.out.println(o.getOrderId() +" removed");
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
