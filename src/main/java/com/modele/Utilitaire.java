package com.modele;

import javax.servlet.http.Cookie;

public class Utilitaire {

	 /**
     * Recherche le cookie "cookieCherche" et le renvoie
     * @param cookies un tableau de cookies
     * @param cookieCherche le cookie recherch�
     * @return revoie le cookie si il existe, null sinon
     * author sofia faddi
     */
    public static Cookie getCookie(Cookie[] cookies, String cookieCherche){

        if(cookies != null){
            
            //recherche du bon cookie
            for(int i = 0; i < cookies.length; i++) {
            	System.out.println("getname="+cookies[i].getName());
                if (cookies[i].getName().equals(cookieCherche))
                    return cookies[i];
            }
        }
        System.out.println("null");
        return null;
    }


}
