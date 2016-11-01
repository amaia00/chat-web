package com.tp;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class Init
 * author: sofia faddi
 * @param <GestionMessage>
 * @param <GestionnMessage>
 */
public class Init extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String pseudo;
	//private GestionMessages gestionnaire;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Init() {
        super();
        //gestionnaire = new GestionMessages();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deconnexion = (String) request.getParameter("deco");
		HttpSession session = request.getSession();
		
		if(deconnexion!=null && deconnexion.equals("true")){
			session.invalidate();
		}

		String pseudo = (String) session.getAttribute( "pseudo");

		if ( pseudo==null ) {

			/* Redirection vers la page publique */
			response.sendRedirect("../index.jsp" );
		}else{
			RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/restreint/Interface.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		// recupérer le pseudo et le salon depuis le formulaire index
		pseudo = request.getParameter("pseudo");
		String salon = request.getParameter("salon");
		if(pseudo == null|| (session==null) || salon == null || pseudo.isEmpty() || salon.isEmpty()){
			// si null redirection vers la page index.jsp
			this.doGet(request,response);
			
		}
		else {
		
			try {

				/* Mise en session d'une chaîne de caractères */
				session.setAttribute( "pseudo", pseudo );
				session.setAttribute("salon", salon);
				
				RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/restreint/Interface.jsp");
				dispatcher.forward(request, response);
	
			} catch (Exception e) {
				e.printStackTrace();
			}
			
	}
	}
}
	
	
	
	


