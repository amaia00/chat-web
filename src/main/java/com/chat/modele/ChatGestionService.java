package com.chat.modele;

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
    public List<User> getUserList(String salon){
        List<User> users = new ArrayList<>();
        map.get(salon).stream().filter(m -> m.getUser().getEtat().equals(User.Status.ONLINE))
                .forEach(m -> users.add(m.getUser()));

        return users;
    }

}
