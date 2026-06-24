package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Adherent;
import model.Utilisateur;

/**
 * Servlet implementation class FicheAdministrative
 */
@WebServlet("/RechercheAdherent")
public class RechercheAdherent extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public RechercheAdherent() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings({ "unchecked", "unchecked" })
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		HttpSession h = request.getSession(false);
	    Utilisateur activeUser = (Utilisateur) h.getAttribute("activeUser");
		
		if (h == null || !activeUser.getRole().equals("admin")) { //Si la session n'existe pas, renvoie vers la page d'accueil
		    response.sendRedirect("/Accueil");
		    return;
		}
		

				
		out.println("<!doctype html>"
				+ "<html>"
				+ "<head>"
				+ "<meta charset='utf-8'/> "
				+ "<link href='licence.css' rel='stylesheet'>"
				+ "</head>");
				
		out.println(Header.afficherEntete(activeUser));
				
		out.println("<div class='formulaire-adherent'>"
				+ "<h1 align='center'> Rechercher un adhérent </h1><br><br>"
				+ "<form action='ControleurRechercheAdherent' name='RechercheAdherent' method='POST'>"
				+ "<table style='width: 50%'>"
				+ "<tr><td> Recherche par numéro de licence </td> <td><input type='text' name='numLic'></td></tr>"
				+ "<tr><td> Recherche par Nom </td><td><input type='text' name='nom'></td></tr>"
				+ "</table>"
				+ "<div class='actions'>"
				+ "<input type='submit' value='Recherche'>"
				+ "</div>"
				+ "</form>");
		
        if (request.getAttribute("erreur") != null) {
        	out.println(request.getAttribute("erreur"));
        }
		out.println("</div></body></html>");
		
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
