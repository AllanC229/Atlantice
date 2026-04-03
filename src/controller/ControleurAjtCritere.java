package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import connection.DAOAcces;

/**
 * Servlet implementation class ControleurAjtCategorie
 */
@WebServlet("/ControleurAjtCritere")
public class ControleurAjtCritere extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControleurAjtCritere() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { //codé pendant le mois de mars 2026 par pauline
		
	    HttpSession h = request.getSession(false);
	    
	    if (h == null || h.getAttribute("activeUser") == null) { //Si la session n'existe pas, renvoie vers la page de connexion
		    response.sendRedirect("/Connexion");
		    return;
		}
		
	//	PrintWriter out=response.getWriter();
		
		String nomcritere = (request.getParameter("nomCritere"));
	    
		if(nomcritere != null && !nomcritere.trim().isEmpty()) { 
			
		    Connection conn = null;
		    String insertCritere = null;
		    PreparedStatement pstInsertCritere = null;
		    
		    String insertCritereAdh = null;
		    PreparedStatement pstInsertCritAdh = null;

			try { 
			
				DAOAcces dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "webadherents", "root", "");
			
			    conn = dao.getConn();
				
			    // désactivation du mode de validation automatique (auto-commit) => gestion de la transaction manuelle
			    conn.setAutoCommit(false);
			    
				// Insertion du nouveau critere dans la table critere
				insertCritere = "INSERT INTO criteres(idcritere, nomcritere) "
										+ "VALUES (DEFAULT, ?);";	
				pstInsertCritere = conn.prepareStatement(insertCritere);
				pstInsertCritere.setString (1, nomcritere);
				pstInsertCritere.executeUpdate();
				System.out.println(pstInsertCritere);
				
				//Insertion de ce nouveau critere pour tout les adh dans critereadherent à 0
				insertCritereAdh = "INSERT INTO critereadherent (numerolicence, idcritere, valcritere) "
								+ "SELECT numerolicence, LAST_INSERT_ID(), ? "
								+ "FROM adherents;";
				
				pstInsertCritAdh = conn.prepareStatement(insertCritereAdh);
				pstInsertCritAdh.setInt (1, 0);
				pstInsertCritAdh.executeUpdate();
				System.out.println(pstInsertCritAdh);				
				
				conn.commit();
				dao.closeConnection(); // à revoir : le mettre aprés le catch dans un finally?
	           
			} catch(SQLException e) {
				System.out.println("Probleme SQL creationCritere !!");
				if (conn != null) { //Si la connection n'est pas nulle, retour en arrière = annule la transaction
					try {
						conn.rollback();
						System.out.println("Transaction annulée : rollback effectué");
					} catch (SQLException ex) {
						System.out.println("Connexion ok mais probleme SQL creationCritere !!");
						ex.printStackTrace();
					}
				}
				e.printStackTrace();
			}
			response.sendRedirect("ControleurAccueil?critere=critere");
		    		
        } else {  
        	//PrintWriter out=response.getWriter("Veuillez remplir le champ !");
        	request.getRequestDispatcher("/CreationCritere").forward(request, response);
        	
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