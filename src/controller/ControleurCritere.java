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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { //codé pendant le mois de mars 2026 par pauline
		HttpSession h = request.getSession(false);	//Charge la variable de session si elle existe (false)
		if (h == null) { //Si la session n'existe pas, renvoie vers la page de connexion
		    response.sendRedirect("/Connexion");
		    return;
		}

		String direction =  (String)request.getParameter("direction");	//Sert à savoir sur quel bouton on a cliqué sur la page accueil (catégories, accéder à mon profil, créer un adhérent)
		HashMap<Integer, String> nomCritere = (HashMap<Integer, String>) h.getAttribute("nomCritere");

		
		if("Valider les modifications".equals(direction)) { //methode POST??
		    System.out.println("bouton modifié critère cliqué");
			
			//pour chaque nomcritere modifié, mettre à jour la table critere
			for (HashMap.Entry<Integer, String> entry : nomCritere.entrySet() ) {
				if (!entry.getValue().equals(request.getParameter(entry.getValue()))){
					try {
						DAOAcces dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "webadherents", "root", "");
						
						String modifCritereSQL = "UPDATE criteres "
												+ "SET nomcritere=? "
												+ "WHERE idcritere = ?;"; 
					
						// Création d'un PreparedStatement
						PreparedStatement pstModifCritere = dao.getConn().prepareStatement(modifCritereSQL);
						System.out.println("connexion BDD ok");
						
						String nouveauNomCritere = request.getParameter(entry.getValue()); 	
						System.out.println("nom critere recup:" + request.getParameter(entry.getValue()));
						int idCritere = entry.getKey();
						System.out.println("idCritere:" + idCritere);
						
						pstModifCritere.setString (1, nouveauNomCritere); 
						pstModifCritere.setInt (2, idCritere);
		 
		
						pstModifCritere.executeUpdate();
						System.out.println("requête exécutée : " + pstModifCritere);
									
					dao.closeConnection();
		
					} catch (SQLException e) {
					System.out.println("Problème SQL modif critere");
					e.printStackTrace();
					}
				}
			}
			response.sendRedirect("ControleurAccueil?critere=critere"); // rafraichir vue Critère à jour 
		}
		
		if ("Supprimer les critères sélectionnés ?".equals(direction)) {
		    System.out.println("bouton confirmer suppression critère cliqué");
		    
		    //récupérer les valeurs des ckbox cochée(s) supprimer le critère et toutes les occurences le concernant dans critereadh : transaction
			String[] idsSupprimes = request.getParameterValues("supprCritere");
			System.out.println("ckbox cochées:" + Arrays.toString(idsSupprimes));
		    
			if (idsSupprimes != null) { //vérif pertinente ou pas? car si on clique sur ces boutons supprimés apriori ça peut pas être null
				try {
					DAOAcces dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "webadherents", "root", "");
					//pour chaque idcritere faire une requete !!!!!
					String deleteCritereSQL = "DELETE FROM critereadherent " //OU FROM criteres (ON CASCADE)
											+ "WHERE idcritere = ?";
					
					// Création d'un PreparedStatement
					PreparedStatement pstSupprCritere = dao.getConn().prepareStatement(deleteCritereSQL);
					System.out.println("connexion BDD ok");
					
		//			int idCritere = entry.getKey();
			//		System.out.println("idCritere:" + idCritere);
					
			//		pstSupprCritere.setInt (1, idCritere);
	 
	
					pstSupprCritere.executeUpdate();
					System.out.println("requête exécutée : " + pstSupprCritere);
								
				dao.closeConnection();
					
					
				} catch (SQLException e) {
					System.out.println("Problème SQL supprimer critere");
					e.printStackTrace();
				}
				
				
			}
			
		    
	
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