package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import connection.DAOAcces;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Adherent;
import model.Utilisateur;

/**
 * Servlet implementation class ControleurRechercheAdherent
 */
@WebServlet("/ControleurRechercheAdherent")
public class ControleurRechercheAdherent extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ControleurRechercheAdherent() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession h = request.getSession(false);
		
		if (h == null) { //Si la session n'existe pas, renvie vers la page de connexion
		    response.sendRedirect("/Connexion");
		    return;
		}
		
		Utilisateur activeUser = (Utilisateur) h.getAttribute("activeUser");
		DAOAcces dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "webadherents", "root", "");
		Connection conn = null;
		PreparedStatement rechAdh = null;
		String numLic = request.getParameter("numLic") ; //on pensera à ajouter une fonction trim()
		String nom = request.getParameter("nom");
		
	/*	if (numLic.equals("")) {
			numLic = null;
		}
		if (nom.equals("")) {
			nom = null;
		} */
		
		String sql;
		ArrayList<Adherent> adherents = new ArrayList<Adherent>();
		HashMap<String, String> categoriesAdh = new HashMap<>();
		
		
		try {
			conn = dao.getConn(); 
			conn.setAutoCommit(false);
			
			if (!numLic.equals("")) {
				sql = "SELECT * FROM adherents JOIN categorieadherent ON categorieadherent.numLic = adherents.numerolicence JOIN categoriesportive ON categorieadherent.idcategorie = categoriesportive.idcategorie WHERE adherents.numerolicence = ? ;" ;
				rechAdh = conn.prepareStatement(sql);
				rechAdh.setString(1,  numLic);				
				}
			
			else {
				sql = "SELECT * FROM adherents JOIN categorieadherent ON categorieadherent.numLic = adherents.numerolicence JOIN categoriesportive ON categorieadherent.idcategorie = categoriesportive.idcategorie WHERE adherents.nom = ? ;" ;
				rechAdh = conn.prepareStatement(sql);
				rechAdh.setString(1,  nom);			
				}
			
			
			
			ResultSet rsAdherent = rechAdh.executeQuery();
			
			String currentLicence = null;
			
			while (rsAdherent.next()) { //remplis la liste vide d'adhérents
				
				 String licence = rsAdherent.getString("numerolicence");

				    // Nouvel adhérent
				    if (!licence.equals(currentLicence)) {

				        categoriesAdh = new HashMap<>();
				        
				adherents.add(new Adherent(rsAdherent.getString("numerolicence"), rsAdherent.getString("nom"), rsAdherent.getString("prenom"), rsAdherent.getString("annee")
						, rsAdherent.getString("tel1"), rsAdherent.getString("tel2"), rsAdherent.getString("adresse1"), rsAdherent.getString("adresse2")
						, rsAdherent.getString("mail1"), rsAdherent.getString("mail2"), rsAdherent.getString("commentaire"), rsAdherent.getString("dernierelicenceactive"),
						rsAdherent.getString("contact1"), rsAdherent.getString("contact2"), rsAdherent.getString("sexe"), rsAdherent.getString("droitimage"), categoriesAdh));
			}	
				    
				    currentLicence = licence;
			// Ajout d’une catégorie si elle existe
		    if (rsAdherent.getString("idcategorie") != null) {
		        categoriesAdh.put(
		            rsAdherent.getString("idcategorie"),
		            rsAdherent.getString("nomcategorie")
		        );
		    }
			}
		}
		catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
		}

		dao.closeConnection();
		
		request.setAttribute("adherents", adherents);
		//response.sendRedirect("Tableau");
		getServletContext().getRequestDispatcher("/Tableau").forward(request, response);
}
}