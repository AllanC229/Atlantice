package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.DAOAcces;
import model.Categorie;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ControleurMenu
 */
@WebServlet("/ControleurMenu")
public class _ControleurMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Categorie> categories;

    /**
     * Default constructor. 
     */
    public _ControleurMenu() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Ouvre la connexion
        DAOAcces dao = new DAOAcces("com.mysql.jdbc.Driver", "webadherents", "root", "");
        String sql = "SELECT * from categorie";
        
        try {
			ResultSet rs = dao.getStatement().executeQuery(sql);
			
			categories = new ArrayList<Categorie>();
	        int cpt = 0;
	        while(rs.next()) 
			{
	        	cpt++;
		        System.out.println(rs.getString(1)); 
		        System.out.println(rs.getString(2)); 
		        
		        //request.setAttribute("c"+rs.getString(1), rs.getString(1));
			}
	        request.setAttribute("cpt", cpt);
	        
	        
		} 
        catch(SQLException e) {
			System.out.println("Probleme SQL b!!");
			e.printStackTrace();
		}
		
        //Ferme la connexion
        dao.closeConnection();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
