package testIntegrationClientME;

import static org.junit.Assert.*;

import org.junit.Test;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalMultipurpose;
import com.pi4j.io.gpio.PinMode;
import com.pi4j.io.gpio.RaspiPin;

import clientME.Pump;

public class testPump {

	final GpioController gpio = GpioFactory.getInstance();
	private GpioPinDigitalMultipurpose IOpin;
	Pump pump = new Pump(IOpin); 
	
	@Test
	public void testNewPump() {
		IOpin = gpio.provisionDigitalMultipurposePin(RaspiPin.GPIO_00, PinMode.DIGITAL_INPUT);
		assertTrue("Error: IOpin is different...", IOpin.equals(pump.getPin()));
	}

}
