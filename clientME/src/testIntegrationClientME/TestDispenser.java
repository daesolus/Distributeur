package testIntegrationClientME;

import static org.junit.Assert.*;
import org.junit.Test;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import clientME.Dispenser;

public class TestDispenser {
	
	@Test
	public void testNewDispenser() {
		final GpioController controller = GpioFactory.getInstance(); 
		int port = 5555;
		String address = "169.254.191.213";
		//Dispenser dispenser = new Dispenser(controller, port, address);  
		
		//assertTrue("Error: port is different...", port == dispenser.getReceiver().getPort());
		//assertTrue("Error: address is different...", address.equals(dispenser.getReceiver().getAddress()));
	} 
	
	@Test //Problem: Instantiate the class a second time which causes "This GPIO pin already exists"
	public void testStart(){
		final GpioController controller = GpioFactory.getInstance(); 
		int port = 5555;
		String address = "169.254.191.213";
		//Dispenser dispenser = new Dispenser(controller, port, address);  
		
		assertTrue("lol", 8==9);
	}
	
	

}
