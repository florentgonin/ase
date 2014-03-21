package courseworkFSV;

import courseworkFSV.controller.RestaurantController;
import courseworkFSV.model.Restaurant;
import courseworkFSV.view.RestaurantGUI;
import courseworkFSV.view.RestaurantInterface;

public class RestaurantDemo {


	public static void main(String[] args){
		Restaurant restaurant = Restaurant.getInstance("menu.txt", "orders.txt");	

		//test threads
		restaurant.start();

		//RestaurantInterface c = new RestaurantInterface(restaurant);
		//test threads
		RestaurantGUI restaurantGUI = new RestaurantGUI(restaurant);
		
		RestaurantController controller = new RestaurantController(restaurantGUI, restaurant);
		
		controller.start();

		//export log
		while(!restaurant.getToTables().getState().equals(Thread.State.TERMINATED)){}

		restaurant.exportLog("RestoLog.txt");
	}
}
