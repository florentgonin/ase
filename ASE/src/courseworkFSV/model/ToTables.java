package courseworkFSV.model;



public class ToTables implements Runnable {

private Kitchen kitchen;
private Tables tables;
	
	public ToTables(final Kitchen kitchen, final Tables tables) {
		this.kitchen=kitchen;
		this.tables=tables;
	}


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
				tables.addAnOrder(currentOrder.getTableId(), currentOrder);
				kitchen.remove(0);
			}
		}
	}
}
