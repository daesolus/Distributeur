package testUnitaireClientME;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import clientME.Liquid;
import clientME.Order;

public class TestOrder {

	Order order;
	ArrayList<Liquid> liquidList;
	
	@Test
	public void testNewOrder() throws InterruptedException {
		order = new Order("{\"recette\":[{\"boisson\":\"Clamato\",\"qte\":\"5\",\"pompe\":\"2\"},{\"boisson\":\"Jus d'orange\",\"qte\":\"10\",\"pompe\":\"0\"}]}");
		liquidList = new ArrayList<Liquid>();
		
		Liquid liquid = new Liquid("Clamato", 5);
		liquid.setPump(2);
		Liquid liquid2 = new Liquid("Jus d'orange", 10);
		liquid2.setPump(0);
		
		liquidList.add(liquid);
		liquidList.add(liquid2);
		
		
		assertTrue("Error: liquidList's first element is different...", liquidList.get(0).getName().equals(order.getLiquidList().get(0).getName()));
		assertTrue("Error: liquidList's second element is different...", liquidList.get(1).getName().equals(order.getLiquidList().get(1).getName()));
		
		assertTrue("Error: liquidList's first element is different...", liquidList.get(0).getPump() == order.getLiquidList().get(0).getPump());
		assertTrue("Error: liquidList's second element is different...", liquidList.get(1).getPump() == order.getLiquidList().get(1).getPump());
		
		assertTrue("Error: liquidList's first element is different...", liquidList.get(0).getQuantity() == order.getLiquidList().get(0).getQuantity());
		assertTrue("Error: liquidList's second element is different...", liquidList.get(1).getQuantity() == order.getLiquidList().get(1).getQuantity());
	}

	public void testStopOrder() throws InterruptedException{
		order = new Order("{\"recette\":[{\"boisson\":\"Clamato\",\"qte\":\"5\",\"pompe\":\"32\"},{\"boisson\":\"Jus d'orange\",\"qte\":\"10\",\"pompe\":\"0\"}]}");
		//Should stop application
	}


}
