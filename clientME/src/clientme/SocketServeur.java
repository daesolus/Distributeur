package clientME;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

import com.pi4j.io.gpio.GpioPinDigitalMultipurpose;

public class SocketServeur {
	private ServerSocket serveur;
	private volatile Socket socket;
	private int port;
	BufferedReader in;
	PrintWriter out;
	boolean isStart = false; 
	public Thread t1;
	private Lumieres led;

	public SocketServeur(LinkedList<Order> orderList) throws IOException, InterruptedException {
		port = 2009;
		try {
			serveur = new ServerSocket(port);
			isStart = true;
			System.out.println("En attente de connection");
			socket = serveur.accept();
			System.out.println("Connecte a: " + socket.getInetAddress());

			t1 = new Thread(new Receiver(in, orderList, serveur, socket, this));
			t1.start();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public SocketServeur(LinkedList<Order> orderList, int port, Lumieres del) throws IOException {

		led = del;
		try {
			serveur = new ServerSocket(port);
			isStart = true;
			System.out.println("En attente de connection");
			socket = serveur.accept();
			System.out.println("Connecte a: " + socket.getInetAddress());

			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			t1 = new Thread(new Receiver(in, orderList, serveur, socket, this));
			t1.start();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getPort() {
		return port;
	}

	public void setSocket(Socket aSocket) {
		this.socket = aSocket;
	}

	public boolean isStart() {
		return isStart;
	}

	public void envoyerMsg(String unMessage) throws IOException {
		out = new PrintWriter(socket.getOutputStream());
		out.println(unMessage);
		out.flush();
	}

	public void setPrintWriter(PrintWriter pr) {
		out = pr;
	}

	public ServerSocket getServerSocket() {
		return serveur;
	}

	public Lumieres getLed() {
		return led;
	}

	public void closeServer() throws IOException, InterruptedException {
		serveur.close();
		t1.interrupt();
		isStart = false;
	}

	/*
	 * public GpioPinDigitalMultipurpose getLedRouge() { return leds[0]; }
	 * 
	 * public GpioPinDigitalMultipurpose getLedVert() { return leds[1]; }
	 */
}
