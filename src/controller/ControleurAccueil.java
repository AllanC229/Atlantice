package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.DAOAcces;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Adherent;
import model.Categorie;
import model.Utilisateur;
import java.util.HashMap;
import java.util.Map;

/**
 * Servlet implementation class ControleurAccueil
 */
@WebServlet("/ControleurAccueil")
public class ControleurAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControleurAccueil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession h = request.getSession();
		Utilisateur activeUser = (Utilisateur) h.getAttribute("activeUser");
		String direction =  (String)request.getParameter("test");
		HashMap<String, String> categoriesAdh = new HashMap<>();
		
		
		if ("ficheadmin".equals(request.getParameter("ficheadmin")) && !activeUser.getRole().equals("admin")) {  //tout ce bloc permet d'afficher seulement les adhérents qui appartiennt à la même catégorie que l'utilisateur actif, si celui ci n'est pas l'admin 
		
			Connection conn = null;
			PreparedStatement psAdhCateg = null;
			
			ArrayList<Adherent> adherents = new ArrayList<Adherent>(); //en arrivant d'accueil, instancie une liste vide d'adhérents
			DAOAcces dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "webadherents", "root", "");
			
				try {
					
					conn = dao.getConn();
					conn.setAutoCommit(false);

					String sql =
					    "SELECT a.numerolicence, a.nom, a.prenom, a.dernierelicenceactive, a.annee, " +
					    "a.tel1, a.tel2, a.adresse1, a.adresse2, a.mail1, a.mail2, a.commentaire, " +
					    "a.contact1, a.contact2, a.sexe, a.droitimage, " +
					    "c.idcategorie, c.nomcategorie " +
					    "FROM adherents a " +
					    "LEFT JOIN categorieadherent ca ON a.numerolicence = ca.numLic " +
					    "LEFT JOIN categoriesportive c ON ca.idcategorie = c.idcategorie " +
					    "WHERE ca.idcategorie IN (";
					
					for (String id : activeUser.getCategoriesUser().keySet()) {
						sql += "?, " ;
					}
					  sql = sql.substring(0, sql.length() -2);
					  sql+=  ") ORDER BY a.numerolicence;";
					  
					  psAdhCateg = conn.prepareStatement(sql);
					  
					  	int i = 1;
					  	
					for (Map.Entry<String, String> entry : activeUser.categoriesUser.entrySet()) {
							psAdhCateg.setString(i,  entry.getKey());
							i++;
						}
				

					
					ResultSet rsAdh = psAdhCateg.executeQuery();

					String currentLicence = null;
					//HashMap<String, String> categoriesAdh = null;

					while (rsAdh.next()) {

					    String licence = rsAdh.getString("numerolicence");

					    // Nouvel adhérent
					    
					    if (!licence.equals(currentLicence)) {

					        categoriesAdh = new HashMap<>();

					        adherents.add(new Adherent(
					            licence,
					            rsAdh.getString("nom"),
					            rsAdh.getString("prenom"),
					            rsAdh.getString("annee"),
					            rsAdh.getString("tel1"),
					            rsAdh.getString("tel2"),
					            rsAdh.getString("adresse1"),
					            rsAdh.getString("adresse2"),
					            rsAdh.getString("mail1"),
					            rsAdh.getString("mail2"),
					            rsAdh.getString("commentaire"),
					            rsAdh.getString("dernierelicenceactive"),
					            rsAdh.getString("contact1"),
					            rsAdh.getString("contact2"),
					            rsAdh.getString("sexe"),
					            rsAdh.getString("droitimage"),
					            categoriesAdh,
					            dao
					        ));

					        currentLicence = licence;
					    }

					    // Ajout d’une catégorie si elle existe
					    if (rsAdh.getString("idcategorie") != null) {
					        categoriesAdh.put(
					            rsAdh.getString("idcategorie"),
					            rsAdh.getString("nomcategorie")
					        );
					    }
					}
					
										
					
				}
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("adherents", adherents);
				dao.closeConnection();
				getServletContext().getRequestDispatcher("/Tableau").forward(request, response);
		}
		
			if ("ficheadmin".equals(request.getParameter("ficheadmin")) && activeUser.getRole().equals("admin")) {  //ce bloc sert à afficher tous les adhérents independamment de la catégorie de l'utilisateur actif si celui ci est l'admin 
			
			Connection conn = null;
			PreparedStatement psAdh = null;
			
			ArrayList<Adherent> adherents = new ArrayList<Adherent>();
			DAOAcces dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "webadherents", "root", "");
			
			try {
				
				conn = dao.getConn();
				conn.setAutoCommit(false);

				String sql =
				    "SELECT a.numerolicence, a.nom, a.prenom, a.dernierelicenceactive, a.annee, " +
				    "a.tel1, a.tel2, a.adresse1, a.adresse2, a.mail1, a.mail2, a.commentaire, " +
				    "a.contact1, a.contact2, a.sexe, a.droitimage, " +
				    "c.idcategorie, c.nomcategorie " +
				    "FROM adherents a " +
				    "LEFT JOIN categorieadherent ca ON a.numerolicence = ca.numLic " +
				    "LEFT JOIN categoriesportive c ON ca.idcategorie = c.idcategorie " +
				    "ORDER BY a.numerolicence";

				psAdh = conn.prepareStatement(sql);
				ResultSet rsAdh = psAdh.executeQuery();

				String currentLicence = null;
				

				while (rsAdh.next()) {

				    String licence = rsAdh.getString("numerolicence");

				    // Nouvel adhérent
				    if (!licence.equals(currentLicence)) {

				        categoriesAdh = new HashMap<>();

				        adherents.add(new Adherent(
				            licence,
				            rsAdh.getString("nom"),
				            rsAdh.getString("prenom"),
				            rsAdh.getString("annee"),
				            rsAdh.getString("tel1"),
				            rsAdh.getString("tel2"),
				            rsAdh.getString("adresse1"),
				            rsAdh.getString("adresse2"),
				            rsAdh.getString("mail1"),
				            rsAdh.getString("mail2"),
				            rsAdh.getString("commentaire"),
				            rsAdh.getString("dernierelicenceactive"),
				            rsAdh.getString("contact1"),
				            rsAdh.getString("contact2"),
				            rsAdh.getString("sexe"),
				            rsAdh.getString("droitimage"),
				            categoriesAdh,
				            dao
				        ));

				        currentLicence = licence;
				    }

				    // Ajout d’une catégorie si elle existe
				    if (rsAdh.getString("idcategorie") != null) {
				        categoriesAdh.put(
				            rsAdh.getString("idcategorie"),
				            rsAdh.getString("nomcategorie")
				        );
				    }
				}
			} 
			
			catch (SQLException e) {
				e.printStackTrace();
			} 
			
			request.setAttribute("adherents", adherents);
			dao.closeConnection();
			getServletContext().getRequestDispatcher("/Tableau").forward(request, response);
		}
		
		
		
		if("Catégories".equals(direction))
		{
			DAOAcces dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "webadherents", "root", "");
			ArrayList<Categorie> categories= new ArrayList<Categorie>();
			try {
				String req = "SELECT * FROM categorie";
				ResultSet rscat = dao.getStatement().executeQuery(req);
				while(rscat.next()) {
					categories.add(new Categorie(rscat.getString("categories"), rscat.getString("annee"),  dao));
				}
				request.setAttribute("categories", categories);
				
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			dao.closeConnection();
			getServletContext().getRequestDispatcher("/Categories").forward(request, response);
		}
		
		else if("Créer un adhérent".equals(direction))
		{
			getServletContext().getRequestDispatcher("/CreationAdherent").forward(request, response);
		}
		
		else if ("Valider".equals(request.getParameter("categorie"))) {
			h.setAttribute("cat", request.getParameterValues("categorie[]"));
			
			Connection conn = null;
			PreparedStatement psAdh = null;
			
			ArrayList<Adherent> adherents = new ArrayList<Adherent>(); //en arrivant d'accueil, instancie une liste vide d'adhérents
			DAOAcces dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "webadherents", "root", "");
			
			try {
			
			conn = dao.getConn(); 
			conn.setAutoCommit(false);
		    
			String[] categorie = request.getParameterValues("categorie[]"); 
			
			 //début : ajout du 17/03/2026 - pauline
			if (categorie == null || categorie.length == 0) { /* traite l'erreur où l'utilisateur coche rien et où il accède au site via l'url */
				request.getSession().setAttribute("erreur", "Veuillez sélectionner au moins une catégorie.");
				getServletContext().getRequestDispatcher("/Accueil").forward(request, response);
			    return; // Arrête l'exécution de doGet() ICI, retour à l'accueil, le code reprend au début
			} //fin 
			
			
			boolean flag = false;
			for (String categ : categorie) {
				
				if (categ.equals("Toutes")) {
					flag = true;
				}
			}
			String sql;
			
			if (flag == false) {
				sql= "select * from adherents join categorieadherent ON categorieadherent.numLic=adherents.numerolicence AND (";
		//obsolète, à refaire
				for (String categ : categorie) {
					
					sql += " idcategorie= ? OR" ;
					
				}
				
				sql = sql.substring(0, sql.length() - 3);
				sql += ");";	
				psAdh = conn.prepareStatement(sql);	 
				
				int i=1;
				for (String categ : categorie) {
					psAdh.setString(i,  categ);
					i++;
				}
				
			}
			
			else {	//GNEGNEuGNEU	
				
				/*sql= "SELECT a.numerolicence, a.nom, a.prenom, a.dernierelicenceactive, a.annee, " +
					    "a.tel1, a.tel2, a.adresse1, a.adresse2, a.mail1, a.mail2, a.commentaire, " +
					    "a.contact1, a.contact2, a.sexe, a.droitimage, " +
					    "c.idcategorie, cs.nomcategorie " +
					    "FROM adherents a JOIN categorieadherent c ON numLic = numerolicence JOIN categoriesportive cs USING (idcategorie) WHERE c.idcategorie = ";
				*/
				sql = "SELECT a.numerolicence, a.nom, a.prenom, a.dernierelicenceactive, a.annee, " +
						"a.tel1, a.tel2, a.adresse1, a.adresse2, a.mail1, a.mail2, a.commentaire, " +
						"a.contact1, a.contact2, a.sexe, c.nomcategorie, a.droitimage, c.idcategorie " +
						"FROM categoriesportive c " +
						"JOIN categorieadherent ca ON ca.idcategorie = c.idcategorie " +
						"JOIN adherents a ON ca.numLic = a.numerolicence " +
						"WHERE c.idcategorie IN(";
				
				for (String i : activeUser.getCategoriesUser().keySet()) {
					sql += "?, ";
				}
				sql = sql.substring(0, sql.length() -2);
				sql += ") ORDER BY a.numerolicence, c.idcategorie;";
				 
				psAdh = conn.prepareStatement(sql);
				 
				  	int i = 1 ;
				  	
			for (String categ : activeUser.getCategoriesUser().keySet()) {
					psAdh.setString(i,  categ);
						i++;
					}
			}
			
			ResultSet rsAdherent = psAdh.executeQuery();
			
			String currentlicence = null ;
				
				while (rsAdherent.next()) {
					
				
					 String licence = rsAdherent.getString("numerolicence");
					 
					if (!licence.equals(currentlicence)) {
						
						categoriesAdh = new HashMap<>();
						

			        adherents.add(new Adherent(
			            licence,
			            rsAdherent.getString("nom"),
			            rsAdherent.getString("prenom"),
			            rsAdherent.getString("annee"),
			            rsAdherent.getString("tel1"),
			            rsAdherent.getString("tel2"),
			            rsAdherent.getString("adresse1"),
			            rsAdherent.getString("adresse2"),
			            rsAdherent.getString("mail1"),
			            rsAdherent.getString("mail2"),
			            rsAdherent.getString("commentaire"),
			            rsAdherent.getString("dernierelicenceactive"),
			            rsAdherent.getString("contact1"),
			            rsAdherent.getString("contact2"),
			            rsAdherent.getString("sexe"),
			            rsAdherent.getString("droitimage"),
			            categoriesAdh,
			            dao
			        ));

			       currentlicence = licence;
			      
			       
			    // Ajout d’une catégorie si elle existe
				  /* if (rsAdherent.getString("idcategorie") != null) {
				        categoriesAdh.put(			        		
				            rsAdherent.getString("idcategorie"),
				            rsAdherent.getString("nomcategorie")
				        );
				   }*/
			    }		    			
			}				
		}	
			 		
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			dao.closeConnection();
			String arrive = "ContrAcc" ;  //pas ouf
			request.setAttribute("adherents", adherents);
			//response.sendRedirect("Tableau");
			getServletContext().getRequestDispatcher("/Tableau").forward(request, response);

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}