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
					
						    +"<header>"
						    + "<nav>"

						    + "<div id='logoClub'>"
						    + "    <img src='images/logo.png' alt='Logo du club sportif'>"
						    + "</div>"

						    + "<div id='menuPrincipal'>"
						    + "<form action='Accueil' name='retouraccueil' method='POST' > <input type = 'submit' name='retouraccueil' value='Accueil'> </form>"

						;

						if(activeUser.getRole().equals("admin") || activeUser.getRole().equals("modif"))
						{
							htmlEntete +=
						        "<form action='ControleurAccueil' method='POST'>"
						        + "<input type='submit' name='direction' value='Catégories'>"
						        + "</form>"

						        + "<form action='ControleurAccueil' method='POST'>"
						        + "<input type='submit' name='direction' value='Créer un adhérent'>"
						        + "</form>"
						        
						    	+ "<form action='RechercheAdherent' method='POST'>"
						    	+ "<input type='hidden' name='recherche' value='recherche'>"
						    	+ "<input type='submit' value='Rechercher un adhérent'>"
						    	+ "</form>"
						    ;
						}

						htmlEntete +=

						    "<form action='ControleurAccueil' method='POST'>"
						    + "<input type='hidden' name='ficheadmin' value='ficheadmin'>"
						    + "<input type='submit' value='Fiches administratives'>"
						    + "</form>"

						    /*
						    + "<form action='ControleurAccueil' method='POST'>"
						    + "<input type='hidden' name='fichesport' value='fichesport'>"
						    + "<input type='submit' value='Fiches sportives'>"
						    + "</form>"
							*/
						    + "<form action='ControleurAccueil' method='POST'>"
						    + "<input type='hidden' name='critere' value='critere'>"
						    + "<input type='submit' name='critere' value='Critères'>"
						    + "</form>"

						    + "</div>"

						    + "<div id='profil'>"
						    + activeUser.getPrenom() + " "
						    + activeUser.getNom() + "<br>"
						    + activeUser.getRole()

						    + "<form action='ControleurAccueil' method='POST'>"
						    + "<input type='submit' name='direction' value='Profil'>"
						    + "</form>"

						    + "<form action='ControleurDeconnexion' method='POST'>"
						    + "<input type='submit' name='deconnexion' value='Déconnexion'>"
						    + "</form>"

						    + "</div>"

						    + "</nav>"
						    + "</header>"
						;
			
			return htmlEntete;		
	}
	
}
