package courseworkFSV;

public class RestaurantDemo {

	
	public static void main(String[] args){
		Restaurant restaurant = new Restaurant("menu.txt","orders.txt");

		//export test
		//restaurant.export("testExport.txt"); is done by the Do the report summary button on the first JFrame of interface

		System.out.println("Finish.");
		
		RestaurantInterface c = new RestaurantInterface(restaurant);
		c.run();
	}
}
