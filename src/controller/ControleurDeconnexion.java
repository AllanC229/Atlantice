package controller;


import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


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
		
		if (h == null || h.getAttribute("activeUser") == null) { //Si la session n'existe pas, renvie vers la page de connexion
		    response.sendRedirect("/Connexion");
		    return;
		}
		
		h.invalidate();
		// Besoin de fermer la connexion ou pas?
		
		
		getServletContext().getRequestDispatcher("/Connexion").forward(request, response);
		
		
	}
	//Fin déconnexion	
}
}
