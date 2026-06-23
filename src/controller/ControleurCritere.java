package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

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
		
		Utilisateur activeUser = (Utilisateur) h.getAttribute("activeUser");
		
		//Instanciation
		DAOAcces dao = null;
		Connection conn = null;

		String direction =  (String)request.getParameter("direction");	//Sert à savoir sur quel bouton on a cliqué sur la page accueil (catégories, accéder à mon profil, créer un adhérent)
		HashMap<Integer, String> nomCritere = (HashMap<Integer, String>) h.getAttribute("nomCritere");

		if("Valider les modifications".equals(direction)) { 
		    System.out.println("bouton modifié critère cliqué");
			
			//pour chaque nomcritere modifié, mettre à jour la table critere
			for (HashMap.Entry<Integer, String> entry : nomCritere.entrySet() ) {
				if (!entry.getValue().equals(request.getParameter(entry.getValue()))){
					
					if (ControleDeSaisie.caractereInterdit(request.getParameter(entry.getValue()))) {
						// insérer la tentative d'injection dans les logs : 
		            	try {
							activeUser.lastseen(activeUser.getIdConnexion(), " tentative insertion caractère interdit sur modif nomcritere;");
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		                // pas donner d'indice quant à la nature de l'erreur (request.setAttribute("erreur", "Caractère interdit détecté (< > \")");)
		                getServletContext().getRequestDispatcher("/Critere").forward(request, response);
		                return;
		            }
					
					
					try {
						dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "webadherents", "root", "");
						
						conn = dao.getConn();
						
						activeUser.lastseen(activeUser.getIdConnexion(), " nom de critère modifié "+ request.getParameter(entry.getValue()) +" dans la BDD;");
						
					    // désactivation du mode de validation automatique (auto-commit) => gestion de la transaction manuelle
					    conn.setAutoCommit(false);
						
						String modifCritereSQL = "UPDATE criteres "
												+ "SET nomcritere=? "
												+ "WHERE idcritere = ?;"; 
					
						// Création d'un PreparedStatement
						PreparedStatement pstModifCritere = conn.prepareStatement(modifCritereSQL);
						System.out.println("connexion BDD ok");
						
						String nouveauNomCritere = request.getParameter(entry.getValue()); 	
						System.out.println("nom critere recup:" + request.getParameter(entry.getValue()));
						int idCritere = entry.getKey();
						System.out.println("idCritere:" + idCritere);
						
						pstModifCritere.setString (1, nouveauNomCritere); 
						pstModifCritere.setInt (2, idCritere);
		 
						pstModifCritere.executeUpdate();
						System.out.println("requête exécutée : " + pstModifCritere);
			          	
						conn.commit();

					} catch(SQLException e) {
						System.out.println("Probleme SQL creationCritere !!");
						if (conn != null) { //Si la connection n'est pas nulle, retour en arrière = annule la transaction
							try {
								conn.rollback();
								// insérer la tentative d'injection dans les logs : 
								activeUser.lastseen(activeUser.getIdConnexion(), " tentative modif critère "+ request.getParameter(entry.getValue()) +" dans la BDD;");
								System.out.println("Transaction annulée : rollback effectué");
							} catch (SQLException ex) {
								System.out.println("Connexion ok mais probleme SQL creationCritere !!");
								ex.printStackTrace();
							}
						}
						e.printStackTrace();
					} finally {
						if (dao != null) { // vérification nécessaire : si la construction a échoué avant la ligne d'affectation, dao vaut encore null
					        dao.closeConnection();
					    }
						dao.closeConnection();
					}
				}
			}
			response.sendRedirect("ControleurAccueil?critere=critere"); // rafraichir vue Critère à jour 
		} // fin du bloc modification du nom du critere
		
		if ("Supprimer les critères sélectionnés ?".equals(direction)) {
		    System.out.println("bouton confirmer suppression critère cliqué");
		    
		    //récupérer les valeurs des ckbox cochée(s) 
			String[] idsSupprimes = request.getParameterValues("supprCritere");
			System.out.println("ckbox cochées:" + Arrays.toString(idsSupprimes));

			if (idsSupprimes != null) { //vérif pertinente ou pas? car si on clique sur ces boutons supprimés apriori ça peut pas être null
				//supprimer le ou les critères et toutes les occurences les concernant dans critereadh 
				for (String idcritere : idsSupprimes){
					try {
						dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "webadherents", "root", "");
						
						conn = dao.getConn();
						
						activeUser.lastseen(activeUser.getIdConnexion(), " critère supprimé "+ request.getParameterValues("supprCritere") +" dans la BDD;");
						
					    // désactivation du mode de validation automatique (auto-commit) => gestion de la transaction manuelle
					    conn.setAutoCommit(false);
						
						//suppression dans critereadheretn
						String deleteCritereAdhSQL = "DELETE FROM critereadherent " 
												+ "WHERE idcritere = ?";
						
						// Création d'un PreparedStatement
						PreparedStatement pstSupprCritereAdh = conn.prepareStatement(deleteCritereAdhSQL);
						
						
						int idCritere= Integer.parseInt(idcritere);
						System.out.println("idCritere:" + idCritere);
						
						pstSupprCritereAdh.setInt (1, idCritere);
		 
						pstSupprCritereAdh.executeUpdate();
						System.out.println("requête exécutée dans critereadh : " + pstSupprCritereAdh);
						
						//suppression dans criteres
						String deleteCritereSQL = "DELETE FROM criteres " 
												+ "WHERE idcritere = ?";
						
						PreparedStatement pstSupprCritere = conn.prepareStatement(deleteCritereSQL);
								
						pstSupprCritere.setInt (1, idCritere);
				 
						pstSupprCritere.executeUpdate();
						System.out.println("requête exécutée dans critere : " + pstSupprCritere);
	
									
					conn.commit();
						
					} catch(SQLException e) {
						System.out.println("Probleme SQL supprCritere !!");
						if (conn != null) { //Si la connection n'est pas nulle, retour en arrière = annule la transaction
							try {
								conn.rollback();
								// insérer la tentative d'injection dans les logs : 
								activeUser.lastseen(activeUser.getIdConnexion(), " tentative de suppression de critère "+ request.getParameterValues("supprCritere") +" dans la BDD;");
								System.out.println("Transaction annulée : rollback effectué");
							} catch (SQLException ex) {
								System.out.println("Connexion ok mais probleme SQL supprCritere !!");
								ex.printStackTrace();
							}
						}
						e.printStackTrace();
					} finally {
						if (dao != null) { // vérification nécessaire : si la construction a échoué avant la ligne d'affectation, dao vaut encore null
					        dao.closeConnection();
					    }
						dao.closeConnection();
					}
				} // fin for each 
			} // fin du if
			response.sendRedirect("ControleurAccueil?critere=critere"); // rafraichir vue Critère à jour 
		}//fin bloc suppression de critère-s
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}