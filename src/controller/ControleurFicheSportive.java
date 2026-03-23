package controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

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
	@WebServlet("/ControleurFicheSportive")
	public class ControleurFicheSportive extends HttpServlet {
		private static final long serialVersionUID = 1L;

	    /**
	     * Default constructor. 
	     */
	    public ControleurFicheSportive() {
	        // TODO Auto-generated constructor stub
	    }

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			HttpSession h = request.getSession();
			String numLic = (String) h.getAttribute("numLic");
					
			HashMap<String, Integer> criteres = (HashMap<String, Integer>) h.getAttribute("criteres"); 
			
			try {
				DAOAcces dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "webadherents", "root", "");
								
					
				//pour chaque critere modifié dans fiche sportive, mettre à jour 
				for (HashMap.Entry<String, Integer> entry : criteres.entrySet()) {
					String anSQLquery = "UPDATE critereadherent JOIN criteres "
										+ "ON critereadherent.idcritere = criteres.idcritere "
										+ "SET `valcritere`=? WHERE numerolicence=? AND nomcritere=?;";

				// Création d'un PreparedStatement
				PreparedStatement pst = dao.getConn().prepareStatement(anSQLquery);
				
				String numeroLicence = numLic;
				String nomcritere = entry.getKey();
				int valcritere = Integer.parseInt(request.getParameter(nomcritere));
				
				pst.setInt (1, valcritere);
				pst.setString (2, numeroLicence);
				pst.setString (3, nomcritere); 

				pst.executeUpdate();
				System.out.println(pst);

				}
				dao.closeConnection();
	
			} catch (SQLException e) {
				System.out.println("Problème SQL");
				e.printStackTrace();
			}
			h.setAttribute("numLic", numLic);
			response.sendRedirect("ControleurTableau?sportif=true");	
			
		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(request, response);
		}

	}