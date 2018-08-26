package clientME;

public class Liquid {

	private String name;
	private double quantity;
	private int pump;
	 
	
	//Initialization
	public Liquid(String name, int quantity){
		this.name = name;
		this.quantity = quantity;
	}
	
	public Liquid() {
		name = "empty";
		pump = 0;
		quantity = 0;
	}

	
	//Getters & Setters
	final public String getName(){
		return name;
		}
	public void setName(String name){
		this.name = name;
	}
	final public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	final public int getPump() {
		return pump;
	}
	public void setPump(int pump) {
		this.pump = pump;
	}
}
