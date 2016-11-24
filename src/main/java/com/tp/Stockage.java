package com.tp;

import com.modele.GestionMessages;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servlet implementation class Stockage
 *
 * @author Sofiaa Faddi
 */
public class Stockage extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(Message.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Stockage() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            this.getServletContext().getRequestDispatcher("/Init").forward(request, response);
        } catch (Exception e){
            LOGGER.log(Level.FINE, e.getMessage(), e);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		String pseudo = session.getAttribute("pseudo").toString();
		String message = request.getParameter("contenu");
		String salon = session.getAttribute("salon").toString();
		

		if (message!=null){
			//AJOUTER MESSAGE SALON
			GestionMessages.addMessage(message, pseudo, salon);
		}
		request.setAttribute("salon", salon);
		RequestDispatcher dp = request.getRequestDispatcher("/restreint/interface.jsp");
		try {
            dp.forward(request, response);
        } catch (Exception e){
            LOGGER.log(Level.FINE, e.getMessage(), e);
        }
	}

}
