package model;

import java.sql.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import connection.DAOAcces;

public class Adherent {

	private String numeroLicence;
	private String nom;
	private String prenom;
	private String anneeNaissance;
	private String tel1;
	private String tel2;
	private String adresse1;
	private String adresse2;
	private String mail1;
	private String mail2;
	private String commentaire;
	private String derniereLicenceActive;
	private String contact1;
	private String contact2;
	private String sexe;
	private String droitImage;
	public HashMap<String, String> categoriesAdh;
	
	
	
	private ArrayList<String> idCategories;
	


	
	public Adherent(/*String categorie, */String numeroLicence, String nom, String prenom, String annee, 
			String tel1, String tel2, String adresse1, String adresse2, String mail1, String mail2, 
			String commentaire, String derniereLicenceActive, String contact1, String contact2, String sexe, String droitImage, HashMap<String, String> categoriesUser /*, DAOAcces dao, String role = "adherent"*/){ 
		//this.categorie = categorie;
		this.numeroLicence = numeroLicence;
		this.nom = nom;
		this.prenom = prenom;
		this.anneeNaissance = annee; 
		this.tel1 = tel1;
		this.tel2 = tel2;
		this.adresse1 = adresse1;
		this.adresse2 = adresse2;
		this.mail1 = mail1;
		this.mail2 = mail2;
		this.commentaire = commentaire;
		this.derniereLicenceActive = derniereLicenceActive;
		this.contact1 = contact1;
		this.contact2 = contact2;
		this.sexe = sexe;
		this.droitImage = droitImage;
		this.categoriesAdh = categoriesUser;

		this.idCategories = new ArrayList<String>();
		
	/*	
//		String sql = "select * from adherents where numeroLicence ="+this.numeroLicence+";";
		String sql1 = "select nomcategorie from categoriesportive JOIN categorieadherent USING idcategorie WHERE categorieadherent.numLic = "+this.numeroLicence+";";
		
		try {
			Statement st = (Statement) dao.getConn().createStatement();
	    	ResultSet rs = st.executeQuery(sql1);
	    	
		    while (rs.next()) {
		    	this.ajouterIdCategorie(rs.getString("nomcategorie"));
		    }
		}
	    catch(SQLException e) {
			System.out.println("Problème SQL a!!");
			e.printStackTrace();
		} */
	}

	public Adherent() {
		// TODO Auto-generated constructor stub
	}

	public String getDerniereLicenceActive() {
		return derniereLicenceActive;
	}

	public void setDerniereLicenceActive(String derniereLicenceActive) {
		this.derniereLicenceActive = derniereLicenceActive;
	}

/*	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	} */

	public String getAnneeNaissance() {
		return anneeNaissance;
	}

	public void setAnneeNaissance(String anneeNaissance) {
		this.anneeNaissance = anneeNaissance;
	}

	public String getTel1() {
		return tel1;
	}

	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}

	public String getTel2() {
		return tel2;
	}

	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}

	public String getAdresse1() {
		return adresse1;
	}

	public void setAdresse1(String adresse1) {
		this.adresse1 = adresse1;
	}

	public String getAdresse2() {
		return adresse2;
	}

	public void setAdresse2(String adresse2) {
		this.adresse2 = adresse2;
	}

	public String getMail1() {
		return mail1;
	}

	public void setMail1(String mail1) {
		this.mail1 = mail1;
	}

	public String getMail2() {
		return mail2;
	}

	public void setMail2(String mail2) {
		this.mail2 = mail2;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNumLicence() {
		return this.numeroLicence;
	}

	public void ajouterIdCategorie(String categorie){
		this.idCategories.add(categorie);
	}
	
	public String getContact1() {
		return contact1;
	}

	public void setContact1(String contact1) {
		this.contact1 = contact1;
	}

	public String getContact2() {
		return contact2;
	}

	public void setContact2(String contact2) {
		this.contact2 = contact2;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getDroitImage() {
		return droitImage;
	}

	public void setDroitImage(String droitImage) {
		this.droitImage = droitImage;
	}

	public ArrayList<String> getIdCategories() {
		return this.idCategories;
	}
	
	public Map<String, String> getCategoriesAdh() {
		return categoriesAdh;

	}
}
