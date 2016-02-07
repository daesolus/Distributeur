package com.projet.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Recette {
	private int id;
	private String nom;
	private String photo;
	private HashMap<Bouteille,Double> mapRecette;
	
	public Recette() {
		id = 0;
		nom = "";
		photo = "";
		mapRecette = new HashMap<Bouteille, Double>();
	}
	
	public Recette(int id, String nom, String photo,HashMap<Bouteille,Double> listeBouteille) {
		this.id = id;
		this.nom = nom;
		this.photo = photo;
		this.mapRecette = listeBouteille;
	}
	
	public Recette(String nom, String photo,HashMap<Bouteille,Double> listeBouteille) {
		this.id = 0;
		this.nom = nom;
		this.photo = photo;
		this.mapRecette = listeBouteille;
	}
	
	public Recette(int id,String nom, String photo) {
		this.id = id;
		this.nom = nom;
		this.photo = photo;
		this.mapRecette = null;
	}
	
	public Recette(ResultSet resultSet) throws SQLException {
		this.id = resultSet.getInt("id");
		this.nom = resultSet.getString("nom");
		this.photo = resultSet.getString("photoUrl");
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public HashMap<Bouteille,Double> getListeBouteille() {
		return mapRecette;
	}

	public void setListeBouteille(HashMap<Bouteille,Double> listeBouteille) {
		this.mapRecette = listeBouteille;
	}
	
	
}
