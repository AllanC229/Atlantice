package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.DAOAcces;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Adherent;
import model.Utilisateur;

@WebServlet("/ControleurModifInfosProfil")
public class ControleurModifInfosProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ControleurModifInfosProfil() {
        // TODO Auto-generated constructor stub
    }
    
    @SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

    			HttpSession h = request.getSession(false);
    			
    			if (h == null) { //Si la session n'existe pas, renvie vers la page de connexion
    			    response.sendRedirect("/Connexion");
    			    return;
    			}
    			
    			Adherent activeAdherent = (Adherent) h.getAttribute("activeAdherent");
    			Utilisateur activeUser = (Utilisateur) h.getAttribute("activeUser");
    			
    			
    			if (request.getParameter("valider").equals("validerinfos")) {
    			
	    			String nom = request.getParameter("nom");
	    			String prenom = request.getParameter("prenom");
	    			String tel1 = request.getParameter("tel1");
	    			String tel2 = request.getParameter("tel2");
	    			String adresse1 = request.getParameter("adresse1");
	    			String adresse2 = request.getParameter("adresse2");
	    			String mail1 = request.getParameter("mail1");
	    			String mail2 = request.getParameter("mail2");
	    			String contact1 = request.getParameter("contact1");
	    			String contact2 = request.getParameter("contact2");
	    			String sexe = request.getParameter("sexe");
	    			String droitimage = request.getParameter("droitimage");
	    			
	    			if (nom.equals("")) {	//tout ces if servent à conserver les parametres qui n'ont pas été modifiés
	    				nom = activeAdherent.getNom();
	    			}
	    			
	    			if (prenom.equals("")) {
	    				prenom = activeAdherent.getPrenom();
	    			}
	    			
	    			if (tel1.equals("")) {
	    				tel1 = activeAdherent.getTel1();
	    			}
	    			
	    			if (tel2.equals("")) {
	    				tel2 = activeAdherent.getTel2();
	    			}
	    			
	    			if (adresse1.equals("")) {
	    				adresse1 = activeAdherent.getAdresse1();
	    			}
	    			
	    			if (adresse2.equals("")) {
	    				adresse2 = activeAdherent.getAdresse2();
	    			}
	    			
	    			if (mail1.equals("")) {
	    				mail1 = activeAdherent.getMail1();
	    			}
	    			
	    			if (mail2.equals("")) {
	    				mail2 = activeAdherent.getMail2();
	    			}
	    			
	    			if (contact1.equals("")) {
	    				contact1 = activeAdherent.getContact1();
	    			}
	    			
	    			if (contact2.equals("")) {
	    				contact2 = activeAdherent.getContact2();
	    			}
	    			
	    			if (sexe.equals("")) {
	    				sexe = activeAdherent.getSexe();
	    			}
	    			
	    			if (droitimage.equals("")) {
	    				droitimage = activeAdherent.getDroitImage();
	    			}
	    			
	    			
	    			DAOAcces dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "webadherents", "root", "");
	    			
	    			try {
	    				
	    				
		    			Connection conn = dao.getConn();	//on fait la mise à jour dans la BDD des infos de l'adhérent
		    			String sqlmodifinfos = "UPDATE adherents SET nom = ? , prenom = ? , tel1 = ? , tel2 = ? , adresse1 = ? , adresse2 = ? ,"
		    					+ "mail1 = ? , mail2 = ? , contact1 = ? , contact2 = ? , sexe = ? , droitimage = ? WHERE numerolicence = ? ;";
		    				
		    			PreparedStatement modifinfos = conn.prepareStatement(sqlmodifinfos);
		    			modifinfos.setString(1, nom);
		    			modifinfos.setString(2, prenom);
		    			modifinfos.setString(3, tel1);
		    			modifinfos.setString(4, tel2);
		    			modifinfos.setString(5, adresse1);
		    			modifinfos.setString(6, adresse2);
		    			modifinfos.setString(7, mail1);
		    			modifinfos.setString(8, mail2);
		    			modifinfos.setString(9, contact1);
		    			modifinfos.setString(10, contact2);
		    			modifinfos.setString(11, sexe);
		    			modifinfos.setString(12, droitimage);
		    			modifinfos.setString(13, activeAdherent.getNumLicence());
		    			
		    			modifinfos.executeUpdate();
		    			activeUser.lastseen(activeUser.getIdConnexion(), " modification de ses infos de profil;");
		    			
		    			activeAdherent.setNom(nom);		//Ici on redéfinit les parametres de l'adhérent actif avec ce qui a été modifié
		    			activeAdherent.setPrenom(prenom);	//Normalement on arrive ici seulement si la MaJ a été faite correctement
		    			activeAdherent.setTel1(tel1);		//Donc pas de risques de modifier notre adhérent actif sans avoir modifié la BDD
		    			activeAdherent.setTel2(tel2);
		    			activeAdherent.setAdresse1(adresse1);
		    			activeAdherent.setAdresse2(adresse2);
		    			activeAdherent.setMail1(mail1);
		    			activeAdherent.setMail2(mail2);
		    			activeAdherent.setContact1(contact1);
		    			activeAdherent.setContact2(contact2);
		    			activeAdherent.setSexe(sexe);
		    			activeAdherent.setDroitImage(droitimage);
		    			
	    			}
	    			
	    			catch(SQLException e) {
	    				System.out.println("mauvaise MaJ de l'adherent");
	    				e.printStackTrace();
	    			}
	    		
	    			dao.closeConnection();
	    			request.getRequestDispatcher("/Profil").forward(request, response);
    			}
    			
    			else if (request.getParameter("valider").equals("validermdp")) {
    				
    				DAOAcces dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "webadherents", "root", "");
    				Connection conn = dao.getConn();
    				
    				try {
    					
    					PreparedStatement checkmdp = conn.prepareStatement("SELECT motdepasse FROM adherents WHERE numerolicence = ?;");
    					checkmdp.setString(1,  activeAdherent.getNumLicence());	//Ce bloc sert à aller chercher le mdp qui correspond à l'utilisateur en cours
    					ResultSet mdp = checkmdp.executeQuery();
    					String mdpactuel = null;
    					
    					while (mdp.next()) {
    						mdpactuel = mdp.getString("motdepasse");	//On stocke le résultat de la requête (donc le mdp de l'utilisateur actif)
    					}
    					
    					if (mdpactuel.equals(request.getParameter("mdpactuel"))) { //On verifie si le mdp en BDD correspond bien à celui rentré par l'utilisateur
	    						
	    					
		    				if ((request.getParameter("nouvmdp")).equals(request.getParameter("confnouvmdp"))) { //On verifie que nouvmdp et confnouvmdp correspondent bien
		    				
			    				try {
				    				    			
					    			PreparedStatement modifmdp = conn.prepareStatement("UPDATE adherents SET motdepasse = ? WHERE motdepasse = ? ;");
					    			modifmdp.setString(1, request.getParameter("nouvmdp"));
					    			modifmdp.setString(2, request.getParameter("mdpactuel"));
					    			System.out.println(modifmdp);
					    			
					    			modifmdp.executeUpdate();
					    			activeUser.lastseen(activeUser.getIdConnexion(), " changement de son mot de passe;");
					    			
			    				}
				    			
				    			catch(SQLException e) {
				    				System.out.println("mauvaise MaJ du MdP");
				    				e.printStackTrace();
				    			}
			    				
			    				dao.closeConnection();
			    				request.getRequestDispatcher("/Profil").forward(request, response);
		    				}
		    				
		    				else {
		    					dao.closeConnection();
		    					request.getRequestDispatcher("/Profil").forward(request, response); //Si nouvmdp et confnouvmdp ne correspondent pas
		    				}
    					}
    					
    					else {
    						dao.closeConnection();
    						request.getRequestDispatcher("/Profil").forward(request, response); //Si mdpactuel correspond pas au mdp dans BDD
    						
    					}
    				}
    			
    				catch(SQLException e) {
    				System.out.println("mauvaise MaJ du MdP");
    				e.printStackTrace();
    				}
    			}
}
}
