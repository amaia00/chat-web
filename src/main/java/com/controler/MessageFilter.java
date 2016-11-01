package com.controler;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Author: sofiaa faddi
 * Servlet Filter implementation class MessageFilter
 */
public class MessageFilter implements Filter {

    /**
     * Default constructor. 
     */
    public MessageFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		/* Cast des objets request et response */ 
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		 /* Récupération de la session depuis la requête */
		 HttpSession session = request.getSession();
		 /**
		 * Si l'objet utilisateur n'existe pas dans la session en cours, alors
		 * l'utilisateur n'est pas connecté il sera rédirgé vers la page index.jsp.
		 */
		 System.out.println( session.getAttribute( "pseudo"));
		 if ( session.getAttribute( "pseudo")==null ) {
			
		 /* Redirection vers la page publique */
			 response.sendRedirect("../index.jsp" );
		 } else {
		 /* Affichage de la page restreinte */
			 chain.doFilter( request, response );
		 } 
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
