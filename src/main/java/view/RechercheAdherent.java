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
		
		if (h == null) { //Si la session n'existe pas, renvie vers la page de connexion
		    response.sendRedirect("/Connexion");
		    return;
		}
		
	    Utilisateur activeUser = (Utilisateur) h.getAttribute("activeUser");
				
		out.println("<!doctype html>"
				+ "<html>"
				+ "<head>"
				+ "<meta charset=\"utf-8\"/> "
				+ "<link href=\"licence.css\" rel=\"stylesheet\">"
				+ "<link href=\"header.css\" rel=\"stylesheet\">"
				+ "</head>");
				
		out.println(Header.afficherEntete(activeUser));
				
		out.println("<h1 align='center'> Rechercher un adhérent </h1><br><br>"
				+ "<form action=\"Accueil\" name=\"retouraccueil\" > <input type = \"submit\" name=\"retouraccueil\" value=\"Retour à l'accueil\"> </form></div><br>"
				+ "<form action='ControleurRechercheAdherent' name='RechercheAdherent' method='get'>"
				+ "<table style='width: 50%' border='5px'>"
				+ "<tr><td> Recherche par numéro de licence </td> <td><input type='text' name='numLic'></td></tr>"
				+ "<tr><td> Recherche par Nom </td><td><input type='text' name='nom'></td></tr>"
				+ "</table>"
				+ "<input type='submit' value='Recherche'>"
				+ "</form>");
}
}
