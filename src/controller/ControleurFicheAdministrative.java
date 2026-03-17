package controller;

import java.io.IOException;
import java.sql.SQLException;

import connection.DAOAcces;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Adherent;

/**
 * Servlet implementation class ControleurFicheAdministrative
 */
@WebServlet("/ControleurFicheAdministrative")
public class ControleurFicheAdministrative extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ControleurFicheAdministrative() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DAOAcces dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "webadherents", "root", "");
	    HttpSession h = request.getSession();

		try {
			
			String sql = "UPDATE adherents SET nom='"+request.getParameter("nom")+"', prenom='"+request.getParameter("prenom")+"', "
					+ "dernierelicenceactive='"+request.getParameter("derniereAnneeLicence")+"', annee='"+request.getParameter("anneeNaissance")+"', "
					+ "tel1='"+request.getParameter("telephone1")+"', tel2='"+request.getParameter("telephone2")+"', adresse1='"+request.getParameter("adresse1")+"', "
					+ "adresse2='"+request.getParameter("adresse2")+"', mail1='"+request.getParameter("mail1")+"', mail2='"+request.getParameter("mail2")+"', "
					+ "commentaire='"+request.getParameter("commentaire")+"', contact1='"+request.getParameter("contact1")+"', contact2='"+request.getParameter("contact2")+"', "
					+ "sexe='"+request.getParameter("sexe")+"', droitimage='"+request.getParameter("droitImage")+"' WHERE numerolicence='"+request.getParameter("numeroLicence")+"';";
			dao.getStatement().executeUpdate(sql); //met à jour les infos dans la BDD, à refaire pour en faire une requête préparée
			
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.closeConnection();
		
		getServletContext().getRequestDispatcher("/Accueil").forward(request, response); //Renvoie vers l'accueil
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
