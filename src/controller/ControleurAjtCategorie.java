package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Utilisateur;
import connection.DAOAcces;
import tool.ControleDeSaisie;


/**
 * Servlet implementation class ControleurAjtCategorie
 */
@WebServlet("/ControleurAjtCategorie")
public class ControleurAjtCategorie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControleurAjtCategorie() {
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
		
		DAOAcces dao = null;
		Connection conn = null;
		PreparedStatement ajtCateg = null;
		
		String nomCategorie =  request.getParameter("nmC");
		String annee = request.getParameter("annee");
		
        if(nomCategorie.trim().isEmpty() && annee.trim().isEmpty()) { //si nomCategorie et annee sont vides -> renvoie sur la vue CreationCategorie
			request.setAttribute("erreur", "Veuillez remplir tout les champs !");
        	request.getRequestDispatcher("/CreationCategorie").forward(request, response);
        }
		
		//appeler la méthode des caractères interdits
        ArrayList<String> champsATester = new ArrayList<>();
        champsATester.add(nomCategorie);
        champsATester.add(annee);
		
        for (String champ : champsATester) {
            if (ControleDeSaisie.caractereInterdit(champ)) {
				// insérer la tentative d'injection dans les logs : 
            	try {
					activeUser.lastseen(activeUser.getIdConnexion(), " tentative insertion caractère interdit creatoinCategorie;");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                // pas donner d'indice quant à la nature de l'erreur? 
            	request.setAttribute("erreur", "Caractère interdit détecté");
                getServletContext().getRequestDispatcher("/CreationCategorie").forward(request, response);
                return;
            }
        }
		
		try {  //Ajout d'une requête préparée pour ajouter une catégorie dans la BDD, 08/12 11:03
			dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "webadherents", "root", "");

			activeUser.lastseen(activeUser.getIdConnexion(), " création de la catégorie "+ request.getParameter("nmC") +" dans la BDD;");
			
			conn = dao.getConn(); 
		    conn.setAutoCommit(false);		

			String sqlCateg= "INSERT INTO anneecategorie(categories, annee) VALUES (?, ?);";
			
			ajtCateg = conn.prepareStatement(sqlCateg);
			ajtCateg.setString(1, nomCategorie);
			ajtCateg.setString(2,  annee);
            	
            ajtCateg.executeUpdate();
          

		} catch(SQLException e) {
			System.out.println("Probleme SQL !!");
			e.printStackTrace();
		}finally {
			if (dao != null) { // vérification nécessaire : si la construction a échoué avant la ligne d'affectation, dao vaut encore null
		        dao.closeConnection();
		    }
			dao.closeConnection();
		}
		request.setAttribute("succes", "Nouvelle catégorie créer !");
        request.getRequestDispatcher("/Accueil").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
