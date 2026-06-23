package view;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Utilisateur;
import connection.DAOAcces;

/**
 * Servlet implementation class CreationClasse
 */
@WebServlet("/CreationCategorie")
public class CreationCategorie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreationCategorie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out=response.getWriter();
		HttpSession h = request.getSession(false);
		Utilisateur activeUser = (Utilisateur) h.getAttribute("activeUser");

		
		//Formulaire d'ajout de classe
		out.println("<!Doctype html><html><head><meta charset=\"utf-8\"/> \r\n"
				+ "<link href=\"licence.css\" rel=\"stylesheet\">"
				+ "<link href=\"header.css\" rel=\"stylesheet\">"
				+ "</head>");
					
		out.println(Header.afficherEntete(activeUser));
		
		out.println("<div align=center><form name=\"ajouterCategorie\" action=\"ControleurAjtCategorie\" method=POST>"
		+ "<h1>Ajouter une catégorie</h1><br>"
		+ "<br>");
		
		out.println("Nom de la catégorie: <input type=\"text\" name=\"nmC\"> </input> <br><br>");
		out.println("Année de la catégorie: <input type=\"text\" name=\"annee\"> </input> <br><br>");

		out.println("<input type=\"submit\" value = \"Valider\" /></input></div>");   
		out.println("</form>");
		
        if (request.getAttribute("erreur") != null) {
        	out.println(request.getAttribute("erreur"));
        }
		
		out.println("</body></html>");
			    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		
	}

}
