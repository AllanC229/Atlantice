package view;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Categorie;

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
		// TODO Auto-generated method stub
		
		String r = "<!Doctype html><html><head><meta charset=\"utf-8\"/> \r\n\r\n"
				+ " <link href=\"licence.css\" rel=\"stylesheet\">"
				+ " </head><body><h1 align=center>Catégories : </h1></br>\r\n"
				+ "	<div align=center><form action=\"ControleurCategories\" method=GET>\r\n"
				+ " <table border>"
				+ " <tr><th>Nom</th><th>Années</th></tr>";
		
				for(Categorie c : (ArrayList<Categorie>)request.getAttribute("categories")) {
					r += "<tr><td>"+c.getNomCateg()+"</td><td><input type=\"text\" name=\"annee\" value='"+c.getIdCateg()+"'></td></tr>";
				}
				r += "</table>"
					+ "<input type=\"submit\" name=\"categories\" value=\"Valider les modifications\"></td></tr> </form>"
					+ "<tr><td><form action=\"CreationCategorie\"> <input type=\"submit\" name=\"creationCategorie\" value=\"Créer une catégorie\"></td></tr></form>"
					+ "</body></html>";
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
