//Solution: http://stackoverflow.com/questions/12812256/how-do-i-implement-a-dao-manager-using-jdbc-and-connection-pools

package com.projet.dao;

import java.sql.*;
import  java.sql.Connection;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import com.projet.dao.GenericDAO;


public class DAOManager {
	
	private DataSource source;
    private Connection connexion;
    protected static Boolean isTest;
    
    
    public enum Table { 
    	BOUTEILLE, 
    	RECETTE,
    	COMMANDE
    }
    
	private DAOManager() throws Exception {
        try
        {
        	Context initContext = new InitialContext();
        	Context  envContext = (Context) initContext.lookup("java:comp/env");
        	
            this.source = (DataSource) envContext.lookup("jdbc/bar");
        }
        catch(Exception e) { 
        	throw e; 
        }
    }
	
	private static class DAOManagerSingleton {

        public static final ThreadLocal<DAOManager> INSTANCE;
        static
        {
            ThreadLocal<DAOManager> dm;
            try
            {
                dm = new ThreadLocal<DAOManager>(){
                    @Override
                    protected DAOManager initialValue() {
                        try
                        {
                            return new DAOManager();
                        }
                        catch(Exception e)
                        {
                            return null;
                        }
                    }
                };
            }
            catch(Exception e){
                dm = null;
            }
            INSTANCE = dm;
        }        

    }
	
	public static DAOManager getInstance() {
		return DAOManagerSingleton.INSTANCE.get();
    }

    public void open() throws SQLException {
        try
        {
            if(connexion==null || this.connexion.isClosed()){
                connexion = source.getConnection();
            }
        }
        catch(SQLException e) { 
        	throw e; 
        }
    }

    public void close() throws SQLException {
        try
        {
            if(connexion!=null && !connexion.isClosed()) {
                connexion.close();
            }
        }
        catch(SQLException e) { 
        	throw e; 
        }
    }
    
    @SuppressWarnings("rawtypes")
	public GenericDAO getDAO(Table t)  
    {
        try
        {
            if(this.connexion == null || this.connexion.isClosed()) //Let's ensure our connection is open   
                this.open();
	        switch(t)
	        {
		        case BOUTEILLE:
		            return new BouteilleDAO(this.connexion);
		        case RECETTE:
		            return new RecetteDAO(this.connexion);
		        case COMMANDE:
		        	return new CommandeDAO(this.connexion);
		        default:
		            throw new SQLException("La table est inexistante.");    
	        }
        }
        
        catch(SQLException e){ 
        	throw new DAOException(); 
        }
    }
}
