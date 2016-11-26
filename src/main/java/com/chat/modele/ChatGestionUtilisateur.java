package com.chat.modele;

import com.chat.util.Constantes;
import com.chat.util.DataException;
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
}