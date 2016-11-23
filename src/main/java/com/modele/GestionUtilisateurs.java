package com.modele;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Amaia Nazábal
 * @version 1.0
 * @since 1.0 11/23/16.
 */
public class GestionUtilisateurs {

    private static List<User> userList = new ArrayList<>();

    public static void addUser(String pseudo, String prenom, String nom, String mail){
        User user = new User(pseudo, prenom, nom, mail);
        userList.add(user);
    }

}
