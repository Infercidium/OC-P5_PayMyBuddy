package fr.infercidium.PayMyBuddy.controller;

import fr.infercidium.PayMyBuddy.configuration.UserComponent;
import fr.infercidium.PayMyBuddy.model.User;
import fr.infercidium.PayMyBuddy.service.UserI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ConnexionController {

    /**
     * Instantiating userComponent.
     */
    @Autowired
    private UserComponent userComponent;

    /**
     * Instantiation of userInterface.
     */
    @Autowired
    private UserI userS;

    /**
     * Deleting a Connection.
     * @param id of the Connection to be deleted.
     * @return the html page with a message indicating
     * the statue of the requested request.
     */
    @GetMapping(value = "/remove{id}")
    public String removecontact(@PathVariable final Long id) {
        //Component
        User user = userComponent.saveUser();

        //Service
        userS.removeConnection(id, user);

        //Return
        userComponent.cleanUser();
        return "redirect:/contact?remove";
    }

    /**
     * Creation of a Connection.
     * @param email of the user to add.
     * @return the html page with a message indicating
     * the statue of the requested request.
     */
    @PostMapping(value = "/addCoC")
    public String addcontactC(final String email) {
        //Component
        User user = userComponent.saveUser();


        //Gestion Error
        if (userS.getUser(email) == null) {
            return "redirect:/contact?addMiss";
        }

        if (user.getKnowUser().contains(userS.getUser(email))
                || user.getEmail().equals(userS.getUser(email).getEmail())) {
            return "redirect:/contact?addError";
        }

        //Service
        userS.addConnection(email, user);

        //Return
        userComponent.cleanUser();
        return "redirect:/contact?addCo";
    }

    /**
     * Creation of a Connection.
     * @param email of the user to add.
     * @return the html page with a message indicating
     * the statue of the requested request.
     */
    @PostMapping(value = "/addCoT")
    public String addcontactT(final String email) {
        //Component
        User user = userComponent.saveUser();

        //Gestion Error
        if (userS.getUser(email) == null) {
            return "redirect:/transfer?addMiss";
        }

        if (user.getKnowUser().contains(userS.getUser(email))
                || user.getEmail().equals(userS.getUser(email).getEmail())) {
            return "redirect:/transfer?addError";
        }

        //Service
        userS.addConnection(email, user);

        //Return
        userComponent.cleanUser();
        return "redirect:/transfer?addCo";
    }
}
