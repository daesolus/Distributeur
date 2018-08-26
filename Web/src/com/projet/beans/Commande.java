package com.projet.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Commande {
	private int id;
	private Date date;
	private Boolean traite;
	private List<Recette> listeRecette;
	
	public Commande(){
		id = 0;
		date = null;
		listeRecette = new ArrayList<Recette>();
	}
	
	public Commande(int id, Date date, List<Recette> listeRecette){
		this.id = id;
		this.date = date;
		this.traite = false;
		this.listeRecette = listeRecette;	
	}
	
	public Commande(Date date, List<Recette> listeRecette){
		this.id = 0;
		this.traite = false;
		this.date = date;
		this.listeRecette = listeRecette;	
	}
	
	public Commande(Date date, Boolean traite,List<Recette> listeRecette){
		this.id = 0;
		this.traite = traite;
		this.date = date;
		this.listeRecette = listeRecette;	
	}

	public Commande(ResultSet resultSet) throws SQLException {
		this.id = resultSet.getInt("id");
		this.date = (Date)resultSet.getDate("date");
		this.traite = resultSet.getBoolean("traite");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Boolean getTraite() {
		return traite;
	}

	public void setTraite(Boolean traite) {
		this.traite = traite;
	}

	public List<Recette> getListeRecette() {
		return listeRecette;
	}

	public void setListeRecette(List<Recette> listeRecette) {
		this.listeRecette = listeRecette;
	}
	

}
