package com.chat.modele;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Amaia Nazábal
 * @version 1.0
 * @since 1.0 11/23/16.
 */
@Service
public class ChatGestionUtilisateur implements GestionUtilisateur {

    private Map<String, List<User>> userList = new HashMap<>();

    @Override
    public void addUser(String pseudo, String prenom, String nom, String mail,
                        String salon) {
        User user = new User(pseudo, prenom, nom, mail);
        userList.get(salon).add(user);
    }

    @Override
    public boolean existsUser(String pseudo, String salon) {
        return userList.get(salon).stream().filter(u -> u.getPseudo().equals(pseudo))
                .findFirst().isPresent();
    }

    @Override
    public User getUserByPseudo(String pseudo, String salon) {
        return userList.get(salon).stream().filter(u -> u.getPseudo().equals(pseudo))
                .findFirst().orElse(null);
    }
}