package controller;

import java.io.IOException;
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
 * Servlet implementation class ControleurFiche
 */
@WebServlet("/ControleurTableau")
public class ControleurTableau extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ControleurTableau() {
        // TODO Auto-generated constructor stub
    }
    
	 /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    
		HttpSession h = request.getSession();
		Utilisateur activeUser = (Utilisateur) h.getAttribute("activeUser");
		String role = activeUser.getRole();
		
		if (request.getParameter("sportif")!=null) {
			
			//à enlever ? response.addHeader("numLic", (String)request.getAttribute("numLic"));
			String numLic = request.getParameter("numLic");
			System.out.println("getParameter numLic : " + numLic);
			
			if (numLic == null) {
			    numLic = (String) h.getAttribute("numLic");
			    System.out.println("session numLic : " + numLic);
			    
			} else {
			    h.setAttribute("numLic", numLic);
			}
			System.out.println("numLic final : " + numLic);
			
			//HashMap : clé = nomcritere valeur=valcritere
			HashMap<String, Integer> criteres = new HashMap() ;
			
			try {
				DAOAcces dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "webadherents", "root", "");

				String critereSQL = "SELECT valcritere, nomcritere "
								+ "FROM critereadherent JOIN criteres "
								+ "ON critereadherent.idCritere = criteres.idcritere "
								+ "WHERE numerolicence=? ;";
				
				// Création d'une requête préparée
				PreparedStatement pstCritere = dao.getConn().prepareStatement(critereSQL);
				
				pstCritere.setString(1, numLic);
												
				ResultSet rsCritere = pstCritere.executeQuery();
				System.out.println(critereSQL);

				
				//remplir une HashMap avec le résultat de la requête
				while(rsCritere.next()) {
					criteres.put(rsCritere.getString("nomcritere"), rsCritere.getInt("valcritere"));
				}
				System.out.println("taille criteres : " + criteres.size());
				System.out.println("contenu criteres : " + criteres);
	    		dao.closeConnection();

			} catch (SQLException e) {
	    		System.out.println("Problème SQL");
	    		e.printStackTrace();
			}
			h.setAttribute("criteres", criteres); //stocker la hashmap dans la session
			//request.setAttribute("criteres", criteres); //pour la vue ficheSportive
			getServletContext().getRequestDispatcher("/FicheSportive").forward(request, response); //pour le controleurFicheSportive = survit à la nouvelle requête HTTP
		}
	
	
		
		
		if (request.getParameter("fiche")!=null) {
			//response.sendRedirect("FicheAdministrative");
			getServletContext().getRequestDispatcher("/FicheAdministrative").forward(request, response); //meme chose avec la fiche administrative
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
