package clientME;

import java.io.*;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;

public class Main 
{
	public static void main(String[] Args) throws IOException , InterruptedException
	{	
		final GpioController controller = GpioFactory.getInstance();
		Dispenser dispenser = new Dispenser(controller); 
	}
}

