package view;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Adherent;

@WebServlet("/ModifInfosProfil")
public class ModifInfosProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ModifInfosProfil() {
        // TODO Auto-generated constructor stub
    }
    
    @SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	
   //Le code va ici
		HttpSession h = request.getSession(false);
		PrintWriter out=response.getWriter();
		
		
		if (h == null || h.getAttribute("activeUser") == null) { //Si la session n'existe pas, renvie vers la page de connexion
		    response.sendRedirect("/Connexion");
		    return;
		}
		
		Adherent activeAdherent = (Adherent) h.getAttribute("activeAdherent");

		
		out.print("<!Doctype html><html><head><meta charset=\"utf-8\"/> \r\n"
				+ "<link href=\"licence.css\" rel=\"stylesheet\">"
				+ "</head><body><h1 align=center>Formulaire adhérent : </h1></br>"
				+ "<div align=center><form action='ControleurModifInfosProfil' method='get'>"
				+ "<table border>"
				+ "<tr><td>Nom : </td><td><input type='text' name='nom' value='"+activeAdherent.getNom()+"'></br></td></tr>"
				+ "<tr><td>Prénom : </td><td><input align=center type='text' name='prenom' value='"+activeAdherent.getPrenom()+"'></br></td></tr>"							
				+ "<tr><td>Numéro de licence : </td><td><input type='text' name='numeroLicence' value='" +activeAdherent.getNumLicence()+ "' readonly></br></td></tr>"
				+ "<tr><td>Modifier le numéro de licence </td><td><input type='text' name='modifnumeroLicence' value=''></br></td></tr>"
				+ "<tr><td>Dernière année de licence active : </td><td><input type='text' name='derniereAnneeLicence' value='"+activeAdherent.getDerniereLicenceActive()+"'></br></td></tr>"
				+ "<tr><td>Année de naissance : </td><td><input align=center type='text' name='anneeNaissance' value='"+activeAdherent.getAnneeNaissance()+"'></br></td></tr>"
				+ "<tr><td>Téléphone 1 : </td><td><input type='text' name='telephone1' value='"+activeAdherent.getTel1()+"'></br></td></tr>"
				+ "<tr><td>Téléphone 2 : </td><td><input align=center type='text' name='telephone2' value='"+activeAdherent.getTel2()+"'></br></td></tr>"
				+ "<tr><td>Adresse 1 : </td><td><input type='text' name='adresse1' value='"+activeAdherent.getAdresse1()+"'></br></td></tr>"
				+ "<tr><td>Adresse 2 : </td><td><input align=center type='text' name='adresse2' value='"+activeAdherent.getAdresse2()+"'></br></td></tr>"
				+ "<tr><td>Mail 1 : </td><td><input type='text' name='mail1' value='"+activeAdherent.getMail1()+"'></br></td></tr>"
				+ "<tr><td>Mail 2 : </td><td><input align=center type='text' name='mail2' value='"+activeAdherent.getMail2()+"'></br></td></tr>"
				+ "<tr><td>Commentaire : </td><td><input type='text' name='commentaire' value='"+activeAdherent.getCommentaire()+"'></br></td></tr>"
				+ "<tr><td>Contact 1 : </td><td><input align=center type='text' name='contact1' value='"+activeAdherent.getContact1()+"'></br></td></tr>"
				+ "<tr><td>Contact 2 : </td><td><input type='text' name='contact2' value='"+activeAdherent.getContact2()+"'></br></td></tr>"
				+ "<tr><td>Sexe : </td><td><input align=center type='text' name='sexe' value='"+activeAdherent.getSexe()+"'></br></td></tr>"
				+ "<tr><td>Droit à l'image : </td><td><input align=center type='text' name='droitImage' value='"+activeAdherent.getDroitImage()+"'></br></td></tr>"
				+ "</table>"
				+ "<input type='submit' name='modifinfos' value='Valider les modifications'> </form>"
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
