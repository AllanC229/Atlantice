package model;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Utilisateur {
	private String nom;
	private String prenom;
	private String role;
	private String id;
	private int idconnexion;	//Ca c'est l'idlog dans la table de la BDD ; ça servira à insérer le timestamp de la déconnexion au moment de celle-ci
	// private String categUser; 
	//private ArrayList<String> categoriesUser;
	public HashMap<String, String> categoriesUser;
	
	
	
	public Utilisateur(String nom, String prenom, String role, String id, int idconnexion,/*String categUser,*/ HashMap<String, String> categoriesUser) {
		this.nom = nom;
		this.prenom = prenom;
		this.role = role;
		this.id = id;
	//	this.categUser = categUser;
		this.categoriesUser = categoriesUser;
		this.idconnexion = idconnexion;
		
	}
	public String getNom() {			
		return nom ;			
	}
	public String getPrenom() {			
		return prenom ;			
	}
	public String getRole() {			
		return role ;			
	}
	public String getId() {			
		return id ;			
	}
	public int getIdConnexion() {			
		return idconnexion ;			
	}
/*	public String getCategUser() {
		return categUser ;
	} */
	public Map<String, String> getCategoriesUser() {
		return categoriesUser;
	}
	
	//rajouter une liste de catégories pour les utilisateurs  Arraylist<categorie>
	}
