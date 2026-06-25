package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import org.mindrot.jbcrypt.BCrypt;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Adherent;
import model.Categorie;
import model.Utilisateur;
import connection.DAOAcces;
//Tool - méthode de contrôle de saisie
import tool.ControleDeSaisie;

/**
 * Servlet implementation class ControleurConnexion
 */
@WebServlet("/ControleurConnexion")
public class ControleurConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       

	/**
     * @see HttpServlet#HttpServlet()
     */
    public ControleurConnexion() {
        super();
        // TODO Auto-generated constructor stub
    }
    
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mailsaisi = (String)request.getParameter("mailCo");	
		String mdpsaisi = (String)request.getParameter("mdpCo");
		
		//regex pour les mails accepte uniquement le format: lettres/chiffres + @ + des lettres/chiffres + des lettres
        String regexMail = ControleDeSaisie.regexPattern;
		
		if (mailsaisi.equals("") || mdpsaisi.equals("")) {
			request.setAttribute("erreur", "Veuillez compléter tous les champs svp !");
			getServletContext().getRequestDispatcher("/Connexion").forward(request, response);
			System.out.println("Veuillez compléter tous les champs svp !");

		}
		
        // format mail invalide
        if (ControleDeSaisie.patternMatches(mailsaisi, regexMail) == false) {
	        request.setAttribute("erreur", "Adresse mail invalide");
	        getServletContext().getRequestDispatcher("/Connexion").forward(request, response);
	        System.out.println("mail saisi invalide:" + mailsaisi);
	        return;
        } 
        
        // tester si mdpsaisi contient des caractères interdits
        if (mdpsaisi != null) {
                if (ControleDeSaisie.caractereInterdit(mdpsaisi)) {
    				// insérer la tentative d'injection dans les logs : 
                	try {
                		DAOAcces dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "webadherents", "root", "");
            			Connection conn = dao.getConn();
            			
            			Timestamp tslastseen = new Timestamp(System.currentTimeMillis());
            			tslastseen.setNanos(0);
            			
            			String sql = "UPDATE log SET lastactivity = ? , navhistory = CONCAT(navhistory, ?) "
            					+ "WHERE idlog = (SELECT CONNECTION_ID()) ;";
            			PreparedStatement lastseen = conn.prepareStatement(sql);
            			
						String page =  " tentative insertion caractère interdit dans le champ mdp vue connexion dans la BDD;";
            			
            			lastseen.setTimestamp(1, tslastseen);
            			lastseen.setString(2, page);
            			//lastseen.setInt(3, id);
            			System.out.println(lastseen);
            			lastseen.executeUpdate();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                	// pas donner d'indice quant à la nature de l'erreur (request.setAttribute("erreur", "Caractère interdit détecté (< > \")");)  getServletContext().getRequestDispatcher("/CreationAdherent").forward(request, response);
                    getServletContext().getRequestDispatcher("/Connexion").forward(request, response);
                    System.out.println("mdp contient caractère interdit:" + mdpsaisi);
                	return;
                }
        }
	
		//Ouvre la connexion
		DAOAcces dao = new DAOAcces("com.mysql.cj.jdbc.Driver", "webadherents", "root", "");
		Connection conn = null;
		PreparedStatement checkUser = null;
		PreparedStatement checkMdp = null;
		PreparedStatement userCateg = null;
		PreparedStatement allCateg = null ;
		String idUser = null;
		
		try {
			conn = dao.getConn();
			conn.setAutoCommit(false);
							
			String sqlcheck = "SELECT motdepasse FROM adherents WHERE mail1 = ? ;";
			checkMdp = dao.getConn().prepareStatement(sqlcheck);
			checkMdp.setString(1, mailsaisi);
			
			ResultSet verifMdp = checkMdp.executeQuery();
			conn.commit();				

			if (verifMdp.next()) {
				
				String sql = "SELECT nom, prenom, role, numerolicence, tentativeconnexion, changementmdp FROM adherents WHERE mail1 = ? ;";
				checkUser = conn.prepareStatement(sql);
				checkUser.setString(1, mailsaisi);
				conn.commit();
				
				ResultSet identification = checkUser.executeQuery();
				
				if (identification.next()) {
					
					if (identification.getInt("tentativeconnexion") <= 0) {
						
						dao.closeConnection();
						request.setAttribute("mdperr", "Nombre de tentatives dépassées ; contactez votre référent");
						request.getRequestDispatcher("/Connexion").forward(request, response);
						System.out.println("Nombre de tentatives de connexions dépassé ! Contactez votre référent.");
					
					}
					
					else if (identification.getInt("tentativeconnexion") >= 1) {
						
						
						
						if (BCrypt.checkpw(mdpsaisi, verifMdp.getString("motdepasse")) == true) {
							
							
							if (identification.getInt("changementmdp") != 1) {
								request.setAttribute("chgtmdp", "<script> alert ('Veuillez changer votre mot de passe'); </script>");
							}
							
							PreparedStatement updtentconn = conn.prepareStatement("UPDATE adherents SET tentativeconnexion = DEFAULT WHERE mail1 = ? ;");
							updtentconn.setString(1,  mailsaisi);
							updtentconn.executeUpdate(); 
							conn.commit();
							
							HttpSession h = request.getSession();  //Crée la session seulement si la requête d'identification renvoie un résultat positif
							h.setMaxInactiveInterval(300);			//Regle un timer qui détruit la session si aucune requête n'est faite au bout de 5 minutes ; chaque requête effectuée rénitialise ce délai 
							
							Timestamp tslogin = new Timestamp(System.currentTimeMillis());
							tslogin.setNanos(0);
							
							String sqLloginTime = "INSERT INTO log VALUES(DEFAULT, ?, ?, NULL, NULL, '0;');";	//Ce bloc sert à insérer en BDD un timeStamp dans la table log avec les infos de connexion de l'utilisateur
							PreparedStatement insertLoginTime = conn.prepareStatement(sqLloginTime);
							String idconnexion = ""+ identification.getString("nom") +" "+identification.getString("prenom")+" "+identification.getString("numerolicence")+"";
							insertLoginTime.setString(1, idconnexion);
							insertLoginTime.setTimestamp(2, tslogin);
							insertLoginTime.executeUpdate();
							conn.commit();
							
							PreparedStatement getIdConnexion = conn.prepareStatement("SELECT idlog FROM log WHERE idconnexion = ? AND logintime = ? ;");
							getIdConnexion.setString(1,  idconnexion);	//On récupère l'idlog correspondant  l'insertion qu'on vient de faire, qui servira plus tard pour insérer logouttime
							getIdConnexion.setTimestamp(2, tslogin);
							ResultSet rsIdConnexion = getIdConnexion.executeQuery();
							
							if (rsIdConnexion.next()) {
							
								System.out.println(tslogin);
								HashMap<String, String> categoriesUser;
								categoriesUser = new HashMap<String, String>() ;
									
								if ("admin".equals(identification.getString("role"))) {
								
									String sql3 ="select idcategorie, nomcategorie from categoriesportive order by idcategorie;";
									allCateg = conn.prepareStatement(sql3);
									ResultSet selectCategories = allCateg.executeQuery();
										
									while(selectCategories.next()) {
										
									categoriesUser.put(selectCategories.getString("idcategorie"), selectCategories.getString("nomcategorie"));
											
									}
											
									Utilisateur activeUser = new Utilisateur(identification.getString("nom"), identification.getString("prenom"),
									identification.getString("role"), identification.getString("numerolicence"), rsIdConnexion.getInt("idlog"), categoriesUser );
									h.setAttribute("activeUser", activeUser);
									System.out.println("CHECK : " + identification.getString("role"));
													
									System.out.println(rsIdConnexion.getInt("idlog"));
								}
									
								else if (!"admin".equals(identification.getString("role"))) {
									String sql3 ="SELECT categoriesportive.idcategorie, nomcategorie FROM categoriesportive INNER JOIN categorieadherent "
									+ "ON categoriesportive.idcategorie=categorieadherent.idcategorie "
									+ "INNER JOIN adherents ON categorieadherent.numLic=adherents.numerolicence "
									+ "WHERE numerolicence=? ORDER BY categoriesportive.idcategorie;" ;
											
									allCateg = conn.prepareStatement(sql3);
									allCateg.setString(1, identification.getString("numerolicence"));
									ResultSet selectCategories = allCateg.executeQuery();
																
									while(selectCategories.next()) {
									
									categoriesUser.put(selectCategories.getString("idcategorie"), selectCategories.getString("nomcategorie"));
											
									}
											
									Utilisateur activeUser = new Utilisateur(identification.getString("nom"), identification.getString("prenom"),
									identification.getString("role"), identification.getString("numerolicence"), rsIdConnexion.getInt("idlog"), categoriesUser );
									h.setAttribute("activeUser", activeUser);		
											
								}
							}
					
						}	
				
						else {
						
							PreparedStatement updtentconn = conn.prepareStatement("UPDATE adherents SET tentativeconnexion = CASE WHEN tentativeconnexion > 0 THEN tentativeconnexion - 1 ELSE 0 END WHERE mail1 = ? ;");
							updtentconn.setString(1,  mailsaisi);
							updtentconn.executeUpdate();
							conn.commit();
							
							dao.closeConnection();
							request.setAttribute("mdperr", "Mot de passe incorrect. Attention, votre compte sera bloqué après 5 tentatives infructueuses");
							request.getRequestDispatcher("/Connexion").forward(request, response);
							System.out.println("Mot de passe erroné");						
						}	
				
						dao.closeConnection();				 
						request.getRequestDispatcher("/Accueil").forward(request, response);				 
					}
				}
		
			}
			
			else {
				 System.out.println("Connexion échouée ; pas de correspondance login / mdp");
				 dao.closeConnection();
				 request.setAttribute("mdperr", "Ce compte n'existe pas");
				 request.getRequestDispatcher("/Connexion").forward(request, response);
			 } 
		
		}
		
		catch (SQLException e) {
		
			e.printStackTrace();
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
