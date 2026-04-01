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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    HttpSession h = request.getSession(false);
	    
	    if (h == null || h.getAttribute("activeUser") == null) { //Si la session n'existe pas, renvoie vers la page de connexion
		    response.sendRedirect("/Connexion");
		    return;
		}
		
	//	PrintWriter out=response.getWriter();
		
		
		try { 
			
			String nomcritere = (request.getParameter("nomCritere"));

			if(nomcritere != "" && nomcritere !=" ") { // vérif nécessaire ou pas ?
				
				DAOAcces dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "webadherents", "root", "");
				
				String insertCritere = "INSERT INTO criteres(nomcritere) "
										+ "VALUES (?);";
							
				// Création d'un PreparedStatement
				PreparedStatement pstInsertCritere = dao.getConn().prepareStatement(insertCritere);
				
				pstInsertCritere.setString (1, nomcritere);
				
				pstInsertCritere.executeUpdate();
				System.out.println(pstInsertCritere);
				
				// faire une seconde requête d'insertion du nouveau critere à 0 dans table critereadherent (transaction?)
				
				response.sendRedirect("ControleurAccueil?critere=critere");
				
	    		dao.closeConnection();

	            
            } else {  
            	//PrintWriter out=response.getWriter("Veuillez remplir le champ !");
            	request.getRequestDispatcher("/CreationCritere").forward(request, response);
            	
            }

		} catch(SQLException e) {
			System.out.println("Probleme SQL creatioCritere !!");
			e.printStackTrace();
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