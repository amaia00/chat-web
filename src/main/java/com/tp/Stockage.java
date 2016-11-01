package com.tp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.modele.GestionMessages;


/**
 * Servlet implementation class Stockage
 * Author sofia faddi
 */
public class Stockage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Stockage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/Init").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String pseudo= (String)session.getAttribute("pseudo"); 

		String message = (String) request.getParameter("message");
		String salon=(String) session.getAttribute("salon"); 
		

		if (message!=null){
			//AJOUTER MESSAGE SALON

			GestionMessages.addMessage(message , pseudo,salon);

		}		
		request.setAttribute("salon", salon);
		RequestDispatcher dp = request.getRequestDispatcher("/restreint/Interface.jsp");
		dp.forward(request, response);
		
	}

}
