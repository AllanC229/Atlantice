package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Adherent;
import model.Utilisateur;

/**
 * Servlet implementation class FicheSportive
 */
@WebServlet("/FicheSportive")
public class FicheSportive extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public FicheSportive() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession h = request.getSession(false);
		
		if (h == null) { //Si la session n'existe pas, renvie vers la page de connexion
		    response.sendRedirect("/Connexion");
		    return;
		}
		
		Utilisateur activeUser = (Utilisateur) h.getAttribute("activeUser");
		String role = activeUser.getRole();
		System.out.println("role:" + role);
		PrintWriter out=response.getWriter();

		
		Adherent a;
		
		HashMap<String, Integer> criteres = (HashMap<String, Integer>) h.getAttribute("criteres");
		String numLic = request.getParameter("numLic");
		if (numLic == null) {
		    numLic = (String) h.getAttribute("numLic");
		}
		System.out.println(numLic);
		
		for (Adherent adh : (ArrayList<Adherent>) h.getAttribute("adherents")) {
			if(adh.getNumLicence().equals(numLic)) {
				a = adh;
				out.println("<!Doctype html><html><head><meta charset='utf-8'> \r\n"
						+"<link href='licence.css' rel='stylesheet'>"
						+ "<link href='range-slider-fiche-sportive.css' rel='stylesheet'>" //fichier CSS pour l'input range-slider
						+ "</head>");
						
				out.println(Header.afficherEntete(activeUser));

						
				out.println("<h1 align=center>Suivi sportif : </h1></br>" 
						+ "<div align=center><table border>"
						+ "<form action='ControleurFicheSportive' method=POST>"
						+ "		<tr><td>Nom : </td><td>"+a.getNom()+"</td></tr>"
						+ "		<tr><td>Prénom : </td><td>"+a.getPrenom()+"</td></tr>"
						+ "		<tr><td>Numéro de licence : </td><td>"+a.getNumLicence()+"</td></tr>" );
						for (HashMap.Entry<String, Integer> entry : criteres.entrySet()) {
							out.println("<tr><td>"+entry.getKey()+"</td><td>"
										+ "<div class='range-slider' style='--value-a: 0; width: 350px;'>"
										+ "<input id='"+entry.getKey()+"' name='"+entry.getKey()+"' type='range' min='0' max='5' value='" + entry.getValue() 
																					+"'oninput='this.parentNode.style.setProperty('--value-a', this.value)'>"
										+ "<div class='range-slider__values'>0 1 2 3 4 5</div>"
										+ "<div class='range-slider__progress'></div></div>");
						}
												
						out.println("</table></div>");
						if ((activeUser.getRole().equals("admin") || activeUser.getRole().equals("modif"))) {				
							out.println("<div align=center>"
								+ "<input type=submit name='modifAd' value=\"Modification de l'adhérent\"></input>"
								+ "</form></div>");
						}
						
						out.println("<div align=center><form action='Accueil' name='retouraccueil' value='accueil' method='POST'>"
						+ "<input type='submit' name='retouraccueil' value=\"Retour à l'accueil\"> </form>");
						
				        if (request.getAttribute("erreur") != null) {
				        	out.println(request.getAttribute("erreur"));
				        }
						
						out.println("</body></html>");
			}
		}
		
		
		//response.getWriter().append(affichage);
	
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
