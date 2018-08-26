package com.projet.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;


public class Client {
	private Socket socket;
	private String hostname;	
	private int port;
	private BufferedReader in;
	private PrintWriter out;
	boolean isStart = false;
	protected Thread t1;
	protected boolean isConnected;
	
	public Client(String hostname,int port) throws IOException  {	
		this.hostname = hostname;
		this.port = port;
	    socket = new Socket();
		socket.connect(new InetSocketAddress(hostname, port), 1000);
		isConnected = true;
		in = new BufferedReader (new InputStreamReader (socket.getInputStream()));
		t1 = new Thread(new Reception(in,this));
		t1.start();	
	}
	
	public Client() throws  IOException {
		hostname = "localhost";
		port = 2009;		
		try{
			socket = new Socket(hostname,port);
			isConnected = true;
			in = new BufferedReader (new InputStreamReader (socket.getInputStream()));
			t1 = new Thread(new Reception(in,this));
			t1.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void envoyerMsg(String unMessage) throws IOException {
		out = new PrintWriter(socket.getOutputStream());
		out.println(unMessage);
		out.flush();
	}
	
	public String getHostname(){
		return this.hostname;
	}
	
	public int getPort(){
		return this.port;
	}
	
	public Socket getSocket(){
		return this.socket;
	}
	
	public boolean estConnecte() throws IOException{
		return isConnected;
	}
	
	public void closeClient() throws IOException {
		socket.close();
		isStart = false;
	}
}
