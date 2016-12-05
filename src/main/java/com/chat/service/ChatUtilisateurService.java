package com.chat.service;

import com.chat.modele.User;
import com.chat.util.Constantes;
import com.chat.util.DataException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Cette classe garde toutes les méthodes sur l'inscription d'utilisateurs
 *
 * @author Amaia Nazábal
 * @version 1.0
 * @since 1.0 11/23/16.
 */
@Service
public class ChatUtilisateurService implements GestionUtilisateur {

    private static final Logger LOGGER = Logger.getLogger(ChatUtilisateurService.class.getName());
    private List<User> userList = new ArrayList<>();

    @Override
    public void addUser(String pseudo, String prenom, String nom, String mail) throws DataException {
        User user = new User(pseudo, prenom, nom, mail);
        if (!existsUsername(pseudo))
            if (!existsMail(mail))
                userList.add(user);
            else
                throw new DataException(Constantes.MAIL_ALREADY_EXISTS);
        else
            throw new DataException(Constantes.USERNAME_ALREADY_EXISTS);
    }

    @Override
    public boolean existsMail(String mail) {

        return userList.stream().filter(u -> u.getMail().equals(mail))
                .findFirst().isPresent();
    }

    @Override
    public boolean existsUsername(String pseudo) {
        return userList.stream().filter(u -> u.getPseudo().equals(pseudo))
                .findFirst().isPresent();
    }

    @Override
    public User getUserByPseudo(String pseudo) {
        return userList.stream().filter(u -> u.getPseudo().equals(pseudo))
                .findFirst().orElse(null);
    }

    @Override
    public User updateUser(String pseudo, User user) {
        Optional<User> first = userList.stream().filter(u ->
                u.getPseudo().equals(pseudo)
        ).findFirst();

        User u = null;
        if (first.isPresent()) {
            u = first.get();
            u.setPseudo(pseudo);
        } else {
            try {
                this.addUser(user.getPseudo(), user.getPrenom(), user.getNom(), user.getMail());
            } catch (DataException e) {
                LOGGER.log(Level.WARNING, "Can't update/add the user", e);
            }
        }
        return u;
    }
}