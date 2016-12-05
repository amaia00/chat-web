package com.chat.rest;

import com.chat.modele.Message;
import com.chat.service.ChatMessageService;
import com.chat.service.GestionMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Amaia Nazábal
 * @version 1.0
 * @since 1.0 12/4/16
 */
@RestController
public class MessageResource {

    private static final Logger LOGGER = Logger.getLogger(MessageResource.class.getName());

    private GestionMessage gestionMessage;


    @Autowired
    public MessageResource(ChatMessageService gestionMessage) {
        this.gestionMessage = gestionMessage;
    }


    @RequestMapping(value = "/messages/{id}", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Message getMessage(@PathVariable Long id, HttpServletResponse response) {
        Message message = this.gestionMessage.getMessage(id);
        if (message == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            LOGGER.log(Level.INFO, "Message not found [id:" + id + "]");
        }
        return message;
    }

    @RequestMapping(value = "/messages/{salon}/{id}", method = RequestMethod.PUT,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Message updateLastMessage(@PathVariable String salon, @PathVariable Long id, @RequestBody Message message) {

        Message lastMessage = gestionMessage.getDernierMessage(salon);

        if (lastMessage.getId().equals(message.getId())) {
            lastMessage.setContenu(message.getContenu());
            lastMessage.setDate(message.getDate());
        } else {
            // Throw an error
        }

        return lastMessage;
    }

    @RequestMapping(value = "/messages/{salon}/{id}", method = RequestMethod.DELETE,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public void deleteDernierMessage(@PathVariable String salon, @PathVariable Long id, HttpServletResponse response) {

        Message lastMessage = gestionMessage.getDernierMessage(salon);
        if (lastMessage.getId().equals(id)) {
            gestionMessage.deleteMessage(salon, id);
        }else{
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

    }


}
