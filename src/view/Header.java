package view;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Utilisateur;

public class Header {
	
	/* methode statique header avec logo/nom user connecté (=active user), Bouton déco à rajouter sur toute les vues, 
	 * accueil, Méthode statique qui te renvoie une string qui comprend le code HTML de ce que tu veux afficher, 
	 * tu n’auras plus qu’à faire un out.println(la méthode) dans chaque vue
	 */
	

	public Header() {
		//constructeur par défaut
	}
	
	static String afficherEntete(Utilisateur activeUser) { //méthode statique générant l'entête de l'appli
		
		//ajouter sur chaque vue : + "<link href='header.css' rel='stylesheet'>"
		
			String htmlEntete = "<body> " 
					+ "<header>"
					+ "<nav>"
					+ "	<div id=\"logoClub\">"
					+ "		<img src='images/logo.png' alt='Logo du club sportif' style='max-width:50%; height:auto;'>" //logo format svg?
					+ "</div>"
					+ "<div id='profil'>" + activeUser.getPrenom() +" "+activeUser.getNom()+" "+activeUser.getRole()+""
					+ "			<form action='ControleurDeconnexion' name='boutondeconnexion' method='get'> <input type ='submit' name='deconnexion' value='Se déconnecter'> </form><br>"
					+ "			<form action='ControleurAccueil' name='accesprofil' method='get'> <input type ='submit' name='direction' value='Profil'> </form>"
					+ "</div>"
					+ "</nav>"
					+ "</header>";
			
			return htmlEntete;

		
		
	}
	
}
