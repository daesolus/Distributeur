package testUnitaireClientME;

import static org.junit.Assert.*;

import java.util.LinkedList;
import org.junit.Test;
import clientME.Order;
import clientME.Receiver;

public class TestReceiver {

	Receiver receiver;
	
	@Test
	public void testNewReceiver() throws InterruptedException {
		LinkedList<Order> orderList = new LinkedList<Order>();
		Order order = new Order("{\"recette\":[{\"boisson\":\"Clamato\",\"qte\":\"5\",\"pompe\":\"2\"},{\"boisson\":\"Jus d'orange\",\"qte\":\"10\",\"pompe\":\"0\"}]}");
		Order order2 = new Order("{\"recette\":[{\"boisson\":\"Clamato\",\"qte\":\"5\",\"pompe\":\"2\"},{\"boisson\":\"Jus d'orange\",\"qte\":\"10\",\"pompe\":\"0\"}]}");
		orderList.add(order);
		orderList.add(order2);
		
		//receiver = new Receiver(orderList, null);
		
		assertTrue("Error: port number is different...", receiver.getPort() == 22);
		assertTrue("Error: list is different...", receiver.getOrderList().equals(orderList));
	}

	public void testIsJSONString(){
	}
}
