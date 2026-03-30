package controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import connection.DAOAcces;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Categorie;

/**
 * Servlet implementation class ControleurCategories
 */
@WebServlet("/ControleurCritere")
public class ControleurCritere extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ControleurCritere() {
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

		String direction =  (String)request.getParameter("direction");		//Sert à savoir sur quel bouton on a cliqué sur la page accueil (catégories, accéder à mon profil, créer un adhérent)


	    
		if("Valider les modifications".equals(direction)) { //methode POST??
		    System.out.println("bouton modifié critère cliqué");
			
			ArrayList<String> modifNomCritere = (ArrayList<String>) request.getAttribute("critères");	
		   	System.out.println("modifNomCritere : " + modifNomCritere);
			
		//	int index = modifNomCritere.indexOf("Endurance");
		//	System.out.println("index de Endurance :" + index);
			
			//pour chaque nomcritere modifié, mettre à jour la table critere
			modifNomCritere.forEach( (nomCritere) -> {
				try {
					DAOAcces dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "webadherents", "root", "");
					
					String modifCritereSQL = "UPDATE criteres "
											+ "SET nomcritere=? "
											+ "WHERE idcritere = ?;";
				
					// Création d'un PreparedStatement
					PreparedStatement pstModifCritere = dao.getConn().prepareStatement(modifCritereSQL);
					System.out.println("connexion BDD ok");
					
					String nomcritere = request.getParameter("nomcritere");
					int idCritere = modifNomCritere.indexOf(nomcritere) + 1;
					System.out.println("idCritere:" + idCritere);
					
					pstModifCritere.setString (1, nomcritere); 
					pstModifCritere.setInt (2, idCritere);
	 
	
					pstModifCritere.executeUpdate();
					System.out.println("requête exécutée : " + pstModifCritere);
								
				dao.closeConnection();
	
				} catch (SQLException e) {
				System.out.println("Problème SQL modif critere");
				e.printStackTrace();
				}
			});
			response.sendRedirect("ControleurAccueil?critere=critere"); // rafraichir vue Critère à jour 
		}
		
		if (equals(request.getParameter("supprCritere"))) {
		    System.out.println("bouton confirmer suppression critère cliqué");

			
			
			
			
			
			
		}
			
			
			
			
			
			
		}
	//}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}