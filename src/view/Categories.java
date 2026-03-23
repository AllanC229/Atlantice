package view;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Categorie;
import model.Utilisateur;

/**
 * Servlet implementation class Categories
 */
@WebServlet("/Categories")
public class Categories extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Categories() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession h = request.getSession();
		Utilisateur activeUser = (Utilisateur) h.getAttribute("activeUser");
		String role = activeUser.getRole();
		System.out.println("role:" + role);
		
		String r = "<!Doctype html><html><head><meta charset=\"utf-8\"/> \r\n\r\n"
				+ " <link href=\"licence.css\" rel=\"stylesheet\">"
				+ " </head><body><h1 align=center>Catégories : </h1></br>\r\n"
				+ "	<div align=center><form action=\"ControleurCategories\" method=GET>\r\n" //méthode POST => UPDATE en BDD
				+ " <table border>"
				+ " <tr><th>Années</th><th>Nom</th></tr>";
		
				for(Categorie c : (ArrayList<Categorie>)request.getAttribute("categories")) {
					r += "<tr><td><input type=\"text\" name=\"annee\" value='"+c.getNomCateg()+"'></td><td><input type=\"text\" name=\"nom\" value='"+c.getIdCateg()+"'></td></tr>";
				}
				
				if ((activeUser.getRole().equals("admin") || activeUser.getRole().equals("modif"))) {
				r += "</table>"
					+ "<input type=\"submit\" name=\"modifCategories\" value=\"Valider les modifications\"></td></tr> </form>"
					+ "<tr><td><form action=\"CreationCategorie\" method=POST> <input type=\"submit\" name=\"creationCategorie\" value=\"Créer une catégorie\"></td></tr></form>"
					+ "</body></html>";						// POST => INSERT en BDD
				
				/*	out.print("<form action=\"ControleurCategories\" name=\"boutonCreerCateg\" method=\"get\"> <input type = \"submit\" name=\"creationCategorie\" value=\"Créer une catégorie\"> </form>");	*/
			
				
				}
		response.getWriter().append(r);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
