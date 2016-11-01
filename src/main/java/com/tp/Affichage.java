package com.tp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author : sofia faddi
 * Servlet implementation class Affichage
 */
public class Affichage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Affichage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //intercepter la méthode get et faire la redirection
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	// redirection vers servlet INIT 
		this.getServletContext().getRequestDispatcher("/Init").forward(request,response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//intercepter la méthode POST et faire la redirection
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// redirection vers la page Interface
		RequestDispatcher dpaffichage =getServletContext().getRequestDispatcher("/restreint/Interface.jsp");
		dpaffichage.forward(request, response);
	}

}
