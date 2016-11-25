package com.chat.modele;

/**
 * @author Amaia Nazábal
 * @version 1.0
 * @since 1.0 11/23/16.
 */
public class User {
    private String pseudo;
    private String prenom;
    private String nom;
    private String mail;

    public User(String pseudo, String prenom, String nom, String mail) {
        this.pseudo = pseudo;
        this.prenom = prenom;
        this.nom = nom;
        this.mail = mail;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
