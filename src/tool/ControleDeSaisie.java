package tool;

import java.util.regex.Pattern;

public class ControleDeSaisie {

	
	//regex pour les mails accepte uniquement le format: lettres/chiffres + @ + des lettres/chiffres + des lettres
   public static String regexPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	
    
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
    
    
	//Cette fonction vérifie si la valeur saisie lors de la modifications de critères est bien un entier entre 1 et 5 (inclus). Le but est d'éviter de saisir des valeurs
    //aberrantes ou des chaines de caractères vulnérables aux injections.
    public static boolean ControleSaisieCritere(String valeur) {
        try {
            if (Integer.parseInt(valeur) < 0 || Integer.parseInt(valeur) > 5) {          
            return false;
        }
            else {
            	return true;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

	
	public ControleDeSaisie() {
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	
}
