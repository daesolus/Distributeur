package com.projet.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

import org.json.*;

import com.projet.beans.Bouteille;
import com.projet.beans.Commande;
import com.projet.beans.Recette;
import com.projet.dao.BouteilleDAO;
import com.projet.dao.CommandeDAO;
import com.projet.dao.DAOException;
import com.projet.dao.DAOManager;
import com.projet.dao.RecetteDAO;
import com.projet.dao.DAOManager.Table;
import com.projet.socket.Client;
	
@WebServlet("/commande")
public class CommandeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private DAOManager dao;
	private CommandeDAO commandeDAO;
	private BouteilleDAO bouteilleDAO;
	private RecetteDAO recetteDAO;
	private Client socket;
	private static final String HOSTNAME = "192.168.1.102";
	private static final int PORT = 2009;
	private final boolean isTesting = false;
	private StringBuffer erreurMsg;
	private volatile String prochaineCommande = "";
	
	public void init() throws ServletException {
		dao = DAOManager.getInstance();
		erreurMsg = new StringBuffer();
		try {
			commandeDAO = (CommandeDAO)dao.getDAO(Table.COMMANDE);
			recetteDAO = (RecetteDAO)dao.getDAO(Table.RECETTE);
			bouteilleDAO = (BouteilleDAO)dao.getDAO(Table.BOUTEILLE);
			
			if(!isTesting){
				socket = new Client(HOSTNAME,PORT);
			}
			else{
				socket = new Client();
			}
		}
		catch (DAOException e) {
			erreurMsg.append("Erreur lors de la connexion à la base de données.<br/>"+e.getMessage()+"<br/>");
			e.printStackTrace();
		} 
		catch (IOException e) {
			erreurMsg.append("Erreur lors de la connexion à la machine distributrice.<br/>");
			e.printStackTrace();
		} 
	}

	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		listeRecette(request,response);
		Cookie cookieAffichage = getAffichageCookie(request,response);
		
				
		try{
			if(!socket.estConnecte()) {
				socket.closeClient();
				if(!isTesting){
					socket = new Client(HOSTNAME,PORT);
				}
				else{
					socket = new Client();
				}
			}
			
		} 
		catch(NullPointerException e)
		{
			erreurMsg.append("La connexion à la machine a été refusée.<br/>");
		}
		
		request.setAttribute("erreurMsg", erreurMsg.toString());
		erreurMsg = new StringBuffer();
		if(cookieAffichage.getValue().equals("advance")){
			this.getServletContext().getRequestDispatcher( "/commande.jsp" ).forward( request, response );
		}
		else{
			this.getServletContext().getRequestDispatcher( "/interface.jsp" ).forward( request, response );
		}
	}
	
	private Cookie getAffichageCookie(HttpServletRequest request, HttpServletResponse response){
		String affichage = null;
		Cookie[] cookies = request.getCookies();
		Cookie unCookie = null;
		for(Cookie cookie : cookies){
		    if("affichage".equals(cookie.getName())){
		    	unCookie = cookie;
		    }
		}
		
		if(unCookie == null){
			affichage = "advance";
			unCookie = new Cookie("affichage", affichage);
			response.addCookie(unCookie);
		}
		return unCookie;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String action = request.getParameter("action");
		String form = request.getParameter("formInter");
		Cookie cookie = getAffichageCookie(request,response);
		
		erreurMsg = new StringBuffer();
		if(action != null) {
			switch(action){
				case "detail":detailRecette(request,response);break;
				case "commander": commander(request,response,cookie);
								  response.sendRedirect(request.getRequestURI());
								  break;
			}
		}
		else {
			
			if(form != null && form.equals("changeInterface")){
				
				if(cookie.getValue().equals("simple")){
					cookie.setValue("advance");
					response.addCookie(cookie);
				}
				else if (cookie.getValue().equals("advance")){
					cookie.setValue("simple");
					response.addCookie(cookie);
					
				}
				doGet(request, response);
			}
			else{
				commander(request,response,cookie);
				response.sendRedirect(request.getRequestURI());
			}
		}	
	}
	
	private void listeRecette(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		try{
			request.setAttribute("listeRecette", recetteDAO.lister());
			request.setAttribute("listeCommande", commandeDAO.lister());
		}
		catch(DAOException e){
			erreurMsg.append(e.getMessage()+"<br/>");	
		}
	}
	
	private void commander(HttpServletRequest request, HttpServletResponse response,Cookie cookie ) throws ServletException {
		List<Recette> listeRecette = new ArrayList<Recette>();
		String listeRequestRecette [] = request.getParameterValues("selectRecette");
		String errorMessage = request.getParameter("erreurMsg");
		String idSimple = request.getParameter("hidCommande");
		String qte = request.getParameter("hidQuantite");
		boolean isSimple = false;
		
		if(cookie.getValue().equals("simple") && listeRequestRecette == null){
			listeRequestRecette = new String[1];
			listeRequestRecette[0] = idSimple;
			isSimple = true;
		}
		
		double liquideUse;
		double proportion;
		if(errorMessage.equals("")) {
			try{		
				JSONObject jsonCommande = new JSONObject();
				if(listeRequestRecette != null )	{
					for(String id : listeRequestRecette){	
						Recette recette = recetteDAO.obtenirObjet(Integer.parseInt(id));
						listeRecette.add(recette);
						JSONArray listBouteille = new JSONArray();
						
						HashMap<Bouteille,Double> listeBouteille = recette.getListeBouteille();
						for (Map.Entry<Bouteille, Double> entry : listeBouteille.entrySet()) {
							Bouteille bouteille = entry.getKey();
							proportion = entry.getValue();
							
							JSONObject boissonObj = new JSONObject();
							boissonObj.put("boisson", bouteille.getType());
							
							if(isSimple){
								liquideUse = qteBreuvage(proportion,Double.parseDouble(qte),bouteille);
							}
							else{
								liquideUse = qteBreuvage(proportion,Double.parseDouble(request.getParameter("txtQte_"+id)),bouteille);
							}
							
							boissonObj.put("qte",liquideUse );
							boissonObj.put("pompe", bouteille.getNumeroPompe());
							listBouteille.put(boissonObj);
							
							bouteille.setQte(bouteille.getQte()-liquideUse);
							bouteilleDAO.modifier(bouteille);
											
						}
						java.util.Date date= new java.util.Date();
						Timestamp time = new Timestamp(date.getTime());
						
						JSONObject jsonTime = new JSONObject();
						jsonTime.put("time",time.toString());
						listBouteille.put(jsonTime);
						jsonCommande.put("recette", listBouteille);
						System.out.println(jsonCommande.toString());
						envoyerCommande(jsonCommande.toString());
					}					
				}
				
				Commande commande = new Commande(new Date(),listeRecette);
				commandeDAO.creer(commande);
			}			
			catch(DAOException |  QuantiteException e){
				erreurMsg.append(e.getMessage()+"<br/>");
			}
		}
	}
	
	private void envoyerCommande(String laCommande){
		try{
			if(socket.estConnecte()) {
				socket.envoyerMsg(laCommande);
			}
			else {
				socket.closeClient();
				if(!isTesting)
					socket = new Client(HOSTNAME,2009);
				else{
					socket = new Client();
				}
				socket.envoyerMsg(laCommande);
			}	
		}
		catch(IOException | NullPointerException e){
			erreurMsg.append("Erreur lors de la connexion à la machine distributrice.<br/>");
			e.printStackTrace();
		}
	}
	
	private double qteBreuvage(double proportion, double qteVoulue,Bouteille bouteille) throws QuantiteException{
		double qteProportionnelle = (proportion*qteVoulue) / 100;
		if(bouteille.getQte() - qteProportionnelle < 0){
			throw new QuantiteException("La bouteille "+bouteille.getNumeroPompe()+" manque de liquide.");
		}
		return qteProportionnelle;
	}
	

	private void detailRecette(HttpServletRequest request, HttpServletResponse response ) throws ServletException {
		try{
			int id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("recette", recetteDAO.obtenirObjet(id));
			this.getServletContext().getRequestDispatcher( "/detailRecette.jsp" ).forward( request, response );
		}
		catch(Exception e){
			erreurMsg.append("Erreur lors de l'obtention des détails d'une recette.<br/>");
			request.setAttribute("erreurMsg",erreurMsg.toString());
			erreurMsg = new StringBuffer();
		}
	}
	
	public void destroy() {
		try {
			socket.closeClient();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
