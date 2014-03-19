package courseworkFSV.model;

import java.util.List;

public class ToHatch implements Runnable {
	/** Hatch to send the orders. */
	private Hatch hatch;
	/** kitchen to take the orders */
	private Kitchen kitchen;
	
	/**
	 * Set up attributes
	 * @param kitchen kitchen to take the orders.
	 * @param hatch hatch to put the orders.
	 */
	public ToHatch (final Kitchen kitchen, final Hatch hatch) {
		this.kitchen = kitchen;
		this.hatch = hatch;
	}

	
	/**
	 * Sends orders to hatch.
	 */
	public void run() {
		while(!kitchen.getFinished()){
			if (!kitchen.isEmpty()) {
				Order currentOrder = kitchen.get(0);
				
				int sec = 1 + (int)(Math.random()*5); 
				try {
					Thread.sleep(sec*1000);
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				}
				hatch.add(currentOrder);
				kitchen.remove(0);
			}
		}
		hatch.setFinished();
	}
}
