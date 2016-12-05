package com.chat.modele;

/**
 * @author Amaia Nazábal
 * @version 1.0
 * @since 1.0 11/23/16.
 */
public class User {
    public enum Status {ONLINE, OFFLINE}

    private String pseudo;
    private String prenom;
    private String nom;
    private String mail;
    private Status etat;

    public User(){
        /* On ajoute le constructeur par défaut por l'instantiation qui fait jackson avec le json quand on appel
        * par le post de addMessage */
    }

    public User(java.lang.String pseudo, java.lang.String prenom, java.lang.String nom, java.lang.String mail) {
        this.pseudo = pseudo;
        this.prenom = prenom;
        this.nom = nom;
        this.mail = mail;
    }

    public Status getEtat() {
        return etat;
    }

    public void setEtat(Status etat) {
        this.etat = etat;
    }

    public java.lang.String getPseudo() {
        return pseudo;
    }

    public void setPseudo(java.lang.String pseudo) {
        this.pseudo = pseudo;
    }

    public java.lang.String getPrenom() {
        return prenom;
    }

    public void setPrenom(java.lang.String prenom) {
        this.prenom = prenom;
    }

    public java.lang.String getNom() {
        return nom;
    }

    public void setNom(java.lang.String nom) {
        this.nom = nom;
    }

    public java.lang.String getMail() {
        return mail;
    }

    public void setMail(java.lang.String mail) {
        this.mail = mail;
    }

    @Override
    public boolean equals(Object obj) {

        if (super.equals(obj)) {
            User u = (User) obj;
            return this.getPseudo().equals(u.getPseudo());
        } else {
            return false;
        }
    }
}
