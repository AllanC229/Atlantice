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
    	
    	if (h == null || h.getAttribute("activeUser") == null) { //Si la session n'existe pas, renvoie vers la page de connexion
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
		out.print("<!Doctype html><html><head><meta charset='utf-8'/> "
				+ "<link href='licence.css' rel='stylesheet'>"
				+ "</head>");  //revoir l'alignement des boutons en haut de la page
	        		
	        out.println(Header.afficherEntete(activeUser));
	    	
	        out.println("<td><div class='formulaire-adherent'>"
	        		  +"<form action='ControleurAjtAdherent' method='POST'>"
	        		  +"<h1>Création d'un adhérent</h1><br>"
		    		  + "<h2>(*) Renseignements obligatoires</h2><br>"
	        		  +"<table>"
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
	        		  +"<select id='choix-categorie'>");
	        //+"<select name='categories[]' id='choix-categorie' multiple required");
	        
	        for(Map.Entry<String, String> entry : activeUser.categoriesUser.entrySet()) {
				
				out.print("<option value=\""+entry.getKey()+"\">"+entry.getValue()+"</option>");  
			}
	        
	        out.println("</select><br><div id='listeCategories'></div><div id='hiddenCategories'></div></td></tr>");

	        out.println("<script>"
	                + "const select = document.getElementById('choix-categorie');"
	                + "const liste = document.getElementById('listeCategories');"
	                + "const hiddenContainer = document.getElementById('hiddenCategories');"
	                + "select.addEventListener('change', function() {"
	                + "			console.log('change détecté, value =', this.value);"
	                + "    const value = this.value;"
	                + "    if (!value) return;"
	                + "    const texte = this.options[this.selectedIndex].text;"
	                + "			console.log('texte =', texte);"
	                + "    if (document.getElementById('cat-' + value)) {"
	                + "        this.selectedIndex = 0;"
	                + "        return;"
	                + "    }"
	                + "			console.log('liste =', liste, 'hiddenContainer =', hiddenContainer);"
	                + "    const badge = document.createElement('span');"
	                + "    badge.id = 'cat-' + value;"
	                + "    badge.style.margin = '5px';"
	                + "    badge.style.padding = '3px 8px';"
	                + "    badge.style.border = '1px solid #ccc';"
	                + "    badge.style.borderRadius = '10px';"
	                + "    const span = document.createElement('span');"
	                + "    span.textContent = texte + ' ';"
	                + "    badge.appendChild(span);"
	                + "    const btn = document.createElement('button');"
	                + "    btn.type = 'button';"
	                + "    btn.textContent = '✖';"
	                + "    btn.dataset.value = value;"
	                + "    btn.addEventListener('click', function() { retirerCategorie(this.dataset.value); });"
	                + "    badge.appendChild(btn);"
	                + "    liste.appendChild(badge);"
	                + "				console.log('badge ajouté, liste.children =', liste.children.length);"
	                + "    const hidden = document.createElement('input');"
	                + "    hidden.type = 'hidden';"
	                + "    hidden.name = 'categories[]';"
	                + "    hidden.value = value;"
	                + "    hidden.id = 'hidden-' + value;"
	                + "    hiddenContainer.appendChild(hidden);"
	                + "				console.log('hidden ajouté');"
	                + "    this.selectedIndex = 0;"
	                + "});"
	                + "function retirerCategorie(value) {"
	                + "    document.getElementById('cat-' + value)?.remove();"
	                + "    document.getElementById('hidden-' + value)?.remove();"
	                + "}"
	                + "</script>");
	        
	        if (activeUser.getRole().equals("admin")) { //Rajout de la sélection du rôle au moment de la création de l'adhérent (accessible seulement par l'admin)
	        	
	        	out.println("<tr><td>Rôle</td>"
	        			+ "<td><select name='role' id='choixrole'>"
	        			+ "<option value ='adherent'> Adhérent </option>"
	        			+ "<option value ='modif'> Responsable de catégorie </option>"
	        			+ "<option value = 'admin'> Administrateur </option>"
	        			+ "</select></td></tr>");	        	
	        }
	        

	        out.println("<tr><td>Commentaire: </td> <td><textarea rows=4 cols=40 name='commentaire'></textarea></td></tr><br>"
	    			+ "</table></div>"
	    			+ "<div class='actions'><input type='submit' value='Valider'></form></div>");     
	        
	        if (request.getAttribute("erreur") != null) {
	        	out.println(request.getAttribute("erreur"));
	        }
			
			out.println("</body></html>");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);

    }

}