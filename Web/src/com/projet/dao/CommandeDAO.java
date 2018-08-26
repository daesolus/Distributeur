package com.projet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.Statement;
import com.projet.beans.Commande;
import com.projet.beans.Recette;

public class CommandeDAO extends GenericDAO<Commande> {

	private final static String NOM_TABLE = "commande";
    public CommandeDAO(Connection con) {
        super(con, NOM_TABLE);
    }
    
	@Override
	public void creer(Commande commande) throws DAOException {
		String query = "INSERT INTO "+ this.nomTable + " (date) VALUES (?)";
		PreparedStatement requete;
		try
		{
			java.sql.Date dateSql = new java.sql.Date(commande.getDate().getTime());
			requete = this.connexion.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			requete.setDate(1,  dateSql);
		    requete.executeUpdate();
		    
		    ResultSet generatedKeys = requete.getGeneratedKeys();
		    
		    if (generatedKeys.next()) {
		    	commande.setId(generatedKeys.getInt(1));
		    	ajoutRecette(commande.getId(),commande.getListeRecette());
	        }
		    else {
		    	throw new DAOException("Erreur lors de l'obtention de l'identifiant lors de la création d'une recette");
	        }
		}
		catch(SQLException e)
		{ 
			throw new DAOException("Recette: Erreur lors de la création d'une recette"); 
		}		
	}
	
	@Override
	public void modifier(Commande commande) throws DAOException {
		String query = "UPDATE "+ this.nomTable + " SET date=? , traite = ? WHERE id = ?";
		PreparedStatement requete;
		try
		{
			int i = 1;
			java.sql.Date dateSql = new java.sql.Date(commande.getDate().getTime());
			requete = this.connexion.prepareStatement(query);
			requete.setDate(i++, dateSql);
			requete.setBoolean(i++, commande.getTraite());
			requete.setInt(i++, commande.getId());
			
		    requete.executeUpdate();
		}
		catch(SQLException e)
		{ 
			throw new DAOException("Commande: Erreur lors de la modification d'un élément"); 
		}
		
	}
	@Override
	public void supprimer(int id) throws DAOException {
		String query = "DELETE FROM "+ this.nomTable + " WHERE id= ?";
		PreparedStatement requete;
		try
		{
			requete = this.connexion.prepareStatement(query);
			requete.setInt(1, id);	
		    requete.executeUpdate();
		}
		catch(SQLException e)
		{ 
			throw new DAOException("Commande: Erreur lors du retrait d'un element"); 
		}
		
	}
	@Override
	public List<Commande> lister() throws DAOException {
		List<Commande> listeCommande = new ArrayList<Commande>();
		
    	String query = "SELECT commande.id, commande.date,commande.traite FROM commande WHERE traite = 0 ";
    	PreparedStatement requete;
    	
    	try
        {
    		requete = this.connexion.prepareStatement(query);
    		ResultSet resultSet = requete.executeQuery();    		
    		
    		while(resultSet.next()) {
    			
    			Commande commande = new Commande(resultSet);
    			commande.setListeRecette(listerRecette(commande.getId()));
    			listeCommande.add(commande);
    		}
        }
    	catch(SQLException e)
        { 
    		throw new DAOException("Commande: Erreur lors de la selection de tous les éléments");
        }
    	
    	return listeCommande;
	}
	private List<Recette> listerRecette(int id) {
		List<Recette> listeRecette = new ArrayList<>();
		
		String query = "SELECT recette.id,recette.nom, recette.photoUrl FROM recette "+
					   "LEFT JOIN commandeRecette ON recette.id = commandeRecette.idRecette "+
					   "WHERE commandeRecette.idCommande = ?";
		
		PreparedStatement requete;
		try
        {
			requete = this.connexion.prepareStatement(query);
			requete.setInt(1, id);
    		ResultSet resultSet = requete.executeQuery();
    		while(resultSet.next()) {
    			
    			Recette recette = new Recette(resultSet);
    			listeRecette.add(recette);
    		}
        }
    	catch(SQLException e)
        { 
    		throw new DAOException("Commande: Erreur lors de la selection des recette ID: "+id);
        }
		return listeRecette;
	}
	
	@Override
	public Commande obtenirObjet(int id) throws DAOException {
		String query ="SELECT commande.id, commande.date, commande.traite FROM commande WHERE commande.id = ?";
		Commande commande = null;
		PreparedStatement requete;
		try
        {
			requete = this.connexion.prepareStatement(query);
			requete.setInt(1, id);
			ResultSet resultSet = requete.executeQuery();    		
    		
    		if(resultSet.next()) {
    			
    			commande = new Commande(resultSet);
    			commande.setListeRecette(listerRecette(commande.getId()));
    		}
        }
    	catch(SQLException e)
        { 
    		throw new DAOException("Commande: Erreur lors de la selection de la commande ID: "+id);
        }
		return commande;
	}
	
	private void ajoutRecette(int idCommande, List<Recette> listeRecette){
		if(listeRecette != null) {
			String query = "INSERT INTO commandeRecette (idCommande, idRecette) VALUES (?,?)";
			PreparedStatement requete;
			try
	        {
				requete = this.connexion.prepareStatement(query);
				requete.setInt(1, idCommande);
				for(Recette recette : listeRecette) {
					requete.setInt(2, recette.getId());
					requete.executeUpdate();
				} 	
	        }
	    	catch(SQLException e)
	        { 
	    		throw new DAOException("Commande: Erreur lors de la selection des recettes ID: "+idCommande);
	        }
		}
	}
}
