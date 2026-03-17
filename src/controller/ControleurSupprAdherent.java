package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import connection.DAOAcces;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


/**
 * Servlet implementation class ControleurSupprAdherent
 */
@WebServlet("/ControleurSupprAdherent")
public class ControleurSupprAdherent extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ControleurSupprAdherent() {
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DAOAcces dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "webadherents", "root", "");
	    HttpSession h = request.getSession();
	    String numLic = request.getParameter("SupprAdherent");
	    numLic = numLic.trim();
	    Connection conn = null ;
	    PreparedStatement supprAdh = null;
	    PreparedStatement supprCateg = null ;
	    PreparedStatement supprSportif = null ;
	 
	    
try {
			
	
		conn = dao.getConn();
		conn.setAutoCommit(false);
					
		
		String sqlCateg = "DELETE FROM categorieadherent WHERE idAd =?" ;
		supprCateg = conn.prepareStatement(sqlCateg);
		supprCateg.setString(1, numLic);
		supprCateg.executeUpdate();
		
		String sqlSportif = "DELETE FROM sportif WHERE numerolicence =?" ;
		supprSportif = conn.prepareStatement(sqlSportif);
		supprSportif.setString(1, numLic);
		supprSportif.executeUpdate();
		
		String sqlAdh = "DELETE FROM adherents WHERE numerolicence =?" ;
		supprAdh = conn.prepareStatement(sqlAdh);
		supprAdh.setString(1, numLic);
		supprAdh.executeUpdate();
				
		}
			catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		
	dao.closeConnection();
    
    	
    
    getServletContext().getRequestDispatcher("/Accueil").forward(request, response);
}
}