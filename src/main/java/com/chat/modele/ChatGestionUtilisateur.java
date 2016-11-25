package com.chat.modele;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Amaia Nazábal
 * @version 1.0
 * @since 1.0 11/23/16.
 */
@Service
public class ChatGestionUtilisateur implements GestionUtilisateur {


    private List<User> userList = new ArrayList<>();

    @Override
    public void addUser(String pseudo, String prenom, String nom, String mail){
        User user = new User(pseudo, prenom, nom, mail);
        userList.add(user);
    }

    @Override
    public boolean existsUser(User user) {
        return userList.stream().filter(u -> u.getPseudo().equals(user.getPseudo()))
                .findFirst().isPresent();
    }

}
