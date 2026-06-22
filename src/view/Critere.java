package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Categorie;
import model.Utilisateur;

/**
 * Servlet implementation class Categories
 */
@WebServlet("/Critere")
public class Critere extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Critere() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { //codé pendant le mois de mars 2026 par pauline
		
		HttpSession h = request.getSession(false);	//Charge la variable de session si elle existe (false)
		
		if (h == null) { //Si la session n'existe pas, renvoie vers la page de connexion
		    response.sendRedirect("/Connexion");
		    return;
		}
		
		Utilisateur activeUser = (Utilisateur) h.getAttribute("activeUser");
		PrintWriter out=response.getWriter();
		//HashMap : clé = idcritere valeur=nomcritere
		HashMap<Integer, String> nomCritere = (HashMap<Integer, String>) h.getAttribute("nomCritere");
		System.out.println("Vue critere HashMap : " + nomCritere);

		
		out.println("<html><head><meta charset='utf-8'/>"
				+ " <link href='licence.css' rel='stylesheet'>"
				+ " </head>");
				
		out.println(Header.afficherEntete(activeUser));
			
		out.println("<body><h1 align=center>Critères : </h1></br>"
				+ "	<div class='formulaire-ficheadmin'>"
				+ "		<form action='ControleurCritere' method=POST>"
				+ " 	<table>"
				+ " 	<tr><th>Nom</th></tr>");
		
				// boucle pour afficher chaque nom de critere 
				for (HashMap.Entry<Integer, String> entry : nomCritere.entrySet()) {
					
					out.println("<tr><td><input type='text' name='"+ entry.getValue() + "' value='" + entry.getValue() + "'></td>"
					   + "<td><input type='checkbox' name='supprCritere' value=" + entry.getKey() + " class='critere' disabled ></td></tr>");
				}
				out.println("</table><br>");
				
				if ((activeUser.getRole().equals("admin") || activeUser.getRole().equals("modif"))) {
					out.println("<input type='submit' name='direction' value='Valider les modifications'><br><br>"
							+ "</form>");
					
					//Suppression d'un ou plusieurs critères : active les checkbox et la div qui contient le bouton d'envoi
					out.println("<form action='ControleurCritere' method=POST>"
						+ "<input type='button' name='supprCritere' onclick='activerCheckbox()' value='Supprimer des critères'>"
						+ "	<div id='divSupprimer' style='display:none;'><br>" 
					    + "		<input type='submit' id='supprimerCritere' name='direction' onclick='confirmSuppr()' value='Supprimer les critères sélectionnés ?'>"
						+ "	</div>"
						+ "</form>"
						+ "<br>"   
						+ "<script>"
						+ "		function activerCheckbox() {" //fonction qui active une checkbox pour chaque critere (class critere) et display la div contenant le bouton pour supprimer
						+ "  		document.querySelectorAll('.critere').forEach(function(checkbox) {"
						+ "        	checkbox.disabled = false;});"
						+ "			document.getElementById('divSupprimer').style.display ='block';"
						+ "		}"
						+ " 	function confirmSuppr() {" //si le bouton 'supprimerCritere' est cliqué affiche une fenêtre de confirmation, si OK -> soumission du formulaire vers ControleurCritere
						+ "			if (window.confirm('Êtes-vous sûr-e de vouloir supprimer ce(s) critère(s) ?')){"
						+ "				document.getElementById('supprimerCritere').submit();"
						+ "			}"
						+ "		}"  
						+ "</script>"
						+ "<br>"	

						//Création de critères
						+ "<tr><td><form action='CreationCritere' method=GET>" 
						+ "<input type='submit' name='creationCritere' value='Créer un critère'></td></tr></form><br>");
				
					out.println("</body></html>");					
							
				}
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
}

