package view;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Adherent;
import model.Utilisateur;
import connection.DAOAcces;


/**
 * Servlet implementation class Index
 */
@WebServlet("/Tableau")
public class Tableau extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Tableau() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
//		PrintWriter out=response.getWriter();
//		
//		out.println("Tableau des adhérents de la catégorie"+request.getParameter("categorie"));
		doPost(request, response);
	}
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		PrintWriter out = response.getWriter();
		HttpSession h = request.getSession(false);
		
		if (h == null) { //Si la session n'existe pas, renvie vers la page de connexion
		    response.sendRedirect("/Connexion");
		    return;
		}
		
	    Utilisateur activeUser = (Utilisateur) h.getAttribute("activeUser");
		
		
		out.println("<!doctype html>"
				+ "<html>"
				+ "<head>"
				+ "<meta charset=\"utf-8\"/> "
				+ "<link href=\"licence.css\" rel=\"stylesheet\">"
				+ "</head>"
				+ "<body>"
				+ "<div align=center>"
				+ "<h1>Licenciés </h1><br><br><br>"
				+ "<div align=right>"
				+ "<form action=\"Accueil\" name=\"retouraccueil\" > <input type = \"submit\" name=\"retouraccueil\" value=\"Retour à l'accueil\"> </form>"
				+ "<div class=\"card-body\">"
				+ "<table style=\"width: 100%;\" id=\"example2\" class=\"table table-hover table-striped table-bordered\">"
				+ "<thead><tr>"
				+ "<th valign='middle'>Nom</th>"
				+ "<th valign='middle'>Prénom</th>"
				+ "<th valign='middle'>Catégorie</th>"
				+ "<th valign='middle'>Année</th>"
				+ "<th valign='middle'>Numéro de licence</th>"
				+ "<th valign='middle'>Dernière licence active 1</th>"
				+ "<th valign='middle'>Téléphone 1</th>"
				+ "<th valign='middle'>Téléphone 2</th>"
				+ "<th valign='middle'>Adresse 1</th>"
				+ "<th valign='middle'>Adresse 2</th>"
				+ "<th valign='middle'>Mail 1</th>"
				+ "<th valign='middle'>Mail 2</th>"
				+ "<th valign='middle'>Commentaire</th>");
		
		if (activeUser.getRole().equals("admin") || activeUser.getRole().equals("modif")) {
				out.println("<th valign='middle'>Fiches administratives</th>"
						  + "<th>Fiches sportives</th>");
		}	
		
		out.println("</tr></thead>");

		
			
		for(Adherent adh: (ArrayList<Adherent>)request.getAttribute("adherents")) {
			
				out.println(  "<tr>"
							+ "<td>"+  adh.getNom() + "</td>"
							+ "<td>"+  adh.getPrenom() + "</td>"
							+ "<td>");
				
							for (String categ : adh.categoriesAdh.values()) {
								out.println( ""+ categ +""); 
							}
							
				out.println(  "</td>"
							+ "<td>"+  adh.getAnneeNaissance() +"</td>"
							+ "<td>"+  adh.getNumLicence() +"</td>"
							+ "<td>"+  adh.getDerniereLicenceActive() +"</td>");
				
				if (adh.getTel1()!=null) {
					out.println("<td>"+  adh.getTel1() +"</td>");							
				}	
				
				else {
					out.println("<td></td> ");
				}
				
				if (adh.getTel2()!=null) {
					out.println("<td>"+  adh.getTel2() +"</td>");							
				}
				
				else {
					out.println("<td></td> ");
				}
				
				if (adh.getAdresse1()!=null) {
					out.println("<td>"+  adh.getAdresse1() +"</td>");							
				}
				
				else {
					out.println("<td></td> ");
				}
				
				if (adh.getAdresse2()!=null) {
					out.println("<td>"+  adh.getAdresse2() +"</td>");
							
				}
				
				else {
					out.println("<td></td> ");
				}
							 
				if (adh.getMail1()!=null) {
					out.println("<td>"+  adh.getMail1() +"</td>");							
				}
				
				else {
					out.println("<td></td>");
				}
				
				if (adh.getMail2()!=null) {
					out.println("<td>"+  adh.getMail2() +"</td>");						
				}
				
				else {
					out.println("<td></td>");
				}
				
				if (adh.getCommentaire()!=null) {
					out.println("<td>"+  adh.getCommentaire() +"</td>");							
				}
				
				else {
					out.println("<td></td> ");
				}
					
				if (activeUser.getRole().equals("admin") || activeUser.getRole().equals("modif")) {
				out.println("<td align='center'><form action='ControleurTableau'>"
						  + "<input type='hidden' name='numLic' value='"+adh.getNumLicence()+"' >"
						  + "<input type='submit' id='"+adh.getNumLicence()+"' name='fiche' value='Consulter/Modifier' width='100%'>"
						  + "</form></td>"
						  + "<td align='center'><form action='ControleurTableau'>" //Identifier sur quel bouton cliquer (Sportif ou consulter/modifier)
						  + "<input type='hidden' name='numLic' value='"+adh.getNumLicence()+"' >"
						  + "<input type='submit' id='"+adh.getNumLicence()+"' name='sportif' value='Suivi sportif' width='100%'>"
						  + "</form></td></tr>");
				}
				
				else {
					out.println("</td</tr>");
				}
		}
		out.println("</table></div>");
		h.setAttribute("adherents", (ArrayList<Adherent>)request.getAttribute("adherents"));
	
		if(activeUser.getRole().equals("admin") || activeUser.getRole().equals("modif"))
		{		
			out.println("<div><form action=\"CreationAdherent\" name=\"boutonAjoutAdherent\" value=\"ajoutAdherent\" method=\"get\">"
				+ "<input type=\"submit\" name=\"test\" value=\"Créer un adhérent\"> </form>"
				+ "</div></body></html>");
		}
		
	}


}


