package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.mindrot.jbcrypt.BCrypt;

import connection.DAOAcces;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Adherent;
import model.Utilisateur;
import tool.ControleDeSaisie;


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
    			
    			if (h == null) { //Si la session n'existe pas, renvoie vers la page de connexion
    			    response.sendRedirect("/Connexion");
    			    return;
    			}
    			
    			Adherent activeAdherent = (Adherent) h.getAttribute("activeAdherent");
    			Utilisateur activeUser = (Utilisateur) h.getAttribute("activeUser");
    			
    			//regex pour les mails accepte uniquement le format: lettres/chiffres + @ + des lettres/chiffres + des lettres
    	        String regexMail = ControleDeSaisie.regexPattern;
    			
    			if (request.getParameter("valider").equals("validerinfos")) {
    			
	    			String nom = request.getParameter("nom");
	    			String prenom = request.getParameter("prenom");
	    			String tel1 = request.getParameter("tel1");
	    			String tel2 = request.getParameter("tel2");
	    			String adresse1 = request.getParameter("adresse1");
	    			String adresse2 = request.getParameter("adresse2");
	    			//String mail1 = request.getParameter("mail1");
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
	    			
	    		/*	if (mail1.equals("")) {
	    				mail1 = activeAdherent.getMail1();
	    			} */
	    			
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
	    			
	    	        //appeler la méthode des caractères interdits
	    	        ArrayList<String> champsATester = new ArrayList<>();
	    	        champsATester.add(nom);
	    	        champsATester.add(prenom);
	    	        champsATester.add(tel1);
	    	        champsATester.add(tel2);
	    	        champsATester.add(adresse1);
	    	        champsATester.add(adresse2);
	    	      //  champsATester.add(mail1);
	    	        champsATester.add(mail2);
	    	        champsATester.add(contact1);
	    	        champsATester.add(contact2);
	    	        champsATester.add(sexe);
	    	        champsATester.add(droitimage);
	    	        
	    	        for (String champ : champsATester) {
	    	            if (ControleDeSaisie.caractereInterdit(champ)) {
	    					// insérer la tentative d'injection dans les logs : 
	    	            	try {
	    						activeUser.lastseen(activeUser.getIdConnexion(), " tentative insertion caractère interdit dans modif Profil;");
	    					} catch (SQLException e) {
	    						// TODO Auto-generated catch block
	    						e.printStackTrace();
	    					}
	    	                // pas donner d'indice quant à la nature de l'erreur ?? 
	    	            	request.setAttribute("erreur", "Caractère interdit détecté");
	    	                getServletContext().getRequestDispatcher("/Profil").forward(request, response);
	    	                return;
	    	            }
	    	        }

	    	        // format mail2 invalide
	    	        if(mail2 != null) {
	    		        if (ControleDeSaisie.patternMatches(mail2, regexMail) == false) {
	    			        request.setAttribute("erreur", "Adresse mail 2 invalide");
	    			        getServletContext().getRequestDispatcher("/Profil").forward(request, response);
	    			        System.out.println("mail2 invalide:" + mail2);
	    			        return;
	    		        }
	    	        }	    			
	    			
	    			DAOAcces dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "webadherents", "root", "");
	    			
	    			try {
	    				
	    				
		    			Connection conn = dao.getConn();	//on fait la mise à jour dans la BDD des infos de l'adhérent
		    			String sqlmodifinfos = "UPDATE adherents SET nom = ? , prenom = ? , tel1 = ? , tel2 = ? , adresse1 = ? , adresse2 = ? ,"
		    					/*+ "mail1 = ? ,*/ + "mail2 = ? , contact1 = ? , contact2 = ? , sexe = ? , droitimage = ? WHERE numerolicence = ? ;";
		    				
		    			PreparedStatement modifinfos = conn.prepareStatement(sqlmodifinfos);
		    			modifinfos.setString(1, nom);
		    			modifinfos.setString(2, prenom);
		    			modifinfos.setString(3, tel1);
		    			modifinfos.setString(4, tel2);
		    			modifinfos.setString(5, adresse1);
		    			modifinfos.setString(6, adresse2);
		    			modifinfos.setString(7, mail2);
		    			modifinfos.setString(8, contact1);
		    			modifinfos.setString(9, contact2);
		    			modifinfos.setString(10, sexe);
		    			modifinfos.setString(11, droitimage);
		    			modifinfos.setString(12, activeAdherent.getNumLicence());
		    			
		    			/*modifinfos.setString(7, mail1);		Modifs du 22/06 pour ne plus rendre possible la modification du mail1, puisque celui-ci sert comme identifiant de connexion
		    			modifinfos.setString(8, mail2);			J'ai laissé en commentaire tout ce qui concerne le mail1, pour faciliter le rollback en cas de changement de décision
		    			modifinfos.setString(9, contact1);
		    			modifinfos.setString(10, contact2);
		    			modifinfos.setString(11, sexe);
		    			modifinfos.setString(12, droitimage);
		    			modifinfos.setString(13, activeAdherent.getNumLicence()); */
		    			
		    			modifinfos.executeUpdate();
		    			activeUser.lastseen(activeUser.getIdConnexion(), " modification de ses infos de profil;");
		    			
		    			activeAdherent.setNom(nom);		//Ici on redéfinit les parametres de l'adhérent actif avec ce qui a été modifié
		    			activeAdherent.setPrenom(prenom);	//Normalement on arrive ici seulement si la MaJ a été faite correctement
		    			activeAdherent.setTel1(tel1);		//Donc pas de risques de modifier notre adhérent actif sans avoir modifié la BDD
		    			activeAdherent.setTel2(tel2);
		    			activeAdherent.setAdresse1(adresse1);
		    			activeAdherent.setAdresse2(adresse2);
		    			//activeAdherent.setMail1(mail1);
		    			activeAdherent.setMail2(mail2);
		    			activeAdherent.setContact1(contact1);
		    			activeAdherent.setContact2(contact2);
		    			activeAdherent.setSexe(sexe);
		    			activeAdherent.setDroitImage(droitimage);
		    			activeUser.setNom(nom);		//On redéfinit les variables dans activeUser pour qu'elle soit mise à jour correctement pour la suite de la navigation
		    			activeUser.setPrenom(prenom);
		    			
	    			}
	    			
	    			catch(SQLException e) {
	    				System.out.println("mauvaise MaJ de l'adherent");
	    				e.printStackTrace();
	    			}
	    		
	    			dao.closeConnection();
	    			request.getRequestDispatcher("/Profil").forward(request, response);
    			}
    			
    			else if (request.getParameter("valider").equals("validermdp")) {
    				
    				//on récupère les données du formulaire
    		        String mdpactuel = request.getParameter("mdpactuel");
    		        String nouvmdp = request.getParameter("nouvmdp");
    		        String confnouvmdp = request.getParameter("confnouvmdp");
    		        
    		        if (mdpactuel.trim().isEmpty() || nouvmdp.trim().isEmpty() || confnouvmdp.trim().isEmpty()) {
    		        	request.setAttribute("erreur", "Veuillez remplir tout les champs !");
    		        	getServletContext().getRequestDispatcher("/Profil").forward(request, response);
    		        	return;
    		        } else {
    		        	//appeler la méthode des caractères interdits
    		            ArrayList<String> champsATester = new ArrayList<>();
    		            champsATester.add(mdpactuel);
    		            champsATester.add(nouvmdp);
    		            champsATester.add(confnouvmdp);
    		            
    		            for (String champ : champsATester) {
    		                if (ControleDeSaisie.caractereInterdit(champ)) {
    		    				// insérer la tentative d'injection dans les logs : 
    		                	try {
    		    					activeUser.lastseen(activeUser.getIdConnexion(), " tentative insertion caractère interdit dans changement mdp;");
    		    				} catch (SQLException e) {
    		    					// TODO Auto-generated catch block
    		    					e.printStackTrace();
    		    				}
    		                    // pas donner d'indice quant à la nature de l'erreur ?? 
    		                	request.setAttribute("erreur", "Caractère interdit détecté");
    		                    getServletContext().getRequestDispatcher("/Profil").forward(request, response);
    		                    return;
    		                }
    		            }
    		        }
    				
    				
    				DAOAcces dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "webadherents", "root", "");
    				Connection conn = dao.getConn();
    				
    				try {
    					
    					PreparedStatement checkmdp = conn.prepareStatement("SELECT motdepasse FROM adherents WHERE numerolicence = ?;");
    					checkmdp.setString(1,  activeAdherent.getNumLicence());	//Ce bloc sert à aller chercher le mdp qui correspond à l'utilisateur en cours
    					ResultSet mdp = checkmdp.executeQuery();
    					String mdpactuelBDD = null;
    					
    					while (mdp.next()) {
    						mdpactuelBDD = mdp.getString("motdepasse");	//On stocke le résultat de la requête (donc le mdp de l'utilisateur actif)
    					}
    					
    				 
    					if (BCrypt.checkpw(request.getParameter("mdpactuel"), mdpactuelBDD) == true) {	//On verifie si le mdp en BDD correspond bien à celui rentré par l'utilisateur	
	    					
		    				if ((request.getParameter("nouvmdp")).equals(request.getParameter("confnouvmdp"))) { //On verifie que nouvmdp et confnouvmdp correspondent bien
		    				
			    				try {
				    				    			
					    			PreparedStatement modifmdp = conn.prepareStatement("UPDATE adherents SET motdepasse = ? , changementmdp = 1 WHERE motdepasse = ? ;");
					    			modifmdp.setString(1, BCrypt.hashpw(request.getParameter("nouvmdp"), BCrypt.gensalt()));
					    			modifmdp.setString(2, mdpactuelBDD);
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
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
    
    
}
