package controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Adherent;

@WebServlet("/ControleurModifInfosProfil")
public class ControleurModifInfosProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ControleurModifInfosProfil() {
        // TODO Auto-generated constructor stub
    }
    
    @SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	//Le code va ici
    			HttpSession h = request.getSession(false);
    			
    			if (h == null) { //Si la session n'existe pas, renvie vers la page de connexion
    			    response.sendRedirect("/Connexion");
    			    return;
    			}
    			
    			Adherent activeAdherent = (Adherent) h.getAttribute("activeAdherent");
    			
    			
    			
    			
    }
}
