package com.chat.modele;

import java.util.List;

/**
 * @author Sofia Faddi
 * @author Amaia Nazábal
 * @version 1.0
 * @since 1.0 11/24/16
 */
public interface GestionMessage {
    /**
     * Méthode qui permet d'ajouter un message à un salon
     *
     * @param contenu le contenu du message
     * @param user  l'entité de l'utilisateur
     * @param salon   le salon du chat
     */
    void addMessage(String contenu, User user, String salon);

    /**
     * Cette méthode permet d'ajouter un salon
     *
     * @param salon le salon du chat
     */
    void addSalon(String salon);

    /**
     * Cette méthode permet de recupérer un message en fonction du salon
     *
     * @param salon le salon du chat
     * @return la liste de messages de ce salon là.
     */
    List<Message> getMessages(String salon);

    /**
     * cette méthode permet de supprimer un message d'un salon
     *
     * @param salon le nom du salon
     */
    void supprimerMessages(String salon);


    /**
     * Cette méthode permet de récuperer le nombre de message d'un salon
     *
     * @param salon le nom du salon
     * @return nombre de messages
     */
    int nombreMessage(String salon);


    /**
     * @param salon le nom du salon
     * @param pseudo le pseudo de l'utilisateur actuel
     * @return la liste d'utilisateurs connectés dans ce salon
     */
    List<User> getUserList(String salon, String pseudo);

}