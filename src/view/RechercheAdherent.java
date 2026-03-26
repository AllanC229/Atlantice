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
		
		PrintWriter out=response.getWriter();
		
		out.println("<html><head><meta charset='utf-8'></head><body><h1 align='center'> Rechercher un adhérent </h1><br><br>"
				+ "<div align='right'> <form action='ControleurDeconnexion' name='boutondeconnexion' method='get'> <input type ='submit' name='deconnexion' value='Se déconnecter'> </form><br>"
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
