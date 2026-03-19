package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Adherent;
import model.Utilisateur;

/**
 * Servlet implementation class FicheAdministrative
 */
@WebServlet("/FicheAdministrative")
public class FicheAdministrative extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public FicheAdministrative() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings({ "unchecked", "unchecked" })
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		HttpSession h = request.getSession();
		Utilisateur activeUser = (Utilisateur) h.getAttribute("activeUser");
		PrintWriter out=response.getWriter();
		Adherent a;

		String affichage = "";
		for (Adherent adh : (ArrayList<Adherent>) h.getAttribute("adherents")) {
			if(adh.getNumLicence().equals(request.getParameter("numLic"))) {
				a = adh;
				affichage = "<!Doctype html><html><head><meta charset=\"utf-8\"/> \r\n"
						+"<link href=\"licence.css\" rel=\"stylesheet\">"
						+ "</head><body><h1 align=center>Formulaire adhérent : </h1></br>"
						+ "	<div align=center><form action=\"ControleurFicheAdministrative\" method=GET>"
						+ " <table border>"
						+ "		<tr><td>Nom : </td><td><input type='text' name='nom' value='"+a.getNom()+"'></br></td></tr>"
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
						+ "</table>";
						if (activeUser.getRole().equals("admin")) {				
							affichage += "	<input type=submit name='modifAd' value=\"Modification de l'adhérent\"></input>"
							+ "<div><form action='ControleurSupprAdherent' method='get'>"
							+ "<input type='hidden' name='SupprAdherent' value='"+a.getNumLicence()+"'> <input type='submit' value='Supprimer cet adhérent'></div>";
							}
						affichage += "<div><form action=\"Accueil\" name=\"boutonAccueil\" value=\"accueil\" method=\"get\">"
						+ "<input type=\"submit\" name=\"accueil\" value=\"Accueil\"> </form>"
						+ "</div>"
						+ "</form></body></html>";

	
		if (activeUser.getRole().equals("admin") || activeUser.getRole().equals("modif")) {
	
			for (Adherent adh : (ArrayList<Adherent>) h.getAttribute("adherents")) {
				
				if(adh.getNumLicence().equals(request.getParameter("numLic"))) {
					
					a = adh;
					
					out.print("<!Doctype html><html><head><meta charset=\"utf-8\"/> \r\n"
							+ "<link href=\"licence.css\" rel=\"stylesheet\">"
							+ "</head><body><h1 align=center>Formulaire adhérent : </h1></br>"
							+ "<div align=center><form action=\"ControleurFicheAdministrative\" method=GET>"
							+ "<table border>"
							+ "<tr><td>Nom : </td><td><input type='text' name='nom' value='"+a.getNom()+"'></br></td></tr>"
							+ "<tr><td>Prénom : </td><td><input align=center type='text' name='prenom' value='"+a.getPrenom()+"'></br></td></tr>"							
							+ "<tr><td>Numéro de licence : </td><td><input type='text' name='numeroLicence' value='" +a.getNumLicence()+ "' readonly></br></td></tr>"
							+ "<tr><td>Dernière année de licence active : </td><td><input type='text' name='derniereAnneeLicence' value='"+a.getDerniereLicenceActive()+"'></br></td></tr>"
							+ "<tr><td>Année de naissance : </td><td><input align=center type='text' name='anneeNaissance' value='"+a.getAnneeNaissance()+"'></br></td></tr>"
							+ "<tr><td>Téléphone 1 : </td><td><input type='text' name='telephone1' value='"+a.getTel1()+"'></br></td></tr>"
							+ "<tr><td>Téléphone 2 : </td><td><input align=center type='text' name='telephone2' value='"+a.getTel2()+"'></br></td></tr>"
							+ "<tr><td>Adresse 1 : </td><td><input type='text' name='adresse1' value='"+a.getAdresse1()+"'></br></td></tr>"
							+ "<tr><td>Adresse 2 : </td><td><input align=center type='text' name='adresse2' value='"+a.getAdresse2()+"'></br></td></tr>"
							+ "<tr><td>Mail 1 : </td><td><input type='text' name='mail1' value='"+a.getMail1()+"'></br></td></tr>"
							+ "<tr><td>Mail 2 : </td><td><input align=center type='text' name='mail2' value='"+a.getMail2()+"'></br></td></tr>"
							+ "<tr><td>Commentaire : </td><td><input type='text' name='commentaire' value='"+a.getCommentaire()+"'></br></td></tr>"
							+ "<tr><td>Contact 1 : </td><td><input align=center type='text' name='contact1' value='"+a.getContact1()+"'></br></td></tr>"
							+ "<tr><td>Contact 2 : </td><td><input type='text' name='contact2' value='"+a.getContact2()+"'></br></td></tr>"
							+ "<tr><td>Sexe : </td><td><input align=center type='text' name='sexe' value='"+a.getSexe()+"'></br></td></tr>"
							+ "<tr><td>Droit à l'image : </td><td><input align=center type='text' name='droitImage' value='"+a.getDroitImage()+"'></br></td></tr>"
							+ "</table>"
							+ "<div>"
							+ "<input type='submit' name='modifAd' value='Modification de l'adhérent'></input>"							
							+ "<input type='submit' name='supprAd' value='Supprimer cet adhérent'></input>"
							+ "</form></div>");
				}

			}
		}
		
		else {
			
			for (Adherent adh : (ArrayList<Adherent>) h.getAttribute("adherents")) {
				
				if(adh.getNumLicence().equals(request.getParameter("numLic"))) {
					
					a = adh;
					out.print("<!Doctype html><html><head><meta charset=\"utf-8\"/> \r\n"
							+ "<link href=\"licence.css\" rel=\"stylesheet\">"
							+ "</head><body><h1 align=center>Formulaire adhérent : </h1></br>"
							+ "<div align=center>"
							+ "<table border>"
							+ "<tr><td>Nom : </td><td>"+a.getNom()+"</br></td></tr>"
							+ "<tr><td>Prénom : </td><td>"+a.getPrenom()+"</br></td></tr>"
							+ "<tr><td>Numéro de licence : </td><td>"+a.getNumLicence()+"</br></td></tr>"
							+ "<tr><td>Dernière année de licence active : </td><td>"+a.getDerniereLicenceActive()+"</br></td></tr>"
							+ "<tr><td>Année de naissance : </td><td>"+a.getAnneeNaissance()+"</br></td></tr>"
							+ "<tr><td>Téléphone 1 : </td><td><input type='text' name='telephone1' value='"+a.getTel1()+"</br></td></tr>"
							+ "<tr><td>Téléphone 2 : </td><td>"+a.getTel2()+"</br></td></tr>"
							+ "<tr><td>Adresse 1 : </td><td>"+a.getAdresse1()+"</br></td></tr>"
							+ "<tr><td>Adresse 2 : </td><td>"+a.getAdresse2()+"</br></td></tr>"
							+ "<tr><td>Mail 1 : </td><td>"+a.getMail1()+"</br></td></tr>"
							+ "<tr><td>Mail 2 : </td><td>"+a.getMail2()+"</br></td></tr>"
							+ "<tr><td>Commentaire : </td><td>"+a.getCommentaire()+"</br></td></tr>"
							+ "<tr><td>Contact 1 : </td><td>"+a.getContact1()+"</br></td></tr>"
							+ "<tr><td>Contact 2 : </td><td>"+a.getContact2()+"</br></td></tr>"
							+ "<tr><td>Sexe : </td><td>"+a.getSexe()+"</br></td></tr>"
							+ "<tr><td>Droit à l'image : </td><td>"+a.getDroitImage()+"</br></td></tr>"
							+ "</table>");
				}
			}
		}
		
		out.print("<div>"
				+ "<form action='Accueil' name='boutonAccueil' value='accueil' method='get'>"
				+ "<input type='submit' name='accueil' value='Accueil'> </form>"
				+ "</div>"
				+ "</body></html>");		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
