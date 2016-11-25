package com.chat.controler;

import com.chat.modele.GestionMessage;
import com.chat.modele.GestionUtilisateur;
import com.chat.modele.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Amaia Nazábal
 * @version 1.0
 * @since 1.0 11/18/16.
 */
@Controller
@RequestMapping("/")
public class BackOfficeController {


    private GestionMessage gestionMessage;

    private GestionUtilisateur gestionUtilisateur;

    @Autowired
    public BackOfficeController(GestionMessage gestionMessage,
                                GestionUtilisateur gestionUtilisateur) {
        this.gestionMessage = gestionMessage;
        this.gestionUtilisateur = gestionUtilisateur;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String test(Model model) {
        model.addAttribute("salut", "SALUT!");
        return "index";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String addUser(@RequestParam(value = "username") String pseudo,
                          @RequestParam(value = "name") String name,
                          @RequestParam(value = "lastName") String lastName,
                          @RequestParam(value = "mail") String mail,
                          HttpServletRequest request,
                          Model model) {
        gestionUtilisateur.addUser(pseudo, name, lastName, mail, request.getAttribute("salon").toString());

        return "index";
    }

    @RequestMapping(value = "/{salon}", method = RequestMethod.GET)
    public String listMessages(ModelMap modelMap, @PathVariable String salon) {

        List<Message> messages = gestionMessage.getMessages(salon);
        modelMap.put("messages", messages);

        return "restreint/affichage";
    }

    @RequestMapping(value = "/{salon}/{num}", method = RequestMethod.GET)
    public String listMessages(ModelMap modelMap,
                               @PathVariable String salon,
                               @PathVariable Integer num) {

        List<Message> messages = gestionMessage.getMessages(salon);
        Message message = messages.get(num);
        modelMap.put("message", message == null ? "" : message);

        return "restreint/affichage";
    }

}