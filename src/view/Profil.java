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
import model.Utilisateur;

@WebServlet("/Profil")
public class Profil extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Profil() {
        // TODO Auto-generated constructor stub
    }
    
    @SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	
   //Le code va ici
		HttpSession h = request.getSession();
		Utilisateur activeUser = (Utilisateur) h.getAttribute("activeUser");
		Adherent activeAdherent = (Adherent) h.getAttribute("activeAdherent");
		PrintWriter out=response.getWriter();
		
		
		out.print("<!Doctype html><html><head><meta charset=\"utf-8\"/> \r\n"
				+ "<link href=\"licence.css\" rel=\"stylesheet\">"
				+ "</head><body><h1 align=center>Informations adhérent : </h1></br>"
				+ "<div align=center>"
				+ "<table border>"
				+ "<tr><td>Nom : </td><td>"+activeAdherent.getNom()+"</br></td></tr>"
				+ "<tr><td>Prénom : </td><td>"+activeAdherent.getPrenom()+"</br></td></tr>"							
				+ "<tr><td>Numéro de licence : </td><td>" +activeAdherent.getNumLicence()+ "</br></td></tr>"
				+ "<tr><td>Dernière année de licence active : </td><td>"+activeAdherent.getDerniereLicenceActive()+"</br></td></tr>"
				+ "<tr><td>Année de naissance : </td><td>"+activeAdherent.getAnneeNaissance()+"</br></td></tr>"
				+ "<tr><td>Téléphone 1 : </td><td>"+activeAdherent.getTel1()+"</br></td></tr>"
				+ "<tr><td>Téléphone 2 : </td><td>"+activeAdherent.getTel2()+"</br></td></tr>"
				+ "<tr><td>Adresse 1 : </td><td>"+activeAdherent.getAdresse1()+"</br></td></tr>"
				+ "<tr><td>Adresse 2 : </td><td>"+activeAdherent.getAdresse2()+"</br></td></tr>"
				+ "<tr><td>Mail 1 : </td><td>"+activeAdherent.getMail1()+"</br></td></tr>"
				+ "<tr><td>Mail 2 : </td><td>"+activeAdherent.getMail2()+"</br></td></tr>"
				+ "<tr><td>Commentaire : </td><td>"+activeAdherent.getCommentaire()+"</br></td></tr>"
				+ "<tr><td>Contact 1 : </td><td>"+activeAdherent.getContact1()+"</br></td></tr>"
				+ "<tr><td>Contact 2 : </td><td>"+activeAdherent.getContact2()+"</br></td></tr>"
				+ "<tr><td>Sexe : </td><td>"+activeAdherent.getSexe()+"</br></td></tr>"
				+ "<tr><td>Droit à l'image : </td><td>"+activeAdherent.getDroitImage()+"</br></td></tr>"
				+ "</table></div>");
    
    }	
   
    

    
    	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		// TODO Auto-generated method stub
    		doGet(request, response);
    	}

    }