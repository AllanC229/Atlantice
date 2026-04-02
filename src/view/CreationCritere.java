package view;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



/**
 * Servlet implementation class CreationClasse
 */
@WebServlet("/CreationCritere")
public class CreationCritere extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreationCritere() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { //codé pendant le mois de mars 2026 par pauline
		
		HttpSession h = request.getSession(false);	//Charge la variable de session si elle existe (false)
		
		if (h == null) { //Si la session n'existe pas, renvoie vers la page de connexion
		    response.sendRedirect("/Connexion");
		    return;
		}

		PrintWriter out=response.getWriter();
		
		//Formulaire d'ajout de critère
			out.println("<html><head><meta charset=\"utf-8\"/>"
					+"<link href=\"licence.css\" rel=\"stylesheet\">"
					+ "</head><body>"+
						"<div align=center><form name=\"ajouterCritere\" action=\"ControleurAjtCritere\" method=GET>" //POST ->INSERT
						+ "<h1>Ajouter un nouveau critère :</h1><br>"
						+ "<br>"
						+ "Nom du critère : <input type=\"text\" name=\"nomCritere\"> </input>"
						+ "<br><br>"
						+ "<input type=\"submit\" value = \"Valider\" /></input>"
						+ "</form><br>"
						+ "<form action=\"Accueil\" name=\"retouraccueil\" value=\"accueil\" method='GET'>"
						+ "<input type=\"submit\" name=\"retouraccueil\" value=\"Retour à l'accueil\"> </form>"
						+ "</div></body></html>");      
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		
	}

}
