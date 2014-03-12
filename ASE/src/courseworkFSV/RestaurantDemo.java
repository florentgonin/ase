package courseworkFSV;

import courseworkFSV.model.Restaurant;
import courseworkFSV.view.RestaurantInterface;

public class RestaurantDemo {

	
	public static void main(String[] args){
		Restaurant restaurant = Restaurant.getInstance("menu.txt", "orders.txt");	
		
		//test threads
		restaurant.start();

		//System.out.println("Finish.");
		
		//RestaurantInterface c = new RestaurantInterface(restaurant);
		//c.run();
	}
}
