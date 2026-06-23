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
		HttpSession h = request.getSession(false);	//Charge la variable de session si elle existe (false)
		
		if (h == null) { //Si la session n'existe pas, renvoie vers la page de connexion
		    response.sendRedirect("/Connexion");
		    return;
		}
		
		PrintWriter out=response.getWriter();
		Utilisateur activeUser = (Utilisateur) h.getAttribute("activeUser");
		String role = activeUser.getRole();
		System.out.println("role:" + role);
		
		
		out.print("<!Doctype html><html><head><meta charset=\"utf-8\"/>"
				+ " <link href=\"licence.css\" rel=\"stylesheet\">"
				+ "<link href='header.css' rel='stylesheet'>"
				+ " </head><body>");
				
		out.print(Header.afficherEntete(activeUser));

		out.print("<h1 align=center>Catégories : </h1></br>"
				+ "<div align=center><table border>"
				+ "<tr><th>Années</th><th>Nom</th></tr>");
		
				for(Categorie c : (ArrayList<Categorie>)request.getAttribute("categories")) {
					out.print("<tr><td><input type=\"text\" name=\"annee\" value='"+c.getNomCateg()+"'></td><td><input type=\"text\" name=\"nom\" value='"+c.getIdCateg()+"'></td></tr>");
				}
				
				out.print("</table></div>");
				
				if ((activeUser.getRole().equals("admin") || activeUser.getRole().equals("modif"))) {
				
					out.print("<div align=center><form action='ControleurCategories' method=POST>"
					+ "<input type=\"submit\" name=\"modifCategories\" value='Valider les modifications'> </form><br>"
					
					+ "<form action=\"CreationCategorie\" method=POST> <input type=\"submit\" name=\"creationCategorie\" value=\"Créer une catégorie\"></form>"
					+ "</div>");					
				
				/*	out.print("<form action=\"ControleurCategories\" name=\"boutonCreerCateg\" method=\"get\"> <input type = \"submit\" name=\"creationCategorie\" value=\"Créer une catégorie\"> </form>");	*/
			
			        if (request.getAttribute("erreur") != null) {
			        	out.println(request.getAttribute("erreur"));
			        }
					
					out.print("</body></html>");
				
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

