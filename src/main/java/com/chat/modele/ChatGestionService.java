package com.chat.modele;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 *
 * @author Sofia Faddi
 * @author Amaia Nazábal
 *
 */
@Service
public class ChatGestionService implements GestionMessage {

    private Map<String, List<Message>> map = new HashMap<>();
    private Map<String, List<User>> userPerSalon = new HashMap<>();

    @Autowired
    private GestionUtilisateur gestionUtilisateur;

    @Override
    public void addMessage(String contenu, User user, String salon) {
        Message message = new Message();
        message.setContenu(contenu);
        message.setUser(user);
        message.setDate(new Date());

        if (map.containsKey(salon)) {
            map.get(salon).add(message);
        } else {
            map.put(salon, new ArrayList<>());
            map.get(salon).add(message);
        }
    }

    @Override
    public void addSalon(String salon) {
        this.map.put(salon, new ArrayList<>());

    }

    @Override
    public List<Message> getMessages(String salon) {
        if (map.containsKey(salon)) {
            return map.get(salon);
        } else {
            return map.put(salon, new ArrayList<>());
        }
    }

    @Override
    public void supprimerMessages(String salon) {
        if (map.containsKey(salon)) {
            map.remove(map.get(salon));
        }
    }

    @Override
    public int nombreMessage(String salon) {
        if (map.containsKey(salon)) {
            return map.get(salon).size();
        } else {
            return -1;
        }
    }

    public Map<String, List<Message>> getMap() {
        return map;
    }


    public void setMap(Map<String, List<Message>> map) {
        this.map = map;
    }


    @Override
    public List<User> getUserList(String salon, String pseudo) {
        List<User> userList = this.userPerSalon.get(salon);

        if(userList != null){
            return userList;
        }else{
            return new ArrayList<>();
        }
    }

    @Override
    public void addUserToSalon(String pseudo, String salon){
        List<User> userList = this.userPerSalon.get(salon);

        if(userList == null){
            userList = new ArrayList<>();
            this.userPerSalon.put(salon,userList);
        }

        /* On contrôle que l'utilisateur n'existe pas déjà dans la liste */
        if (!userList.stream().filter(u -> u.getPseudo().equals(pseudo))
                .findFirst().isPresent()) {
            User user = gestionUtilisateur.getUserByPseudo(pseudo);
            userList.add(user);
        }
    }
}