package testUnitaireClientME;

import static org.junit.Assert.*;
import org.junit.Test;
import clientME.Liquid;


public class TestLiquid {

	Liquid liquid = new Liquid("Liquide 1", 40);
	Liquid liquid2 = new Liquid();
	
	@Test
	public void testNewLiquid() {
		assertTrue("Error: liquid name is different...", liquid.getName().equals("Liquide 1"));
		assertTrue("Error: quantity is different...", liquid.getQuantity() == 40);
		assertFalse("Error: quantity is different...", liquid.getQuantity() == 0);
		assertFalse("Error: liquid name is different...", liquid.getName().equals(null));
	}

	
	@Test
	public void testAddPump(){
		liquid.setPump(0);
		assertTrue("Error: pump number is different...", liquid.getPump() == 0);
	}
	
	@Test
	public void testNewLiquidEmpty(){
		assertTrue("Error: liquid name is not null...", liquid2.getName().equals("empty"));
		assertTrue("Error: quantity is not null...", liquid2.getQuantity() == 0);
		
		liquid2.setName("Liquide 1");
		liquid2.setPump(0);
		liquid2.setQuantity(40);
		
		assertTrue("Error: liquid name is different...", liquid2.getName().equals("Liquide 1"));
		assertTrue("Error: quantity is different...", liquid2.getQuantity() == 40);
		assertTrue("Error: pump number is different...", liquid.getPump() == 0);
	}
}
