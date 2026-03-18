package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import connection.DAOAcces;


public class Categorie {
	
	private String idcategorie;
	private String nomcategorie;
	private ArrayList<Adherent> adherents;
	private HashMap<String, String> categoriesUser;
	
	
	
	public Categorie(String idcategorie, String nomcategorie, DAOAcces dao ){

		this.adherents = new ArrayList<Adherent>();
		this.idcategorie = idcategorie;
		this.nomcategorie = nomcategorie;
		
		
		try {
		
		    for (Adherent adh: adherents) {
		    	String sql2 = "select * from adherents, categorieadherent where numeroLicence ="+adh+" and idAd=numerolicence;";
		    	ResultSet rs2 = dao.getStatement().executeQuery(sql2);
			    
			    while (rs2.next()) {
		    	      
			        this.ajouterAdherent(new Adherent(/*rs2.getString("categAd"), */rs2.getString("numerolicence"), rs2.getString("nom"), 
			        		rs2.getString("prenom"), rs2.getString("annee"), rs2.getString("tel1"), rs2.getString("tel2"), rs2.getString("adresse1"), rs2.getString("adresse2"), 
			        		rs2.getString("mail1"), rs2.getString("mail2"), rs2.getString("commentaire"), rs2.getString("dernierelicenceactive"), rs2.getString("contact1"),
			        		rs2.getString("contact2"), rs2.getString("sexe"), rs2.getString("droitimage"), categoriesUser, dao));
			       
			      }
		    }
		    
		    
		   
		    
	    }
	    catch(SQLException e) {
			System.out.println("Problème SQL b!!");
			e.printStackTrace();
		}
		
		
	}


//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}

	public String getIdCateg() {
		return idcategorie;
	}


/*	public void setAnnee(String annee) {
		this.annee = annee;
	} */


	public String getNomCateg() {
		return this.nomcategorie;
	}

/*	public void setNom(String nom) {
		this.nom = nom;
	} */
	
	public void ajouterAdherent(Adherent adherent) {
		this.adherents.add(adherent);
	}

	
	
	
	
}
