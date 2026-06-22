package tool;

import java.util.regex.Pattern;

public class ControleDeSaisie {

	
	//regex pour les mails accepte uniquement le format: lettres/chiffres + @ + des lettres/chiffres + des lettres
    String regexPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	
    
 // Méthode qui vérifie que les mails correspond au pattern regex 
    public static boolean patternMatches(String userInput, String regexPattern) {
        return Pattern.compile(regexPattern)
            .matcher(userInput)
            .matches();
    }
    
    //Méthode qui met à null les champs que non requis laissés vides
    public static String videVersNull(String s) {
    	if (s.equals("") || s.isEmpty() || s.isBlank()) {
    		s = null;
    	}
        return s;
    }

    // Méthode pour vérifier si un champ contient des chevrons et/ou des guillemets, si oui = renvoie à la vue CreationAdhérent
    public static boolean caractereInterdit(String entreeUtilisateur) {
    	    if (entreeUtilisateur == null) {
    	        return false;
    	    }
    	    return entreeUtilisateur.contains("<") || entreeUtilisateur.contains(">") || entreeUtilisateur.contains("\"")
    	    	|| entreeUtilisateur.contains("*") || entreeUtilisateur.contains("\'") ||entreeUtilisateur.contains(";");
    	}
	


	
	public ControleDeSaisie() {
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	
}
