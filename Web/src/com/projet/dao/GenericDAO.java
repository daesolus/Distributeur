//Solution: http://stackoverflow.com/questions/12812256/how-do-i-implement-a-dao-manager-using-jdbc-and-connection-pools

package com.projet.dao;
import java.util.List;


import  java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class GenericDAO<T> {
	
	public abstract void creer(T t) throws DAOException;
	public abstract void modifier(T t) throws DAOException;
	public abstract void supprimer(int id) throws DAOException;
	public abstract List<T> lister() throws DAOException;
	public abstract T obtenirObjet(int id) throws DAOException;
	

    protected final String nomTable;
    protected Connection connexion;

    protected GenericDAO(Connection connexion, String nomTable) {
        this.nomTable = nomTable;
        this.connexion = connexion;
    }
    

    protected int count() throws DAOException {
    	String query = "SELECT COUNT(*) AS count FROM "+ this.nomTable;
        PreparedStatement counter;
        try
        {
	        counter = this.connexion.prepareStatement(query);
	        ResultSet res = counter.executeQuery();
	        res.next();
	        return res.getInt("count");
        }
        catch(SQLException e)
        { 
        	throw new DAOException(this.nomTable+": Erreur lors de la lecture du nombre d'éléments"); 
        }
    }

}
