package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Pattern;

import org.mindrot.jbcrypt.BCrypt;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Utilisateur;
import connection.DAOAcces;

/**
 * Servlet implementation class ControleurAjtMatiere
 */
@WebServlet("/ControleurAjtAdherent")
public class ControleurAjtAdherent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControleurAjtAdherent() {
        super();
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
		
		//Instanciation
		DAOAcces dao = null;
		Connection conn = null;
		PreparedStatement psAdh = null;
		PreparedStatement psSportif = null;
		PreparedStatement psCategorie = null ;
		
		//regex pour les mails accepte uniquement le format: lettres/chiffres + @ + des lettres/chiffres + des lettres
        String regexPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        		
        //on récupère les données du formulaire
        String nomAdh = request.getParameter("nmAdh");
        String prenomAdh = request.getParameter("pnmAdh");
        String derAnneeLic = request.getParameter("derAnneeLic");
        String numeroLic = request.getParameter("numLic");
        String anneeAdh = request.getParameter("anneeAdh");
        String numTel1 = request.getParameter("numTel1");
        String numTel2 = request.getParameter("numTel2");
        String adresse1 = request.getParameter("adresse1");
        String adresse2 = request.getParameter("adresse2");
        String mail1 = request.getParameter("mail1");
        String mail2 = request.getParameter("mail2");
        String commentaire = request.getParameter("commentaire");
        String[] categories = request.getParameterValues("categories[]");
        String role = request.getParameter("role");
        
        //Si un des champs requis est vide, renvoie au formulaire de CreationAdhrent
        if(nomAdh.equals("") || prenomAdh.equals("")|| numeroLic.equals("") || derAnneeLic.equals("") 
        	|| anneeAdh.equals("") || numTel1.equals("") || adresse1.equals("") || mail1.equals("") 
        	|| ("").equals(request.getParameter("mdpprov"))) {           	
        	request.setAttribute("cs", "vide");
        	request.getRequestDispatcher("/CreationAdherent").forward(request, response);
        	return;
        }
        
        numTel2 = videVersNull(numTel2);
        adresse2 = videVersNull(adresse2);
        mail2 = videVersNull(mail2);
        commentaire = videVersNull(commentaire);
        System.out.println("vérif méthode videVersNull pour commentaire : " + commentaire);
        
        //appeler la méthode des caractères interdits
        ArrayList<String> champsATester = new ArrayList<>();
        champsATester.add(nomAdh);
        champsATester.add(prenomAdh);
        champsATester.add(derAnneeLic);
        champsATester.add(numeroLic);
        champsATester.add(anneeAdh);
        champsATester.add(numTel1);
        champsATester.add(numTel2);
        champsATester.add(adresse1);
        champsATester.add(adresse2);
        champsATester.add(commentaire);
        champsATester.add(role);

        for (String champ : champsATester) {
            if (caractereInterdit(champ)) {
				// insérer la tentative d'injection dans les logs : 
            	try {
					activeUser.lastseen(activeUser.getIdConnexion(), " tentative insertion caractère interdit dans la BDD;");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                // pas donner d'indice quant à la nature de l'erreur (request.setAttribute("erreur", "Caractère interdit détecté (< > \")");)
                getServletContext().getRequestDispatcher("/CreationAdherent").forward(request, response);
                return;
            }
        }

        if (categories != null) {
            for (String cat : categories) {
                if (caractereInterdit(cat)) {
    				// insérer la tentative d'injection dans les logs : 
                	try {
						activeUser.lastseen(activeUser.getIdConnexion(), " tentative insertion caractère interdit dans les categ dans la BDD;");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                	// pas donner d'indice quant à la nature de l'erreur (request.setAttribute("erreur", "Caractère interdit détecté (< > \")");)                    getServletContext().getRequestDispatcher("/CreationAdherent").forward(request, response);
                    getServletContext().getRequestDispatcher("/CreationAdherent").forward(request, response);
                	return;
                }
            }
        }
        
        // format mail1 invalide
        if (patternMatches(mail1, regexPattern) == false) {
	        request.setAttribute("erreur", "Adresse mail1 invalide");
	        getServletContext().getRequestDispatcher("/CreationAdherent").forward(request, response);
	        System.out.println("mail1 invalide:" + mail1);
	        return;
        } 
        // format mail2 invalide
        if(mail2 != null) {
	        if (patternMatches(mail2, regexPattern) == false) {
		        request.setAttribute("erreur", "Adresse mail2 invalide");
		        getServletContext().getRequestDispatcher("/CreationAdherent").forward(request, response);
		        System.out.println("mail2 invalide:" + mail2);
		        return;
	        }
        }
        
			try {
				dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "webadherents", "root", ""); 
				conn = dao.getConn();
				
				activeUser.lastseen(activeUser.getIdConnexion(), " ajout de l'adhérent "+ request.getParameter("numLic") +" dans la BDD;");
				
				// désactivation du mode de validation automatique (auto-commit) => gestion de la transaction manuelle
			    conn.setAutoCommit(false);
			    
	            
	          //Modification pour effectuer une requête préparée, 08/12 10:11
	          String mdpprov = BCrypt.hashpw(request.getParameter("mdpprov"), BCrypt.gensalt());
	          String sqlAdh = "INSERT INTO adherents (numerolicence, nom, prenom, dernierelicenceactive, annee, tel1, tel2, adresse1, adresse2, mail1, mail2, commentaire, role, motdepasse) "
	                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);"; //PENSE BETE AJOUT NB TENTATIVE CONNEXION --> tentativeconnexion a une valeur DEFAULT de 5 en BDD, pas besoin donc de renseigner sa valeur à l'insertion d'une nouvelle ligne (signé : Allan)
	          
	          psAdh = conn.prepareStatement(sqlAdh);
	          psAdh.setString(1, numeroLic);
	          psAdh.setString(2, nomAdh);
	          psAdh.setString(3, prenomAdh);
	          psAdh.setString(4, derAnneeLic);
	          psAdh.setString(5, anneeAdh);
	          psAdh.setString(6, numTel1);
	          psAdh.setString(7, numTel2);
	          psAdh.setString(8, adresse1);
	          psAdh.setString(9, adresse2);
	          psAdh.setString(10, mail1);
	          psAdh.setString(11, mail2);
	          psAdh.setString(12, commentaire);
	          psAdh.setString(13, role);
	          psAdh.setString(14, mdpprov);
	          psAdh.executeUpdate();
	          
	          String sqlCritere = "INSERT INTO critereadherent (numerolicence, idcritere, valcritere) "
	                  			+ "SELECT ?, idcritere, 0 FROM criteres;";          
	          
	          psSportif = conn.prepareStatement(sqlCritere);
	          psSportif.setString(1, numeroLic);
	          
	          psSportif.executeUpdate(); 
	          
	          if (categories != null && categories.length != 0) {
	        	  System.out.println("entrée dans la requete");
	        	  String sqlCat = "";
	        	  for (String indice : categories) {
	        		  
			          sqlCat = "INSERT INTO categorieadherent (numLic, idcategorie) VALUES (?, ?);";
			          psAdh = conn.prepareStatement(sqlCat);
			          psAdh.setString(1, numeroLic);
			          psAdh.setString(2,  indice); 
			          psAdh.executeUpdate(); 
			          System.out.println(sqlCat);
	        	  }
	        	  System.out.println(sqlCat);
	          }
	
	            	
	          	conn.commit();
				request.setAttribute("succes", "Adhérent ajouté !");
				request.getRequestDispatcher("/Accueil").forward(request, response);
				System.out.println("adhérent ajouté");

	            
	
			 } catch(SQLException e) {
				System.out.println("Probleme SQL creationAdherent !!");
				if (conn != null) { //Si la connection n'est pas nulle, retour en arrière = annule la transaction
					try {
						conn.rollback();
						System.out.println("Transaction annulée : rollback effectué");
					} catch (SQLException ex) {
						System.out.println("Connexion ok mais probleme SQL creationAdherent !!");
						ex.printStackTrace();
					}
				}
				e.printStackTrace();
			} finally {
				if (dao != null) { // vérification nécessaire : si la construction a échoué avant la ligne d'affectation, dao vaut encore null
			        dao.closeConnection();
			    }
				dao.closeConnection();
			}
	
		response.sendRedirect(request.getContextPath() + "/Accueil");
	} 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

    // Méthode qui vérifie que les mails correspond au pattern regex 
    public boolean patternMatches(String userInput, String regexPattern) {
        return Pattern.compile(regexPattern)
            .matcher(userInput)
            .matches();
    }
    
    //Méthode qui met à null les champs que non requis laissés vides
    public String videVersNull(String s) {
    	if (s.equals("") || s.isEmpty() || s.isBlank()) {
    		s = null;
    	}
        return s;
    }

    // Méthode pour vérifier si un champ contient des chevrons et/ou des guillemets, si oui = renvoie à la vue CreationAdhérent
    public boolean caractereInterdit(String entreeUtilisateur) {
    	    if (entreeUtilisateur == null) {
    	        return false;
    	    }
    	    return entreeUtilisateur.contains("<") || entreeUtilisateur.contains(">") || entreeUtilisateur.contains("\"")
    	    	|| entreeUtilisateur.contains("*") || entreeUtilisateur.contains("\'") ||entreeUtilisateur.contains(";");
    	}
	
}
