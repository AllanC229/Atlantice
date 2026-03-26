package controller;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import connection.DAOAcces;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Utilisateur;


/**
 * Servlet implementation class ControleurDeconnexion
 */
@WebServlet("/ControleurDeconnexion")
public class ControleurDeconnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       

	/**
     * @see HttpServlet#HttpServlet()
     */
    public ControleurDeconnexion() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    String deconnexion = (String)request.getParameter("deconnexion");
	
	//Deconnexion de la session, Allan
	if("Se déconnecter".equals(deconnexion)) {
		
		HttpSession h = request.getSession(false);	
		
		
		if (h == null) { //Si la session n'existe pas, renvie vers la page de connexion
			getServletContext().getRequestDispatcher("/Connexion").forward(request, response);
		    return;
		}
		
		Utilisateur activeUser = (Utilisateur) h.getAttribute("activeUser");
		DAOAcces dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "webadherents", "root", "");
		Connection conn = null;
	
		try {
			conn = dao.getConn();
			conn.setAutoCommit(false);
			
			Timestamp tslogout = new Timestamp(System.currentTimeMillis());
			tslogout.setNanos(0);
			
			PreparedStatement insertLogoutTime = conn.prepareStatement("UPDATE log SET logouttime = ? , navhistory = CONCAT(navhistory, ' logout;') WHERE idlog = ? ;");
			insertLogoutTime.setTimestamp(1, tslogout);
			insertLogoutTime.setInt(2,  activeUser.getIdConnexion());
			insertLogoutTime.executeUpdate();
			conn.commit();
			h.invalidate();
			dao.closeConnection();
		}
		
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		// Besoin de fermer la connexion ou pas?
		
		
		getServletContext().getRequestDispatcher("/Connexion").forward(request, response);
		
		
	}
	//Fin déconnexion	
}
}
