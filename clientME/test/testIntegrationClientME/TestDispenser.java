package testIntegrationClientME;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import clientME.Dispenser;

public class TestDispenser {
	
	@Test
	public void testNewDispenser() throws IOException {
		final GpioController controller = GpioFactory.getInstance(); 
		Dispenser dispenser = new Dispenser(controller);  
		
		assertFalse("Error: Dipsenser instantiation didn't work, null received...", dispenser.equals(null));
		assertFalse("Error: Controller object is invalid...", dispenser.getController().equals(controller));
	} 

}
