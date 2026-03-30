package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession h = request.getSession(false);	//Charge la variable de session si elle existe (false)
		
		if (h == null) { //Si la session n'existe pas, renvoie vers la page de connexion
		    response.sendRedirect("/Connexion");
		    return;
		}
		
		Utilisateur activeUser = (Utilisateur) h.getAttribute("activeUser");
		PrintWriter out=response.getWriter();
		ArrayList<String> criteres = (ArrayList<String>) request.getAttribute("critères");	
		System.out.println("Vue critere arraylist : " + criteres);

		
		String r = "<!Doctype html><html><head><meta charset=\"utf-8\"/>"
				+ " <link href=\"licence.css\" rel=\"stylesheet\">"
				+ " </head><body><h1 align=center>Critères : </h1></br>"
				+ "	<div align=center><form action=\"ControleurCritere\" method=GET>" //méthode POST => UPDATE en BDD
				+ " <table border>"
				+ " <tr><th>Nom</th></tr>";
		
				// boucle pour afficher chaque nom de critere 
				Iterator<String> iteratorCritere = criteres.iterator();
				while(iteratorCritere.hasNext()) {
					// ajouter methode pour avoir la première des critères en MAJ
					r += "<tr><td><input type=\"text\" name=\"nomcritere\" value="+ iteratorCritere.next() +"></td>"
							+ "<td><input type=\"checkbox\" class='critere' value='' disabled ></td></tr>";
				}
				
				if ((activeUser.getRole().equals("admin") || activeUser.getRole().equals("modif"))) {
					r += "</table><br>"
						+ "<input type=\"submit\" name=\"direction\" value=\"Valider les modifications\"></form><br>";
					
					//Suppression d'un ou plusieurs critères : active les checkbox 
					r += "<input type=\"submit\" name=\"supprCritere\" onclick=\"activerCheckbox()\" value=\"Supprimer des critères\">"
						+ "<tr><td> <div id='divSupprimer' style='display:none;'>"
						+ "		<form action='ControleurCritere' method=GET>"  //méthode POST => DELETE en BDD
						+ "			<input type='submit' name='supprimerCritere' onclick='confirmSuppr()' value='Supprimer les critères sélectionnés ?'>"
						+ "</form></div></td></tr><br>"
						+ "<script>"
						+ "		function activerCheckbox() {" //fonction qui active une checkbox pour chaque critere (class critere) et display la div contenant le bouton pour supprimer
						+ "  		document.querySelectorAll('.critere').forEach(function(checkbox) {"
						+ "        	checkbox.disabled = false;});"
						+ "			document.getElementById('divSupprimer').style.display ='block';"
						+ "		}"
						+ " 	function confirmSuppr() {" //si le bouton 'supprimerCritere' est cliqué affiche une fenêtre de confirmation, si OK -> soumission du formulaire vers ControleurCritere
						+ "			if (window.confirm('Confirmer la suppression ?')){"
						+ "				document.getElementById('supprimerCritere').submit();"
						+ "			}"
						+ "		}"  
						+ "</script>"
						+ "<br>"	
					
					//Création de critères
						+ "<tr><td><form action=\"CreationCritere\" method=GET>"
						+ "<input type=\"submit\" name=\"creationCritere\" value=\"Créer un critère\"></td></tr></form><br>";
				
					r += "<div><form action=\"Accueil\" name=\"retouraccueil\" value=\"accueil\" method=\"GET\">"
						+ "<input type=\"submit\" name=\"retouraccueil\" value=\"Retour à l'accueil\"> </form>"
						+ "</div></body></html>";					
							
				}
				
				
			response.getWriter().append(r);
			ArrayList<String> criteresRequest = (ArrayList<String>) iteratorCritere;	
			System.out.println("Vue critere request : " + criteresRequest);

		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
}

