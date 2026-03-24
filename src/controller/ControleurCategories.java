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
	    HttpSession h = request.getSession();
	    

		//if(equals(request.getParameter("modifCategories"))) { //methode POST??
		    System.out.println("controleurCategories instancié depuis le formulaire");
			
			try {
				DAOAcces dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "webadherents", "root", "");
				
				HashMap<String, String> modifCateg = new HashMap<>();
				modifCateg.put(request.getParameter("nom"), request.getParameter("annee"));
				System.out.println(modifCateg);	
				
				//pour chaque categorie modifiée (nom et/ou annee) dans categorie, mettre à jour la table categorieannee et toutes les tables où il y a un  nom de categ
				for (HashMap.Entry<String, String> entry : modifCateg.entrySet()) {
					System.out.println(entry);
				
				String modifCategSQL = "UPDATE anneecategorie "
									+ "SET `categories`=?, annee`=?;";

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
			response.sendRedirect("ControleurAccueil?categories=true"); // rafraichir vue Categorie à jour 
			
			
			
			
			
			
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
