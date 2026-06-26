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

		Utilisateur activeUser = (Utilisateur) h.getAttribute("activeUser");
		PrintWriter out=response.getWriter();
		
		//Formulaire d'ajout de critère
			out.println("<html><head><meta charset='utf-8'/>"
					+"<link href='licence.css' rel='stylesheet'>"
					+ "</head>");
			
			out.println(Header.afficherEntete(activeUser));
					
			out.println("<div class='formulaire-ficheadmin'><form name='ajouterCritere' action='ControleurAjtCritere' method=POST>" 
						+ "<h1>Ajouter un nouveau critère </h1><br>"
						+ "<br>"
						+ "<h2>Nom du critère : </h2> <br> <input type='text' name='nomCritere'>"
						+ "<br>"
						+ "<input type='submit' value = 'Valider'>"
						+ "</form><br>"
						+ "<form action='Accueil' name='retouraccueil' value='accueil' method='POST'>"
						+ "</div>");   
			
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
