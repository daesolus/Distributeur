package com.projet.dao;
import java.util.ArrayList;
import java.util.List;
import  java.sql.*;

import com.mysql.jdbc.Statement;
import com.projet.beans.Bouteille;

public class BouteilleDAO extends GenericDAO<Bouteille>{

	private final static String NOM_TABLE = "bouteille";
    public BouteilleDAO(Connection con) {
        super(con, NOM_TABLE);
    }
    
    @Override
    public void creer(Bouteille bouteille) throws DAOException {
    	String query = "INSERT INTO "+ this.nomTable + " (type,marque,qte,numeroPompe) VALUES "+
    					"(?,?,?,?)";
    	PreparedStatement requete;
    	try
        {
    		int i = 1;
    		requete = this.connexion.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);;
    		requete.setString(i++, bouteille.getType());
    		requete.setString(i++, bouteille.getMarque());
    		requete.setDouble(i++, bouteille.getQte());
    		requete.setInt(i++, bouteille.getNumeroPompe());
    		
    		
	        requete.executeUpdate();
	        ResultSet generatedKeys = requete.getGeneratedKeys();
		    if (generatedKeys.next()) {
		    	bouteille.setId(generatedKeys.getInt(1));
		    }
		    else {
		    	throw new DAOException("Erreur lors de l'obtention de l'identifiant lors de la création d'une bouteille");
	        }
        }
        catch(SQLException e)
        { 
        	throw new DAOException("Bouteille: Erreur lors de la création d'une bouteille"); 
        }
    }
    
    @Override
    public void modifier(Bouteille bouteille) throws DAOException {
    	String query = "UPDATE "+ this.nomTable + " SET type=? , marque= ?, qte = ?, numeroPompe=? "+
    				   "WHERE id = ?";
		PreparedStatement requete;
		try
		{
			int i = 1;
			requete = this.connexion.prepareStatement(query);
			requete.setString(i++, bouteille.getType());
			requete.setString(i++, bouteille.getMarque());
			requete.setDouble(i++, bouteille.getQte());
			requete.setInt(i++, bouteille.getNumeroPompe());
			requete.setInt(i++, bouteille.getId());
			
		    requete.executeUpdate();
		}
		catch(SQLException e)
		{ 
			throw new DAOException("Bouteille: Erreur lors de la modification d'un élément"); 
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
			throw new DAOException("Bouteille: Erreur lors de la création du nombre d'éléments"); 
		}
	}

    
    @Override
    public List<Bouteille> lister() throws DAOException {
    	List<Bouteille> listeBouteille = new ArrayList<Bouteille>();
    	String query = "SELECT id,type,marque,qte,numeroPompe FROM "+ this.nomTable;
    	PreparedStatement requete;
    	
    	try
        {
    		requete = this.connexion.prepareStatement(query);
    		ResultSet resultSet = requete.executeQuery();
    		while(resultSet.next()) {
    			Bouteille bouteille = new Bouteille(resultSet);
    			listeBouteille.add(bouteille);
    		}
    		return listeBouteille;
        }
    	catch(SQLException e)
        { 
    		throw new DAOException("Bouteille: Erreur lors de la selection de tous les éléments");
        }
    	
    	
    }

	@Override
	public Bouteille obtenirObjet(int id) throws DAOException {
		String query ="SELECT bouteille.id, bouteille.type, bouteille.marque, bouteille.qte, " + 
					  "bouteille.numeroPompe FROM bouteille WHERE bouteille.id = ?";
		Bouteille bouteille = null;
		PreparedStatement requete;
		try
        {
			requete = this.connexion.prepareStatement(query);
			requete.setInt(1, id);
			ResultSet resultSet = requete.executeQuery();    		
    		
    		if(resultSet.next()) {
    			
    			bouteille = new Bouteille(resultSet);
    		}
        }
    	catch(SQLException e)
        { 
    		throw new DAOException("Recette: Erreur lors de la selection de la recette ID: "+id);
        }
		return bouteille;
	}    
}
