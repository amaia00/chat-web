package com.chat.service;

import com.chat.modele.User;
import com.chat.util.DataException;

/**
 * @author Amaia Nazábal
 * @version 1.0
 * @since 1.0 11/25/16.
 */
public interface GestionUtilisateur {

    /**
     * Ajoute un nouveau utilisateur à la liste
     *
     * @param pseudo le pseudo de l'utilisateur
     * @param prenom le prenom de l'utilisateur
     * @param nom    le nom de l'utilisateur
     * @param mail   le mail de l'utilisateur
     */
    void addUser(String pseudo, String prenom, String nom, String mail) throws DataException;


    /**
     * Cette méthode évalue l'existance de l'utilisateur
     * dans la liste avec ce mail
     *
     * @param mail le mail de l'utilisateur
     * @return true si le mail déjà exists
     */
    boolean existsMail(String mail);

    /**
     * Cette méthode évalue l'existance de l'utilisateur
     * dans la liste avec ce pseudo
     *
     * @param pseudo le pseudo de l'utilisateur
     * @return true si le pseudo déjà exists
     */
    boolean existsUsername(String pseudo);


    /**
     *
     * Cette méthode retourne l'entité de l'utilisateur selon le pseudo et
     * le salon indiqués
     *
     * @param pseudo le pseudo de l'utilisateur
     * @return l'entite de l'utilisateur
     */
    User getUserByPseudo(String pseudo);
}
