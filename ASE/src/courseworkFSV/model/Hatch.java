package courseworkFSV.model;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import courseworkFSV.view.Observer;

public class Hatch extends LinkedList<Order> implements Observable{
		/** Observers of the hatch */
		private Set<Observer> observers;
		/** state of the hatch */
		private boolean finished =false;
		/**
		 * Set up the structure : LinkedList<Order>
		 */
		public Hatch() {
			super();
			observers = new HashSet<Observer>();
		}
		
		/**
		 * Notifies that all the orders has been sent to the hatch.
		 * @return true if all the orders has been sent to the hatch.
		 */
		public void setFinished () {
			finished = true;
		}
		
		/**
		 * Tests if all the orders has been sent to the hatch.
		 * @return true if all the orders has been sent to the hatch.
		 */
		public boolean getFinished() {
			return finished;
		}
		
		/**
		 * Get an order of the hatch.
		 */
		@Override
		public synchronized Order get(int index) {
			return super.get(index);
		}

		/**
		 * Adds an order of the hatch and notify the observers.
		 */
		@Override
		public synchronized boolean add(Order o) {
			System.out.println(o.getOrderId() +" moved to hatch");
			notifyObservers();
			return super.add(o);
		}
		
		/**
		 * Removes an order of the hatch and notify the observers.
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
