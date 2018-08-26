//Class used to obtain simultaneous pouring using threads

package clientME;

public class PourController implements Runnable{
	
	double quantityInOunces;
	Pump pump;
	private int id;
	 
	
	//Initialization
	public PourController(double quantityInOunces, Pump pump, int id){
		this.quantityInOunces = quantityInOunces;
		this.pump = pump;
		this.id = id;
	}	
	
	//Usage
	public void run() {	
		try {
			pump.activateFor(quantityInOunces, id);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
