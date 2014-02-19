package courseworkFSV;

public class RestaurantDemo {

	
	public static void main(String[] args){
		Restaurant restaurant = new Restaurant("menu.txt","orders.txt");

		//export test
		restaurant.export("testExport.txt");

		System.out.println("Finish.");
		
		RestaurantInterface c = new RestaurantInterface(restaurant);
		c.run();
	}
}
