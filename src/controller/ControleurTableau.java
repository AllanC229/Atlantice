package controller;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Adherent;

/**
 * Servlet implementation class ControleurFiche
 */
@WebServlet("/ControleurTableau")
public class ControleurTableau extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ControleurTableau() {
        // TODO Auto-generated constructor stub
    }
    
	 /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    

		request.setAttribute("numLic", (String)request.getAttribute("numLic"));
		
		if (request.getParameter("sportif")!=null) {
			
			response.addHeader("numLic", (String)request.getAttribute("numLic"));
			//response.sendRedirect("FicheSportive");
			getServletContext().getRequestDispatcher("/FicheSportive").forward(request, response); //modifier pour afficher la fiche sportive et non le tableau général
		}
		if (request.getParameter("fiche")!=null) {
			//response.sendRedirect("FicheAdministrative");
			getServletContext().getRequestDispatcher("/FicheAdministrative").forward(request, response); //meme chose avec la fiche administrative
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
