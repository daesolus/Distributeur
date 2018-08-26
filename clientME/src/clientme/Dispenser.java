package clientME;

import java.util.ArrayList;
import java.util.LinkedList;
import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

import java.net.Socket;
import java.io.IOException;
import java.net.InetAddress;

public class Dispenser {

	private GpioController controller;
	private GpioPinDigitalMultipurpose button;

	private ArrayList<Bottle> bottleList;
	private LinkedList<Order> orderList;

	private SocketServeur serveur;
	boolean available;
	private Lumieres led;

	// Initialization
	public Dispenser(GpioController controller) throws IOException {

		this.orderList = new LinkedList<Order>();
		this.controller = controller;
		available = true;

		initializeDispenser();
		updateLed();
		serveur = new SocketServeur(orderList, 2009, led);
	}

	private void initializeDispenser() {

		bottleList = new ArrayList<Bottle>();

		// Button Initialization
		button = controller.provisionDigitalMultipurposePin(RaspiPin.GPIO_05, PinMode.DIGITAL_INPUT);
		button.addListener(new GpioPinListenerDigital() {

			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				if (button.getState() == PinState.HIGH)
					try {
						buttonPressed();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		});

		// LED Initialization
		led = new Lumieres(controller);

		initializeBottles();
	}

	private void initializeBottles() {
		System.out.println("Bottle Initialization");

		ArrayList<GpioPinDigitalMultipurpose> GPIOPins = initializePins();

		for (int i = 0; i < GPIOPins.size(); i++) {
			bottleList.add(i, new Bottle(GPIOPins.get(i), i));
		}
	}

	private ArrayList<GpioPinDigitalMultipurpose> initializePins() {
		System.out.println("Pin Initialization");
		System.out.println(" ");

		final ArrayList<GpioPinDigitalMultipurpose> GPIOPins = new ArrayList<GpioPinDigitalMultipurpose>();

		GPIOPins.add(0, controller.provisionDigitalMultipurposePin(RaspiPin.GPIO_00, PinMode.DIGITAL_INPUT));
		GPIOPins.add(1, controller.provisionDigitalMultipurposePin(RaspiPin.GPIO_01, PinMode.DIGITAL_INPUT));
		GPIOPins.add(2, controller.provisionDigitalMultipurposePin(RaspiPin.GPIO_02, PinMode.DIGITAL_INPUT));
		GPIOPins.add(3, controller.provisionDigitalMultipurposePin(RaspiPin.GPIO_03, PinMode.DIGITAL_INPUT));
		GPIOPins.add(4, controller.provisionDigitalMultipurposePin(RaspiPin.GPIO_04, PinMode.DIGITAL_INPUT));
		return GPIOPins;
	}

	private void buttonPressed() throws IOException {

		// When button pressed, looks if pumps are available and calls
		// pourExecution
		if (available) {
			try {
				available = false;
				pourOrder();
				available = true;
				System.out.println(" ");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void pourOrder() throws InterruptedException, IOException {
		if (!orderList.isEmpty()) {
			Order recette = orderList.pop();

			// fermeture des leds pendant que le liquide est en train de couler
			led.closeAll();
						
			double maxPourTime = 0;
			double currentQuantityOunces = 0;
			double lastQuantityOunces = 0;
			
			// Pour Liquids
			for (Liquid ingredient : recette.liquidList) {
				System.out.println("Verser " + ingredient.getQuantity() + " oz de " + ingredient.getName()
						+ " de la pompe " + ingredient.getPump());
				bottleList.get(ingredient.getPump()).pour(ingredient.getQuantity());
				
				currentQuantityOunces = ingredient.getQuantity();
				if (currentQuantityOunces > lastQuantityOunces){
					maxPourTime = bottleList.get(ingredient.getPump()).getPump().ouncesToTime(currentQuantityOunces);
				}
				lastQuantityOunces = currentQuantityOunces;
			}
			
			// Sleep pour attendre que le drink soit fini de couler
			Thread.sleep((int)maxPourTime);
			
			updateLed();
			
			try {
				serveur.envoyerMsg(recette.getJSONOrder());
			} catch (IOException ioe) { 	
				ioe.printStackTrace();
			}

			System.out.println("Fini avec la recette");
			System.out.println("Il reste " + orderList.size() + " commande dans la liste");
		} else {
			System.out.println("Il n'y a pas de breuvage en attente!");
		}
	}
	
	private void stopAllPump(Order recette){
		for (Liquid ingredient : recette.liquidList) {
			bottleList.get(ingredient.getPump()).stop();
		}
	}

	private void updateLed() {
		// If orderLIst empty, shuts down LED
		if (orderList.isEmpty()) {
			led.Rouge();
		}
		else {
			led.Vert();		
		}
	}

	final public GpioController getController() {
		return controller;
	}
	
	final public SocketServeur getSocketServeur(){
		return serveur;
	}

}
