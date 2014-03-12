package courseworkFSV.test;

import static org.junit.Assert.*;

import org.junit.Test;

import courseworkFSV.exception.ImpossiblePriceException;
import courseworkFSV.model.Category;
import courseworkFSV.model.MenuItem;

public class MenuItemTest {

	/*
	 * tests that two menu items are equals. 
	 * Test all components diff, 2 same, all same
	 */
	@Test
	public void testEquals() {
		try {
			MenuItem item1 = new MenuItem("Item",3.00,Category.STARTER);
			MenuItem item2 = new MenuItem("Item",3.00,Category.STARTER);
			assertTrue(item1.equals(item2));
			
			MenuItem item3 = new MenuItem("Item3",3.00,Category.STARTER);
			assertFalse(item1.equals(item3));
			
			MenuItem item4 = new MenuItem("Item",4.00,Category.STARTER);
			assertFalse(item1.equals(item4));
			
			MenuItem item5 = new MenuItem("Item",3.00,Category.MAIN);
			assertFalse(item1.equals(item5));
		} catch (ImpossiblePriceException e) {
			//Do nothing
		}
	}
	
	/*
	 * tests that two menu items are sort by alphabetical order. 
	 * Test all components before, 2 same, after
	 */
	@Test
	public void testCompareTo() {
		try {
			MenuItem item1 = new MenuItem("A",3.00,Category.STARTER);
			MenuItem item2 = new MenuItem("B",5.00,Category.MAIN);
			MenuItem item3 = new MenuItem("B",3.00,Category.STARTER);
			MenuItem item4 = new MenuItem("C",3.00,Category.STARTER);
			 assertEquals("should be before", -1, item1.compareTo(item2));
			 assertEquals("should be after", 1, item4.compareTo(item3));
			 assertEquals("should be the same", 0, item2.compareTo(item3));
		} catch (ImpossiblePriceException e) {
			//Do nothing
		}
	}
	
	/*
	 * tests that an exception is thrown when a negatif integer is supplied for the price 
	 */
	@Test
	public  void invalidPrice() {
		try {
			MenuItem item1 = new MenuItem("A",-3.00,Category.STARTER);
			fail("Negatif price supplied - should throw exception");
		}
		catch (ImpossiblePriceException e) {
			assertTrue(e.getMessage().contains(String.valueOf(-3.00)));
		}
	}
}
