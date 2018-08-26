package com.projet.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.Statement;
import com.projet.beans.Bouteille;
import com.projet.beans.Recette;

public class RecetteDAO extends GenericDAO<Recette> {
	
	private final static String NOM_TABLE = "recette";

    public RecetteDAO(Connection con) {
        super(con, NOM_TABLE);
    }
    
	
	@Override
	public void creer(Recette recette) throws DAOException {
		String query = "INSERT INTO "+ this.nomTable + " (nom,photoUrl) VALUES "+
				"(?,?)";
		PreparedStatement requete;
		try
		{
			requete = this.connexion.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			requete.setString(1, recette.getNom());
			requete.setString(2, recette.getPhoto());
		    requete.executeUpdate();
		    
		    ResultSet generatedKeys = requete.getGeneratedKeys();
		    if (generatedKeys.next()) {
		    	recette.setId(generatedKeys.getInt(1));
		    	ajoutBouteille(recette.getId(),recette.getListeBouteille());
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
	public void modifier(Recette recette) throws DAOException {
		String query = "UPDATE "+ this.nomTable + " SET nom=?, photoUrl=?  WHERE id = ?";
		PreparedStatement requete;
		try
		{
			int i = 1;
			requete = this.connexion.prepareStatement(query);
			requete.setString(i++, recette.getNom());
			requete.setString(i++, recette.getPhoto());
			requete.setInt(i++, recette.getId());
			
		    requete.executeUpdate();
		}
		catch(SQLException e)
		{ 
			throw new DAOException("Recette: Erreur lors de la modification d'un élément"); 
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
			throw new DAOException("Recette: Erreur lors du retrait d'un élément."); 
		}
		
	}
	
	

	public void supprimerBouteille(int idBouteille,int idRecette) throws DAOException {
		String query = "DELETE FROM bouteilleRecette  WHERE idBouteille= ? AND idRecette = ?";
		PreparedStatement requete;
		try
		{
			requete = this.connexion.prepareStatement(query);
			requete.setInt(1, idBouteille);
			requete.setInt(2, idRecette);
		    requete.executeUpdate();
		}
		catch(SQLException e)
		{ 
			throw new DAOException("Recette: Erreur lors du retrait d'une bouteille."); 
		}		
	}
	
	@Override
	public List<Recette> lister() throws DAOException {
		List<Recette> listeRecette = new ArrayList<Recette>();
		
    	String query = "SELECT recette.id, recette.nom, recette.photoUrl FROM recette";
    	PreparedStatement requete;
    	
    	try
        {
    		requete = this.connexion.prepareStatement(query);
    		ResultSet resultSet = requete.executeQuery();    		
    		
    		while(resultSet.next()) {
    			
    			Recette recette = new Recette(resultSet);
    			recette.setListeBouteille(listerBouteille(recette.getId()));
    			listeRecette.add(recette);
    		}
        }
    	catch(SQLException e)
        { 
    		throw new DAOException("Recette: Erreur lors de la selection de tous les éléments");
        }
    	
    	return listeRecette;
	}
	
	public void modifierBouteilleRecette(int idBouteille, int idRecette, double proportion) throws DAOException {
		String query ="UPDATE bouteilleRecette SET proportion = ? WHERE idBouteille = ? AND idRecette = ?";
		Recette recette = null;
		PreparedStatement requete;
		try
        {
			requete = this.connexion.prepareStatement(query);
			requete.setDouble(1, proportion);
			requete.setInt(2, idBouteille);
			requete.setInt(3, idRecette);
			requete.executeUpdate();    		
        }
    	catch(SQLException e)
        { 
    		throw new DAOException("Recette: Erreur lors de la modification de la bouteille: "+idBouteille+" dans la recette: "+idRecette);
        }
	}
	
	public Recette obtenirObjet(int id) throws DAOException {
		String query ="SELECT recette.id, recette.nom, recette.photoUrl FROM recette WHERE recette.id = ?";
		Recette recette = null;
		PreparedStatement requete;
		try
        {
			requete = this.connexion.prepareStatement(query);
			requete.setInt(1, id);
			ResultSet resultSet = requete.executeQuery();    		
    		
    		if(resultSet.next()) {	
    			recette = new Recette(resultSet);
    			recette.setListeBouteille(listerBouteille(recette.getId()));
    		}
        }
    	catch(SQLException e)
        { 
    		throw new DAOException("Recette: Erreur lors de la selection de la recette ID: "+id);
        }
		return recette;
	}
	
	private HashMap<Bouteille,Double> listerBouteille(int id) {
		HashMap<Bouteille,Double> listeBouteille = new HashMap<Bouteille,Double>();
		
		String query = "SELECT bouteille.id,bouteille.type,bouteille.marque,bouteille.qte,bouteille.numeroPompe, bouteilleRecette.proportion "+
					   "FROM bouteille LEFT JOIN bouteilleRecette ON bouteille.id = bouteilleRecette.idBouteille "+
					   "WHERE bouteilleRecette.idRecette = ?";
		
		PreparedStatement requete;
		try
        {
			requete = this.connexion.prepareStatement(query);
			requete.setInt(1, id);
    		ResultSet resultSet = requete.executeQuery();
    		while(resultSet.next()) {
    			
    			Bouteille bouteille = new Bouteille(resultSet);
    			listeBouteille.put(bouteille, resultSet.getDouble("proportion"));
    		}
        }
    	catch(SQLException e)
        { 
    		throw new DAOException("Recette: Erreur lors de la selection des bouteilles ID: "+id);
        }
		return listeBouteille;
	}
	
	private void ajoutBouteille(int idRecette, HashMap<Bouteille,Double> listeBouteille){
		if(listeBouteille != null) {
			String query = "INSERT INTO bouteilleRecette (idBouteille, idRecette,proportion) VALUES (?,?,?)";
			PreparedStatement requete;
			try
	        {
				requete = this.connexion.prepareStatement(query);
				requete.setInt(2, idRecette);
				for (Map.Entry<Bouteille, Double> entre : listeBouteille.entrySet()) {
					Bouteille bouteille =  entre.getKey();
					requete.setInt(1, bouteille.getId());
					requete.setDouble(3, entre.getValue());
					requete.executeUpdate();
				} 
	        }
	    	catch(SQLException e)
	        { 
	    		throw new DAOException("Recette: Erreur lors de la selection des bouteilles ID: "+idRecette);
	        }
		}
	}	
}
