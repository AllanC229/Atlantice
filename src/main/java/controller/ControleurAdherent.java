package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.DAOAcces;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class ControleurAdherent
 */
@WebServlet("/ControleurAdherent")
public class ControleurAdherent extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ControleurAdherent() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession h = request.getSession(false);
		
		if (h == null || h.getAttribute("activeUser") == null) { //Si la session n'existe pas, renvie vers la page de connexion
		    response.sendRedirect("/Connexion");
		    return;
		}
		
		DAOAcces dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "webadherents", "root", "");
		String sql = "INSERT INTO adherents (numerolicence, nom, prenom, dernierelicenceactive, annee, tel1, tel2, adresse1, adresse2, mail1, mail2, "
				+ "commentaire, contact1, contact2, sexe, droitimage) "
				+ "VALUES ('"+request.getParameter("numeroLicence")+"', '"+request.getParameter("nom")+"', '"+request.getParameter("prenom") +"', '"+request.getParameter("derniereAnneeLicence")+"', '"
				+ request.getParameter("anneeNaissance")+"', '"+request.getParameter("telephone1")+"', '"+request.getParameter("telephone2")+"', '"
				+ request.getParameter("adresse1")+"', '"+request.getParameter("adresse2")+"', '"+request.getParameter("mail1")+"', '"
				+ request.getParameter("mail2")+"', '"+ request.getParameter("commentaire")+"', '"+request.getParameter("contact1")+"', '"
				+ request.getParameter("contact2")+"', "+ request.getParameter("sexe")+", "+ request.getParameter("droitImage")+", "+ request.getParameter("critere1")+", "+ request.getParameter("critere2")+", "+ request.getParameter("critere3")+", "+ request.getParameter("critere4")+", "+ request.getParameter("critere5")+"); "; //modif critere le 04/12 12:56
		
		//System.out.println(ControleurAccueil.cat);
		
		String toto = ((String[])h.getAttribute("cat"))[0];
		System.out.println(toto);
		String sql1 = "INSERT INTO categorieadherent (numLic, idcategorie) VALUES ('"+request.getParameter("numeroLicence")+"', '"+((String[])h.getAttribute("cat"))[0]+"'); ";
		
		try {
			dao.getStatement().executeUpdate(sql);
			dao.getStatement().executeUpdate(sql1);
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dao.closeConnection();
		response.sendRedirect("Accueil");
		//getServletContext().getRequestDispatcher("/Accueil").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
