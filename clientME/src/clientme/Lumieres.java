package clientME;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalMultipurpose;
import com.pi4j.io.gpio.PinMode;
import com.pi4j.io.gpio.RaspiPin;

public class Lumieres {
	private GpioPinDigitalMultipurpose Rouge;
	private GpioPinDigitalMultipurpose Vert;
	GpioController controller;
	
	//test variables
	protected boolean instantialized = false;
	protected boolean rougeWorking = false;
	protected boolean vertWorking = false;
	protected boolean closed = false;
	
	public Lumieres(GpioController ctrlr){ 

		controller = ctrlr;
		// LED Initialization
		Rouge = ctrlr.provisionDigitalMultipurposePin(RaspiPin.GPIO_06, PinMode.DIGITAL_INPUT); // led rouge
		Vert = ctrlr.provisionDigitalMultipurposePin(RaspiPin.GPIO_07, PinMode.DIGITAL_INPUT); // led vert
		instantialized=true;

	}
	
	public void Rouge(){
		Rouge.setMode(PinMode.DIGITAL_OUTPUT); // Led rouge = on
		Vert.setMode(PinMode.DIGITAL_INPUT); // Led vert = off
		rougeWorking = true;
	} 
	
	public void Vert(){
		Rouge.setMode(PinMode.DIGITAL_INPUT); // Led rouge = off
		Vert.setMode(PinMode.DIGITAL_OUTPUT); // Led vert = on		
		vertWorking = true;
	}
	
	public void closeAll(){
		Rouge.setMode(PinMode.DIGITAL_INPUT); // Led rouge = off
		Vert.setMode(PinMode.DIGITAL_INPUT); // Led vert = off
		closed = true;
	}
	
	public boolean isInstantialized(){
		return instantialized;
	}
	public boolean isRougeWorking(){
		return rougeWorking;
	}
	
	public boolean isVertWorking(){
		return vertWorking;
	}
	public boolean isClosed(){
		return closed;
	}

}
