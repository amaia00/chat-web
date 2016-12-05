package com.chat.rest;

import com.chat.modele.User;
import com.chat.service.GestionMessage;
import com.chat.service.GestionUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;

/**
 * @author Amaia Nazábal
 * @version 1.0
 * @since 1.0 12/5/16
 */
@RestController
public class UserResource {

    private GestionUtilisateur gestionUtilisateur;
    private GestionMessage gestionMessage;

    @Autowired
    public UserResource(GestionUtilisateur gestionUtilisateur, GestionMessage gestionMessage) {
        this.gestionUtilisateur = gestionUtilisateur;
    }

    @RequestMapping(value = "/users/{pseudo}", produces = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.GET)
    @ResponseBody
    public Utilisateur getUser(@PathVariable String pseudo) {
        User user = gestionUtilisateur.getUserByPseudo(pseudo);
        Utilisateur utilisateur = new Utilisateur(user.getPseudo(), user.getPrenom(), user.getNom(), user.getMail());
        utilisateur.setSalons(gestionMessage.getSalonsByUser(user));
        return utilisateur;
    }

    @RequestMapping(value = "/users/{pseudo}", produces = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.PUT)
    @ResponseBody
    public User updateUser(@PathVariable String pseudo, @RequestBody User user) {
        gestionUtilisateur.updateUser(pseudo, user);
        Utilisateur utilisateur = new Utilisateur(user.getPseudo(), user.getPrenom(), user.getNom(), user.getMail());
        utilisateur.setSalons(gestionMessage.getSalonsByUser(user));
        return utilisateur;
    }
}
