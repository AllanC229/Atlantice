package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import connection.DAOAcces;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


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
	    
	    
	    
	    Connection conn = null;
		PreparedStatement updateAdh = null;	
		PreparedStatement supprcritadh = null;
		PreparedStatement supprcategadh = null;
		PreparedStatement suppradh = null;
		String sql = null;
		
	    ArrayList<String> adherentupdate = new ArrayList<>();
	    adherentupdate.addAll(Arrays.asList(request.getParameter("nom"), request.getParameter("prenom"), request.getParameter("derniereAnneeLicence"), 
	    		request.getParameter("anneeNaissance"), request.getParameter("telephone1"), request.getParameter("telephone2"), 
	    		request.getParameter("adresse1"), request.getParameter("adresse2"), request.getParameter("mail1"), request.getParameter("mail2"), 
	    		request.getParameter("commentaire"), request.getParameter("contact1"), request.getParameter("contact2"), 
	    		request.getParameter("sexe"), request.getParameter("droitImage"),  request.getParameter("role"), request.getParameter("numeroLicence")));
	    
	    if (request.getParameter("modifAd") != null) { //Sert à modifier les valeurs d'un adhérent dans la BDD
	    	System.out.println("bouton modif cliqué");
	    	
	    	try {
	    		
	    		conn = dao.getConn();
				conn.setAutoCommit(false);
	    		
	    		if (request.getParameter("role").equals("nochange")) {	//If/else créé deux chaines sql différentes en fonction du choix du rôle dans la ficheadmin, une sans update le role (if) l'autre en update le role (else)
	    			adherentupdate.remove(15);  
	    			
	    			sql = "UPDATE adherents SET nom= ? , prenom= ? , dernierelicenceactive= ? , annee= ? , tel1= ? , tel2= ? , adresse1= ? , adresse2= ? ,"
							+ " mail1= ? , mail2= ? , commentaire= ? , contact1= ? , contact2= ? , sexe= ? , droitimage= ? WHERE numerolicence= ? ;";
	    		}
				
	    		else {				
				
	    			sql = "UPDATE adherents SET nom= ? , prenom= ? , dernierelicenceactive= ? , annee= ? , tel1= ? , tel2= ? , adresse1= ? , adresse2= ? ,"
						+ " mail1= ? , mail2= ? , commentaire= ? , contact1= ? , contact2= ? , sexe= ? , droitimage= ? , role = ? WHERE numerolicence= ? ;";
	    		}
	    		
				updateAdh = conn.prepareStatement(sql);
				int i = 1;
				
				for (String adherent : adherentupdate) {  
					System.out.println(adherent);
					updateAdh.setString(i, adherent);
					i++;
				}
				
				System.out.println(updateAdh);
				updateAdh.executeUpdate();
				conn.commit();
				
					
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dao.closeConnection();
	    }
	    
	    else if (request.getParameter("supprAd") != null) {	//Sert à supprimer les infos d'un adhérent dans la BDD ainsi que ses critères sportifs et ses categories
	    	System.out.println("bouton supprimer cliqué");
	    	
	    		try {
				
				conn = dao.getConn();
				conn.setAutoCommit(false);
				
				supprcritadh = conn.prepareStatement("DELETE FROM critereadherent WHERE numerolicence = ? ");
				supprcritadh.setString(1, request.getParameter("numeroLicence"));
				supprcritadh.executeUpdate();
				
				supprcategadh = conn.prepareStatement("DELETE FROM categorieadherent WHERE numLic = ? ");
				supprcategadh.setString(1,  request.getParameter("numeroLicence"));
				supprcategadh.executeUpdate();
				
				suppradh = conn.prepareStatement("DELETE FROM adherents WHERE numerolicence = ? ");
				suppradh.setString(1,  request.getParameter("numeroLicence"));
				suppradh.executeUpdate();
				
				System.out.println("adherent "+ request.getParameter("numeroLicence") +" correctement supprimé");
				conn.commit();
	    		}
	    		
	    		catch (SQLException e) {
					if (conn != null) {
						try {
							conn.rollback();
						}
						catch (SQLException ex) {
							ex.printStackTrace();
						}
					}
					e.printStackTrace();
				}
	    		
	    		finally {
	    		
				dao.closeConnection();
				
	    		}
	    		
	    }

	    
		
		
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
