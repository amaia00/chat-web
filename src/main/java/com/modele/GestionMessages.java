package com.modele;

import java.util.*;

/****
 *
 * @author sofia faddi
 *
 */
public class GestionMessages {

    private static Map<String, ArrayList<Message>> map = new HashMap<>();

    public GestionMessages(){
        /* On ajout le constructeur par défaut pour respecter la convention */
    }

    /**
     *
     * Méthode qui permet d'ajouter un message à un salon
     * @param contenu le contenu du message
     * @param pseudo le pseudo de l'utilisateur
     * @param salon le salon du chat
     */
    public static void addMessage(String contenu, String pseudo, String salon) {
        Message message = new Message();
        message.setContenu(contenu);
        message.setUser(pseudo);
        message.setDate(new Date());

        if (map.containsKey(salon)) {
            map.get(salon).add(message);
        } else {
            map.put(salon, new ArrayList<>());
            map.get(salon).add(message);
        }
    }

    /**
     *
     * Cette méthode permet d'ajouter un salon
     *
     * @param salon le salon du chat
     */
    public static void addSalon(String salon) {
        GestionMessages.map.put(salon, new ArrayList<Message>());

    }

    /**
     * Cette méthode permet de recupérer un message en fonction du salon
     *
     * @param salon le salon du chat
     * @return la liste de messages de ce salon là.
     */
    public static List<Message> getMessages(String salon) {
        if (map.containsKey(salon)) {
            return map.get(salon);
        } else {
            return map.put(salon, new ArrayList<Message>());
        }
    }

    /**
     * cette méthode permet de supprimer un message d'un salon
     * @param salon le nom du salon
     */
    public static void supprimerMessages(String salon) {
        if (map.containsKey(salon)) {
            map.remove(map.get(salon));
        }
    }


    /**
     * Cette méthode permet de récuperer le nombre de message d'un salon
     *
     * @param salon le nom du salon
     * @return
     */
    public static int nombreMessage(String salon) {
        if (map.containsKey(salon)) {
            return map.get(salon).size();
        } else {
            return -1;
        }
    }

    public static Map<String, ArrayList<Message>> getMap() {
        return map;
    }

    public static void setMap(Map<String, ArrayList<Message>> map) {
        GestionMessages.map = map;
    }

}
