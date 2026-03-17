package view;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Adherent;

/**
 * Servlet implementation class FicheSportive
 */
@WebServlet("/FicheSportive")
public class FicheSportive extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public FicheSportive() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession h = request.getSession();
		String role = (String) h.getAttribute("role");
		
		
		if ("fichesport".equals(request.getParameter("fichesport"))) {
			//Créer un tableau qui demande quel adhérent afficher
			String affichage = "";
			//Créer un tableau qui demande quel adhérent afficher
			affichage = "<html><body> Ca fonctionne bien</body></hmtl>";
			response.getWriter().append(affichage);
		}
		
		else {
		Adherent a;
		String affichage = "";
		for (Adherent adh : (ArrayList<Adherent>) h.getAttribute("adherents")) {
			if(adh.getNumLicence().equals(request.getParameter("numLic"))) {
				a = adh;
				affichage = "<!Doctype html><html><head><meta charset=\"utf-8\"/> \r\n"
						+"<link href=\"licence.css\" rel=\"stylesheet\">"
						+ "</head><body><h1 align=center> Formulaire adhérent : </h1></br>"
						+ "	<div align=center><form action=\"ControleurFicheAdministrative\" method=GET>"
						+ " <table border>"
						+ "		<tr><td>Nom : </td><td><input type='text' name='nom' value='"+a.getNom()+"' ></br></td></tr>"
						+ "		<tr><td>Prénom : </td><td><input align=center type='text' name='prenom' value='"+a.getPrenom()+"'></br></td></tr>"
						+ "		<tr><td>Numéro de licence : </td><td><input type='text' name='numeroLicence' value='"+a.getNumLicence()+"'></br></td></tr>"
						+ "		<tr><td>Dernière année de licence active : </td><td><input type='text' name='derniereAnneeLicence' value='"+a.getDerniereLicenceActive()+"'></br></td></tr>"
						+ "		<tr><td>Année de naissance : </td><td><input align=center type='text' name='anneeNaissance' value='"+a.getAnneeNaissance()+"'></br></td></tr>"
						+ "		<tr><td>Téléphone 1 : </td><td><input type='text' name='telephone1' value='"+a.getTel1()+"'></br></td></tr>"
						+ "		<tr><td>Téléphone 2 : </td><td><input align=center type='text' name='telephone2' value='"+a.getTel2()+"'></br></td></tr>"
						+ "		<tr><td>Adresse 1 : </td><td><input type='text' name='adresse1' value='"+a.getAdresse1()+"'></br></td></tr>"
						+ "		<tr><td>Adresse 2 : </td><td><input align=center type='text' name='adresse2' value='"+a.getAdresse2()+"'></br></td></tr>"
						+ "		<tr><td>Mail 1 : </td><td><input type='text' name='mail1' value='"+a.getMail1()+"'></br></td></tr>"
						+ "		<tr><td>Mail 2 : </td><td><input align=center type='text' name='mail2' value='"+a.getMail2()+"'></br></td></tr>"
						+ "		<tr><td>Commentaire : </td><td><input type='text' name='commentaire' value='"+a.getCommentaire()+"'></br></td></tr>"
						+ "		<tr><td>Contact 1 : </td><td><input align=center type='text' name='contact1' value='"+a.getContact1()+"'></br></td></tr>"
						+ "		<tr><td>Contact 2 : </td><td><input type='text' name='contact2' value='"+a.getContact2()+"'></br></td></tr>"
						+ "		<tr><td>Sexe : </td><td><input align=center type='text' name='sexe' value='"+a.getSexe()+"'></br></td></tr>"
						+ "		<tr><td>Droit à l'image : </td><td><input align=center type='text' name='droitImage' value='"+a.getDroitImage()+"'></br></td></tr>"
					//	+ "		<tr><td>Critère 1 : </td><td><input align=center type='text' name='critere1' value='"+a.getCritere1()+"'></input></br></td></tr>"  //modif critere le 04/12 12:56
						+ "</table>";
						if ("admin".equals(role)) {				
						affichage += "	<input type=submit name='modifAd' value=\"Modification de l'adhérent\"></input>";
						}
						affichage += "<div><form action=\"Accueil\" name=\"boutonAccueil\" value=\"accueil\" method=\"get\">"
						+ "<input type=\"submit\" name=\"accueil\" value=\"Accueil\"> </form>"
						+ "</div></body></html>";
						
			}
		}
		
		
		response.getWriter().append(affichage);
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
