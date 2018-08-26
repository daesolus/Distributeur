package com.projet.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.projet.dao.DAOManager.Table;
import com.projet.dao.*;
import org.json.*;
import com.projet.beans.Bouteille;

@WebServlet("/bouteille")
public class BouteilleController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private DAOManager dao;
	private BouteilleDAO bouteilleDAO;
	private StringBuffer erreurMsg;
	
	public void init() throws ServletException {
		erreurMsg = new StringBuffer();
		dao = DAOManager.getInstance();
		try {
			bouteilleDAO = (BouteilleDAO)dao.getDAO(Table.BOUTEILLE);
		}
		catch (DAOException e) {
			erreurMsg.append(e.getMessage()+"<br/>");
		}
	}
	
	
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{	
		try{
			request.setAttribute("listeBouteille", bouteilleDAO.lister());
		}
		catch(DAOException e){
			erreurMsg.append(e.getMessage()+"<br/>");
		}
		request.setAttribute("erreurMsg", erreurMsg.toString());
		erreurMsg = new StringBuffer();
	    this.getServletContext().getRequestDispatcher( "/bouteille.jsp" ).forward( request, response );
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {				
		erreurMsg = new StringBuffer();
		try{
			switch (request.getParameter("hidAction")) {
				case "ajouter": ajouterBouteille(request,response);break;
				case "modifier": modifierBouteille(request,response);break;
				case "supprimer": supprimerBouteille(request,response);break;
			}
		}
		catch(DAOException e){
			erreurMsg.append(e.getMessage()+"<br/>");
		}
		
		response.sendRedirect(request.getRequestURI());
	 }
	
	
	
	
	private void ajouterBouteille(HttpServletRequest request, HttpServletResponse response) {
		String chaineBouteilles = request.getParameter("hidBouteille");	
		if(chaineBouteilles != null && !chaineBouteilles.equals("")) {
			JSONObject jsonBouteilles = new JSONObject(chaineBouteilles);
			
			JSONArray lesBouteilles = jsonBouteilles.getJSONArray("nouvelleBouteille");
			for (int i = 0; i < lesBouteilles.length(); i++)
			{
				JSONObject currentBouteille = lesBouteilles.getJSONObject(i);
				Bouteille bouteille = new Bouteille(currentBouteille);
				bouteilleDAO.creer(bouteille);							
			} 
		}		
		
	}
	
	private void modifierBouteille(HttpServletRequest request, HttpServletResponse response) {
		String chaineBouteilles = request.getParameter("hidBouteille");
		if(chaineBouteilles != null && !chaineBouteilles.equals("")) {
			JSONObject jsonBouteilles = new JSONObject(chaineBouteilles);
			
			JSONArray lesBouteilles = jsonBouteilles.getJSONArray("nouvelleBouteille");
			for (int i = 0; i < lesBouteilles.length(); i++)
			{
				JSONObject currentBouteille = lesBouteilles.getJSONObject(i);
				Bouteille bouteille = new Bouteille(currentBouteille);
				bouteilleDAO.modifier(bouteille);	
			}		
		}
	}
	
	private void supprimerBouteille(HttpServletRequest request, HttpServletResponse response) {
		String chaineId = request.getParameter("hidBouteille");
		if(chaineId != null && !chaineId.equals("")) {
			bouteilleDAO.supprimer(Integer.parseInt(chaineId));
		}
	}
	
	
	
	public void destroy() {
		try {
			dao.close();
		} 
		catch (SQLException e) {
			// TODO A faire
			e.printStackTrace();
		}
	}
}
