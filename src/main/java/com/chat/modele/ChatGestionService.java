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
    public void addMessage(String contenu, String pseudo, String salon) {
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


    /**
     * Cette méthode permet de récuperer le nombre de message d'un salon
     *
     * @param salon le nom du salon
     * @return
     */
    public int nombreMessage(String salon) {
        if (map.containsKey(salon)) {
            return map.get(salon).size();
        } else {
            return -1;
        }
    }

    @Override
    public Map<String, List<Message>> getMap() {
        return map;
    }


    @Override
    public void setMap(Map<String, List<Message>> map) {
        this.map = map;
    }

}
