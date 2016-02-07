package com.projet.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONObject;

public class Bouteille {

	private int id;
	private String type;
	private String marque;
	private double qte;
	private int numeroPompe;
	
	public Bouteille(){
		id = 0;
		type = "";
		marque = "";
		qte = 0;
		numeroPompe = 0;
	}

	public Bouteille(int idBouteille,String type,String marque,double qte,int numPompe) {
		this.id = idBouteille;
		this.type = type;
		this.marque = marque;
		this.qte = qte;
		this.numeroPompe = numPompe;
	}

	
	public Bouteille(String type,String marque,double qte,int numPompe) {
		this.id = 0;
		this.type = type;
		this.marque = marque;
		this.qte = qte;
		this.numeroPompe = numPompe;
	}

	public Bouteille(JSONObject currentBouteille) {
		
		if(!currentBouteille.isNull("id")){
			this.setId(currentBouteille.getInt("id"));
		}
		else
		{
			this.setId(0);
		}
		
		this.setMarque(currentBouteille.getString("marque"));
		this.setType(currentBouteille.getString("type"));
		this.setQte(currentBouteille.getDouble("qte"));
		this.setNumeroPompe(currentBouteille.getInt("pompe"));
	}

	public Bouteille(ResultSet resultSet) throws SQLException {
		this.setId(resultSet.getInt("id"));
		this.setType(resultSet.getString("type"));
		this.setMarque(resultSet.getString("marque"));
		this.setQte(resultSet.getDouble("qte"));
		this.setNumeroPompe(resultSet.getInt("numeroPompe"));
	}

	public int getId() {
		return id;
	}

	public void setId(int idBouteille) {
		this.id = idBouteille;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public double getQte() {
		return qte;
	}

	public void setQte(double qte) {
		this.qte = qte;
	}

	public int getNumeroPompe() {
		return numeroPompe;
	}

	public void setNumeroPompe(int numeroPompe) {
		this.numeroPompe = numeroPompe;
	}
}