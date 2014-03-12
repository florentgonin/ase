package courseworkFSV.test;

import static org.junit.Assert.*;

import org.junit.Test;

import courseworkFSV.exception.ImpossiblePriceException;
import courseworkFSV.exception.ImpossibleQuantityException;
import courseworkFSV.model.Category;
import courseworkFSV.model.MenuItem;
import courseworkFSV.model.Order;

public class OrderTest {

	/*
	 * tests if when an order is created, its id is equals to the last id more one.
	 */
	@Test
	public void testID() {
		try {
			Order o = new Order (null,2);
			int id1 = o.getOrderId();
			Order o1 = new Order(null,2);
			int id2 = o1.getOrderId();
			assertTrue ((id1 + 1) == id2);
		} catch (ImpossibleQuantityException e) {
			e.printStackTrace();
		}

	}
	
	/*
	 * tests the computation of the total of an order
	 */
	@Test
	public void testTotal() {
		MenuItem item = null;
		try {
			item = new MenuItem("Item",3.00,Category.STARTER);
			Order o = new Order(item,3);
			assertTrue (o.totalCost() == 3*3.00);
		} catch (ImpossiblePriceException e) {
			//Do nothing
		} catch (ImpossibleQuantityException e) {
			//Do nothing
		}

	}
	
	/*
	 * tests that an exception is thrown when a negatif integer is supplied for the quantity
	 */
	@Test
	public  void invalidQuantity() {
		try {
			MenuItem item = new MenuItem("A",3.00,Category.STARTER);
			Order o = new Order(item,-3);
			fail("Negatif price supplied - should throw exception");
		}
		catch (ImpossibleQuantityException e) {
			assertTrue(e.getMessage().contains(String.valueOf(-3)));
		}
		catch (ImpossiblePriceException e) {
			//Do nothing
		}
	}

}
