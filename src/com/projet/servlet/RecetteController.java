package com.projet.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.projet.dao.BouteilleDAO;
import com.projet.dao.DAOException;
import com.projet.dao.RecetteDAO;
import com.projet.dao.DAOManager;
import com.projet.dao.DAOManager.Table;
import com.projet.beans.Bouteille;
import com.projet.beans.Recette;
import java.util.HashMap;

@WebServlet("/recette")
@MultipartConfig
public class RecetteController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private DAOManager dao;
	private BouteilleDAO bouteilleDAO;
	private RecetteDAO recetteDAO;
	private final static String UPLOAD ="/opt/tomcat8/webapps/ROOT/WEB-INF/upload/";
	
	//TODO: Rendre dynamique
	//private final static String UPLOAD ="/home/daesolus/Dropbox/usherbrooke/GenieInformatique/S3/Projet/Projet3/serveur/WebContent/img/upload";
	private StringBuffer erreurMsg;
	
	public void init() throws ServletException {
		dao = DAOManager.getInstance();
		erreurMsg = new StringBuffer();
		try {
			bouteilleDAO = (BouteilleDAO)dao.getDAO(Table.BOUTEILLE);
			recetteDAO = (RecetteDAO)dao.getDAO(Table.RECETTE);
		}
		catch (DAOException e) {
			erreurMsg.append(e.getMessage()+"<br/>");
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		String action = request.getParameter("action");
		String direction = "";
		try {
			if(action == null){	
				direction = "/recette.jsp";
				request.setAttribute("listeBouteille", bouteilleDAO.lister());
				
			}
			else if (action.equals("liste")){
				 direction = "/listeRecette.jsp";
				 request.setAttribute("listeRecette", recetteDAO.lister());	 		
				 
			}
			else if(action.equals("modifier")){
				direction = "/modifierRecette.jsp";
				int id = Integer.parseInt(request.getParameter("id"));
				request.setAttribute("recette", recetteDAO.obtenirObjet(id));
			}
		}
		catch(DAOException e){
			erreurMsg.append(e.getMessage()+"<br/>");
		}
		request.setAttribute("erreurMsg", erreurMsg.toString());
		erreurMsg = new StringBuffer();
		this.getServletContext().getRequestDispatcher(direction).forward( request, response );	
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		erreurMsg = new StringBuffer();
		String action  = request.getParameter("action");
		String direction = "";
		try {
			if(action.equals("ajouter")){
				ajouterRecette(request,response);
			}
			else if(action.equals("modifier")){
				direction = "?action=liste";
				modifierRecette(request,response);
			}
			else if(action.equals("supprimerBouteilleRecette")){
				direction = "?action=liste";
				supprimerBouteilleRecette(request,response);
			}
			else if(action.equals("supprimer")){
				direction = "?action=liste";
				supprimerRecette(request,response);
			}
		}
		catch(IOException e){
			erreurMsg.append("Erreur lors de l'upload de la photo.<br/>");
		}
		catch(DAOException e){
			erreurMsg.append(e.getMessage()+"<br/>");
		}
		response.sendRedirect(request.getRequestURI()+direction);
	}
	
	private void ajouterRecette(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String nomRecette = request.getParameter("txtNomRecette");
		String bouteilleSelection [] = request.getParameterValues("chkBouteille");	

		Part fichierPart = request.getPart("photo");
		String photoUrl = uploadPhoto(fichierPart);
		
		if(nomRecette != "" && bouteilleSelection != null) {
			
			HashMap<Bouteille,Double> mapRecette = new HashMap<Bouteille,Double>();			
			for(String id : bouteilleSelection) {
				Bouteille bouteille = new Bouteille();
				bouteille.setId(Integer.parseInt(id));
				double proportion = Double.parseDouble(request.getParameter("txtProportion_"+id));
				mapRecette.put(bouteille,proportion);	
			}
			
			Recette recette = new Recette(nomRecette,photoUrl,mapRecette);
			recetteDAO.creer(recette);
		}
	}
	private void supprimerBouteilleRecette(HttpServletRequest request, HttpServletResponse response){
		String chaineId = request.getParameter("bouteilleRecette");
		String IdRecette = request.getParameter("recetteId");
		if(chaineId != null && !chaineId.equals("")) {
			recetteDAO.supprimerBouteille(Integer.parseInt(chaineId),Integer.parseInt(IdRecette));
		}
	}
	
	private void supprimerRecette(HttpServletRequest request, HttpServletResponse response){
		String chaineId = request.getParameter("hidRecette");
		if(chaineId != null && !chaineId.equals("")) {
			recetteDAO.supprimer(Integer.parseInt(chaineId));
		}
	}
	
	private void modifierRecette(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String nomRecette = request.getParameter("txtNom");
		int recetteId = Integer.parseInt(request.getParameter("recetteId"));
		String listeProportion [] = request.getParameterValues("bouteillePercent");
		String listeBouteilleId [] = request.getParameterValues("hidIdBouteilleRecette");
		Part fichierPart = request.getPart("photo");
		String photoInBD = request.getParameter("nomFichier");		
		String photoUrl = uploadPhoto(fichierPart);
		
		if(photoUrl.equals("")){
			photoUrl = photoInBD;
		}
		
		double proportion;
		int idBouteille;
		Recette recette = new Recette(recetteId,nomRecette,photoUrl);
		recetteDAO.modifier(recette);
		
		
		
		for(int i = 0;i<listeBouteilleId.length;i++){
			proportion = Double.parseDouble(listeProportion[i]);
			idBouteille = Integer.parseInt(listeBouteilleId[i]);
			recetteDAO.modifierBouteilleRecette(idBouteille, recetteId, proportion);
		}
	}
	
	private String uploadPhoto(Part photo) throws IOException{
		try{
			String photoUrl = photo.getSubmittedFileName();
			if(!photoUrl.equals("")){
				File fichierImage = new File(UPLOAD,photoUrl);
				
				try (InputStream input = photo.getInputStream()) {
				    Files.copy(input, fichierImage.toPath(), StandardCopyOption.REPLACE_EXISTING);
				}
			}
			return photoUrl;
		}
		catch(DirectoryNotEmptyException e){
			return "";
		}
	}
}
