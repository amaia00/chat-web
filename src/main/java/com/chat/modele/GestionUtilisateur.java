package com.chat.modele;

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
     * @param salon  le salon du chat
     */
    void addUser(String pseudo, String prenom, String nom, String mail, String salon);

    /**
     * Cette méthode évalue l'existance de l'utilisateur
     * dans la liste
     *
     * @param pseudo l'entité de l'utilisateur
     * @param salon  le salon du chat
     * @return true si l'utilisateur est déjà inscrit
     */
    boolean existsUser(String pseudo, String salon);


    /**
     *
     * Cette méthode retourne l'entité de l'utilisateur selon le pseudo et
     * le salon indiqués
     *
     * @param pseudo le pseudo de l'utilisateur
     * @param salon  le salon du chat
     * @return l'entite de l'utilisateur
     */
    User getUserByPseudo(String pseudo, String salon);
}
