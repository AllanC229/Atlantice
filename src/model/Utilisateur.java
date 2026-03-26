package model;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import connection.DAOAcces;



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
	public Map<String, String> getCategoriesUser() {
		return categoriesUser;
	}
	
	public void lastseen (int id, String page) throws SQLException {
		
		DAOAcces dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "webadherents", "root", "");
		System.out.println("entrée dans lastseen");
		try {
			System.out.println("entrée dans le try");
			Connection conn = dao.getConn();
			
			Timestamp tslastseen = new Timestamp(System.currentTimeMillis());
			tslastseen.setNanos(0);
			
			String sql = "UPDATE log SET lastactivity = ? , navhistory = CONCAT(navhistory, ?) WHERE idlog = ? ;";
			PreparedStatement lastseen = conn.prepareStatement(sql);
			
			lastseen.setTimestamp(1, tslastseen);
			lastseen.setString(2, page);
			lastseen.setInt(3, id);
			System.out.println(lastseen);
			lastseen.executeUpdate();
		}
		
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		dao.closeConnection();
		
	}
	
}
