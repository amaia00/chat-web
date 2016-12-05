package com.chat.rest;

import com.chat.modele.Message;
import com.chat.service.GestionMessage;
import com.chat.service.GestionSalon;
import com.chat.util.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * @author Amaia Nazábal
 * @version 1.0
 * @since 1.0 12/5/16
 */
@RestController
public class SalonResource {
    private static final Logger LOGGER = Logger.getLogger(SalonResource.class.getName());

    private GestionSalon gestionSalon;
    private GestionMessage gestionMessage;

    @Autowired
    public SalonResource(GestionSalon gestionSalon, GestionMessage gestionMessage) {
        this.gestionSalon = gestionSalon;
        this.gestionMessage = gestionMessage;
    }


    /**
     * Récupérer la liste des messages
     */
    @RequestMapping(value = "/salon/{salon}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Message> getMessages(@PathVariable String salon) {
        List<Message> messages = new ArrayList<>();
        try {
            messages = gestionMessage.getMessages(salon);
        } catch (DataException e) {
            LOGGER.log(Level.WARNING, "Can't retrieve the messages ", e);
        }

        return messages;
    }

    /**
     * Récupérer le nombre de messages
     */
    @RequestMapping(value = "/salon/{salon}/nombre",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Long getMessagesNombre(@PathVariable String salon) {
        List<Message> messages = new ArrayList<>();
        try {
            messages = gestionMessage.getMessages(salon);
        } catch (DataException e) {
            LOGGER.log(Level.WARNING, "Can't retrieve the messages ", e);
        }

        return new Long(messages.size());
    }


    /**
     * Récupérer tous les messages envoyés après un message donné
     *
     * @param salon Nom du salon
     * @return
     */
    @RequestMapping(value = "/salon/{salon}/{idMessage}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Message> getMessagesApresMessage(@PathVariable String salon, @PathVariable Long idMessage) {
        List<Message> filteredMessages = new ArrayList<>();

        try {
            List<Message> messages = gestionMessage.getMessages(salon);
            Message currentMessage = gestionMessage.getMessage(idMessage);
            filteredMessages = messages.stream().filter(m ->
                    m.getDate().after(currentMessage.getDate())
            ).collect(Collectors.toList());
        } catch (DataException e) {
            LOGGER.log(Level.WARNING, "Can't retrieve the messages ", e);
        }

        return filteredMessages;
    }

    /**
     * Supprimer un message
     *
     * @param salon Nom du salon
     * @return Le message ajutee
     */
    @RequestMapping(value = "/salon/{salon}",
            method = RequestMethod.DELETE,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public void deleteSalon(@PathVariable String salon, HttpServletResponse response) {
        try {
            gestionSalon.removeSalon(salon);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (DataException e) {
            LOGGER.log(Level.SEVERE, "Can't remove salon", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Ajouter un message
     *
     * @param salon Nom du salon
     * @return Le message ajutee
     */
    @RequestMapping(value = "/salon/{salon}",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Message addMessage(@PathVariable String salon, @RequestBody Message message, HttpServletResponse response) {
        try {
            Message ret = gestionMessage.addMessage(message.getContenu(), message.getUser(), salon);
            return ret;
        } catch (DataException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        return null;
    }

}
