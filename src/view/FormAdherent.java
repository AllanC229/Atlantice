package view;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class FormStagiaire
 */
@WebServlet("/FormAdherent")
public class FormAdherent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormAdherent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		
			out.println("<!Doctype html><html><head><meta charset=\"utf-8\"/> \r\n"
					+"<link href=\"licence.css\" rel=\"stylesheet\">"
					+ "</head><body><h1 align=center>Formulaire d'ajout d'un adhérent : </h1></br>"
					+ "	<div align=center><form action=\"ControleurAdherent\" method=GET>"
					+ " <table border>"
					+ "		<tr><td>Nom : </td><td><input type='text' name='nom' value=''></input></br></td></tr>"
					+ "		<tr><td>Prénom : </td><td><input align=center type='text' name='prenom' value=''></input></br></td></tr>"
					+ "		<tr><td>Numéro de licence : </td><td><input type='text' name='numeroLicence' value=''></input></br></td></tr>"
					+ "		<tr><td>Dernière année de licence active : </td><td><input type='text' name='derniereAnneeLicence' value=''></input></br></td></tr>"
					+ "		<tr><td>Année de naissance : </td><td><input align=center type='text' name='anneeNaissance' value=''></input></br></td></tr>"
					+ "		<tr><td>Téléphone 1 : </td><td><input type='text' name='telephone1' value=''></input></br></td></tr>"
					+ "		<tr><td>Téléphone 2 : </td><td><input align=center type='text' name='telephone2' value=''></input></br></td></tr>"
					+ "		<tr><td>Adresse 1 : </td><td><input type='text' name='adresse1' value=''></input></br></td></tr>"
					+ "		<tr><td>Adresse 2 : </td><td><input align=center type='text' name='adresse2' value=''></input></br></td></tr>"
					+ "		<tr><td>Mail 1 : </td><td><input type='text' name='mail1' value=''></input></br></td></tr>"
					+ "		<tr><td>Mail 2 : </td><td><input align=center type='text' name='mail2' value=''></input></br></td></tr>"
					+ "		<tr><td>Contact 1 : </td><td><input align=center type='text' name='contact1' value=''></input></br></td></tr>"
					+ "		<tr><td>Contact 2 : </td><td><input type='text' name='contact2' value=''></input></br></td></tr>"
					+ "		<tr><td>Sexe : </td><td><input align=center type='text' name='sexe' value=''></input></br></td></tr>"
					+ "		<tr><td>Droit à l'image : </td><td><input align=center type='text' name='droitImage' value=''></input></br></td></tr>"
					//Ajout des différents critères dans le formulaire, Allan
					+ "		<tr><td>Critère 1 : </td><td><input align=center type='text' name='critere1' value=''></input></br></td></tr>"
					+ "		<tr><td>Critère 2 : </td><td><input align=center type='text' name='critere2' value=''></input></br></td></tr>"
					+ "		<tr><td>Critère 3 : </td><td><input align=center type='text' name='critere3' value=''></input></br></td></tr>"
					+ "		<tr><td>Critère 4 : </td><td><input align=center type='text' name='critere4' value=''></input></br></td></tr>"
					+ "		<tr><td>Critère 5 : </td><td><input align=center type='text' name='critere5' value=''></input></br></td></tr>"
					+ "</table>"
					+ "		Commentaire : </br><textarea rows=4 cols=40 name=\"commentaire\"></textarea></br>"
					+ "<input type=submit name='newSt' value='Ajout Adhérent'></input>") ;
			String footer =  "	<input type=submit name='modifAd' value=\"Modification de l'adhérent\"></input>"
					+ "<div><form action=\"Accueil\" name=\"boutonAccueil\" value=\"accueil\" method=\"get\">"
					+ "<input type=\"submit\" name=\"accueil\" value=\"Accueil\"> </form>"
					+ "</div></body></html>";
	      //  out.print(footer);
			//<input type='text' name='commentaire' value=''></input>
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
