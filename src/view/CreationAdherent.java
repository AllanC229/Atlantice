package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Categorie;
import model.Utilisateur;
import connection.DAOAcces;
import controller.ControleurConnexion;

/*
 * Servlet implementation class CreationMatiere
 */
@WebServlet("/CreationAdherent")
public class CreationAdherent extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /*
     * @see HttpServlet#HttpServlet()
     */
    public CreationAdherent() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession h = request.getSession(false);
    	
    	if (h == null || h.getAttribute("activeUser") == null) { //Si la session n'existe pas, renvie vers la page de connexion
		    response.sendRedirect("/Connexion");
		    return;
		}
    	
        PrintWriter out =response.getWriter();
        Utilisateur activeUser = (Utilisateur) h.getAttribute("activeUser");
        if( request.getAttribute("cs") == "vide") {
	        out.println("<script language='javascript'>");
	    	out.println("alert('Veuillez renseigner les champs obligatoires.')");
	    	out.println("</script>");
        }
 
			//Formulaire d'ajout d'adhérent
		out.print("<!Doctype html><html><head><meta charset='utf-8'/> \r\n"
				+ "<link href='licence.css' rel='stylesheet'>"
				+ "<link href='header.css' rel='stylesheet'>"
				+ "</head>");  //revoir l'alignement des boutons en haut de la page
	        		
	        out.println(Header.afficherEntete(activeUser));   	
	        
			out.println("<table width=100%><tr><td>");
			
			out.println("<div class='sidebar' align='top'>"
					+ "<div class='logo'>Plan du site</div>"
					+ "<div class='menu'>");
		
					if(activeUser.getRole().equals("admin") || activeUser.getRole().equals("modif"))
					{
						out.print("<form action='ControleurAccueil' name='boutonajout' method='POST'> <input type = 'submit' name='direction' value='Catégories'> </form><br>");
						out.print("<form action='ControleurAccueil' name= 'boutonajout' value = 'ajoutAdherent' method='POST'> <input type = 'submit' name = 'direction' value='Créer un adhérent'> </form><br>");	
					}
					
					out.println("<form action = 'ControleurAccueil' method='POST'> <input type='hidden' name='ficheadmin' value='ficheadmin'> <input type='submit'  value='Consulter les fiches Administratives'> </form><br>"
					+ "<form action='RechercheAdherent' method='POST'> <input type='hidden' name='recherche' value='recherche'> <input type='submit' value='Rechercher un adhérent'> </form> <br>"
					+ "<form action='ControleurAccueil' method='POST'> <input type='hidden' name='fichesport' value='fichesport'> <input type='submit' value='Consulter les fiches sportives'> </form><br>"
					+ "<form action ='ControleurAccueil' method='POST'> <input type='hidden' name='critere' value='critere'><input type='submit' name='critere' value='Consulter les critères'></form><br>"
					+ "</div>"
					+ "</div></td>");
	        
	        //Afficher erreur mail invalide
	        if( request.getAttribute("erreur") == "Adresse mail1 invalide" ||  request.getAttribute("erreur") == "Adresse mail2 invalide" ) {
		        out.println("<script language='javascript'>");
		    	out.println("alert('Adresse mail invalide')");
		    	out.println("</script>");
	        }

	    	
	        out.println("<td><div align=center>"
	        		  +"<form action='ControleurAjtAdherent' method='POST'>"
	        		  +"<h1>Création d'un adhérent</h1><br>"
	        		  +"<table border=1>"
	        		  +"<tr><td>Nom de l'adhérent (*): </td><td><input type='text' name='nmAdh' required></td></tr>"
	        		  +"<tr><td>Prénom de l'adhérent (*): </td><td><input type='text' name='pnmAdh' required></td></tr>"
	        		  +"<tr><td>Numéro de licence (*): </td><td><input type='text' name='numLic' required></td></tr>"
	        		  +"<tr><td>Dernière licence active (*): </td><td><input type='text' name='derAnneeLic' required></td></tr>"
	        		  +"<tr><td>Année de naissance de l'adhérent (*): </td><td><input type='text' name='anneeAdh' required></td></tr>"
	        		  +"<tr><td>Numéro de téléphone 1 (*): </td><td><input type='tel' name='numTel1' required></td></tr>"
	        		  +"<tr><td>Numéro de téléphone 2: </td><td><input type='tel' name='numTel2'></td></tr>"
	        		  +"<tr><td>Adresse postale 1 (*): </td><td><input type='text' name='adresse1' required></td></tr>"
	        		  +"<tr><td>Adresse postale 2: </td><td><input type='text' name='adresse2'></td></tr>"
	        		  +"<tr><td>Adresse mail 1 (*): </td><td><input type='email' name='mail1' required></td></tr>"
	        		  +"<tr><td>Adresse mail 2: </td><td><input type='email' name='mail2'></td></tr>"
	        		  +"<tr><td>Contact 1: </td><td><input type='text' name='contact1'></td></tr>"
	        		  +"<tr><td>Contact 2: </td><td><input type='text' name='contact2'></td></tr>"
	        		  +"<tr><td>Sexe : </td><td><input type='text' name='sexe'></td></tr>"
	        		  +"<tr><td>Droit à l'image : </td><td><input type='text' name='droitImage'></td></tr>"
	        		  +"<tr><td>Catégorie(s) (*):</td><td>"
	        		  +"<select name='categories[]' id='choix-categorie' multiple required>");
	        
	        for(Map.Entry<String, String> entry : activeUser.categoriesUser.entrySet()) {
				
				out.print("<option value="+entry.getKey()+">"+entry.getValue()+"</option>");  
			}
	        
	        out.println("</select></td></tr>");
	        
	        if (activeUser.getRole().equals("admin")) { //Rajout de la sélection du rôle au moment de la création de l'adhérent (accessible seulement par l'admin)
	        	
	        	out.println("<tr><td>Rôle</td>"
	        			+ "<td><select name='role' id='choixrole'>"
	        			+ "<option value ='adherent'> Adhérent </option>"
	        			+ "<option value ='modif'> Responsable de catégorie </option>"
	        			+ "<option value = 'admin'> Administrateur </option>"
	        			+ "</select></td></tr>");	        	
	        }
	        

	        out.println("</table>"
	    			+ "Commentaire: <br> <textarea rows=4 cols=40 name='commentaire'></textarea><br>"
	    			+ "Renseignements obligatoires: (*)<br>"
	    			+ "<input type='submit' value='Valider'></form></div></td></tr>"
	    			+ "</table></body></html>");      
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);

    }

}