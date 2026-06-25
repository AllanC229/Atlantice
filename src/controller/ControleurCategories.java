package controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import connection.DAOAcces;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Categorie;
import model.Utilisateur;
import tool.ControleDeSaisie;


/**
 * Servlet implementation class ControleurCategories
 */
@WebServlet("/ControleurCategories")
public class ControleurCategories extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ControleurCategories() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession h = request.getSession(false);	//Charge la variable de session si elle existe (false)
		
		if (h == null) { //Si la session n'existe pas, renvoie vers la page de connexion
		    response.sendRedirect("/Connexion");
		    return;
		}
		
		Utilisateur activeUser = (Utilisateur) h.getAttribute("activeUser");
		
		/* UPDATE PAS FONCTIONNEL !!
		 * à revoir 
		 */
		
		String[] noms = request.getParameterValues("nom");
		String[] annees = request.getParameterValues("annee");
		
		// contrôle des caractères interdits
		if (noms != null && annees != null) {
		    for (String champ : noms) {
		        if (ControleDeSaisie.caractereInterdit(champ)) {
		        	// insérer la tentative d'injection dans les logs : 
	            	System.out.println("controle de saisie categ ok");
	            	try {
						activeUser.lastseen(activeUser.getIdConnexion(), " tentative insertion caractère interdit modifCateg champ nom;");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                // pas donner d'indice quant à la nature de l'erreur ?
	            	request.setAttribute("erreur", "Caractère interdit détecté");
	                getServletContext().getRequestDispatcher("/Accueil").forward(request, response);
	                return;
		        }
		    }
		    for (String champ : annees) {
		        if (ControleDeSaisie.caractereInterdit(champ)) {
		        	// insérer la tentative d'injection dans les logs : 
	            	try {
						activeUser.lastseen(activeUser.getIdConnexion(), " tentative insertion caractère interdit modifCateg champs annee;");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                // pas donner d'indice quant à la nature de l'erreur ?
	            	request.setAttribute("erreur", "Caractère interdit détecté");
	                getServletContext().getRequestDispatcher("/Accueil").forward(request, response);
	                return;
		        }
		    }
		}
            
    	HashMap<String, String> modifCateg = new HashMap<>();
		modifCateg.put(request.getParameter("nom"), request.getParameter("annee"));
		System.out.println(modifCateg);
			
		try {
			DAOAcces dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "webadherents", "root", "");
			
			//pour chaque categorie modifiée (nom et/ou annee) dans categorie, mettre à jour la table categorieannee et toutes les tables où il y a un  nom de categ
			for (HashMap.Entry<String, String> entry : modifCateg.entrySet()) {
				System.out.println(entry);
			
			String modifCategSQL = "UPDATE anneecategorie "
								+ "SET categories=?, annee=? "
								+ "WHERE;";

			/* "UPDATE anneecategorie, categoriesportive, categorieutilisateur "
								+ "SET `anneecategorie.categories`=?, anneecategorie.annee`=?, `categoriesportive.nomcategorie`=?, `categorieutilisateur.categorieUser`=?;";
			*/
			
			// Création d'un PreparedStatement
			PreparedStatement pstModifCateg = dao.getConn().prepareStatement(modifCategSQL);
			System.out.println("connexion BDD ok");
			
			String nomAnneeCateg = entry.getKey();
			String annee = entry.getValue();
		//	String nomCategSport = request.getParameter("nom") ;
		//	String nomCategUser = request.getParameter("nom");
			
			pstModifCateg.setString (1, nomAnneeCateg); 
			pstModifCateg.setString (2, annee);
	//		pstModifCateg.setString (3, nomCategSport); 
		//	pstModifCateg.setString (4, nomCategUser); 

			pstModifCateg.executeUpdate();
			System.out.println("requête exécutée : " + pstModifCateg);
			
			}
			dao.closeConnection();

		} catch (SQLException e) {
			System.out.println("Problème SQL modif categorie");
			e.printStackTrace();
		}
		//response.sendRedirect("ControleurAccueil?categories=true"); // rafraichir vue Categorie à jour 
		response.sendRedirect(request.getContextPath() + "/Accueil");
	}
   // }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
