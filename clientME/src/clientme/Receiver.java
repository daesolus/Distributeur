package clientME;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.LinkedList;

import org.json.JSONObject;

import com.pi4j.io.gpio.PinMode;

import java.net.InetAddress;

import clientME.Order;

import java.net.ServerSocket;

public class Receiver implements Runnable {

	private String receivedString;
	private int port;

	private ServerSocket serverSocket;
	private Socket socket;

	private LinkedList<Order> orderList;

	private BufferedReader in;
	private SocketServeur socketServeur;

	public Receiver(BufferedReader in, LinkedList<Order> orderList, ServerSocket serverSocket, Socket socket,
			SocketServeur socketServeur) {
		this.orderList = orderList;
		this.in = in;
		this.serverSocket = serverSocket;
		this.socket = socket;
		this.socketServeur = socketServeur;
	}

	// Usage
	public void run() {

		while (!Thread.currentThread().isInterrupted()) {
			try {
				receiveOrders(in);
			} catch (NullPointerException | InterruptedException | IOException e) {
				e.printStackTrace();
			} catch (SocketConnectionException se) {
				System.out.println(se.getMessage());
				try {
					System.out.println("\nEn attente de connection apres une deconnection");
					socket = serverSocket.accept();
					socketServeur.setSocket(socket);
					System.out.println("Connecte a: " + socket.getInetAddress());
					in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				} catch (Exception e1) {
					Thread.currentThread().interrupt();
					e1.printStackTrace();
				}
			}
		}
	}

	private void receiveOrders(BufferedReader in) throws IOException, InterruptedException, SocketConnectionException {
		// Order Reception
		while (true) {

			try {
				receivedString = in.readLine();
			} catch (SocketException se) {
				throw new SocketConnectionException("Connexion reset");
			}
			if (receivedString != null) {
				orderList.add(new Order(receivedString));
				updateLed();

			} else {
				throw new SocketConnectionException("Connexion perdue");
			}
			Thread.sleep(50);
		}
	}

	private void updateLed() {
		// If orderLIst empty, shuts down LED

		if (orderList.isEmpty()) {
			socketServeur.getLed().Rouge();
		} else {
			socketServeur.getLed().Vert();
		}
	}

	public boolean isJSONRequest() throws IOException {
		// vérifier si format JSON
		return (receivedString != null);
	}

	// Getters
	public int getPort() {
		return port;
	}

	public LinkedList<Order> getOrderList() {
		return orderList;
	}
}
