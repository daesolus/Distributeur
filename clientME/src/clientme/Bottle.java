package clientME;

import com.pi4j.io.gpio.*;

public class Bottle {
	
	private Pump pump;
	private int id;
	
	//Initialization
	public Bottle(GpioPinDigitalMultipurpose IOpin, int id){
		this.pump = new Pump(IOpin);
		this.id = id;
	}
	
	public Bottle(){
	}
	
	public void stop(){
		pump.stop();
	}
	
	//Usage
	public void pour(double quantityInOunces) throws InterruptedException
	{
		Thread t1 = new Thread(new PourController(quantityInOunces, pump, id));
		t1.start();
		
		//tempo 
		//t1.join();
	}
	
	
	//Getters
	final public Pump getPump(){
		return this.pump;
	}
}
