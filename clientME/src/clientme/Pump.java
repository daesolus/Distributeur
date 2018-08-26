package clientME;

import com.pi4j.io.gpio.*;

public class Pump {

	private GpioPinDigitalMultipurpose IOpin;

	// Initialization
	public Pump(GpioPinDigitalMultipurpose IOpin) {
		this.IOpin = IOpin;
	};

	// Usage
	public void activateFor(double quantityInOunces, int pumpid) throws InterruptedException {
		
		int pourTime = ouncesToTime(quantityInOunces);
		
		if (pumpid == 3 || pumpid == 4){
			pourTime += 150;			
		}
		//System.out.println("Start pouring " + quantityInOunces + " ounces with pin " + IOpin.getName());

		IOpin.setMode(PinMode.DIGITAL_OUTPUT);
		Thread.sleep(pourTime);
		IOpin.setMode(PinMode.DIGITAL_INPUT);

		//System.out.println("Stop pouring with pin " + IOpin.getName());
	}

	public int ouncesToTime(double quantityInOunces) {
		return (int) (quantityInOunces * 833);
	}
	
	public void stop(){
		IOpin.setMode(PinMode.DIGITAL_INPUT);
	}

	// Getters
	public GpioPinDigitalMultipurpose getPin() {
		return IOpin;
	}
}
