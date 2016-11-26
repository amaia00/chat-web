package com.chat.controler;

import com.chat.modele.GestionMessage;
import com.chat.modele.GestionUtilisateur;
import com.chat.modele.Message;
import com.chat.modele.User;
import com.chat.util.Constantes;
import com.chat.util.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Amaia Nazábal
 * @version 1.0
 * @since 1.0 11/18/16.
 */
@Controller
@RequestMapping("/")
public class BackOfficeController {

    private static final Logger LOGGER = Logger.getLogger(BackOfficeController.class.getName());

    private GestionMessage gestionMessage;

    private GestionUtilisateur gestionUtilisateur;

    @Autowired
    public BackOfficeController(GestionMessage gestionMessage,
                                GestionUtilisateur gestionUtilisateur) {
        this.gestionMessage = gestionMessage;
        this.gestionUtilisateur = gestionUtilisateur;
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String addUser(@RequestParam(value = "username") String pseudo,
                          @RequestParam(value = "name") String name,
                          @RequestParam(value = "lastName") String lastName,
                          @RequestParam(value = "mail") String mail,
                          Model model) {

        try {
            gestionUtilisateur.addUser(pseudo, name, lastName, mail);

            model.addAttribute("msg", Constantes.CORRECT_INSCRIPTION);
            model.addAttribute("username", pseudo);
            return "redirect:/index.jsp";
        } catch (DataException e) {
            LOGGER.log(Level.FINE, e.getMessage(), e);

            model.addAttribute("name", name);
            model.addAttribute("lastName", lastName);
            model.addAttribute("msg", e.getMessage());

            return "redirect:/inscription.jsp";
        }

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

    @RequestMapping(value = "/user/{salon}", method = RequestMethod.GET)
    public String listUsers(ModelMap modelMap,
                            @PathVariable String salon,
                            HttpServletRequest request) {

        HttpSession session = request.getSession();
        String pseudo = session.getAttribute("pseudo").toString();

        List<User> userList = gestionMessage.getUserList(salon, pseudo);
        modelMap.addAttribute("users", userList);

        return "restreint/listuser";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String connect(@RequestParam(value = "username") String pseudo,
                          @RequestParam(value = "channel") String salon,
                          HttpServletRequest request,
                          Model model) {


        if (!gestionUtilisateur.existsUsername(pseudo)) {
            model.addAttribute("msg", Constantes.USER_NOT_EXISTS);
            return "redirect:/inscription.jsp";
        }

        HttpSession session = request.getSession();
        session.setAttribute("pseudo", pseudo);
        session.setAttribute("salon", salon);
        System.out.print("ok1");

        gestionUtilisateur.getUserByPseudo(pseudo).setEtat(User.Status.ONLINE);

        return "restreint/interface";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();

        String pseudo = session.getAttribute("pseudo").toString();
        gestionUtilisateur.getUserByPseudo(pseudo).setEtat(User.Status.OFFLINE);
        session.invalidate();


        return "redirect:/index.jsp";
    }

}