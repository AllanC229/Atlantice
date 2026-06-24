package view;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import connection.DAOAcces;

/**
 * Servlet implementation class Connexion
 */
@WebServlet("/Connexion")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Connexion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out=response.getWriter();
		
		
	
		
		
		//Formulaire de connexion
			out.println("<html><head><meta charset='utf-8'/>"
					+"<link href='licence.css' rel='stylesheet'>"
					+"</head>"
					+ "<body>"
					+"<div class='formulaire-adherent'><form name = 'FormConnexion' action='ControleurConnexion' method=POST>"
					+"<h1>Connexion</h1><br>"
					+"<br>");
			out.println("<table><tr><td>");
			
			out.println("Adresse mail: </td><td><input type='text' name='mailCo'> </input> </td></tr><tr><td>");
			out.println("Mot de passe: </td><td><input type='password' name='mdpCo'> </input> </td></tr></table><br><br>");

			if (request.getAttribute("mdperr") != null) {
				
				out.println(""+request.getAttribute("mdperr")+"<br>");
				
			}
			
			out.print("<input type='submit' value = 'Valider' /></input>");   
			out.print("</form></div>");
			
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
