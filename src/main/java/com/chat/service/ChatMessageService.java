package com.chat.service;

import com.chat.modele.Message;
import com.chat.modele.Salon;
import com.chat.modele.User;
import com.chat.util.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 *
 * Cette classe garde tous les fonctions sur le salon et les messages
 *
 * @author Sofia Faddi
 * @author Amaia Nazábal
 *
 */
@Service
public class ChatMessageService implements GestionMessage {

    private Map<Salon, List<Message>> map = new HashMap<>();
    private Map<Salon, List<User>> userPerSalon = new HashMap<>();

    @Autowired
    private GestionUtilisateur gestionUtilisateur;

    @Autowired
    private GestionSalon gestionSalon;

    @Override
    public void addMessage(String contenu, User user, String salonName) throws DataException {
        Message message = new Message();
        message.setContenu(contenu);
        message.setUser(user);
        message.setDate(new Date());

        Salon salon = gestionSalon.getSalonByName(salonName);

        if (map.containsKey(salon)) {
            map.get(salon).add(message);
        } else {
            map.put(salon, new ArrayList<>());
            map.get(salon).add(message);
        }
    }

    @Override
    public void addSalon(String salonName) throws DataException {
        Salon salon = gestionSalon.getSalonByName(salonName);
        this.map.put(salon, new ArrayList<>());

    }

    @Override
    public List<Message> getMessages(String salonName) throws DataException {
        Salon salon = gestionSalon.getSalonByName(salonName);
        if (map.containsKey(salon)) {
            return map.get(salon);
        } else {
            return map.put(salon, new ArrayList<>());
        }
    }

    @Override
    public void supprimerMessages(String salonName) throws DataException {
        Salon salon = gestionSalon.getSalonByName(salonName);
        if (map.containsKey(salon)) {
            map.remove(map.get(salon));
        }
    }

    @Override
    public int nombreMessage(String salonName) throws DataException {
        Salon salon = gestionSalon.getSalonByName(salonName);
        if (map.containsKey(salon)) {
            return map.get(salon).size();
        } else {
            return -1;
        }
    }

    public Map<Salon, List<Message>> getMap() {
        return map;
    }


    public void setMap(Map<Salon, List<Message>> map) {
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
    public void addUserToSalon(String pseudo, String salonName) throws DataException {
        Salon salon = gestionSalon.getSalonByName(salonName);
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

    @Override
    public void removeUserToSalon(String pseudo, String salon){
        List<User> userList = this.userPerSalon.get(salon);
        userList.removeIf(u -> u.getPseudo().equals(pseudo));
    }
}