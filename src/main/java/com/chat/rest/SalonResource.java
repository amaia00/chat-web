package com.chat.rest;

import com.chat.modele.Message;
import com.chat.service.GestionMessage;
import com.chat.service.GestionSalon;
import com.chat.util.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
     * Cette méthode corresponds à l'ennoncé: Récupérer la liste des messages
     *
     * @param salon     le nom du salon
     * @param response la reponse http
     * @return la liste de messages du salon
     */
    @RequestMapping(value = "/salons/{salon}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Messages getMessages(@PathVariable String salon,
                                     HttpServletResponse response) {
        Messages messages = new Messages();
        try {
            messages.setMessages(gestionMessage.getMessages(salon));
        } catch (DataException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        return messages;
    }

    /**
     * Cette méthode corresponds à l'ennoncé: Récupérer le nombre de messages
     *
     * @param salon       le nom du salon
     * @param response    la reponse http
     * @return le nombre des messages dans le salon donné
     */
    @RequestMapping(value = "/salons/{salon}/nombre",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Quantite getQuantiteMessages(@PathVariable String salon,
                                  HttpServletResponse response) {
        Quantite quantite = new Quantite(0L);
        try {
            List<Message> messages = gestionMessage.getMessages(salon);
            quantite.setNombre(Long.valueOf(messages.size()));

            response.setStatus(HttpServletResponse.SC_OK);
        } catch (DataException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            LOGGER.log(Level.WARNING, "Can't retrieve the messages ", e);
        } catch (Exception e){
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        return quantite;
    }


    /**
     * Cette méthode corresponds à l'ennoncé: Récupérer tous les messages envoyés après un message donné
     *
     * @param salon Nom du salon
     * @param idMessage l'id du message à partir duquel on veut récupérer la liste
     * @param response response http
     * @return list du messages adans
     */
    @RequestMapping(value = "/salons/{salon}/{idMessage}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Messages getLastMessagesAfterId(@PathVariable String salon,
                                                 @PathVariable Long idMessage, HttpServletResponse response) {
        Messages messages = new Messages();

        try {
            messages.setMessages(gestionMessage.getLastMessagesAfterId(salon, idMessage));

        } catch (DataException e) {
            LOGGER.log(Level.WARNING, "Can't retrieve the messages ", e);
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }catch (Exception e){
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        return messages;
    }

    /**
     * Cette méthode corresponds à l'ennoncé: Supprimer un message
     *
     * @param salon Nom du salon
     * @param response  response http
     */
    @RequestMapping(value = "/salons/{salon}",
            method = RequestMethod.DELETE,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public void deleteSalon(@PathVariable String salon, HttpServletResponse response) {
        try {
            gestionSalon.removeSalon(salon);

            /* On ne retourne pas aucun réponse */
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (DataException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } catch (Exception e){
            LOGGER.log(Level.SEVERE, "Can't remove salon", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Cette méthode correspond à l'ennoncé: Ajouter un message
     *
     * @param salon Nom du salon
     * @return Le message ajutee
     */
    @RequestMapping(value = "/salons/{salon}",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Message addMessage(@PathVariable String salon, @RequestBody Message message,
                              HttpServletResponse response) {
        Message ret = null;
        try {
                ret = gestionMessage.addMessage(message.getContenu(), message.getUser(), salon);
            response.setStatus(HttpServletResponse.SC_CREATED);
        } catch (DataException e) {
            /* Si on ne trouve pas le salon indique dans le path */
            LOGGER.log(Level.WARNING, e.getMessage(), e);
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }catch (Exception e){
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        return ret;
    }

}
