package com.chat.controler;

import com.chat.modele.GestionMessage;
import com.chat.modele.GestionUtilisateur;
import com.chat.modele.Message;
import com.chat.modele.User;
`import com.chat.tp.Init;
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

    /**
     *
     * Constructeur pour l'instantiation des classes
     *
     * @param gestionMessage l'instance de la gestion de messages
     * @param gestionUtilisateur l'instance de la gestion d'utilisateurs
     */
    @Autowired
    public BackOfficeController(GestionMessage gestionMessage,
                                GestionUtilisateur gestionUtilisateur) {
        this.gestionMessage = gestionMessage;
        this.gestionUtilisateur = gestionUtilisateur;
    }

    /**
     *
     * Cette méthode inscrit un nouveau utilisateur
     *
     * @param pseudo le pseudo de l'utilisateur
     * @param name le prenom de l'utilisateur
     * @param lastName le nom de l'utilisateur
     * @param mail le mail de l'utilisateur
     * @param model le modele
     * @return à la vue initiale
     */
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

    /**
     *
     * Cette méthode retourne la liste des messages du salon
     *
     * @param modelMap le modele
     * @param salon le salon auquel l'utilisateur est connecté
     * @return à la page d'affichage
     */
    @RequestMapping(value = "/{salon}", method = RequestMethod.GET)
    public String listMessages(ModelMap modelMap, @PathVariable String salon) {

        List<Message> messages = gestionMessage.getMessages(salon);
        modelMap.put("messages", messages);

        return "restreint/affichage";
    }

    /**
     *
     * Cette méthode retorune le contenu du messages selon le
     * salon et le nombre du message
     *
     * @param modelMap le modele
     * @param salon le salon auquel l'utilisateur est connecté
     * @param num le nombre de messages
     * @return à la page d'affichage
     */
    @RequestMapping(value = "/{salon}/{num}", method = RequestMethod.GET)
    public String listMessages(ModelMap modelMap,
                               @PathVariable String salon,
                               @PathVariable Integer num) {

        List<Message> messages = gestionMessage.getMessages(salon);
        Message message = messages.get(num);
        modelMap.put("message", message == null ? "" : message);

        return "restreint/affichage";
    }

    /**
     *
     * Cette méthode retorune la liste d'utilisateurs pour salon
     *
     * @param modelMap le modele
     * @param salon le salon auquel l'utilisateur est connecté
     * @param request la rêquete
     * @return à la vue d'utilisateurs
     */
    @RequestMapping(value = "/user/{salon}", method = RequestMethod.GET)
    public String listUsers(ModelMap modelMap,
                            @PathVariable String salon,
                            HttpServletRequest request) {

        HttpSession session = request.getSession();
        String pseudo = session.getAttribute(Init.USERNAME).toString();

        List<User> userList = gestionMessage.getUserList(salon, pseudo);
        modelMap.addAttribute("users", userList);

        return "restreint/listuser";
    }

    /**
     *
     * Cette méthode ajoute l'utilisateurs au salon et le mets
     * dans l'etat ONLINE
     *
     * @param pseudo le pseudo de l'utilisateur
     * @param salon le salon auquel l'utilisateur est connecté
     * @param request la rêquete
     * @param model le modele
     * @return à la vue d'interface
     */
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
        session.setAttribute(Init.USERNAME, pseudo);
        session.setAttribute(Init.CHANNEL, salon);

        gestionMessage.addUserToSalon(pseudo,salon);
        gestionUtilisateur.getUserByPseudo(pseudo).setEtat(User.Status.ONLINE);

        return "restreint/interface";
    }

    /**
     *
     * Cette méthode fait la déconnexion de la page et mets l'utilisateur
     * au OFFLINE
     *
     * @param request la rêquete
     * @return à la page initiale
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();

        String pseudo = session.getAttribute(Init.USERNAME).toString();
        gestionUtilisateur.getUserByPseudo(pseudo).setEtat(User.Status.OFFLINE);
        session.invalidate();


        return "redirect:/index.jsp";
    }

}