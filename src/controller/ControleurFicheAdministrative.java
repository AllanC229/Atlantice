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
import jakarta.servlet.http.HttpSession;
import model.Utilisateur;
import tool.ControleDeSaisie;



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
	    
	    HttpSession h = request.getSession(false);
	    
		if (h == null) { //Si la session n'existe pas, renvoie vers la page de connexion
		    response.sendRedirect("/Connexion");
		    return;
		}
	    
		Utilisateur activeUser = (Utilisateur) h.getAttribute("activeUser");
		
		//regex pour les mails accepte uniquement le format: lettres/chiffres + @ + des lettres/chiffres + des lettres
        String regexMail = ControleDeSaisie.regexPattern;

		DAOAcces dao = null;
	    Connection conn = null;
	    PreparedStatement modifNumLicCrit = null;
	    PreparedStatement modifNumLicCateg = null;
	    PreparedStatement modifNumLicAdh = null;
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
	    		request.getParameter("sexe"), request.getParameter("droitImage"), request.getParameter("role"), request.getParameter("numeroLicence")));
	    
	    if (request.getParameter("modifAd") != null) { //Sert à modifier les valeurs d'un adhérent dans la BDD
	    	System.out.println("bouton modif cliqué");
	    	
	    	for (String champTest : adherentupdate) {
	            if (ControleDeSaisie.caractereInterdit(champTest)) {
					// insérer la tentative d'injection dans les logs : 
	            	try {
						activeUser.lastseen(activeUser.getIdConnexion(), " tentative insertion caractère interdit modif adherent;");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                // pas donner d'indice quant à la nature de l'erreur (request.setAttribute("erreur", "Caractère interdit détecté (< > \")");)
	                getServletContext().getRequestDispatcher("/Accueil").forward(request, response);
	                return;
	            }
	        }
	    	
	        // format mail1 invalide
	    	String mail1 = request.getParameter("mail1");
	        if (ControleDeSaisie.patternMatches(mail1, regexMail) == false) {
		        request.setAttribute("erreur", "Adresse mail1 invalide");
		        getServletContext().getRequestDispatcher("/Accueil").forward(request, response);
		        System.out.println("mail1 invalide:" + mail1);
		        return;
	        } 
	         //format mail2 invalide
	    	String mail2 = request.getParameter("mail2");
	    	System.out.println();
	        if(mail2 != null && !mail2.equals("")) {
		        if (ControleDeSaisie.patternMatches(mail2, regexMail) == false) {
			       request.setAttribute("erreur", "Adresse mail2 invalide");
			       getServletContext().getRequestDispatcher("/Accueil").forward(request, response);
			        System.out.println("mail2 invalide:" + mail2);
			       return;
		        }
	        }
	        
	    	
	    	try {
	    		dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "webadherents", "root", "");
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
				
				if (!request.getParameter("modifnumeroLicence").equals("")) { //Vérifie si le champ modifier le numéro de licence contient quelque chose et si oui, fait la MàJ dans les différentes tables concernées
					//Methode un peu barbare selon moi, mais fonctionnelle
					modifNumLicCrit = conn.prepareStatement("UPDATE critereadherent SET numerolicence = ? WHERE numerolicence = ?;");
					modifNumLicCrit.setString(1, request.getParameter("modifnumeroLicence"));
					modifNumLicCrit.setString(2, request.getParameter("numeroLicence"));
					modifNumLicCrit.executeUpdate();
					
					modifNumLicCateg = conn.prepareStatement("UPDATE categorieadherent SET numLic = ? WHERE numLic = ?;");
					modifNumLicCateg.setString(1, request.getParameter("modifnumeroLicence"));
					modifNumLicCateg.setString(2, request.getParameter("numeroLicence"));
					modifNumLicCateg.executeUpdate();
					
					modifNumLicAdh = conn.prepareStatement("UPDATE adherents SET numerolicence = ? WHERE numerolicence = ?;");
					modifNumLicAdh.setString(1, request.getParameter("modifnumeroLicence"));
					modifNumLicAdh.setString(2, request.getParameter("numeroLicence"));
					modifNumLicAdh.executeUpdate();

					activeUser.lastseen(activeUser.getIdConnexion(), " modification de l'adhérent "+ request.getParameter("modifnumeroLicence") +" dans la BDD;");
					
				}

				else {
					activeUser.lastseen(activeUser.getIdConnexion(), " modification de l'adhérent "+ request.getParameter("numeroLicence") +" dans la BDD;");
				}
				
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
	    
	    else if (request.getParameter("supprAd") != null) {	//Sert à supprimer les infos d'un adhérent dans la BDD ainsi que ses critères sportifs et ses categories
	    	System.out.println("bouton supprimer cliqué");
	    	
	    		try {
	    		dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "webadherents", "root", "");
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
				activeUser.lastseen(activeUser.getIdConnexion(), " suppression de l'adhérent "+ request.getParameter("numeroLicence") +" dans la BDD;");	
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
