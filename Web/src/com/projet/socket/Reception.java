package com.projet.socket;

import java.io.BufferedReader;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import com.projet.dao.CommandeDAO;
import com.projet.dao.DAOException;
import com.projet.dao.DAOManager;
import com.projet.dao.RecetteDAO;
import com.projet.dao.DAOManager.Table;


public class Reception implements Runnable {

	private BufferedReader in;
	private String message = null;
	private Client client;
	private DAOManager dao;
	private CommandeDAO commandeDAO;
	private RecetteDAO recetteDAO;
	
	public Reception(BufferedReader in,Client client){	
		this.in = in;
		this.client = client;
		dao = DAOManager.getInstance();
		try {
			commandeDAO = (CommandeDAO)dao.getDAO(Table.COMMANDE);
			recetteDAO = (RecetteDAO)dao.getDAO(Table.RECETTE);
		}
		catch (DAOException e) {
			//TODO:A faire
		}
	}	
	
	//Section de code pour contrôler nos pompes
	public void run() {
		while(!Thread.currentThread().isInterrupted()){
		     try {
				message = in.readLine();
				if(message != null){
					/*
					System.out.println(message);
					
					JSONObject jsonCommande = new JSONObject(message);
					JSONArray jsonListeRecette = jsonCommande.getJSONArray("recette");
					
					for(int i =0;i<jsonListeRecette.length();i++)
					{
						JSONArray jsonRecette = jsonListeRecette.getJSONArray(i);
						
						for(int k = 0; k<jsonRecette.length();k++){
							JSONObject jsonBoisson = jsonRecette.getJSONObject(k);
							System.out.println(jsonBoisson.get("boisson").toString());
							System.out.println(jsonBoisson.get("qte").toString());
							System.out.println(jsonBoisson.get("pompe").toString());
						}	
					}
					*/	
				}
				else {
					client.closeClient();
					Thread.currentThread().interrupt();
			    	client.isConnected = false;	
				}
			 } 
		     catch (IOException e) {
		    	 Thread.currentThread().interrupt();
		    	 client.isConnected = false;
		    	 e.printStackTrace();
			 } 	 
		}
	}
}

