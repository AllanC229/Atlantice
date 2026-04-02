package view;

public class Header {
	
	/* methode statique header avec logo/nom user connecté (=active user), Bouton déco à rajouter sur toute les vues, 
	 * accueil, Méthode statique qui te renvoie une string qui comprend le code HTML de ce que tu veux afficher, 
	 * tu n’auras plus qu’à faire un out.println(la méthode) dans chaque vue
	 */
	

	public Header() {
		//constructeur par défaut
	}
	
	static String getEntete() { //méthode statique générant l'entête de l'appli
		
			String htmlEntete = "<!Doctype html><html><head><meta charset=\\\"utf-8\\\"/>\"\r\n"
					+ "				+ \" <link href=\\\"licence.css\\\" rel=\\\"stylesheet\\\">" //créer un css spécifique au header 
					+ "<header>"
							+ "        <nav>"
							+ "<div id=\"logoClub\">"
							+ "		<img src='images/logo-example.png' alt='Descriptive text for the image' style='max-width:100%; height:auto;'>" //logo format svg?
							+ "</div>"
							+ "<div id='profil'>"
							+ "		<a href=\"#lafleur\">Projet Lafleur</a>\r\n"
							+ "     <a href=\"#hunvre\">Hunvre</a>\r\n"
							+ "</div>"
							+ "</nav>\r\n"
							+ "</header>";
			
			return htmlEntete;

		
		
	}
	
}
