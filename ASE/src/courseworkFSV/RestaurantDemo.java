package courseworkFSV;

import courseworkFSV.model.Restaurant;
import courseworkFSV.view.RestaurantInterface;

public class RestaurantDemo {


	public static void main(String[] args){
		Restaurant restaurant = Restaurant.getInstance("menu.txt", "orders.txt");	

		//test threads
		restaurant.start();

		//RestaurantInterface c = new RestaurantInterface(restaurant);

		//export log
		while(!restaurant.getToTables().getState().equals(Thread.State.TERMINATED)){
		}

		restaurant.exportLog("RestoLog.txt");
	}
}
