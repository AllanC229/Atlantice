package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import connection.DAOAcces;

/**
 * Servlet implementation class ControleurAjtCategorie
 */
@WebServlet("/ControleurAjtCategorie")
public class ControleurAjtCategorie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControleurAjtCategorie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		
		DAOAcces dao = new DAOAcces("com.mysql.jdbc.Driver", "webadherents", "root", "");
		Connection conn = null;
		PreparedStatement ajtCateg = null;
		
		
		try {  //Ajout d'une requête préparée pour ajouter une catégorie dans la BDD, 08/12 11:03
			
			conn = dao.getConn(); 
		    conn.setAutoCommit(false);		
			String nomCategorie =  request.getParameter("nmC");
			String annee = request.getParameter("annee");
			String sqlCateg= "INSERT INTO categorie(categories, annee) VALUES (?, ?);";
			
			ajtCateg = conn.prepareStatement(sqlCateg);
			ajtCateg.setString(1, nomCategorie);
			ajtCateg.setString(2,  annee);
			
            if(nomCategorie != "") {
            	
            ajtCateg.executeUpdate();
            request.getRequestDispatcher("/Accueil").forward(request, response);
           
            }
            
            else { 
            	
            	request.getRequestDispatcher("/CreationCategorie").forward(request, response);
            }

		 }
		
	    catch(SQLException e) {
			System.out.println("Probleme SQL !!");
			e.printStackTrace();
		}
		
		dao.closeConnection();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
