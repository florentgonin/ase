package courseworkFSV.controller;

import courseworkFSV.model.Order;
import courseworkFSV.model.Restaurant;
import courseworkFSV.view.RestaurantGUI;

public class RestaurantController {
	//controller GUI
	private RestaurantGUI restaurantGUI;
	
	//Restaurant class object required to access orders Map in that class 
	private Restaurant restaurant;

	//constructor
	public RestaurantController(RestaurantGUI restaurantGUI,
			Restaurant restaurant) {
		this.restaurantGUI = restaurantGUI;
		this.restaurant = restaurant;
		this.restaurant.setController(this);
	}
	
	public void start(){
		//test threads
		restaurant.start();
		
		//making frames (kitchen and tables) visible
		restaurantGUI.setVisible(true);	
	}

	//add order to kitchen
	public void addOrderToKitchen(Order o) {
		restaurant.getKitchen().add(o);		
	}
	
	//add order to table
	public void addOrderToTable(int id, Order o) {
		restaurant.getTables().addAnOrder(id, o);
	}
	
	
}
